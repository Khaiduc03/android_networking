package com.example.android_networking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.URL;

public class Bai1 extends AppCompatActivity implements View.OnClickListener {
    ImageView imgResult;
    TextView tvMessage;
    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        //Khai báo biến và ánh xạ
        imgResult = findViewById(R.id.imgResult);
        tvMessage = findViewById(R.id.tvMessage);
        btnLoad = findViewById(R.id.btnLoad);

        btnLoad.setOnClickListener(this);
    }
    //Hàm load ảnh từ Internet
    private Bitmap loadImg(String link){
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(link);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public void onClick(View v) {
        //Sử dụng Thread
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = loadImg("https://caodang.fpt.edu.vn/wp-content/uploads/1-255-1.jpg");
                imgResult.post(new Runnable() {
                    @Override
                    public void run() {
                        tvMessage.setText("Image Downloaded");
                        imgResult.setImageBitmap(bitmap);
                    }
                });
            }
        });
        thread.start();
    }

}
