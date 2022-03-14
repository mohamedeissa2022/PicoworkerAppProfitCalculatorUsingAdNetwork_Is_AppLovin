package com.dev.picoworkerappprofitcalculator.moha;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adcolony.sdk.AdColony;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.dev.picoworkerappprofitcalculator.moha.db.data;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.BannerListener;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

public class HomePageApp extends AppCompatActivity {
    private static String APP_ID = "appcef2aa1328bb423e8b";
    private static String ZONE_IDS = "vz8f3dde53ed66435596";
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    public ads_mangers AdManager;
    public ClipboardManager clipboardManager;
    public data firebaseGetData;
    private Bundle data = new Bundle();
    public static String rateNow;
    private static boolean activeData = false;
    private static String ad_banner_config_firebase = "test";
    private static String ad_inters_config_firebase = "test";
    private final static String TAG = HomePageApp.ACTIVITY_SERVICE;
    private IronSourceBannerLayout mIronSourceBannerLayout;
    private MaxInterstitialAd interstitialAd;
    private int retryAttempt;
public StartAppAd startAppAd;

    Button btn;
    private int cunt = 0;
    EditText et_hour, et_earn_par_job, et_jobs;
    private static startAppAdsManger startAppAdsManger;
    public ActivityInfo activityInfo;
    public ApplovinListnerAds applovinListnerAds;

    public adColony adColony;

    public vungleAds vungleAds = new vungleAds(this);

    public static FrameLayout bannerContainer;
    public static String appKey = "135db5891";

