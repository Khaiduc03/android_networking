package com.example.android_networking;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bai5 extends AppCompatActivity {
    private Handler handler;
    private boolean isThreadRunning = false;
    private Thread numberThread;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai5);

        Button btnStart = findViewById(R.id.btnStart);
        Button btnStop = findViewById(R.id.btnStop);
        tvOutput = findViewById(R.id.tvOutput);

        handler = new Handler(Looper.getMainLooper());

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartNumberThread();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StopNumberThread();
            }
        });
    }

    private void StartNumberThread() {
        isThreadRunning = true;

        // khỞi tạo thread mới
        numberThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    if (!isThreadRunning) {
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    int number = i;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            tvOutput.setText("Number: " + number);
                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvOutput.setText("Hoàn thành thread hihi");
                    }
                });
            }
        });
        numberThread.start();
    }

    private void StopNumberThread() {
        isThreadRunning = false;
    }
}