package com.xykj.demo.Class;

/*
*评论
 */
public class Comment {

    String name; //评论者
    String content; //评论内容
    String time;//评论时间

    public Comment(){

    }

    public Comment(String name, String content){
        this.name = name;
        this.content = content;
    }

    public Comment(String name, String content, String time) {
        this.name = name;
        this.content = content;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
