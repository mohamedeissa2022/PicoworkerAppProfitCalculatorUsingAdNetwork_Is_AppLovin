package com.dev.picoworkerappprofitcalculator.moha;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;

public class vungleAds {
    public Context context;public String appId;
    public vungleAds(Context context) {this.context = context;
    }


    public void data(){

        Vungle.init(appId, context, new InitCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(VungleException exception) {

            }

            @Override
            public void onAutoCacheAdAvailable(String placementId) {

            }
        });


    }
    public  boolean isInitialized(String ad){
        if (Vungle.isInitialized()){


            Vungle.loadAd(ad, new LoadAdCallback() {
                @Override
                public void onAdLoad(String placementReferenceId) {

                }

                @Override
                public void onError(String placementReferenceId, VungleException exception) { }
            });











            Log.d("isInitialized","isInitialized++++++");
            return true;
        }
        return false;
    }
public void InterstitialAd(){
     final LoadAdCallback vungleLoadAdCallback = new LoadAdCallback() {
        @Override
        public void onAdLoad(String id) {
                Log.d("ad_load",id);

        }

        @Override
        public void onError(String id, VungleException exception) {
            Log.d("onError:-",id+" error:-\n"+exception.getMessage());
        }
    };

     final PlayAdCallback vunglePlayAdCallback = new PlayAdCallback() {
        @Override
        public void onAdStart(String id) {
            // Ad experience started
        }

         @Override
         public void onAdEnd(String placementId, boolean completed, boolean isCTAClicked) {

         }

         @Override
        public void onAdViewed(String id) {
            // Ad has rendered
        }

        @Override
        public void onAdEnd(String id) {
            // Ad experience ended
        }

        @Override
        public void onAdClick(String id) {
            // User clicked on ad
        }

        @Override
        public void onAdRewarded(String id) {
            // User earned reward for watching an rewarded ad
        }

        @Override
        public void onAdLeftApplication(String id) {
            // User has left app during an ad experience
        }

        @Override
        public void creativeId(String creativeId) {
            // Vungle creative ID to be displayed
        }

        @Override
        public void onError(String id, VungleException exception) {
            // Ad failed to play
        }
    };

}


}
