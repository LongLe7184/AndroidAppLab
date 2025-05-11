package com.example.androidlab;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button backBT = findViewById(R.id.backBT);

        //Link object by component ID
        TextView nameTV = findViewById(R.id.nameTV);
        TextView sidTV = findViewById(R.id.sidTV);
        TextView classTV = findViewById(R.id.classTV);
        TextView phoneNumTV = findViewById(R.id.phoneNumTV);
        TextView seniorityTV = findViewById(R.id.seniorityTV);
        TextView majorityTV = findViewById(R.id.majorityTV);
        TextView planTV = findViewById(R.id.planTV);

        Intent intent = getIntent();
        int lookupID = intent.getIntExtra("STUDENT_ID", -1);
        if(lookupID != -1) {
            StudentDB db = StudentDB.getInstance(this);
            DatabaseHelper dbHelper = db.getDatabaseHelper();

            // Make sure having at least one student in the database
            db.ensureStudentExists();

            Cursor cursor = dbHelper.getAllStudents();

            if (cursor.moveToFirst()) {
                int n = 0;
                int idIdx, nameIdx, classIdx, phoneNumberIdx, majorityIdx, seniorityIdx;
                int studentID;
                String name, classID, phoneNumber, majority;

                do {
                    idIdx = cursor.getColumnIndex("studentID");
                    nameIdx = cursor.getColumnIndex("name");
                    classIdx = cursor.getColumnIndex("class");
                    phoneNumberIdx = cursor.getColumnIndex("phoneNumber");
                    seniorityIdx = cursor.getColumnIndex("seniority");
                    majorityIdx = cursor.getColumnIndex("majority");

                    studentID = cursor.getInt(idIdx);

                    if(studentID == lookupID){
                        nameTV.setText(cursor.getString(nameIdx));
                        sidTV.setText(String.valueOf(lookupID));
                        classTV.setText(cursor.getString(classIdx));
                        phoneNumTV.setText(cursor.getString(phoneNumberIdx));
                        seniorityTV.setText(cursor.getString(seniorityIdx));
                        majorityTV.setText(cursor.getString(majorityIdx));
                        break;
                    }
                    n++;
                } while (cursor.moveToNext());
            } else {
                Log.e("SubActivity", "No students found in database");
            }
            cursor.close();
        }

        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}