package com.example.android_networking;

import android.graphics.Bitmap;

public interface Listener {
    void onImageDownload(Bitmap bitmap);
    void onError();
}
