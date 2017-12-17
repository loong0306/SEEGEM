package me.dragon.service.impl;

import me.dragon.model.entity.FacebookFeed;
import me.dragon.model.entity.FacebookFeedPicture;
import me.dragon.service.FacebookService;
import me.dragon.service.InstagramService;
import me.dragon.service.ScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dragon on 12/10/2017.
 */
@Service("scheduleServiceImpl")
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private FacebookService facebookService;
    @Resource
    private InstagramService instagramService;

    @Override
    public List<FacebookFeed> getFacebookFeedList() {
        return facebookService.getFeedList();
    }

    @Override
    public void saveFacebookFeedList(List<FacebookFeed> facebookFeedList, List<FacebookFeedPicture> facebookFeedPictureList) {
        facebookService.saveFeedList(facebookFeedList, facebookFeedPictureList);
    }

    @Override
    public List<FacebookFeedPicture> getFacebookFeedPictureList() {
        return facebookService.getFeedPictureList();
    }

    @Override
    public void updateLocalFacebookFeedPic(String id, String picRealPath) {
        facebookService.updateLocalFeedPic(id, picRealPath);
    }
}
