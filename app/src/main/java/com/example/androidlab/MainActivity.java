package com.example.androidlab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    String str_in = "", tmpStr = "";
    double result;
    boolean addPressed = false;

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

        Button delBT = findViewById(R.id.delBT);
        Button equalBT = findViewById(R.id.equalBT);
        Button zeroBT = findViewById(R.id.zeroBT);
        Button oneBT = findViewById(R.id.oneBT);
        Button twoBT = findViewById(R.id.twoBT);
        Button threeBT = findViewById(R.id.threeBT);
        Button fourBT = findViewById(R.id.fourBT);
        Button fiveBT = findViewById(R.id.fiveBT);
        Button sixBT = findViewById(R.id.sixBT);
        Button sevenBT = findViewById(R.id.sevenBT);
        Button eightBT = findViewById(R.id.eightBT);
        Button nineBT = findViewById(R.id.nineBT);
        Button divBT = findViewById(R.id.divBT);
        Button subBT = findViewById(R.id.subBT);
        Button mulBT = findViewById(R.id.mulBT);
        Button addBT = findViewById(R.id.addBT);
        Button percentBT = findViewById(R.id.percentBT);
        Button pointBT = findViewById(R.id.pointBT);

        TextView outputTV = findViewById(R.id.outputTV);
        TextView inputTV = findViewById(R.id.inputTV);

        /*       NUMBER BUTTONS        */
        zeroBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "0";
                inputTV.setText(str_in);
            }
        });
        oneBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "1";
                inputTV.setText(str_in);
            }
        });
        twoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "2";
                inputTV.setText(str_in);
            }
        });
        threeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "3";
                inputTV.setText(str_in);
            }
        });
        fourBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "4";
                inputTV.setText(str_in);
            }
        });
        fiveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "5";
                inputTV.setText(str_in);
            }
        });
        sixBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "6";
                inputTV.setText(str_in);
            }
        });
        sevenBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "7";
                inputTV.setText(str_in);
            }
        });
        eightBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "8";
                inputTV.setText(str_in);
            }
        });
        nineBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "9";
                inputTV.setText(str_in);
            }
        });

        /*FUNCTION BUTTON*/
        delBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 0;
                if(!str_in.isEmpty()) {
                    str_in = str_in.substring(0, str_in.length() - 1);
                }
                inputTV.setText(str_in);
            }
        });
        addBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "+";
                inputTV.setText(str_in);
            }
        });
        subBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "-";
                inputTV.setText(str_in);
            }
        });
        divBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "/";
                inputTV.setText(str_in);
            }
        });
        mulBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "*";
                inputTV.setText(str_in);
            }
        });
        percentBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += "%";
                inputTV.setText(str_in);
            }
        });
        pointBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_in += ".";
                inputTV.setText(str_in);
            }
        });


        equalBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!str_in.isEmpty()){
                    ShuntingYard equation = new ShuntingYard(str_in);
                    result = equation.calculateEquation();
                }
                outputTV.setText(String.valueOf(result));
            }
        });
    }
}