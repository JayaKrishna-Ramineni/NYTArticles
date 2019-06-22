package com.dmss.nytnews.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.dmss.nytnews.Model.Pojo.NewsModel;
import com.dmss.nytnews.R;

public class ArticleDetailsActivity extends AppCompatActivity {
    TextView articleHeadingTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent articleIntent = getIntent();
        NewsModel newsModel = (NewsModel) articleIntent.getSerializableExtra("currentArticle");
        articleHeadingTextView = (TextView) findViewById(R.id.articleHeadingTextView);
        articleHeadingTextView.setText(newsModel.getTitle());




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
