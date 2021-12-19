package com.example.sampleassessment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.heaploglibrary.entities.ViewActions;
import com.example.heaploglibrary.entities.ViewTypes;
import com.example.heaploglibrary.api.HeapLogUtil;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOne = findViewById(R.id.btnOne);
        EditText etOne = findViewById(R.id.etOne);
        ImageView imageView = findViewById(R.id.imageView);

        btnOne.setOnClickListener(this::onClick);
        etOne.setOnClickListener(this::onTouchEvent);
        imageView.setOnClickListener(this::logClick);

        HeapLogUtil.log(ViewTypes.ACTIVITY,ViewActions.ACTIVITY_CREATED);
    }

    private void onTouchEvent(View view) {
        HeapLogUtil.log(ViewTypes.EDIT_TEXT, ViewActions.ON_TOUCH);

    }

    void onClick(View view) {
        HeapLogUtil.log(ViewTypes.BUTTON, ViewActions.CLICK);
    }

    public void logClick(View view) {
        HeapLogUtil.log(ViewTypes.IMAGE_VIEW,ViewActions.CLICK);
    }
}