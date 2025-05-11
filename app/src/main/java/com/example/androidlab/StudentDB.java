package com.example.androidlab;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class StudentDB {
    private static StudentDB instance;
    private DatabaseHelper databaseHelper;
    private Context context;
    private static final String TAG = "StudentDBTag"; // Changed tag to be more unique

    public StudentDB(Context context){
        this.context = context.getApplicationContext(); // Use application context to avoid leaks
        databaseHelper = new DatabaseHelper(this.context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.execSQL("DELETE FROM students");
        ensureStudentExists();
    }

    public void ensureStudentExists() {
        // Check if having any students in the database
        Cursor cursor = databaseHelper.getAllStudents();
        Log.d(TAG, "Checking for existing students. Found: " + cursor.getCount());
        if (cursor.getCount() == 0) {
            Log.d(TAG, "Database is empty, adding default student");
            // Add a default student if the database is empty
            try {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sticker8);
                byte[] imageBytes = imageToByteArray(bitmap);
                Student x = new Student(21200310, "Le Ngoc Long", imageBytes, "21-Nhung", "0123456789", 4, "MT-HTN");
                boolean success = databaseHelper.addStudent(x);

                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sticker2);
                imageBytes = imageToByteArray(bitmap);
                x = new Student(21200311, "Le Lai", imageBytes, "21-VienThong", "0123456789", 3, "Vien thong-Mang");
                success = databaseHelper.addStudent(x);

                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sticker3);
                imageBytes = imageToByteArray(bitmap);
                x = new Student(21200312, "Nhu Ngoc", imageBytes, "21-Nhung", "0123456789", 4, "MT-HTN");
                success = databaseHelper.addStudent(x);

                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sticker4);
                imageBytes = imageToByteArray(bitmap);
                x = new Student(21200313, "Bao Y", imageBytes, "21-DienTu", "0123456789", 4, "DT");
                success = databaseHelper.addStudent(x);

                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sticker5);
                imageBytes = imageToByteArray(bitmap);
                x = new Student(21200314, "Tran Quang", imageBytes, "21-Nhung", "0123456789", 4, "MT-HTN");
                success = databaseHelper.addStudent(x);

                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sticker6);
                imageBytes = imageToByteArray(bitmap);
                x = new Student(21200315, "Thanh Loc", imageBytes, "21-Nhung", "0123456789", 4, "MT-HTN");
                success = databaseHelper.addStudent(x);

                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sticker7);
                imageBytes = imageToByteArray(bitmap);
                x = new Student(21200316, "Do Dat", imageBytes, "21-Nhung", "0123456789", 4, "MT-HTN");
                success = databaseHelper.addStudent(x);

                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sticker1);
                imageBytes = imageToByteArray(bitmap);
                x = new Student(21200317, "Nguyen Ninh", imageBytes, "21-Nhung", "0123456789", 4, "MT-HTN");
                success = databaseHelper.addStudent(x);

                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sticker9);
                imageBytes = imageToByteArray(bitmap);
                x = new Student(21200318, "Le Hoa", imageBytes, "21-Nhung", "0123456789", 4, "MT-HTN");
                success = databaseHelper.addStudent(x);

            } catch (Exception e) {
                Log.e(TAG, "Error adding default student", e);
            }
        }
        cursor.close();
    }

    private static byte[] imageToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static synchronized StudentDB getInstance(Context context){
        if(instance == null){
            instance = new StudentDB(context);
        }
        return instance;
    }

    public DatabaseHelper getDatabaseHelper(){
        return databaseHelper;
    }
}