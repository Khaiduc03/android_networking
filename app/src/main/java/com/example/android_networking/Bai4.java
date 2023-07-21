package com.example.android_networking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Bai4 extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText textInputEdtTime;
    Button btnRun;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);

        textInputEdtTime = findViewById(R.id.textInputEdtTime);
        tvResult = findViewById(R.id.tvResult);
        btnRun = findViewById(R.id.btnRun);

        btnRun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String sleepTime = textInputEdtTime.getText().toString();
        new AsyncTaskBai4(tvResult, textInputEdtTime, this).execute(sleepTime);
    }
}
