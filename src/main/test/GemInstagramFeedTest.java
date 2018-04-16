import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dragon on 4/21/2017.
 */
public class GemInstagramFeedTest implements PageProcessor {

    private final int METHOD_NUM = 2;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setHttpProxy(new HttpHost("127.0.0.1",1087));


    @Override
    public void process(Page page) {
        try {
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
        Spider.create(new GemInstagramFeedTest()).addUrl("https://www.instagram.com/gem0816/").thread(1).run();
    }
}
