package me.dragon.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by dragon on 4/18/2017.
 */
@Data
@Table(name = "instagram_feed")
public class InstagramFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String date;
    @Column(name="caption", length=1000, nullable=true)
    private String caption;
}
