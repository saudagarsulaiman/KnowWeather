package com.weather.knowweather.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.LocaleList;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.weather.knowweather.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public final class CommonUtilities {

    private static Locale myLocale;


    /********************* Live Server Links ****************************************************/

    public static final String URL = "https://api.openweathermap.org";


    //    ********************* Reusable Methods*******************************************************
    public static boolean isConnectionAvailable(Context ctx) {
        //boolean bConnection = false;
        ConnectivityManager connMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static void saveLocale(Context ctx, String lang) {
        String langPref = "Language";
        SharedPreferences prefs = ctx.getSharedPreferences(CONSTANTS.appName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public static String loadLocale(Context ctx) {
        String langPref = "Language";
        SharedPreferences prefs = ctx.getSharedPreferences(CONSTANTS.appName, Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "en");
        changeLang(ctx, language);
        return language;
    }

    public static void changeLang(Context ctx, String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(ctx, lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(myLocale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            config.setLocale(myLocale);
        } else {
            config.locale = myLocale;
            ctx.getResources().updateConfiguration(config, ctx.getResources().getDisplayMetrics());
        }
    }

    public static String convertToHumanReadable(long milliseconds) {
        Calendar today = Calendar.getInstance();
        Calendar postedDay = Calendar.getInstance();
        postedDay.setTimeInMillis(milliseconds);
        long day = 86400000L;
        long hour = 3600000L;
        long minute = 60000L;
        long month = 2628000000L;
        long year = 31536000000L;
        int time;

        if (today.getTimeInMillis() - postedDay.getTimeInMillis() > year) {
            time = Math.round((today.getTimeInMillis() - postedDay.getTimeInMillis()) / year);
            return time + (time == 1 ? "yr" : "yrs");
        } else if (today.getTimeInMillis() - postedDay.getTimeInMillis() > month) {
            time = Math.round((today.getTimeInMillis() - postedDay.getTimeInMillis()) / month);
            return time + (time == 1 ? "m" : "m");
        } else if (today.getTimeInMillis() - postedDay.getTimeInMillis() > day) {
            time = Math.round((today.getTimeInMillis() - postedDay.getTimeInMillis()) / day);
            return time + (time == 1 ? "d" : "d");
        } else if (today.getTimeInMillis() - postedDay.getTimeInMillis() > hour) {
            time = Math.round((today.getTimeInMillis() - postedDay.getTimeInMillis()) / hour);
            return time + (time == 1 ? "hr" : "hrs");
        } else {
            time = Math.round((today.getTimeInMillis() - postedDay.getTimeInMillis()) / minute);
            return time + (time == 1 ? "min" : "mins");
        }
    }


    public static void ShowToastMessage(Context mContext, String xMessage) {
        Toast mToast = Toast.makeText(mContext, xMessage, Toast.LENGTH_SHORT);
//        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

//    public static void customTost(Context context, String message) {
//        Cue.init()
//                .with(context)
//                .setMessage(message)
//                .setType(Type.PRIMARY)
//                .show();
//    }

    public static String getValueFromSP(Context ctx, String Key) {
        SharedPreferences sp = ctx.getSharedPreferences(CONSTANTS.appName, Context.MODE_PRIVATE);
        return sp.getString(Key, "");
    }


    //    QR Code Generator
    public static void qrCodeGenerate(String addressValue, ImageView imageView, Context context) {
        try {
            Bitmap bitmap = encodeAsBitmap(addressValue);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    static Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
            hintMap.put(EncodeHintType.MARGIN, new Integer(1));
            result = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 500, 500, hintMap);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, 500, 0, 0, w, h);
        return bitmap;
    }

    public static void copyToClipboard(Context context, String address, String str_coin_name) {
        int sdk = Build.VERSION.SDK_INT;
        if (sdk < Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(address);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText(str_coin_name + " address", address);
            clipboard.setPrimaryClip(clip);
        }
        CommonUtilities.ShowToastMessage(context, context.getResources().getString(R.string.copied));
    }

    public static void shareAddress(String str_data_address, Context context) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, str_data_address);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    public static String getDeviceID(Context context) {
        String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }

    public static String convertFtoC(String str_temp) {
        String result = "0";
        double res = 0.0, f = Double.parseDouble(str_temp);
        res = (f - 32) * 5 / 9;
        result = String.valueOf(res);
//        result = res + "";
        return result;
    }

    public static String covertMsToDate(long timestampInMilliSeconds) {
        timestampInMilliSeconds = timestampInMilliSeconds * 1000;
        Date date = new Date();
        date.setTime(timestampInMilliSeconds);
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
        return formattedDate;

    }
}


