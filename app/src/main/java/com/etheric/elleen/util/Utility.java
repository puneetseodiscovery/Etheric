package com.etheric.elleen.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;


import com.etheric.elleen.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class Utility {


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    /*public static boolean validEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static boolean validMobile(String mobileNo) {
        return Patterns.PHONE.matcher(mobileNo).matches();
    }

    // need to remove below method

    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        if (event.getAction() == MotionEvent.ACTION_UP) {
//            if (event.getRawX() >= (locationText.getLeft() - locationText.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
//                // your action here
//
//                return true;
//            }
        }
        return false;
    }

    // need to remove above method

    public static Typeface typeFaceForSemiBoldText(Context context)
    {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/bahnschrift_semibold.ttf");
    }*/

    public static Typeface typeFaceForBoldText(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(),
                "fonts/segoeuib.ttf");
    }

    public static Typeface typeFaceForText(Context context)
    {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/segoeui.ttf");
    }

    public static String message(Response response) {
        try {
            ResponseBody responseBody = response.errorBody();
            return responseBody == null ? response.message() : responseBody.string();
        } catch (IOException e) {
            return response.message();
        }
    }


    public static ProgressDialog showLoader(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Loading..");
        try {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        catch (Exception e)
        {
            e.fillInStackTrace();
        }
        progressDialog.setCancelable(false);
        progressDialog.show();
        progressDialog.setContentView(R.layout.layout_progress);
        return progressDialog;
    }
}
