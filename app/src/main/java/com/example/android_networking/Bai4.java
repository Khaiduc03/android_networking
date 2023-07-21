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


public class Bai4 extends AppCompatActivity {
    private TextInputEditText edtA;
    private TextInputEditText edtB;
    private TextInputEditText edtC;
    private Button btnGetData;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);

        //ánh xạ các thành phần
        edtA = findViewById(R.id.textInputEdtA);
        edtB = findViewById(R.id.textInputEdtB);
        edtC = findViewById(R.id.textInputEdtC);
        btnGetData = findViewById(R.id.btnGetData);
        tvResult = findViewById(R.id.tvResult);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy giá trị từ các TextInputEditText
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                int c = Integer.parseInt(edtC.getText().toString());

                //gọi phương thức đã truy xuất API
                giaiPTB2(a,b,c);
            }
        });
    }

    private void giaiPTB2(int a, int b, int c) {
        //tạo client OkHttpClient
        OkHttpClient  client = new OkHttpClient();

        //tạo requestBody để gửi dữ liệu lên server
        RequestBody requestBody = new FormBody.Builder()
                .add("a",String.valueOf(a))
                .add("b",String.valueOf(b))
                .add("c",String.valueOf(c))
                .build();
        //tạo request
        Request request = new Request.Builder()
                .url("http://192.168.1.6/API-L2-NWK/gptb2_POST.php")
                .post(requestBody)
                .build();
        //gửi request bất đồng bộ
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //xử lý phản hồi từ server
                final String result = response.body().string();

                //hiển thị kết qua trong TextView (phải được thực hiện trên luồng giao diện chính)
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
