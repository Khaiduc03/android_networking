package com.example.android_networking;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImgActivity extends AppCompatActivity implements View.OnClickListener {

    //Khai báo biến
    private ImageView imageView;
    private TextView tvMessage;
    private Button btnLoad;
    private String url = "https://cdn01.dienmaycholon.vn/filewebdmclnew/public//userupload/images/he-dieu-hanh-android-la-gi-3.jpg";
    private Bitmap bitmap = null;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_img);

        imageView = findViewById(R.id.imgResult);
        tvMessage = findViewById(R.id.tvMessage);
        btnLoad = findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);
    }

    //sử dụng Handler
    private Handler messageHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            tvMessage.setText(message);
            imageView.setImageBitmap(bitmap);

            progressDialog.dismiss();
        }
    };

    //hàm load hình ảnh từ server
    private Bitmap downloadBitmap(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        progressDialog = ProgressDialog.show(LoadImgActivity.this, "Downloading", "Downloading");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                bitmap = downloadBitmap(url);
                Message msg = messageHandler.obtainMessage();
                Bundle bundle = new Bundle();
                String thMessage = "Finished Download";
                bundle.putString("message", thMessage);
                msg.setData(bundle);
                messageHandler.sendMessage(msg);
            }
        };
        Thread th = new Thread(runnable);
        th.start();
    }

}