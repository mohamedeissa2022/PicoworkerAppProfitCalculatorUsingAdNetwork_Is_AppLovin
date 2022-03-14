package com.dev.picoworkerappprofitcalculator.moha;

import android.app.Activity;
import android.util.Log;

import com.applovin.mediation.MaxAdListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;

public class AppLovinAdsNetwork  {

    public Activity activity;
    public AppLovinAdsNetwork(Activity activity){
        this.activity = activity;
    }



    public void Start_Loading_Ads_Network(){
        AppLovinSdk.getInstance( activity ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( activity, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                Log.d("StartNetwork_AppLoven","StartedNetWork>>>>>>>>>>>>>>>>>>>");
            }
        } );
    }


}
