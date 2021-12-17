package com.example.sampleassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.samplelibrary.user.LogUtil;
import com.example.samplelibrary.entities.ViewActions;
import com.example.samplelibrary.entities.ViewTypes;


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
            case R.id.btnOne: LogUtil.log(ViewTypes.BUTTON, ViewActions.CLICK);
                                break;
            case R.id.etOne: LogUtil.log(ViewTypes.EDIT_TEXT, ViewActions.CLICK);
                                break;
            default: break;
        }
    }
}