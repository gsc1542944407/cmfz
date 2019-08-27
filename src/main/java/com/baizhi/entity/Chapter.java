package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {
    private String id;
    private String title;
    private String size;
    private String duration;
    private String status;
    private String url;
    private Date createDate;
    private String albumId;

}
