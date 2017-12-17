package me.dragon.service;

import me.dragon.model.entity.FacebookFeed;
import me.dragon.model.entity.FacebookFeedPicture;

import java.util.List;

/**
 * Created by dragon on 12/10/2017.
 */
public interface ScheduleService {
    List<FacebookFeed> getFacebookFeedList();

    void saveFacebookFeedList(List<FacebookFeed> facebookFeedList, List<FacebookFeedPicture> facebookFeedPictureList);

    List<FacebookFeedPicture> getFacebookFeedPictureList();

    void updateLocalFacebookFeedPic(String id, String picRealPath);
}
