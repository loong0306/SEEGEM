package me.dragon.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * Created by dragon on 11/4/2017.
 */
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;
}
