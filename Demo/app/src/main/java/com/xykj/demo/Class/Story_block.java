package com.xykj.demo.Class;

public class Story_block {
    private String story_place; //故事地点
    private String story_content;//故事内容
    private String story_title;//故事标题
    private int story_imgId;// 故事id
    private String story_img;
    private int  author_id;
    private String author_img;
    private String author_name;

    public Story_block() {
    }

    public Story_block(String story_place, String story_title, String story_img) {
        this.story_place = story_place;
        this.story_title = story_title;
        this.story_img = story_img;
    }

    public Story_block(String story_place, String story_title, int story_imgId) {
        this.story_place = story_place;
        this.story_title = story_title;
        this.story_imgId = story_imgId;
    }

    public Story_block(String story_title, int story_imgId) {
        this.story_title = story_title;
        this.story_imgId = story_imgId;
    }

    public Story_block(String story_place, String story_content, String story_title, int story_imgId, String story_img, int author_id, String author_img, String author_name) {
        this.story_place = story_place;
        this.story_content = story_content;
        this.story_title = story_title;
        this.story_imgId = story_imgId;
        this.story_img = story_img;
        this.author_id = author_id;
        this.author_img = author_img;
        this.author_name = author_name;
    }

    public String getStory_place() {
        return story_place;
    }

    public void setStory_place(String story_place) {
        this.story_place = story_place;
    }

    public String getStory_content() {
        return story_content;
    }

    public void setStory_content(String story_content) {
        this.story_content = story_content;
    }

    public String getStory_img() {
        return story_img;
    }

    public void setStory_img(String story_img) {
        this.story_img = story_img;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_img() {
        return author_img;
    }

    public void setAuthor_img(String author_img) {
        this.author_img = author_img;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getStory_title() {
        return story_title;
    }

    public void setStory_title(String story_title) {
        this.story_title = story_title;
    }

    public int getStory_imgId() {
        return story_imgId;
    }

    public void setStory_imgId(int story_imgId) {
        this.story_imgId = story_imgId;
    }


}
