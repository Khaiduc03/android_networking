package com.example.android_networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.net.URL;

public class AsyncTaskBai3 extends android.os.AsyncTask<String,Void, Bitmap> {

    Listener mlilistener; //khai báo sử dụng giao diện
    ProgressDialog progressDialog; //sử dụng tiến trình

    public AsyncTaskBai3(Listener listener, Context context) {
        this.mlilistener = listener; //khởi tạo listener
        this.progressDialog = new ProgressDialog(context); //khởi tạo tiến trình
    }

    //chuẩn bị thực hiện
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Start download...");
        progressDialog.show();
    }

    //thực hiện
    Bitmap bitmap = null;
    @Override
    protected Bitmap doInBackground(String... strings) {
        URL url;
        try {
            url = new URL(strings[0]);//lấy về đường dẫn ảnh
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

    //sau thực hiện
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (progressDialog.isShowing()){//nếu đang hiển thị
            progressDialog.dismiss();//tắt hiển thị
        }
        if (bitmap != null){//nếu ảnh khác null
            mlilistener.onImageDownload(bitmap);//đưa lên
        }else {
            mlilistener.onError();//nếu không, đưa ra thông báo lỗi
        }
    }

}
