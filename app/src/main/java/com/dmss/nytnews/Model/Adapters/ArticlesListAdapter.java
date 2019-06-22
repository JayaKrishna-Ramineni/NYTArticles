package com.dmss.nytnews.Model.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dmss.nytnews.Model.Pojo.NewsModel;
import com.dmss.nytnews.R;
import com.dmss.nytnews.View.ArticleDetailsActivity;

import java.util.ArrayList;

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListViewHolder> {
    ArrayList<NewsModel> newsModels = new ArrayList<>();
    Context context;
    Activity activity;

    public ArticlesListAdapter(ArrayList<NewsModel> newsModels, Context context, Activity activity) {
        this.newsModels = newsModels;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ArticlesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.article_list_adapter_view, parent, false);
        ArticlesListViewHolder viewHolder = new ArticlesListViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesListViewHolder holder, int position) {
        NewsModel newsModel = newsModels.get(position);
        holder.titleTextView.setText(newsModel.getTitle());
        holder.articleByTextView.setText(newsModel.getByline());
        holder.articleDateTextView.setText(newsModel.getPublishedDate());
        holder.articleListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent articalDetailsIntent = new Intent(context, ArticleDetailsActivity.class);
                articalDetailsIntent.putExtra("currentArticle",newsModel );
                /*ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(this, (View)ivProfile, "profile");*/
                ActivityOptionsCompat optionsCompat =
ActivityOptionsCompat.makeSceneTransitionAnimation(activity,(View)holder.titleTextView,"Title");
                context.startActivity(articalDetailsIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }
}
