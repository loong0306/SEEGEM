package me.dragon.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by dragon on 4/24/2017.
 */
@Data
@Table(name = "instagram_feed_picture")
public class InstagramFeedPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String date;
    @Column(name="thumbnail_src", length=255, nullable=true)
    private String thumbnail_src;
    @Column(name="pic_real_path", length=255, nullable=true)
    private String pic_real_path;

}
