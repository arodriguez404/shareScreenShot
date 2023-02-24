package com.awesomeproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.*;

public class MyModule extends ReactContextBaseJavaModule {
    private Context context;

    public MyModule(ReactApplicationContext context) {
        super(context);
        this.context = context;
    }
    @ReactMethod
    public void screenShot(Promise promise) {
    try {
        ReactContext reactContext = getReactApplicationContext();
        Activity activity = reactContext.getCurrentActivity();

        if (activity == null) {
            promise.reject(new Exception("Activity is null"));
            return ;
        }

        View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.setDrawingCacheEnabled(true);

        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);

        String pathOfBmp = MediaStore.Images.Media.insertImage(activity.getContentResolver(), bitmap, "title", null);
        Uri uri = Uri.parse(pathOfBmp);

        shareImage(activity, uri);
    } catch (Exception e) {
        promise.reject(e);
    }
}


   
@ReactMethod
    
    private void storeBitmap(Bitmap bitmap) {
        Activity activity = getCurrentActivity();
        String pathOfBmp = MediaStore.Images.Media.insertImage(activity.getContentResolver(), bitmap, "title", null);
        Uri uri = Uri.parse(pathOfBmp);

        shareImage(activity, uri);
    }
@ReactMethod
    private void shareImage(Activity activity, Uri uri) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "");
        shareIntent.putExtra(Intent.EXTRA_TITLE, "");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);

        activity.startActivity(Intent.createChooser(shareIntent, null));
    }

    @NonNull
    @Override
    public String getName() {
        return "MyModule";
    }
}
