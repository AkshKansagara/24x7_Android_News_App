package com.example.madnewsapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackFragment extends Fragment {

    private EditText nameEditText, emailEditText, descriptionEditText;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        // Initialize views
        nameEditText = view.findViewById(R.id.feedback_name);
        emailEditText = view.findViewById(R.id.feedback_email);
        descriptionEditText = view.findViewById(R.id.feedback_description);
        Button submitButton = view.findViewById(R.id.submit_button);

        // Initialize database helper
        dbHelper = new DatabaseHelper(getContext());

        // Submit button click listener
        submitButton.setOnClickListener(v -> submitFeedback());

        return view;
    }

    private void submitFeedback() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        // Validation
        if (name.isEmpty() || email.isEmpty() || description.isEmpty()) {
            Toast.makeText(getContext(), getString(R.string.feedback_error_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getContext(), getString(R.string.feedback_error_email), Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert into database
        boolean success = dbHelper.insertFeedback(name, email, description);
        if (success) {
            Toast.makeText(getContext(), getString(R.string.feedback_success), Toast.LENGTH_SHORT).show();
            nameEditText.setText("");
            emailEditText.setText("");
            descriptionEditText.setText("");
        } else {
            Toast.makeText(getContext(), "Failed to submit feedback", Toast.LENGTH_SHORT).show();
        }
    }
}