package com.dev.picoworkerappprofitcalculator.moha;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import com.startapp.sdk.adsbase.AutoInterstitialPreferences;
import com.startapp.sdk.adsbase.StartAppAd;

public class startAppAdsManger {
   @SuppressLint("StaticFieldLeak")
   public static Context montext;

    public startAppAdsManger(Context context) {
          montext=context;
    }

    public static StartAppAd startAppAd;

    public  void startAppFullScreenVideoReword(){
        startAppAd=new StartAppAd(montext);
        StartAppAd.setAutoInterstitialPreferences(
                new AutoInterstitialPreferences()
                        .setSecondsBetweenAds(60)
        );
        StartAppAd.setAutoInterstitialPreferences(
                new AutoInterstitialPreferences()
                        .setActivitiesBetweenAds(3)
        );
        startAppAd.loadAd(StartAppAd.AdMode.REWARDED_VIDEO);

    }
    public  void startAppFullScreenVideoInter(){
        startAppAd=new StartAppAd(montext);
        StartAppAd.setAutoInterstitialPreferences(
                new AutoInterstitialPreferences()
                        .setSecondsBetweenAds(60)
        );
        StartAppAd.setAutoInterstitialPreferences(
                new AutoInterstitialPreferences()
                        .setActivitiesBetweenAds(3)
        );
        StartAppAd.showAd(montext);
    }


}
