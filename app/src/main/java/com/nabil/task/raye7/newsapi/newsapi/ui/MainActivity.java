package com.nabil.task.raye7.newsapi.newsapi.ui;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.nabil.task.raye7.newsapi.newsapi.R;
import com.nabil.task.raye7.newsapi.newsapi.data.models.Articles;
import com.nabil.task.raye7.newsapi.newsapi.data.models.NewsResponse;
import com.nabil.task.raye7.newsapi.newsapi.data.remote.ApiFactory;
import com.nabil.task.raye7.newsapi.newsapi.data.remote.UsersService;
import com.nabil.task.raye7.newsapi.newsapi.databinding.ActivityMainBinding;
import com.nabil.task.raye7.newsapi.newsapi.ui.favArticles.FavFragment;
import com.nabil.task.raye7.newsapi.newsapi.ui.newArticles.ArticlesFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements ArticlesFragment.OnListFragmentInteractionListener {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private void fetchUsersList() {

//        AppController appController = AppController.create(MainActivity.this);
//        UsersService usersService = appController.getUserService();
        UsersService usersService = ApiFactory.create();

        Disposable disposable = usersService.fetchUsers("1")
//                .subscribeOn(appController.subscribeScheduler())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsResponse>() {


                    @Override
                    public void accept(NewsResponse newsResponse) throws Exception {
                        Toast.makeText(MainActivity.this, "size " + newsResponse.getArticles().size(), Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "throwable " + throwable, Toast.LENGTH_SHORT).show();
                    }
                });

        compositeDisposable.add(disposable);
    }

    ActionBar actionBar;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        actionBar = getSupportActionBar();
        showNavigation();
        showHeadlines();


    }


    private void showHeadlines() {
        actionBar.setTitle("Headlines");
        loadFragment(ArticlesFragment.newInstance());
    }

    private void showNavigation() {
        binding.navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_headline:
                    actionBar.setTitle("Headlines");
                    loadFragment(ArticlesFragment.newInstance());
                    return true;

                case R.id.navigation_favourite:
//                    FavouriteFragment favouriteFragment = new FavouriteFragment();
//                    ItemListDialogFragment favouriteFragment = new ItemListDialogFragment();
                    actionBar.setTitle("Favourite");
                    loadFragment(FavFragment.newInstance());
                    return true;

                default:
                    actionBar.setTitle("Headlines");
                    loadFragment(ArticlesFragment.newInstance());
                    return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onListFragmentInteraction(Articles item) {

        Toast.makeText(this, "clicked" + item.getSource().getName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);

        intent.putExtra("item", item);

        startActivity(intent);


    }
}
