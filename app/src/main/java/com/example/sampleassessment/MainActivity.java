package com.example.sampleassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.samplelibrary.LogUtil;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///RecyclerView scItems = findViewById(R.id.rcvItems);


        Button btnOne = findViewById(R.id.btnOne);
        EditText etOne = findViewById(R.id.etOne);


        btnOne.setOnClickListener(this::onClick);
        etOne.setOnClickListener(this::onClick);

        LogUtil.d("Activity created");
    }
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOne: LogUtil.d("button one clicked at ",new Date());
                                break;
            case R.id.etOne: LogUtil.d("edit text one edited at ",new Date());
                                break;
            default: break;
        }
    }
}