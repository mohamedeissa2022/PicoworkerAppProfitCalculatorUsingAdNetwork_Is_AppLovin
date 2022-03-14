package com.dev.picoworkerappprofitcalculator.moha;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinSdkUtils;

import java.util.concurrent.TimeUnit;

public class ApplovinListnerAds implements MaxAdListener, MaxRewardedAdListener {
    public int retryAttempt;
    public Activity activity;
    private MaxInterstitialAd interstitialAd;
    private MaxRewardedAd rewardedAd;
    private MaxAdView adView;

    // key in network ads ( ads_intrs_app_pico_Worker_App_2022  = 3fd5e1eefc428b34)
    private final String AD_UNIT_KEY_interstitialAD = "3fd5e1eefc428b34";

    // key in network ads ( ads_Reword_app_pico_Worker_App_2022 =  dbba70ea75b1e62b)
    private final String AD_UNIT_KEY_REWORD_AD = "dbba70ea75b1e62b";

    //key in network ads ( ads_Banner_app_pico_Worker_App_2022 =  b7bee11e44bb15fa)
    private final String AD_UNIT_KEY_Banner = "b7bee11e44bb15fa";


    public ApplovinListnerAds(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void onAdLoaded(MaxAd ad) {
        retryAttempt = 0;

    }

    @Override
    public void onAdDisplayed(MaxAd ad) {

    }

    @Override
    public void onAdHidden(MaxAd ad) {
        // Interstitial ad is hidden. Pre-load the next ad
        interstitialAd.loadAd();
        rewardedAd.loadAd();

    }

    @Override
    public void onAdClicked(MaxAd ad) {

    }

    @Override
    public void onAdLoadFailed(String adUnitId, MaxError error) {

        retryAttempt++;
        long delayMillis = TimeUnit.SECONDS.toMillis((long) Math.pow(2, Math.min(6, retryAttempt)));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                interstitialAd.loadAd();
                rewardedAd.loadAd();

            }
        }, delayMillis);
    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, MaxError error) {
        // Interstitial ad failed to display. AppLovin recommends that you load the next ad.
        interstitialAd.loadAd();
        rewardedAd.loadAd();

    }


    public void createInterstitialAd() {
        interstitialAd = new MaxInterstitialAd(AD_UNIT_KEY_interstitialAD, activity);
        interstitialAd.setListener(this);

        // Load the first ad
        interstitialAd.loadAd();
    }

    public void showAd() {
        if (interstitialAd.isReady()) {

            interstitialAd.showAd();
            Log.d("ads_33333:", "ad_is_showing _ interstitialAd _");

        }
    }

    @Override
    public void onRewardedVideoStarted(MaxAd ad) {
        String data = ad.getAdUnitId();
        Log.d("ad_Unit_Id", data);
    }

    @Override
    public void onRewardedVideoCompleted(MaxAd ad) {
        String data = ad.getNetworkName();
        Log.d("getNetworkName", data);
    }

    @Override
    public void onUserRewarded(MaxAd ad, MaxReward reward) {

    }

    void createRewardedAd() {
        rewardedAd = MaxRewardedAd.getInstance(AD_UNIT_KEY_REWORD_AD, activity);
        rewardedAd.setListener(this);
        rewardedAd.loadAd();
    }

    void showRewardedVideo() {
        if (rewardedAd.isReady()) {
            rewardedAd.showAd();
            Log.d("ads_33333:", "ad_is_showing _ rewardedAd _");

        }
    }


    void createMrecAd()
    {
        adView = new MaxAdView( AD_UNIT_KEY_Banner, MaxAdFormat.BANNER, activity );
        adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {
                  retryAttempt=0;
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {
                adView.loadAd();
            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                retryAttempt++;
                long delayMillis = TimeUnit.SECONDS.toMillis((long) Math.pow(2, Math.min(6, retryAttempt)));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adView.loadAd();
                    }
                }, delayMillis);
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });

        // MREC width and height are 300 and 250 respectively, on phones and tablets
      //  int widthPx = AppLovinSdkUtils.dpToPx( activity, 300 );
       // int heightPx = AppLovinSdkUtils.dpToPx( activity, 50 );

       // adView.setLayoutParams( new FrameLayout.LayoutParams( widthPx, heightPx ) );

        // Set background or background color for MRECs to be fully functional



        ViewGroup rootView = activity.findViewById( R.id.bannerContainer );
        rootView.addView( adView );

        // Load the ad
        adView.loadAd();
    }


}
