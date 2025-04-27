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

        int[] icons = {R.drawable.sticker1, R.drawable.sticker2, R.drawable.sticker3,
                       R.drawable.sticker4, R.drawable.sticker5, R.drawable.sticker6,
                       R.drawable.sticker7, R.drawable.sticker8, R.drawable.sticker9};

        String[] labels = {"Shy", "Happy", "Amazing",
                           "Surprise", "Angry", "Pensive",
                           "Sleep", "Cool", "Think"};

        String[] descriptions = {"Shy Emote sticker", "Happy Emote sticker", "Amazing Emote sticker",
                                 "Surprise Emote sticker", "Angry Emote sticker", "Pensive Emote sticker",
                                 "Sleep Emote sticker", "Cool Emote sticker", "Think Emote sticker"};

        CustomAdapter adapter = new CustomAdapter(this, labels, descriptions, icons);
        listView.setAdapter(adapter);
    }
}