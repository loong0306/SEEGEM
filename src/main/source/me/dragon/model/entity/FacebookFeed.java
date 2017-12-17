package me.dragon.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by dragon on 4/18/2017.
 */
@Data
@Table(name = "fb_feed")
public class FacebookFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name="created_time", length=100, nullable=true)
    private String created_time;
    @Column(name="message", length=255, nullable=true)
    private String message;

}
