package com.dev.picoworkerappprofitcalculator.moha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.ironsource.mediationsdk.IronSource;

public class MainActivity extends AppCompatActivity {
    public static String appKey = "135db5891";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenTwo(this);
    }



    private void screenTwo(Context context){
        Thread tr =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    Intent go =new Intent(context,HomePageApp.class);


                    startActivity(go);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        tr.start();


    }



}