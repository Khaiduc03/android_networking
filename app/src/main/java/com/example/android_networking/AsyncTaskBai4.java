package com.example.android_networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class AsyncTaskBai4 extends AsyncTask<String, String, String> {

    private ProgressDialog dialog;
    TextView tvResult;
    TextInputEditText edtTime;
    Context context;
    String kq;

    public AsyncTaskBai4(TextView tvResult, TextInputEditText textInputEdtTime, Context context) {
        this.tvResult = tvResult;
        this.edtTime = textInputEdtTime;
        this.context = context;
    }

    //hàm chuẩn bị thực hiện
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Title", "Please wait "
                + edtTime.getText().toString() + " miliseconds");
    }

    // Hàm thực hiện
    @Override

    protected String doInBackground(String... strings) {
        publishProgress("Sleeping...");
        try {
            int time = Integer.parseInt(strings[0]) + 1000;
            Thread.sleep(time);
            kq = "Been asleep for " + strings[0] + " miliseconds";
        } catch (Exception e) {
            e.printStackTrace();
            kq = e.getMessage();
        }
        return kq;
    }

    //hàm sau thực hiện
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (dialog.isShowing()){
            dialog.dismiss();
        }
        tvResult.setText(s);
    }

}
