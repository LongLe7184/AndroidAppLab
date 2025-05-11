package com.example.androidlab;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;
import android.database.Cursor;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Integer> IDs = new ArrayList<>();
    private ArrayList<String> NAMEs = new ArrayList<>();
    private ArrayList<Bitmap> IMAGEs = new ArrayList<>();
    private static final String TAG = "MainActivityTag";

    private int n = 0;
    private String[] labels;
    private String[] descriptions;
    private Bitmap[] icons;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        StudentDB db = StudentDB.getInstance(this);
        DatabaseHelper dbHelper = db.getDatabaseHelper();

        // Make sure having at least one student in the database
        db.ensureStudentExists();

        Log.d(TAG, "Getting all students");
        Cursor cursor = dbHelper.getAllStudents();
        Log.d(TAG, "Cursor row count: " + cursor.getCount());

        if (cursor.moveToFirst()) {
            n = 0;
            do {
                int idIdx = cursor.getColumnIndex("studentID");
                int nameIdx = cursor.getColumnIndex("name");
                int imageIdx = cursor.getColumnIndex("image");

                int studentId = cursor.getInt(idIdx);
                String name = cursor.getString(nameIdx);
                byte[] imageData = cursor.getBlob(imageIdx);
                IDs.add(studentId);
                NAMEs.add(name);

                try {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                    if (bitmap != null) {
                        IMAGEs.add(bitmap);
                        Log.d(TAG, "Successfully decoded bitmap: " + bitmap.getWidth() + "x" + bitmap.getHeight());
                        n++;
                    } else {
                        Log.e(TAG, "Failed to decode bitmap for student: " + name);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error decoding image for student: " + name, e);
                }
            } while (cursor.moveToNext());
        } else {
            Log.e(TAG, "No students found in database");
        }
        cursor.close();

        // Guard against empty database
        if (n > 0) {
            ListView listView = findViewById(R.id.myListView);

            labels = new String[n];
            descriptions = new String[n];
            icons = new Bitmap[n];
            for(int i=0; i<n; i++){
                labels[i] = NAMEs.get(i);
                descriptions[i] = String.valueOf(IDs.get(i));
                icons[i] = IMAGEs.get(i);
            }

            CustomAdapter adapter = new CustomAdapter(this, labels, descriptions, icons);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener((parent, view, position, id) -> {
                // Get the data for the clicked item
                int selectedId = Integer.parseInt(descriptions[position]);

                // Or start a new activity with the selected item's data
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("STUDENT_ID", selectedId);
                startActivity(intent);
            });


        } else {
            Log.e(TAG, "No valid students with images found");
        }
    }
}