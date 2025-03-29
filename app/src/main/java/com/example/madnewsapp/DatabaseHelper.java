package com.example.madnewsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "NewsAppDB";
    private static final int DATABASE_VERSION = 1;

    // Feedback table
    public static final String TABLE_FEEDBACK = "feedback";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_TIMESTAMP = "timestamp";

    // Create table query
    private static final String CREATE_TABLE_FEEDBACK = "CREATE TABLE " + TABLE_FEEDBACK + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " TEXT NOT NULL, " +
            COL_EMAIL + " TEXT NOT NULL, " +
            COL_DESCRIPTION + " TEXT NOT NULL, " +
            COL_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creating feedback table");
        db.execSQL(CREATE_TABLE_FEEDBACK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
        onCreate(db);
    }

    // Insert feedback into the database
    public boolean insertFeedback(String name, String email, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_EMAIL, email);
        values.put(COL_DESCRIPTION, description);

        long result = db.insert(TABLE_FEEDBACK, null, values);
        db.close();

        if (result == -1) {
            Log.e(TAG, "Failed to insert feedback");
            return false;
        } else {
            Log.d(TAG, "Feedback inserted successfully");
            return true;
        }
    }

    // Retrieve all feedback (for debugging or display)
    public Cursor getAllFeedback() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_FEEDBACK, null);
    }
}