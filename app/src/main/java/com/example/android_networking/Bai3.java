package com.example.android_networking;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bai3 extends AppCompatActivity implements View.OnClickListener, Listener {

    //khai báo biến
    Button btnLoad;
    TextView tvMessage;
    ImageView imgResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        btnLoad = findViewById(R.id.btnLoad);
        tvMessage = findViewById(R.id.tvMessage);
        imgResult = findViewById(R.id.imgResult);

        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //gọi hàm doInbackground
        new AsyncTaskBai3(this, this).execute("https://www.computerhope.com/jargon/a/android.png");
    }

    @Override
    public void onImageDownload(Bitmap bitmap) {
        imgResult.setImageBitmap(bitmap);//đưa ảnh download lên ImageView
        tvMessage.setText("Successful Download");
    }

    @Override
    public void onError() {

    }

}

