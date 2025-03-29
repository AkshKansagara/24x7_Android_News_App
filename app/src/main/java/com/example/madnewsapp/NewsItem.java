package com.example.madnewsapp;

public class NewsItem {
    private String title;
    private String description;
    private int imageResId; // Image resource ID
    private String fullNews; // New field for full news content

    public NewsItem(String title, String description, int imageResId, String fullNews) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
        this.fullNews = fullNews;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getFullNews() {
        return fullNews;
    }
}