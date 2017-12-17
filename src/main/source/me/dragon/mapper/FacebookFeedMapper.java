package me.dragon.mapper;

import me.dragon.config.MyMapper;
import me.dragon.model.entity.FacebookFeed;
import me.dragon.model.vo.FacebookVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dragon on 12/9/2017.
 */
@Repository
public interface FacebookFeedMapper extends MyMapper<FacebookFeed> {
    List<FacebookFeed> getFacebookFeedList();

    List<FacebookVO> apiGetFacebookVoList();
}
