package com.tando.mba01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Passport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport);
    }
    //link to homepage
    public void backHome (View home) {
        startActivity(new Intent(this, MainActivity.class));
    }
    //link to MBAlearninggoals
    public void learnGoals (View view01) {
        startActivity(new Intent(this, MBAlearninggoals.class));
    }
    //link to MBA Focus Areas activity
    public void CBPA (View viewFocusAreas) {
        startActivity(new Intent(this, MBAFocusAreas.class));
    }
    //link to Virtual Passport
    public void bntVirtualPP (View viewPP) {
        startActivity(new Intent(this, VirtualPassport.class));
    }
    //link to CheckEvents
    public void bnt_Events (View view) {
        startActivity(new Intent(this, CheckEvents.class));
    }
    //link to Facebook page
    public void fbFunc (View viewFb) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/cbpamba")));
    }
    //link to Instagram page
    public void igFunc (View viewIg) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/mba_csusb/")));
    }
    //link to Linkedin page
    public void lnFunc (View viewLn) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/mbacsusb/")));
    }
    //link to Twitter page
    public void twFunc (View viewTw) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/MBACSUSB")));
    }
}
