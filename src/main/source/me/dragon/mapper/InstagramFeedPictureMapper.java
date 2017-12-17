package me.dragon.mapper;

import me.dragon.config.MyMapper;
import me.dragon.model.entity.InstagramFeed;
import me.dragon.model.entity.InstagramFeedPicture;
import me.dragon.model.vo.InstagramVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dragon on 12/9/2017.
 */
@Repository
public interface InstagramFeedPictureMapper extends MyMapper<InstagramFeedPicture> {
}
