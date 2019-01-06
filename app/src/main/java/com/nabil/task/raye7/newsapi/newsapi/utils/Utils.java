package com.nabil.task.raye7.newsapi.newsapi.utils;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String formattedDate(String dateUTC) {
        DateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date sd = null;
        try {
            sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(dateUTC);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(sd);
        return formattedDate;
    }


    public static String truncateExtra(String content) {
        if (content == null)
            return "";
        return content.replaceAll("(\\[\\+\\d+ chars])", "");
    }

    public static boolean hasNetwork(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
