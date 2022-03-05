package com.dev.picoworkerappprofitcalculator.moha.db;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class data {
    public   String ad_banner;
    public   String ad_Interstitial;
    public   String app_id;
    public data(Context context){

}


public  void dataSet(Context context){
    FirebaseFirestore db = FirebaseFirestore.getInstance();

         String TAG =context.getPackageName() ;

    DocumentReference docRef = db.collection("ads").document("admob");


    docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {


            if (value.exists()){
                ad_banner=(String) value.get("ad_bannner");
                ad_Interstitial=(String) value.get("ad_inters");
                app_id=(String) value.get("app_id");
            }
        }
    });


//    docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//        @Override
//        public void onSuccess(DocumentSnapshot documentSnapshot) {
//          if (documentSnapshot.exists()){
//              ad_banner= (String) documentSnapshot.get("ad_banner");
//              ad_Interstitial= (String) documentSnapshot.get("ad_inters");
//              app_id= (String) documentSnapshot.get("app_id");
//              Toast.makeText(context, "data Geted :- "+ad_banner, Toast.LENGTH_SHORT).show();
//          }
//        }
//    }).addOnFailureListener(new OnFailureListener() {
//        @Override
//        public void onFailure(@NonNull Exception e) {
//         Log.d("errorrs:-",e.getLocalizedMessage());
//            Toast.makeText(context, "data Not :- "+e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    });
}






}