    @RequiresApi(api = Build.VERSION_CODES.N)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_app);
        adColony = new adColony();
        AdColony.configure(this, APP_ID, ZONE_IDS);
        StartAppSDK.init(this, "201839901", true);


        vungleAds.appId = "6200e0dd884d4921d9e895f5";
        vungleAds.InterstitialAd();

        vungleAds.data();


        applovinListnerAds = new ApplovinListnerAds(this);
        bannerContainer = findViewById(R.id.bannerContainer);
        applovinListnerAds.createInterstitialAd();
        applovinListnerAds.createRewardedAd();
        applovinListnerAds.createMrecAd();


        AdManager = new ads_mangers(this);
        AdManager.init();
        // AdManager.AdBanner();

        //  Toast.makeText(this, "gr:-"+activityInfoGravity, Toast.LENGTH_SHORT).show();
        mIronSourceBannerLayout = IronSource.createBanner(this, ISBannerSize.BANNER);
        bannerContainer.addView(mIronSourceBannerLayout);
        mIronSourceBannerLayout.setBannerListener(new BannerListener() {
            @Override
            public void onBannerAdLoaded() {
            }

            @Override
            public void onBannerAdLoadFailed(IronSourceError ironSourceError) {

            }

            @Override
            public void onBannerAdClicked() {

            }

            @Override
            public void onBannerAdScreenPresented() {

            }

            @Override
            public void onBannerAdScreenDismissed() {

            }

            @Override
            public void onBannerAdLeftApplication() {

            }
        });
        IronSource.loadBanner(mIronSourceBannerLayout, "DefaultBanner");


        //  startAppAdsManger = new startAppAdsManger(this);


        // set the interstitial listener


        et_hour = findViewById(R.id.et_hour);
        et_earn_par_job = findViewById(R.id.et_earn_par_job);
        et_jobs = findViewById(R.id.et_jobs);
        String myname = Names();
        Log.d("name:", myname);
        mAdView = findViewById(R.id.ads);
        //ad_Banner(false, getString(R.string.admobBannerAds));
        btn = findViewById(R.id.btn_calculator);
        //44 config(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_hour.getText().toString().isEmpty() &&
                        et_jobs.getText().toString().isEmpty() &&
                        et_earn_par_job.getText().toString().isEmpty()) {
                    try {
                        Toast.makeText(HomePageApp.this, "Please fill in the empty fields", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.d(TAG, e.getLocalizedMessage());
                    }
                } else {
                    data.putString("hour", et_hour.getText().toString());
                    data.putString("eran_per_job", et_earn_par_job.getText().toString());
                    data.putString("jobsNumbers", et_jobs.getText().toString());
                    // AdManager.InterstitialAdIronSource();
                    //  applovinListnerAds.showAd();
                   // adColony.start();
                }
                alert(HomePageApp.this);

                try {
                    if (cunt == 3) {
                        applovinListnerAds.showRewardedVideo();
                        // ايقاف الاعلانات لحد مينتهي الليميت بتاع admob
                        boolean ad = ad_Interstitial(false, getString(R.string.admobIntersAds2_mmedm1915));
                        cunt=0;
                    } else {
                       // applovinListnerAds.showAd();

                           boolean ad = ad_Interstitial(true, getString(R.string.admobIntersAds2_mmedm1915));
                        cunt++;
                       // StartAppAd.showAd(HomePageApp.this);

                    }

                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });


    }


    protected boolean ad_Banner(boolean isActiveAd, String adId) {
        if (isActiveAd) {
            AdView adView = new AdView(this);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(adId);
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                }
            });
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError adError) {

                }

                @Override
                public void onAdOpened() {


                }

                @Override
                public void onAdClicked() {

                }

                @Override
                public void onAdClosed() {

                }
            });
        } else {
            return false;
        }

        return isActiveAd;
    }

    protected boolean ad_Interstitial(boolean isActiveAd, String adId) {
        if (isActiveAd) {
            AdRequest adRequest = new AdRequest.Builder().build();

            InterstitialAd.load(this, adId, adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            mInterstitialAd = interstitialAd;
                            Log.i(TAG, "onAdLoaded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                            Log.i(TAG, loadAdError.getMessage());


                            mInterstitialAd = null;
                        }
                    });
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when fullscreen content is dismissed.
                    Log.d(TAG, "The ad was dismissed.");

                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when fullscreen content failed to show.
                    Log.d(TAG, "The ad failed to show.");

                }

                @Override
                public void onAdShowedFullScreenContent() {
                    mInterstitialAd = null;


                    Log.d(TAG, "The ad was shown.");

                }
            });

        }

        if (mInterstitialAd != null) {

            mInterstitialAd.show(HomePageApp.this);

        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
        return isActiveAd;
    }


    @SuppressLint("SetTextI18n")
    public void alert(Context context) {

        float hour = Float.parseFloat(data.getString("hour"));
        float jobsNumbers = Float.parseFloat(data.getString("jobsNumbers"));
        float profitPerJob = Float.parseFloat(data.getString("eran_per_job"));

        float result = profitPerJob * jobsNumbers;


        View alertCustomdialog = LayoutInflater.from(context).inflate(R.layout.alert_calculator, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(alertCustomdialog);
        TextView tv_title = alertCustomdialog.findViewById(R.id.tv_title);
        // String t = "Earning per " + data.getString("hour") + " hour";
        String t = getString(R.string.earning_per_hour) + data.getString("hour") + getString(R.string.hour_name);
        tv_title.setText(t);
        TextView tv_profit = alertCustomdialog.findViewById(R.id.tv_profit);
        tv_profit.setText(" $ " + result);
        Button btn = alertCustomdialog.findViewById(R.id.btn_close);
        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 boolean ad = ad_Interstitial(true, getString(R.string.admobIntersAds2_mmedm1915));

                //  iron_source_Ad_Interstitial_rewordVideo(getBaseContext());
                // AdManager.InterstitialRewordVideo();

              //  applovinListnerAds.showAd();
               // startAppAd.loadAd(StartAppAd.AdMode.REWARDED_VIDEO);


             //   adColony.start();
                dialog.dismiss();

            }
        });
        dialog.show();
    }


    public static String Names() {
        String names = "Mohamed";
        return String.format("name is :- %s", names);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  startAppAdsManger.startAppFullScreenVideoReword();


    }

    protected void onResume() {
        super.onResume();
//        config(this);
//        firebaseGetData.dataSet(this);
        IronSource.onResume(this);

        //startAppAdsManger.startAppFullScreenVideoReword();

    }

    protected void onPause() {
        super.onPause();
//        config(this);
//        firebaseGetData.dataSet(this);
        IronSource.onPause(this);

       // startAppAdsManger.startAppFullScreenVideoReword();

    }
    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }

}