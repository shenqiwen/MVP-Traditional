package com.sqw.mvp_traditional.bean.entity;

/**
 * 新闻数据实体类
 */

public class MultiNewsArticleDataBean  {

    private String Title ;
    private String Content ;
    private String Time ;
    private String Type ;

    public MultiNewsArticleDataBean(String title, String content, String time,String type) {
        Title = title;
        Content = content;
        Time = time;
        Type = type ;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}