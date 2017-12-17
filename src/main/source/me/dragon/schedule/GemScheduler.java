package me.dragon.schedule;

import com.alibaba.fastjson.JSON;
import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.RawAPIResponse;
import facebook4j.internal.org.json.JSONObject;
import me.dragon.biz.GemFacebookFeed;
import me.dragon.biz.GemInstagramFeed;
import me.dragon.model.entity.FacebookFeed;
import me.dragon.model.entity.FacebookFeedPicture;
import me.dragon.service.FacebookService;
import me.dragon.service.InstagramService;
import me.dragon.utils.SaveFacebookPictureByURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dragon on 12/9/2017.
 */
@Component
public class GemScheduler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FacebookService facebookService;
    @Autowired
    private InstagramService instagramService;

    @Scheduled(cron="${sch}")
    public void facebookTimeScheduler() {
        logger.info("正在连接Facebook获取最新Feed 。。。");
        GemFacebookFeed gemFacebookFeed = new GemFacebookFeed();
        gemFacebookFeed.flushFacebook();
        logger.info("断开连接Facebook 。。。");
    }

    @Scheduled(cron="${sch}")
    public void instagramTimeScheduler() {
        logger.info("正在连接Instagram获取最新Feed 。。。");
        GemInstagramFeed gemInstagramFeed = new GemInstagramFeed();
        gemInstagramFeed.flushInstagram();
        logger.info("断开连接Instagram 。。。");
    }

}
