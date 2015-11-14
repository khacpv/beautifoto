package com.oicmap.beautifoto.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by khacpham on 11/14/15.
 */
@Table(name = "photo")
public class Photo extends BaseModel {

    @Column(name = "url")
    public String url;

    @Column(name = "width")
    public int width;

    @Column(name = "height")
    public int height;

    public Photo(){
        super();
    }

}
