package com.example.firebase_practice;

public class NewSpeedRecyclerViewItem {

    private String content;
    private String comment;

    public NewSpeedRecyclerViewItem(String content, String comment) {
        this.content = content;
        this.comment = comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
