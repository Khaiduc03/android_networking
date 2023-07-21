package com.example.android_networking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Bai1 extends AppCompatActivity {
    private TextInputEditText edtName;
    private TextInputEditText edtScore;
    private TextView tvResult;
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        //Ánh xạ
        edtName = findViewById(R.id.textInputEdtName);
        edtScore = findViewById(R.id.textInputEdtScore);
        Button btnGetData = findViewById(R.id.btnGetData);
        tvResult = findViewById(R.id.tvResult);

        //Khởi tạo OkHttpClient
        okHttpClient = new OkHttpClient();

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });
    }

    private void submitData() {
        String name = edtName.getText().toString().trim();
        String score = edtScore.getText().toString().trim();

        //Kiểm tra xem name và score có rỗng không
        if (name.isEmpty() || score.isEmpty()){
            tvResult.setText("Vui lòng nhập name và score");
            return;
        }
        //URL API
        String url = "http://172.16.81.127/API-L2-NWK/std_GET.php?name=" + name + "&score=" + score;

        //Tạo Request
        Request request = new Request.Builder()
                .url(url).build();

        //Gửi request bất đồng bộ
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                tvResult.setText("Error occurred");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    final String responseData = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Hiển thị dữ liệu trả về lên TextView
                            tvResult.setText(responseData);
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResult.setText("Error occurred");
                        }
                    });
                }
            }
        });
    }
}