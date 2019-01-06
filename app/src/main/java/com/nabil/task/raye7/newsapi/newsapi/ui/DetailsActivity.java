package com.nabil.task.raye7.newsapi.newsapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.databinding.DataBindingUtil;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nabil.task.raye7.newsapi.newsapi.R;
import com.nabil.task.raye7.newsapi.newsapi.data.models.Articles;
import com.nabil.task.raye7.newsapi.newsapi.databinding.ActivityDetailsBinding;
import com.nabil.task.raye7.newsapi.newsapi.utils.Utils;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_details);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("item")) {
            Articles currentArticle = bundle.getParcelable("item");
            if (currentArticle != null) {
//                this.currentArticle = currentArticle;
//                binding.setArticle(currentArticle);
//                setupShareButton(currentArticle);
//                setupButtonClickListener(currentArticle);

                Glide.with(this).load(currentArticle.getUrlToImage()).into(binding.ivNewsImage);
//                binding.tvNewsSource.setText(currentArticle.getSource().getName());

                binding.tvNewsTitle.setText(currentArticle.getTitle());
                binding.tvNewsDesc.setText(currentArticle.getDescription());
                binding.tvTime.setText(Utils.formattedDate(currentArticle.getPublishedAt()));
                binding.btnReadFull.setOnClickListener(view -> {
                    String url = currentArticle.getUrl();
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(this, Uri.parse(url));
                });



                Toast.makeText(this, "Details" + currentArticle.getDescription(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
