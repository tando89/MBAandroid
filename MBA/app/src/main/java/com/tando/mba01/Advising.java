package com.tando.mba01;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Advising extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advising);
    }
    //back to homepage logo
    public void backHome (View home) {
        startActivity(new Intent(this, MainActivity.class));
    }
    //coordinatorInfo activity
    public void coordinatorInfo (View viewCoordinator) {
        startActivity(new Intent(this, CoordinatorInfo.class));
    }
    //MBA Focus Areas activity
    public void StartFocusAreas (View viewFocusAreas) {
        startActivity(new Intent(this, MBAFocusAreas.class));
    }
    //Request Appointment Activity
    public void startRequestAppoint (View viewAppointment) {
        startActivity(new Intent(this, RequestAppointment.class));
    }
    //GPA calculator
    public void StartGPACalc (View viewGPA) {
        startActivity(new Intent(this, GPAcalculator.class));
    }
    //Request Advising activity
    public void startRequestAdvising (View viewAdvising) {
        startActivity(new Intent(this, RequestAdvising.class));
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
