package com.bwie.zhangzili20190120.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * date:2019/1/20
 * author:张自力(DELL)
 * function: GREENDao的封装类
 */
@Entity
public class ShopBeanDao {

    @Id
    private long id;
    private String title;
    private String content;
    private int price;
    @Generated(hash = 1599355827)
    public ShopBeanDao(long id, String title, String content, int price) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
    }
    @Generated(hash = 2076677685)
    public ShopBeanDao() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }



}
