package com.example.androidlab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        EditText displayET = findViewById(R.id.displayET);
        //Get bundle
        Bundle bundle = getIntent().getBundleExtra("MyData1");

        //Get data and display
        if(bundle != null){
            String str = "";
            str += "THÔNG TIN SINH VIÊN\n\n";
            str += "Họ và tên: " + bundle.getString("name") + "\n";
            str += "MSSV: " + String.valueOf(bundle.getInt("sid")) + "\n";
            str += "Lớp: " + bundle.getString("cid") + "\n";
            str += "SĐT: " + bundle.getString("pn") + "\n";
            str += "Sinh viên năm: " + String.valueOf(bundle.getInt("sen")) + "\n";
            str += "Chuyên ngành: " + bundle.getString("mj") + "\n";

            str += "Kế hoạch bản thân: " + bundle.getString("plan");
            displayET.setText(str);
        }

        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}