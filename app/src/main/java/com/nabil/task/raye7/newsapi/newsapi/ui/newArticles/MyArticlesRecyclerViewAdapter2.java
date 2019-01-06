package com.nabil.task.raye7.newsapi.newsapi.ui.newArticles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nabil.task.raye7.newsapi.newsapi.R;
import com.nabil.task.raye7.newsapi.newsapi.data.models.Articles;
import com.nabil.task.raye7.newsapi.newsapi.databinding.FragmentArticles2Binding;
import com.nabil.task.raye7.newsapi.newsapi.ui.newArticles.ArticlesFragment.OnListFragmentInteractionListener;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class MyArticlesRecyclerViewAdapter2 extends RecyclerView.Adapter<MyArticlesRecyclerViewAdapter2.ViewHolder>
        implements ListItemClickListener {

    public MyArticlesRecyclerViewAdapter2(OnListFragmentInteractionListener mListener, Context context) {
        this.mListener = mListener;
        this.context = context;
    }

    public void setmValues(List<Articles> mValues) {
        if (mValues != null) {
            this.mValues = mValues;
            notifyDataSetChanged();
        }
    }

    private List<Articles> mValues;

    private OnListFragmentInteractionListener mListener;
    private OnBottomReachedListener onBottomReachedListener;
    Context context;

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public MyArticlesRecyclerViewAdapter2(List<Articles> items, OnListFragmentInteractionListener listener, Context context) {
        mValues = items;
        mListener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        FragmentArticles2Binding binding;
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.fragment_articles2, parent, false);

        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        holder.articles2Binding.setArticle(mValues.get(position));
        holder.articles2Binding.setItemClickListener(this);


        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();


        Glide.with(context)
                .load(mValues.get(position).getUrlToImage())
                .placeholder(progressDrawable)
                .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                .error(R.drawable.ic_insert_photo_black_24dp)
                .centerCrop()
                .crossFade()
                .into(holder.articles2Binding.articleImage);

//        if (position == mValues.size()-1)
//            onBottomReachedListener.onBottomReached(position);
    }

    @Override
    public int getItemCount() {
        return  mValues == null ? 0 : mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public FragmentArticles2Binding articles2Binding;

        public ViewHolder(FragmentArticles2Binding fragmentArticles2Binding) {
            super(fragmentArticles2Binding.getRoot());
            articles2Binding = fragmentArticles2Binding;
        }

    }

    @Override
    public void onClick(int i) {

    }

    public void onClick(Articles article) {

        if (null != mListener) {
            mListener.onListFragmentInteraction(article);
        }
    }
}

