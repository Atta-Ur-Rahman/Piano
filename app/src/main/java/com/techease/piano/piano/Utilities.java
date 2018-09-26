package com.techease.piano.piano;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by AttaUrRahman on 5/7/2018.
 */

public class Utilities {

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static Button button;
    public static ImageView imageView;
    public static TextView textView;
    public static EditText editText;
    public static LinearLayout linearLayout;

    public static SharedPreferences.Editor putValueInEditor(Context context) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        return editor;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        //sharedPreferences = context.getSharedPreferences(Configuration.MY_PREF, 0);
        return context.getSharedPreferences(Configurations.MY_PREF, 0);
    }

    private static class Configurations {
        public static final String MY_PREF = null;
    }

/*
    public static Fragment connectFragment(Context context, Fragment fragment) {
        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("true").commit();
        return fragment;
    }

    public static Fragment withOutBackStackConnectFragment(Context context, Fragment fragment) {
        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        return fragment;
    }*/

    public static Button buttonDeclaration(int buttonID, View view) {
        button = (Button) view.findViewById(buttonID);
        return button;
    }

    public static ImageView imageViewDeclaration(int imageViewID, View view) {
        imageView = (ImageView) view.findViewById(imageViewID);
        return imageView;
    }

    public static TextView textViewDeclaration(int textViewID, View view) {
        textView = (TextView) view.findViewById(textViewID);

        return textView;
    }

    public static EditText editTextDeclaration(int edtihTextID, View view) {

        editText = (EditText) view.findViewById(edtihTextID);

        return editText;
    }


}
