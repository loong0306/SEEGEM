package me.dragon.service;

import me.dragon.model.entity.InstagramFeed;
import me.dragon.model.entity.InstagramFeedPicture;
import me.dragon.model.vo.InstagramVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dragon on 12/9/2017.
 */
public interface InstagramService extends IService<InstagramFeed>{
    List<InstagramFeed> getFeedList();
    void saveFeedList(List<InstagramFeed> instagramFeedList,List<InstagramFeedPicture> instagramFeedPictureList);
    void updateLocalFeedPic(String date, String picRealPath);
    List<InstagramFeedPicture> getFeedPictureList();
    List<InstagramVO> apiGetInstagramVoList();
}
