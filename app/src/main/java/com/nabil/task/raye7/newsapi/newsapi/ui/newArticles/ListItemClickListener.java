package com.nabil.task.raye7.newsapi.newsapi.ui.newArticles;

import com.nabil.task.raye7.newsapi.newsapi.data.models.Articles;
import com.nabil.task.raye7.newsapi.newsapi.ui.newArticles.dummy.DummyContent;

public interface ListItemClickListener {
    public void onClick(int i);
    public void onClick(Articles article);
}
