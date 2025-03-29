package com.example.madnewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsItem> {

    private static class ViewHolder {
        ImageView newsImageView;
        TextView titleTextView;
        TextView descriptionTextView;
    }

    public NewsAdapter(Context context, List<NewsItem> newsItems) {
        super(context, 0, newsItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsItem newsItem = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_news, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.newsImageView = convertView.findViewById(R.id.newsImageView);
            viewHolder.titleTextView = convertView.findViewById(R.id.newsTitleTextView);
            viewHolder.descriptionTextView = convertView.findViewById(R.id.newsDescriptionTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.titleTextView.setText(newsItem.getTitle());
        viewHolder.descriptionTextView.setText(newsItem.getDescription());
        viewHolder.newsImageView.setImageResource(newsItem.getImageResId());

        return convertView;
    }
}