package com.dev.picoworkerappprofitcalculator.moha;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.dev.picoworkerappprofitcalculator.moha.db.data;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class app extends Application {
    public data firebaseGetData ;

    @Override
    public void onCreate() {
        super.onCreate();
        firebaseGetData=new data(this);
    }

}
