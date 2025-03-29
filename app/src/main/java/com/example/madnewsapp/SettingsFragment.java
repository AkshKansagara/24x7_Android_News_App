package com.example.madnewsapp;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Locale;

public class SettingsFragment extends Fragment {

    private static final String TAG = "SettingsFragment";
    private Spinner languageSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Initialize language spinner
        languageSpinner = view.findViewById(R.id.language_spinner);
        if (languageSpinner == null) {
            Log.e(TAG, "Spinner not found! Check fragment_settings.xml");
            return view;
        }

        String[] languages = {"English", "हिन्दी (Hindi)", "ગુજરાતી (Gujarati)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);
        Log.d(TAG, "Spinner adapter set with " + languages.length + " options");

        // Set current language as default selection
        String currentLang = Locale.getDefault().getLanguage();
        Log.d(TAG, "Current language: " + currentLang);
        if (currentLang.equals("hi")) {
            languageSpinner.setSelection(1);
        } else if (currentLang.equals("gu")) {
            languageSpinner.setSelection(2);
        } else {
            languageSpinner.setSelection(0);
        }

        // Handle language change
        languageSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                String lang;
                switch (position) {
                    case 1: lang = "hi"; break; // Hindi
                    case 2: lang = "gu"; break; // Gujarati
                    default: lang = "en"; break; // English
                }
                Log.d(TAG, "Language selected: " + lang);
                if (!Locale.getDefault().getLanguage().equals(lang)) {
                    setLocale(lang);
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                Log.d(TAG, "No language selected");
            }
        });

        return view;
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getContext().getResources().updateConfiguration(config, getContext().getResources().getDisplayMetrics());
        Log.d(TAG, "Locale set to: " + lang);

        // Restart activity to apply changes
        if (getActivity() != null) {
            getActivity().recreate();
            Log.d(TAG, "Activity recreated");
        } else {
            Log.e(TAG, "Activity is null, cannot recreate");
        }
    }
}