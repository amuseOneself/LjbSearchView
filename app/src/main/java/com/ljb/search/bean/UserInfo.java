package com.ljb.search.bean;

/**
 * 作者：ljb
 * 时间：2016/11/19 15:26
 * 邮箱：563773219@qq.com
 * 描述：
 */
public class UserInfo {


    private String name;
    private int id;
    private String avatar_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", avatar_url='" + avatar_url + '\'' +
                '}';
    }
}
