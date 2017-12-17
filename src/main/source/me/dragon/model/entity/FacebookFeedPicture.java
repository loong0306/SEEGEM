package me.dragon.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by dragon on 4/18/2017.
 */
@Data
@Table(name = "fb_feed_picture")
public class FacebookFeedPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name="full_picture")
    private String fullPicture;
    @Column(name="pic_real_path")
    private String picRealPath;
}
