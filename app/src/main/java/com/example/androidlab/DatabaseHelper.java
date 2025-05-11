package com.example.androidlab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studentDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_ID = "studentID";
    private static final String COLUMN_NAME = "name";
    private static final String TAG = "DatabaseHelperTag"; // Added unique tag

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_IMAGE + " BLOB, " +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, "Adding student: " + student.getName() + ", ID: " + student.getStudentID());

        // Check if student already exists
        Cursor cursor = db.rawQuery("SELECT * FROM students WHERE studentID = ?",
                new String[]{String.valueOf(student.getStudentID())});
        if (cursor.getCount() > 0) {
            Log.d(TAG, "Student already exists, not inserting");
            cursor.close();
            return false; // Student already exists, so we don't insert
        }

        cursor.close();

        ContentValues values = new ContentValues();
        byte[] imageData = student.getImage();
        if (imageData != null) {
            Log.d(TAG, "Image data size: " + imageData.length + " bytes");
            values.put(COLUMN_IMAGE, imageData);
        } else {
            Log.e(TAG, "Image data is null, not adding image column");
        }
        values.put(COLUMN_ID, student.getStudentID());
        values.put(COLUMN_NAME, student.getName());

        long result = db.insert(TABLE_NAME, null, values);
        Log.d(TAG, "Insert result: " + result + " (success = " + (result != -1) + ")");

        // Verify the data was inserted correctly
        Cursor verify = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?",
                new String[]{String.valueOf(student.getStudentID())});
        if (verify.moveToFirst()) {
            byte[] checkImage = verify.getBlob(verify.getColumnIndex(COLUMN_IMAGE));
            Log.d(TAG, "Verified in DB - Name: " + verify.getString(verify.getColumnIndex(COLUMN_NAME)) +
                    ", Has image: " + (checkImage != null) +
                    (checkImage != null ? ", Image size: " + checkImage.length : ""));
        } else {
            Log.e(TAG, "Failed to verify student in database after insert");
        }
        verify.close();

        return result != -1;
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}