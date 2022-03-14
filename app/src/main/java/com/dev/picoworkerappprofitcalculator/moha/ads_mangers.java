package com.dev.picoworkerappprofitcalculator.moha;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.BannerListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;

public class ads_mangers {
    public Activity activity;
    public String appKey = "135db5891";
    public IronSourceBannerLayout mIronSourceBannerLayout;
    public  FrameLayout bannerContainer;
    public ads_mangers(Activity activity) {
        this.activity = activity;
    }
public void  init(){
    IronSource.init(activity, appKey, IronSource.AD_UNIT.BANNER, IronSource.AD_UNIT.INTERSTITIAL, IronSource.AD_UNIT.REWARDED_VIDEO);
}
    public void InterstitialAdIronSource() {
        IronSource.loadInterstitial();

        IronSource.setInterstitialListener(new InterstitialListener() {

            @Override
            public void onInterstitialAdReady() {
                IronSource.showInterstitial("DefaultInterstitial");
            }


            @Override
            public void onInterstitialAdLoadFailed(IronSourceError error) {
                IronSource.loadInterstitial();
                Log.d("Inters_error2022",error.getErrorMessage());

            }


            @Override
            public void onInterstitialAdOpened() {


            }

            /*
             * Invoked when the ad is closed and the user is about to return to the application.
             */
            @Override
            public void onInterstitialAdClosed() {


            }


            @Override
            public void onInterstitialAdShowFailed(IronSourceError error) {


            }


            @Override
            public void onInterstitialAdClicked() {


            }


            @Override
            public void onInterstitialAdShowSucceeded() {



            }
        });


    }

    public void InterstitialRewordVideo() {
        IronSource.loadRewardedVideo();

        IronSource.setRewardedVideoListener(new RewardedVideoListener() {
            @Override
            public void onRewardedVideoAdOpened() {
            }

            @Override
            public void onRewardedVideoAdClosed() {
            }

            @Override
            public void onRewardedVideoAvailabilityChanged(boolean available) {
            }

            @Override
            public void onRewardedVideoAdRewarded(Placement placement) {

            }

            @Override
            public void onRewardedVideoAdShowFailed(IronSourceError error) {
                Log.d("Inters_error2022111",error.getErrorMessage());

            }

            @Override
            public void onRewardedVideoAdClicked(Placement placement) {
            }

            @Override
            public void onRewardedVideoAdStarted() {
            }

            @Override
            public void onRewardedVideoAdEnded() {
            }
        });
        IronSource.showRewardedVideo("DefaultRewardedVideo");
    }
public void AdBanner(){


}

}
