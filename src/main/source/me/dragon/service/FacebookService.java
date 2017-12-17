package me.dragon.service;

import me.dragon.model.entity.FacebookFeed;
import me.dragon.model.entity.FacebookFeedPicture;
import me.dragon.model.vo.FacebookVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dragon on 12/9/2017.
 */
public interface FacebookService extends IService<FacebookFeed>{
    List<FacebookFeed> getFeedList();
    List<FacebookFeedPicture> getFeedPictureList();
    void saveFeedList(List<FacebookFeed> facebookFeedList, List<FacebookFeedPicture> facebookFeedPictureList);
    FacebookFeedPicture needFbFeedPicUpdate(String id);
    void updateLocalFeedPic(String id, String picRealPath);
    List<FacebookVO> apiGetFacebookVoList();
}
