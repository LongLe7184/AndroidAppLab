package com.example.androidlab;

import android.content.Intent;
import android.os.Bundle;
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
        int studentID = intent.getIntExtra("STUDENT_ID", -1);
        if(studentID != -1) {
            sidTV.setText(String.valueOf(studentID));
        }

        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}