package com.nabil.task.raye7.newsapi.newsapi.data.models;

import java.util.ArrayList;

/**
 * Created by debajyotibasak on 17/12/17.
 */

public class NewsResponse {
    private String status;
    private int totalResults;
    private ArrayList<Articles> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }
}
