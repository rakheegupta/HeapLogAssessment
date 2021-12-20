package com.example.heapapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.heaploglibrary.entities.ViewActions;
import com.example.heaploglibrary.entities.ViewIdentifiers;
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

        etOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                HeapLogUtil.log(ViewIdentifiers.NAME_EDIT_TEXT, ViewActions.TEXT_CHANGED);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        HeapLogUtil.log(ViewIdentifiers.MAIN_ACTIVITY,ViewActions.ACTIVITY_CREATED);
    }

    private void onTouchEvent(View view) {
        HeapLogUtil.log(ViewIdentifiers.NAME_EDIT_TEXT, ViewActions.ON_TOUCH);

    }

    void onClick(View view) {
        HeapLogUtil.log(ViewIdentifiers.LOG_BUTTON, ViewActions.CLICK);
    }

    public void logClick(View view) {
        HeapLogUtil.log(ViewIdentifiers.IMAGE_VIEW_USER_PROFILE,ViewActions.CLICK);
    }
}