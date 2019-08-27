package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private String id;
    private String title;
    private Double score;
    private String author;
    private String broadcast;
    private Integer count;
    private String brief;
    private Date publishDate;
    private String cover;
    private String status;
}
