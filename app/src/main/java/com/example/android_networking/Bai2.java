package com.example.android_networking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Bai2 extends AppCompatActivity {
    public static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Bai2.this, LoadImgActivity.class);
                startActivity(intent);
                finish();
            }
        },TIME_OUT);
    }
}

