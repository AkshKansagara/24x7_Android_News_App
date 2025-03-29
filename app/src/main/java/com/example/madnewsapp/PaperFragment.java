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

import androidx.cardview.widget.CardView;

public class PaperFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paper, container, false);

        // Populate the cards and set click listeners
        setupCards(view);

        return view;
    }

    private void setupCards(View view) {
        // Card 1: India Today
        CardView card1 = view.findViewById(R.id.card1);
        ImageView image1 = view.findViewById(R.id.paper_image1);
        TextView title1 = view.findViewById(R.id.paper_title1);
        image1.setImageResource(R.drawable.india_today);
        title1.setText("India Today");
        card1.setOnClickListener(v -> navigateToWebView("https://www.indiatoday.in"));

        // Card 2: Forbes India
        CardView card2 = view.findViewById(R.id.card2);
        ImageView image2 = view.findViewById(R.id.paper_image2);
        TextView title2 = view.findViewById(R.id.paper_title2);
        image2.setImageResource(R.drawable.forbes_india);
        title2.setText("Forbes India");
        card2.setOnClickListener(v -> navigateToWebView("https://www.forbesindia.com"));

        // Card 3: Gujarat Samachar
        CardView card3 = view.findViewById(R.id.card3);
        ImageView image3 = view.findViewById(R.id.paper_image3);
        TextView title3 = view.findViewById(R.id.paper_title3);
        image3.setImageResource(R.drawable.gujarat_samachar);
        title3.setText("Gujarat Samachar");
        card3.setOnClickListener(v -> navigateToWebView("https://epaper.gujaratsamachar.com/"));

        // Card 4: The Times of India
        CardView card4 = view.findViewById(R.id.card4);
        ImageView image4 = view.findViewById(R.id.paper_image4);
        TextView title4 = view.findViewById(R.id.paper_title4);
        image4.setImageResource(R.drawable.times_india);
        title4.setText("The Times of India");
        card4.setOnClickListener(v -> navigateToWebView("https://epaper.indiatimes.com/"));
    }

    private void navigateToWebView(String url) {
        Fragment webFragment = WebViewFragment.newInstance(url);
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, webFragment)
                .addToBackStack(null) // Allows back navigation
                .commit();
    }
}