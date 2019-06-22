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
    TextView articleHeadingTextView, articleDateTextView,
            articleByTextView,
            articleAbstractHeadTextView, articleAbstractTextView,
            articleWebHeadTextView, articleWebLinkTextView,
            articleSourceHeadTextView, articleSourceTextView;
    NewsModel newsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent articleIntent = getIntent();
        newsModel = (NewsModel) articleIntent.getSerializableExtra("currentArticle");
        initializeUi();
    }

    public void initializeUi() {
        articleHeadingTextView = (TextView) findViewById(R.id.articleHeadingTextView);
        articleDateTextView = (TextView) findViewById(R.id.articleDateTextView);

        articleByTextView = (TextView) findViewById(R.id.articleByTextView);
        articleAbstractHeadTextView = (TextView) findViewById(R.id.articleAbstractHeadTextView);
        articleAbstractTextView = (TextView) findViewById(R.id.articleAbstractTextView);

        articleWebHeadTextView = (TextView) findViewById(R.id.articleWebHeadTextView);
        articleWebLinkTextView = (TextView) findViewById(R.id.articleWebLinkTextView);
        articleSourceHeadTextView = (TextView) findViewById(R.id.articleSourceHeadTextView);
        articleSourceTextView = (TextView) findViewById(R.id.articleSourceTextView);
        setDataToUI();
    }

    public void setDataToUI() {
        if (newsModel != null) {
            articleHeadingTextView.setText(newsModel.getTitle());
            articleDateTextView.setText(newsModel.getPublishedDate());
            articleByTextView.setText(newsModel.getByline());
            articleAbstractHeadTextView.setText("Article Abstract");
            articleAbstractTextView.setText(newsModel.getArticleAbstract());
            articleWebHeadTextView.setText("Article Full Story");
            articleWebLinkTextView.setText(newsModel.getUrl());
            articleSourceHeadTextView.setText("Article Aource");
            articleSourceTextView.setText(newsModel.getSource());
        }
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
