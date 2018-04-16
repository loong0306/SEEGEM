package me.dragon.biz;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.dragon.config.ApplicationContextUtil;
import me.dragon.model.entity.InstagramFeed;
import me.dragon.model.entity.InstagramFeedPicture;
import me.dragon.service.InstagramService;
import me.dragon.utils.SaveInstagramPicByURL;
import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dragon on 4/21/2017.
 */
public class GemInstagramFeed implements PageProcessor {

    private final int METHOD_NUM = 2;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setHttpProxy(new HttpHost("127.0.0.1",1080));

    private InstagramService instagramService = (InstagramService) ApplicationContextUtil.getBean("instagramService");

    @Override
    public void process(Page page) {
        try {
            // 获取本地库数据
            List<InstagramFeed> localInsFeedList = instagramService.getFeedList();
            // 初始化抓取方式
            // 获取图片元素所在JS靶，并找出锚点
            List<String> urls = page.getHtml().$("script").all();
            Iterator<String> it = urls.iterator();
            while (it.hasNext()) {
                String urlStr = it.next();
                if (!urlStr.startsWith("<script type=\"text/javascript\">window._sharedData =")) {
                    it.remove();
                }
            }
            if (1 == METHOD_NUM) {
                // 获取所有图片地址
                List<String> picAllUrls = new ArrayList<String>();
                String urlStrs = urls.toString();
                Matcher m = Pattern.compile("\"((https)://scontent)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?\"").matcher(urlStrs);
                while (m.find()) {
                    picAllUrls.add(m.group());
                }
                // 剔除不必要的元素
                picAllUrls.remove(1);
                picAllUrls.remove(0);
                // 获取可用的元素
                List<String> picUrls = new ArrayList<String>();
                for (int i = 0; i < picAllUrls.size(); i++) {
                    if (0 != i % 2) {
                        picUrls.add(picAllUrls.get(i));
                    }
                }
                for (String s : picUrls) {
                    System.out.println("picUrl = " + s);
                }
            } else if (2 == METHOD_NUM) {
                // 解析JSON
                String urlStrs = urls.toString();
                String jsonStr = urlStrs.substring(53, urlStrs.length() - 11);

                JSONObject jsonObject_0 = JSON.parseObject(jsonStr);
                String jsonResult_EntityData = jsonObject_0.get("entry_data").toString();

                JSONObject jsonObject_1 = JSON.parseObject(jsonResult_EntityData);
                String jsonResult_ProfilePage = jsonObject_1.get("ProfilePage").toString();

                JSONArray jsonArray_2 = JSONArray.parseArray(jsonResult_ProfilePage);
                JSONObject jsonObject_3 = (JSONObject) jsonArray_2.get(0);
                String jsonResult_Graphql = jsonObject_3.get("graphql").toString();

                JSONObject jsonObject_4 = JSON.parseObject(jsonResult_Graphql);
                String jsonResult_User = jsonObject_4.get("user").toString();

                JSONObject jsonObject_5 = JSON.parseObject(jsonResult_User);
                String jsonResult_Media = jsonObject_5.get("edge_owner_to_timeline_media").toString();

                JSONObject jsonObject_6 = JSON.parseObject(jsonResult_Media);
                String jsonResult_Edges = jsonObject_6.get("edges").toString();

                // jsonArray_7 为 JSON最后结果集
                JSONArray jsonObject_7 = JSONArray.parseArray(jsonResult_Edges);

                // 初始化用于存放获取当前数据的InstagramFeed集合
                List<InstagramFeed> instagramFeedList = new ArrayList<InstagramFeed>();
                // 初始化用于存放获取当前数据的InstagramFeedPicture集合
                List<InstagramFeedPicture> instagramFeedPictureList = new ArrayList<InstagramFeedPicture>();

                for (Object object : jsonObject_7) {
                    JSONObject jsonObjectFlag = (JSONObject) object;
                    String jsonResult_Node = jsonObjectFlag.get("node").toString();
                    JSONObject jsonObject = JSON.parseObject(jsonResult_Node);
                    // 获取ID
                    String feed_date = jsonObject.get("id").toString();
                    // 获取图片
                    String feed_thumbnailSrc = jsonObject.get("display_url").toString();
                    // 获取caption
                    String jsonResult_Media_Caption = jsonObject.get("edge_media_to_caption").toString();
                    JSONObject jsonObjectCaptionFlag = JSON.parseObject(jsonResult_Media_Caption);
                    String jsonResultCaptionEdgesFlag = jsonObjectCaptionFlag.get("edges").toString();
                    JSONArray jsonArrayCaptionNodeFlag = JSONArray.parseArray(jsonResultCaptionEdgesFlag);
                    String feed_caption = "";
                    if (jsonArrayCaptionNodeFlag.size() > 0) {
                        JSONObject jsonObjectCaptionNodeFlag = (JSONObject) jsonArrayCaptionNodeFlag.get(0);
                        String jsonObjectCaptionNode = jsonObjectCaptionNodeFlag.get("node").toString();
                        JSONObject jsonObjectTextFlag = JSON.parseObject(jsonObjectCaptionNode);
                        feed_caption = jsonObjectTextFlag.get("text").toString();
                    } else {
                        feed_caption = "NULL";
                    }

                    // 保存操作
                    // 保存到InstagramFeed
                    InstagramFeed instagramFeed = new InstagramFeed();
                    instagramFeed.setDate(feed_date);
                    instagramFeed.setCaption(feed_caption);
                    instagramFeedList.add(instagramFeed);

                    if(0 == localInsFeedList.size()){
                        // 保存到InstagramFeedPicture
                        InstagramFeedPicture instagramFeedPicture = new InstagramFeedPicture();
                        String pic_real_path = SaveInstagramPicByURL.saveToFile(feed_thumbnailSrc);
                        instagramFeedPicture.setDate(feed_date);
                        instagramFeedPicture.setThumbnail_src(feed_thumbnailSrc);
                        instagramFeedPicture.setPic_real_path(pic_real_path);
                        instagramFeedPictureList.add(instagramFeedPicture);
                    }else{
                        // 需要更新的图片
                        InstagramFeedPicture instagramFeedPicture = new InstagramFeedPicture();
                        instagramFeedPicture.setDate(feed_date);
                        instagramFeedPicture.setThumbnail_src(feed_thumbnailSrc);
                        instagramFeedPicture.setPic_real_path("new");
                        instagramFeedPictureList.add(instagramFeedPicture);
                    }

                }
                if(0 == localInsFeedList.size()){
                    // 新增动态
                    instagramService.saveFeedList(instagramFeedList,instagramFeedPictureList);
                }else{
                    // 本地最后一条动态ID
                    String localLastId = localInsFeedList.get(0).getDate();
                    // 线上最后一条动态ID
                    String lineLastId = instagramFeedList.get(0).getDate();
                    // 对比动态
                    if (localLastId.equals(lineLastId)){
                        // 不更新动态
                        logger.info("*连接Instagram结束，本时间节点没有可用更新");
                        List<InstagramFeedPicture> localFeedPicList = instagramService.getFeedPictureList();
                        for (InstagramFeedPicture localPic : localFeedPicList) {
                            if (null == localPic.getPic_real_path()){
                                String picRealPath = SaveInstagramPicByURL.saveToFile(localPic.getThumbnail_src());
                                instagramService.updateLocalFeedPic(localPic.getDate(),picRealPath);
                                logger.info("*更新了date为：" + localPic.getDate() + "的历史动态绑定的图片库");
                            }
                        }
                    }else{
                        // 筛选新动态
                        for (InstagramFeed local : localInsFeedList) {
                            Iterator<InstagramFeed> itFeed = instagramFeedList.iterator();
                            while(itFeed.hasNext()){
                                InstagramFeed line = itFeed.next();
                                if((local.getDate()).equals(line.getDate())){
                                    itFeed.remove();
                                }
                            }
                            Iterator<InstagramFeedPicture> itFeedPic = instagramFeedPictureList.iterator();
                            while(itFeedPic.hasNext()){
                                InstagramFeedPicture lineFeedPic = itFeedPic.next();
                                if((local.getDate()).equals(lineFeedPic.getDate())){
                                    itFeedPic.remove();
                                }
                            }
                        }
                        // 拷贝需要更新的图片，并保存数据
                        for (InstagramFeedPicture lineFeedPic : instagramFeedPictureList) {
                            if ((lineFeedPic.getPic_real_path()).equals("new")){
                                String picRealPath = SaveInstagramPicByURL.saveToFile(lineFeedPic.getThumbnail_src());
                                lineFeedPic.setPic_real_path(picRealPath);
                            }
                        }
                        if(instagramFeedList.size() > 0){
                            for (InstagramFeed instagramFeed : instagramFeedList){
                                logger.info("*新增了date为：" + instagramFeed.getDate() + "的新动态");
                            }
                            // 更新动态到本地
                            instagramService.saveFeedList(instagramFeedList,instagramFeedPictureList);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Site getSite() {
        return site;
    }


    public void flushInstagram() {
        Spider.create(new GemInstagramFeed()).addUrl("https://www.instagram.com/gem0816/").thread(1).run();
    }
}
