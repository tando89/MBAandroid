package com.tando.mba01;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Page02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page02);
    }
    public void backHome (View home) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void starStaff (View view) {

        startActivity(new Intent(this, MBAstaff.class));
    }

    public void starMission (View view) {

        startActivity(new Intent(this, MBAmission.class));
    }

    public void starMBAgoals (View view) {

        startActivity(new Intent(this, MBAlearninggoals.class));
    }

    //Social Media Functions
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
