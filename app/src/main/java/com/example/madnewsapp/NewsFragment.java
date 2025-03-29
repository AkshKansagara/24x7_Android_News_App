package com.example.madnewsapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private static final String TAG = "NewsFragment"; // For logging
    private ListView newsListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        // Initialize the ListView
        newsListView = view.findViewById(R.id.newsListView);
        if (newsListView == null) {
            Log.e(TAG, "ListView is null! Check fragment_news.xml for id 'newsListView'");
            return view; // Early return to avoid crashes
        }

        // Load news data and set adapter
        List<NewsItem> newsItems = new ArrayList<>();
        loadNewsData(newsItems);

        // Create and set the adapter
        NewsAdapter adapter = new NewsAdapter(getContext(), newsItems);
        newsListView.setAdapter(adapter);
        Log.d(TAG, "Adapter set with " + newsItems.size() + " items");

        // Set click listener for ListView items
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsItem selectedNews = (NewsItem) parent.getItemAtPosition(position);
                Log.d(TAG, "Item clicked: " + selectedNews.getTitle());

                Fragment detailFragment = NewsDetailFragment.newInstance(
                        selectedNews.getTitle(),
                        selectedNews.getDescription(),
                        selectedNews.getImageResId(),
                        selectedNews.getFullNews()
                );
                navigateToDetail(detailFragment);
            }
        });

        return view;
    }

    private void loadNewsData(List<NewsItem> newsItems) {
        // Expanded sample news data
        newsItems.add(new NewsItem(
                "Global Tech Summit 2025",
                "Tech leaders gather to discuss AI innovations.",
                R.drawable.tech,
                "The Global Tech Summit 2025 kicked off today, bringing together industry leaders to explore advancements in artificial intelligence and their impact on society."
        ));
        newsItems.add(new NewsItem(
                "New Climate Policy Announced",
                "Governments pledge to reduce emissions by 2030.",
                R.drawable.climate,
                "A coalition of nations has unveiled a bold new climate policy aimed at cutting global emissions by 40% before the end of the decade."
        ));
        newsItems.add(new NewsItem(
                "Sports Championship Finals",
                "Team A clinches victory in a thrilling match.",
                R.drawable.sports,
                "Team A emerged victorious in the championship finals after a nail-biting finish, defeating Team B with a last-minute goal."
        ));
        newsItems.add(new NewsItem(
                "Breakthrough in Medical Research",
                "Scientists develop new treatment for chronic disease.",
                R.drawable.news1,
                "Researchers have announced a groundbreaking treatment that could revolutionize care for patients with chronic illnesses."
        ));
        newsItems.add(new NewsItem(
                "Economic Growth Report",
                "GDP rises by 3% in Q1 2025.",
                R.drawable.news1,
                "The latest economic report shows a steady 3% increase in GDP, signaling robust growth across multiple sectors."
        ));
        newsItems.add(new NewsItem(
                "Space Mission Update",
                "Mars rover discovers signs of ancient water.",
                R.drawable.space,
                "NASA’s Mars rover has uncovered evidence of ancient water flows, raising hopes of finding past microbial life."
        ));
        newsItems.add(new NewsItem(
                "Cultural Festival Begins",
                "Annual arts festival attracts thousands.",
                R.drawable.news1,
                "The city’s annual cultural festival opened today, featuring art exhibitions, performances, and workshops for all ages."
        ));
        newsItems.add(new NewsItem(
                "Cybersecurity Alert",
                "New malware targets mobile devices.",
                R.drawable.news1,
                "Experts warn of a sophisticated new malware strain affecting smartphones, urging users to update their security software."
        ));
        newsItems.add(new NewsItem(
                "Education Reform Bill",
                "New law aims to improve school funding.",
                R.drawable.news1,
                "Legislators have passed a bill to increase funding for public schools, focusing on underserved communities."
        ));
        newsItems.add(new NewsItem(
                "Wildlife Conservation Success",
                "Endangered species population grows.",
                R.drawable.wildlife,
                "Conservation efforts have led to a significant increase in the population of an endangered species, marking a major milestone."
        ));
        newsItems.add(new NewsItem(
                "Fashion Week Highlights",
                "Designers showcase sustainable trends.",
                R.drawable.news1,
                "Fashion Week 2025 featured eco-friendly designs, with designers emphasizing sustainability in their latest collections."
        ));
        newsItems.add(new NewsItem(
                "Energy Sector Innovation",
                "Solar panel efficiency reaches new high.",
                R.drawable.news1,
                "A breakthrough in solar technology has boosted panel efficiency by 25%, promising cheaper renewable energy."
        ));
    }

    private void navigateToDetail(Fragment fragment) {
        Log.d(TAG, "Navigating to NewsDetailFragment");
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }
}