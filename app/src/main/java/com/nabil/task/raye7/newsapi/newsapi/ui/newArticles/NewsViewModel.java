package com.nabil.task.raye7.newsapi.newsapi.ui.newArticles;

import android.util.Log;

import com.nabil.task.raye7.newsapi.newsapi.data.models.Articles;
import com.nabil.task.raye7.newsapi.newsapi.data.remote.ApiFactory;
import com.nabil.task.raye7.newsapi.newsapi.data.remote.UsersService;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<Articles> articlesList;
    private final MutableLiveData<Integer> progress = new MutableLiveData<>();
    private final MutableLiveData<List<Articles>> articles = new MutableLiveData<>();


    public LiveData<List<Articles>> getNewsArticles(int i) {
        UsersService usersService = ApiFactory.create();

        Disposable disposable = usersService.fetchUsers(
//                String.valueOf(i)
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        newsResponse -> {
                            progress.setValue(8);
                            articles.setValue(newsResponse.getArticles());

                        },
                        throwable ->
                                Log.i("HeadlineFragment.class", "onStart: " + throwable)
                );

        compositeDisposable.add(disposable);

        return articles;
    }


    public LiveData<Integer> getProgress() {
        return progress;
    }
}
