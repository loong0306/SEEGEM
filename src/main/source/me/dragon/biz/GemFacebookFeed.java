package me.dragon.biz;

import com.alibaba.fastjson.JSON;
import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.RawAPIResponse;
import facebook4j.internal.org.json.JSONObject;
import me.dragon.config.ApplicationContextUtil;
import me.dragon.model.entity.FacebookFeed;
import me.dragon.model.entity.FacebookFeedPicture;
import me.dragon.service.FacebookService;
import me.dragon.utils.SaveFacebookPictureByURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GemFacebookFeed {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private FacebookService facebookService = (FacebookService) ApplicationContextUtil.getBean("facebookService");

    public void flushFacebook(){
        try {
            // 获取数据库数据
            List<FacebookFeed> localFeedList = facebookService.getFeedList();
            // 连接Facebook - 获取动态
            Facebook facebook = new FacebookFactory().getInstance();
            RawAPIResponse res = facebook.callGetAPI("/13062516037/feed");
            JSONObject object = res.asJSONObject();
            Object result = object.get("data");
            String resultJSON = result.toString();
            List<FacebookFeed> facebookFeedList = JSON.parseArray(resultJSON,FacebookFeed.class);
            // 获取动态绑定图片
            RawAPIResponse resPic = facebook.callGetAPI("13062516037/feed?fields=full_picture&limit=50");
            JSONObject objectPic = resPic.asJSONObject();
            Object resultPic = objectPic.get("data");
            String resultJSONPic = resultPic.toString();
            List<FacebookFeedPicture> fbNeedPic_Flag_List = JSON.parseArray(resultJSONPic,FacebookFeedPicture.class);
            List<FacebookFeedPicture> facebookFeedPictureList = new ArrayList<FacebookFeedPicture>();
            for (FacebookFeed facebookFeed : facebookFeedList) {
                String fbNeedId = facebookFeed.getId();
                for (FacebookFeedPicture facebookFeedPicture : fbNeedPic_Flag_List) {
                    String fbNeedPictureId = facebookFeedPicture.getId();
                    if (fbNeedId.equals(fbNeedPictureId)){
                        // 拷贝图片
                        if (0 == localFeedList.size()){
                            String picRealPath = SaveFacebookPictureByURL.saveToFile(facebookFeedPicture.getFullPicture());
                            // 另存为本地项目图库
                            facebookFeedPicture.setPicRealPath(picRealPath);
                            facebookFeedPictureList.add(facebookFeedPicture);
                        }else{
                            // 需要更新的图片
                            facebookFeedPicture.setPicRealPath("new");
                            facebookFeedPictureList.add(facebookFeedPicture);
                        }
                    }
                }
            }
            /**
             * 获取数据库数据
             * 1.判断是否没有数据
             * 2.是否需要更新数据
             */
            if(0 == localFeedList.size()){
                // 新增动态
                facebookService.saveFeedList(facebookFeedList, facebookFeedPictureList);
            }else{
                // 本地最后一条动态ID
                FacebookFeed localFacebookFeed = localFeedList.get(0);
                String localLastId = localFacebookFeed.getId();
                // 线上最后一条动态ID
                FacebookFeed lineFacebookFeed = facebookFeedList.get(0);
                String lineLastId = lineFacebookFeed.getId();
                // 对比动态
                if (localLastId.equals(lineLastId)){
                    // 不更新动态
                    logger.info("*连接Facebook结束，本时间节点没有可用更新");
                    List<FacebookFeedPicture> localFeedPicList = facebookService.getFeedPictureList();
                    for (FacebookFeedPicture localPic : localFeedPicList) {
                        if (null == localPic.getPicRealPath()){
                            String picRealPath = SaveFacebookPictureByURL.saveToFile(localPic.getFullPicture());
                            // 另存为本地项目图库
                            facebookService.updateLocalFeedPic(localPic.getId(),picRealPath);
                            logger.info("*更新了id为：" + localPic.getId() + "的历史动态绑定的图片库");
                        }
                    }
                }else{
                    // 筛选新动态
                    for (FacebookFeed local : localFeedList) {
                        Iterator<FacebookFeed> itFeed = facebookFeedList.iterator();
                        while(itFeed.hasNext()){
                            FacebookFeed line = itFeed.next();
                            if((local.getId()).equals(line.getId())){
                                itFeed.remove();
                            }
                        }
                        Iterator<FacebookFeedPicture> itFeedPic = facebookFeedPictureList.iterator();
                        while(itFeedPic.hasNext()){
                            FacebookFeedPicture lineFeedPic = itFeedPic.next();
                            if((local.getId()).equals(lineFeedPic.getId())){
                                itFeedPic.remove();
                            }
                        }
                    }
                    // 拷贝需要更新的图片，并保存数据
                    for (FacebookFeedPicture lineFeedPic : facebookFeedPictureList) {
                        if ((lineFeedPic.getPicRealPath()).equals("new")){
                            String picRealPath = SaveFacebookPictureByURL.saveToFile(lineFeedPic.getFullPicture());
                            // 另存为本地项目图库
                            lineFeedPic.setPicRealPath(picRealPath);
                        }
                    }
                    if(facebookFeedList.size() > 0){
                        for (FacebookFeed facebookFeed : facebookFeedList){
                            logger.info("*新增了id为：" + facebookFeed.getId() + "的新动态");
                        }
                        // 更新动态到本地
                        facebookService.saveFeedList(facebookFeedList, facebookFeedPictureList);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}