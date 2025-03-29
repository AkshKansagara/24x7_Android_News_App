package com.example.madnewsapp;

import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewFeedbackFragment extends Fragment {

    private static final String TAG = "ViewFeedbackFragment";
    private DatabaseHelper dbHelper;
    private ListView feedbackListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_feedback, container, false);

        feedbackListView = view.findViewById(R.id.feedback_list);
        dbHelper = new DatabaseHelper(getContext());

        loadFeedback();

        return view;
    }

    private void loadFeedback() {
        ArrayList<String> feedbackList = new ArrayList<>();
        Cursor cursor = dbHelper.getAllFeedback();

        if (cursor == null) {
            Log.e(TAG, "Cursor is null, query failed");
            Toast.makeText(getContext(), "Error loading feedback", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cursor.moveToFirst()) {
            do {
                try {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NAME));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_EMAIL));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DESCRIPTION));
                    String timestamp = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TIMESTAMP));
                    String entry = "Name: " + name + "\nEmail: " + email + "\nDescription: " + description + "\nTime: " + timestamp;
                    feedbackList.add(entry);
                } catch (IllegalArgumentException e) {
                    Log.e(TAG, "Column not found: " + e.getMessage());
                    Toast.makeText(getContext(), "Error: Invalid column in database", Toast.LENGTH_SHORT).show();
                    break;
                }
            } while (cursor.moveToNext());
        } else {
            Log.d(TAG, "No feedback entries found");
            feedbackList.add("No feedback available");
        }

        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, feedbackList);
        feedbackListView.setAdapter(adapter);
    }
}