package com.example.ahmed.project6_1;

public class News {

    private String sectionName;
    private String webUrl;
    private String webTitle;
    private String webPublicationDate;
    private String author;

    public News(String section, String author, String title, String publicationDate, String url) {
        sectionName = section;
        webUrl = url;
        webTitle = title;
        this.author = author;
        webPublicationDate = publicationDate;

    }
    public String getSectionName() {
        return sectionName;
    }

    public String getUrl() {
        return webUrl;
    }

    public String getTitle() {
        return webTitle;
    }

    public String getPublicationDate() {
        return webPublicationDate;
    }

    public String getAuthor() {
        return author;
    }
}
