package com.nabil.task.raye7.newsapi.newsapi.ui.newArticles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nabil.task.raye7.newsapi.newsapi.R;
import com.nabil.task.raye7.newsapi.newsapi.data.models.Articles;
import com.nabil.task.raye7.newsapi.newsapi.databinding.FragmentArticlesListBinding;
import com.nabil.task.raye7.newsapi.newsapi.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ArticlesFragment extends Fragment implements OnBottomReachedListener {

    private int mPageCount = 1;
    private OnListFragmentInteractionListener mListener;
    private NewsViewModel mViewModel;

    public ArticlesFragment() {
    }

    public static ArticlesFragment newInstance() {
        ArticlesFragment fragment = new ArticlesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView recyclerView;

    FragmentArticlesListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_articles_list, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_articles_list, container, false);
        mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        recyclerView = binding.list;
        setupRecycler();

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!Utils.hasNetwork(getContext())) {
            Toast.makeText(getActivity(), "Network not available!", Toast.LENGTH_SHORT).show();
        }
        mViewModel.getProgress().observe(this, binding.progressBar::setVisibility);
        loadNewsArticles(++mPageCount);

        binding.pullToRefresh.setOnRefreshListener(() -> {
            if (!Utils.hasNetwork(getActivity())) {
                Toast.makeText(getActivity(), "Network not available!", Toast.LENGTH_SHORT).show();
                binding.pullToRefresh.setRefreshing(false);
                return;
            }
            mViewModel.getProgress().observe(getActivity(), binding.progressBar::setVisibility);
            loadNewsArticles(++mPageCount);
            binding.pullToRefresh.setRefreshing(false);
        });




    }

    MyArticlesRecyclerViewAdapter2 recyclerViewAdapter2;

    private List<Articles> articlesList;
    private List allArticlesList;

    private void loadNewsArticles(int i) {
        mViewModel.getNewsArticles(i).observe(this, list -> {
            articlesList = list;
            if (articlesList != null) {

                allArticlesList.addAll(articlesList);

                Toast.makeText(getContext(), "s" + allArticlesList.size(), Toast.LENGTH_SHORT).show();

                recyclerViewAdapter2=new MyArticlesRecyclerViewAdapter2(allArticlesList ,mListener ,getContext());
                recyclerView.setAdapter(recyclerViewAdapter2);
            }
        });

    }

    LinearLayoutManager layoutManager;

    void setupRecycler() {
        if (recyclerView != null) {
             allArticlesList= new ArrayList();
            Context context = getContext();
            layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            recyclerViewAdapter2 = new MyArticlesRecyclerViewAdapter2(null, mListener, context);
//            recyclerViewAdapter2.setOnBottomReachedListener(this);
            recyclerView.setAdapter(recyclerViewAdapter2);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int position = layoutManager.findLastCompletelyVisibleItemPosition();

                    if (position == allArticlesList.size() - 1 && position < 61) {
                        loadNewsArticles(++mPageCount);
                        snackbar = Snackbar
                                .make(binding.frameContainer, "loading new data "+allArticlesList.size(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }
            });
        }
    }

    Snackbar snackbar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onBottomReached(int position) {
        loadNewsArticles(++mPageCount);


    }


    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Articles item);
    }
}
