package com.dmss.nytnews.Model.Adapters;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dmss.nytnews.R;

public class ArticlesListViewHolder extends RecyclerView.ViewHolder {
    public TextView titleTextView,articleByTextView,articleDateTextView;
    public LinearLayout articleListLayout;
    public ArticlesListViewHolder(View itemView) {
        super(itemView);
        this.titleTextView = (TextView) itemView.findViewById(R.id.articleHeadingTextView);
        this.articleByTextView = (TextView) itemView.findViewById(R.id.articleByTextView);
        this.articleDateTextView = (TextView) itemView.findViewById(R.id.articleDateTextView);
        articleListLayout = (LinearLayout)itemView.findViewById(R.id.articleListLayout);
    }
}