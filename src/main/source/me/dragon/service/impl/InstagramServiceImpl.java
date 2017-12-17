package me.dragon.service.impl;

import me.dragon.core.exception.BusinessException;
import me.dragon.mapper.InstagramFeedMapper;
import me.dragon.mapper.InstagramFeedPictureMapper;
import me.dragon.model.entity.InstagramFeed;
import me.dragon.model.entity.InstagramFeedPicture;
import me.dragon.model.vo.InstagramVO;
import me.dragon.service.BaseService;
import me.dragon.service.InstagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragon on 12/9/2017.
 */
@Service("instagramService")
@Transactional
public class InstagramServiceImpl extends BaseService<InstagramFeed> implements InstagramService{

    @Autowired
    private InstagramFeedMapper instagramFeedMapper;
    @Autowired
    private InstagramFeedPictureMapper instagramFeedPictureMapper;

    @Override
    public List<InstagramFeed> getFeedList() {
        List<InstagramFeed> insFeedList = new ArrayList<InstagramFeed>();
        try{
            insFeedList = instagramFeedMapper.getFeedList();
        } catch (BusinessException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return insFeedList;
    }

    @Override
    public void saveFeedList(List<InstagramFeed> instagramFeedList,List<InstagramFeedPicture> instagramFeedPictureList) {
        try{
            for (InstagramFeed instagramFeed : instagramFeedList) {
                instagramFeedMapper.insert(instagramFeed);
            }
            for (InstagramFeedPicture instagramFeedPicture : instagramFeedPictureList) {
                instagramFeedPictureMapper.insert(instagramFeedPicture);
            }
        } catch (BusinessException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void updateLocalFeedPic(String date, String picRealPath) {
        try{
            InstagramFeedPicture instagramFeedPicture = new InstagramFeedPicture();
            instagramFeedPicture.setDate(date);
            instagramFeedPicture = instagramFeedPictureMapper.selectByPrimaryKey(instagramFeedPicture);
            instagramFeedPicture.setPic_real_path(picRealPath);
            instagramFeedPictureMapper.updateByPrimaryKey(instagramFeedPicture);
        } catch (BusinessException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<InstagramFeedPicture> getFeedPictureList() {
        List<InstagramFeedPicture> instagramFeedPictures = new ArrayList<InstagramFeedPicture>();
        try{
            instagramFeedPictures = instagramFeedPictureMapper.selectAll();
        } catch (BusinessException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return instagramFeedPictures;
    }

    @Override
    public List<InstagramVO> apiGetInstagramVoList() {
        List<InstagramVO> instagramVOS = new ArrayList<InstagramVO>();
        try{
            instagramVOS = instagramFeedMapper.apiGetInstagramVoList();
        } catch (BusinessException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return instagramVOS;
    }
}
