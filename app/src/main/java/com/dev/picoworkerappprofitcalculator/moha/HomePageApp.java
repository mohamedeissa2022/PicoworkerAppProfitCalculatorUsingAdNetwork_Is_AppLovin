package com.dev.picoworkerappprofitcalculator.moha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.model.ReviewErrorCode;
import com.google.android.play.core.review.testing.FakeReviewManager;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.ironsource.mediationsdk.IronSource;
import com.startapp.sdk.adsbase.StartAppAd;

public class HomePageApp extends AppCompatActivity {
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;


public data firebaseGetData ;
    private Bundle data = new Bundle();
    public static String rateNow;
    private static boolean activeData = false;
    private static String ad_banner_config_firebase = "test";
    private static String ad_inters_config_firebase = "test";
    private final static String TAG = HomePageApp.ACTIVITY_SERVICE;
    Button btn;
    private int cunt = 0;
    EditText et_hour, et_earn_par_job, et_jobs;
    private static startAppAdsManger startAppAdsManger;
    private static ReviewInfo reviewInfo;
    private static ReviewManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_app);
        startAppAdsManger = new startAppAdsManger(this);


        et_hour = findViewById(R.id.et_hour);
        et_earn_par_job = findViewById(R.id.et_earn_par_job);
        et_jobs = findViewById(R.id.et_jobs);
        String myname = Names();
        Log.d("name:", myname);
        mAdView = findViewById(R.id.ads);
        ad_Banner(true, getString(R.string.admobBannerAds));
        btn = findViewById(R.id.btn_calculator);
        config(this);
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

                }
                alert(HomePageApp.this);

                try {
                    if (cunt == 5) {
                        // ايقاف الاعلانات لحد مينتهي الليميت بتاع admob
                        //  boolean ad = ad_Interstitial(false, getString(R.string.admobIntersAds));

                    } else {

                        //   boolean ad = ad_Interstitial(true, getString(R.string.admobIntersAds));
                        cunt++;

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


                // boolean ad = ad_Interstitial(true, getString(R.string.admobIntersAds));


                dialog.dismiss();

            }
        });
        dialog.show();
    }


//   public static void alertRatingBar(Context context) {
//
//        View alertCustomdialog = LayoutInflater.from(context).inflate(R.layout.alert_rating_bar, null);
//        AlertDialog.Builder alert = new AlertDialog.Builder(context);
//        alert.setView(alertCustomdialog);
//
//
//        RatingBar ratingBar = alertCustomdialog.findViewById(R.id.rb_rate_app);
//
//
//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
//
//                float rate = ratingBar.getRating();
//                rateNow = String.valueOf(rate);
//
//
//            }
//        });
//
//
//        TextView my_be_later = alertCustomdialog.findViewById(R.id.tv_my_be_later);
//
//
//        Button btn = alertCustomdialog.findViewById(R.id.btn_rate_now);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // go to google play
//                Toast.makeText(context, "rate is :- " + rateNow, Toast.LENGTH_SHORT).show();
//                Intent i = new Intent();
//                i.setAction(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(""));
//
//            }
//        });
//
//
//        final AlertDialog dialog = alert.create();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        my_be_later.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                dialog.dismiss();
//
//            }
//        });
//        dialog.show();
//
//
//    }


    protected static void config(Context context) {

        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);
        activeData = mFirebaseRemoteConfig.getBoolean("active");


        ad_banner_config_firebase = mFirebaseRemoteConfig.getString("ad_banner");
        ad_inters_config_firebase = mFirebaseRemoteConfig.getString("ad_inters");


        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener((Activity) context, new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            activeData = task.getResult();
                            ad_banner_config_firebase = task.getResult().toString();
                            ad_inters_config_firebase = task.getResult().toString();
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("active", activeData);
                            Log.d("adBannner", ad_banner_config_firebase);
                            Log.d("adInters", ad_inters_config_firebase);
                            editor.apply();
                            Log.d(TAG, "Config params updated: " + activeData);
                            Log.d("adBanners", "Config Banner: " + ad_banner_config_firebase);

                        } else {


                        }

                    }
                });


    }


    protected void onResume() {
        super.onResume();
        config(this);
        firebaseGetData.dataSet(this);
    }

    protected void onPause() {
        super.onPause();
        config(this);
        firebaseGetData.dataSet(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        config(this);
        firebaseGetData.dataSet(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        config(this);
        firebaseGetData.dataSet(this);
    }

    public static String Names() {
        String names = "Mohamed";
        return String.format("name is :- %s", names);
    }


}