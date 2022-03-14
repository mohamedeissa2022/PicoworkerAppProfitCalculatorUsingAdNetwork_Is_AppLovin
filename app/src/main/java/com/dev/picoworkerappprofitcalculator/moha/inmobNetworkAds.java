package com.dev.picoworkerappprofitcalculator.moha;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.inmobi.sdk.InMobiSdk;
import com.inmobi.sdk.SdkInitializationListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class inmobNetworkAds {
    JSONObject consentObject = new JSONObject();
private final String TAG="inmob";
public inmobNetworkAds(){

}
   public  void start(Context context) {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Toast.makeText(context, "Goooos", Toast.LENGTH_SHORT).show();            }
        }, 2000);







    }
    }












