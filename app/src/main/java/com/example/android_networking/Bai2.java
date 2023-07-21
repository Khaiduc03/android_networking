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
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Bai2 extends AppCompatActivity {
    private TextInputEditText edtDai;
    private TextInputEditText edtRong;
    private Button btnGetData;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        //Ánh xạ
        edtDai = findViewById(R.id.textInputEdtDai);
        edtRong = findViewById(R.id.textInputEdtRong);
        tvResult = findViewById(R.id.tvResult);
        btnGetData = findViewById(R.id.btnGetData);
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhChuViDienTich();
            }
        });
    }

    private void tinhChuViDienTich() {
        String dai = edtDai.getText().toString();
        String rong = edtRong.getText().toString();

        //Kiểm tra rỗng
        if (dai.isEmpty() || rong.isEmpty()){
            tvResult.setText("Vui lòng nhập chiều dài và chiều rộng");
            return;
        }

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("chieurong",rong)
                .add("chieudai",dai)
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.1.6/API-L2-NWK/dientich_POST.php")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText("Request failed");
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText(result);
                    }
                });
            }
        });
    }
}
