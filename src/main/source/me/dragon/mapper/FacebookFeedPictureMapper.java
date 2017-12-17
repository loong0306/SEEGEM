package me.dragon.mapper;

import me.dragon.config.MyMapper;
import me.dragon.model.entity.FacebookFeedPicture;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dragon on 12/9/2017.
 */
@Repository
public interface FacebookFeedPictureMapper extends MyMapper<FacebookFeedPicture> {
    List<FacebookFeedPicture> getFacebookFeedPictureList();

    FacebookFeedPicture needFbFeedPicUpdate(String id);
}
