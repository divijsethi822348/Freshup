package com.example.freshup.Util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.Window;

import com.example.freshup.R;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class CommonUtils {

    private static Dialog progressDialog;

    public static void showProgress(Activity activity) {
        if (!(activity).isFinishing()) {
            if (progressDialog == null) {
                progressDialog = new Dialog(activity);
                progressDialog.setContentView(R.layout.progress_bar);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                Window dialogWindow = progressDialog.getWindow();
                dialogWindow.setGravity(Gravity.CENTER);
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
        }
    }

    public static void dismiss() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static boolean InternetCheck(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
    }
}