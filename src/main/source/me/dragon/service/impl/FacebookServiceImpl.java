package me.dragon.service.impl;

import me.dragon.core.exception.BusinessException;
import me.dragon.mapper.FacebookFeedMapper;
import me.dragon.mapper.FacebookFeedPictureMapper;
import me.dragon.model.entity.FacebookFeed;
import me.dragon.model.entity.FacebookFeedPicture;
import me.dragon.model.vo.FacebookVO;
import me.dragon.service.BaseService;
import me.dragon.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragon on 12/9/2017.
 */
@Service("facebookService")
@Transactional
public class FacebookServiceImpl extends BaseService<FacebookFeed> implements FacebookService {

    @Autowired
    private FacebookFeedMapper facebookFeedMapper;
    @Autowired
    private FacebookFeedPictureMapper facebookFeedPictureMapper;

    // 获取本地动态
    @Override
    public List<FacebookFeed> getFeedList(){
        List<FacebookFeed> facebookFeedList = new ArrayList<FacebookFeed>();
        try{
            facebookFeedList = facebookFeedMapper.getFacebookFeedList();
        } catch (BusinessException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return facebookFeedList;
    }

    @Override
    public List<FacebookFeedPicture> getFeedPictureList() {
        List<FacebookFeedPicture> facebookFeedPictureList = new ArrayList<FacebookFeedPicture>();
        try{
            facebookFeedPictureList = facebookFeedPictureMapper.getFacebookFeedPictureList();
        } catch (BusinessException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return facebookFeedPictureList;
    }

    // 新增动态
    @Override
    public void saveFeedList(List<FacebookFeed> facebookFeedList, List<FacebookFeedPicture> facebookFeedPictureList) {
        try{
            for (FacebookFeed facebookFeed : facebookFeedList) {
                facebookFeedMapper.insert(facebookFeed);
            }
            for (FacebookFeedPicture facebookFeedPicture : facebookFeedPictureList) {
                facebookFeedPictureMapper.insert(facebookFeedPicture);
            }
        } catch (BusinessException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public FacebookFeedPicture needFbFeedPicUpdate(String id) {
        FacebookFeedPicture facebookFeedPicture = new FacebookFeedPicture();
        try{
            facebookFeedPicture = facebookFeedPictureMapper.needFbFeedPicUpdate(id);
        } catch (BusinessException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return facebookFeedPicture;
    }

    @Override
    public void updateLocalFeedPic(String id, String picRealPath) {
        try{
            FacebookFeedPicture facebookFeedPicture = new FacebookFeedPicture();
            facebookFeedPicture.setId(id);
            facebookFeedPicture = facebookFeedPictureMapper.selectByPrimaryKey(facebookFeedPicture);
            facebookFeedPicture.setPicRealPath(picRealPath);
            facebookFeedPictureMapper.updateByPrimaryKey(facebookFeedPicture);
        } catch (BusinessException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<FacebookVO> apiGetFacebookVoList() {
        List<FacebookVO> facebookVOS = new ArrayList<FacebookVO>();
        try{
            facebookVOS = facebookFeedMapper.apiGetFacebookVoList();
        } catch (BusinessException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return facebookVOS;
    }
}
