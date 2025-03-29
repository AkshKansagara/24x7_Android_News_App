package com.example.madnewsapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsDetailFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_IMAGE_RES_ID = "image_res_id";
    private static final String ARG_FULL_NEWS = "full_news";

    public static NewsDetailFragment newInstance(String title, String description, int imageResId, String fullNews) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESCRIPTION, description);
        args.putInt(ARG_IMAGE_RES_ID, imageResId);
        args.putString(ARG_FULL_NEWS, fullNews);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        // Initialize views
        ImageView imageView = view.findViewById(R.id.detail_image);
        TextView titleTextView = view.findViewById(R.id.detail_title);
        TextView descriptionTextView = view.findViewById(R.id.detail_description);
        TextView fullNewsTextView = view.findViewById(R.id.detail_full_news);

        // Get data from arguments
        Bundle args = getArguments();
        if (args != null) {
            String title = args.getString(ARG_TITLE);
            String description = args.getString(ARG_DESCRIPTION);
            int imageResId = args.getInt(ARG_IMAGE_RES_ID);
            String fullNews = args.getString(ARG_FULL_NEWS);

            // Set data to views
            titleTextView.setText(title);
            descriptionTextView.setText(description);
            imageView.setImageResource(imageResId);
            fullNewsTextView.setText(fullNews);
        }

        return view;
    }
}