package com.dev.picoworkerappprofitcalculator.moha;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;

public class adColony {
    public AdColonyInterstitial ads;


    private static  String ZONE_IDS ="vz8f3dde53ed66435596";

    public adColony() {
    }


    public void start() {
        AdColonyInterstitialListener listener = new AdColonyInterstitialListener() {
            @Override
            public void onRequestFilled(AdColonyInterstitial ad) {
                ad.show();
            }
        };
        AdColony.requestInterstitial(ZONE_IDS, listener);
    }



}
