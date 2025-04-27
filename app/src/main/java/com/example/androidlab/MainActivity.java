package com.example.androidlab;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        ListView listView = findViewById(R.id.myListView);

        int[] icons = {R.drawable.apple, R.drawable.avocado, R.drawable.banana,
                       R.drawable.blueberry, R.drawable.cherry, R.drawable.dragonfruit,
                       R.drawable.pear, R.drawable.pineapple, R.drawable.watermelon};

        String[] labels = {"Apple", "Avocado", "Banana",
                           "Blueberry", "Cherry", "Dragon Fruit",
                           "Pear", "Pineapple", "Watermelon"};

        String[] descriptions = {"50 Calories", "97 Calories", "82 Calories",
                                 "64 Calories", "33 Calories", "47 Calories",
                                 "52 Calories", "48 Calories", "76 Calories",};

        CustomAdapter adapter = new CustomAdapter(this, labels, descriptions, icons);
        listView.setAdapter(adapter);
    }
}