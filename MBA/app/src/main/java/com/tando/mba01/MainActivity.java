package com.tando.mba01;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //button About Us function
    public void startChild (View view) {

        startActivity(new Intent(this, Page02.class));
    }
    //button Contact Us Function
    public void contactUs (View viewContactUs) {
        startActivity(new Intent(this, Contact_Us.class));
    }
    //button Passport Function
    public void passPort (View viewPassPort) {
        startActivity(new Intent(this, Passport.class));
    }
    //button Advising Function
    public void advising (View viewAdvising) {
        startActivity(new Intent(this, Advising.class));
    }
    //button Calendar
    public void calendar (View view) {
        startActivity(new Intent(this, Calendar.class));
    }
    //button MyCoyote Function
    public void StartMyCoyote (View viewMyCoyote) {
        startActivity(new Intent(this, MyCoyote.class));
    }
    //button Student Ambassadors
    public void StartStudentAmbassadors (View vSb) {
        startActivity(new Intent(this, StudentAmbassadors.class));
    }
    //button Graduate Assistantship
    public void StartGraduateAssistantship (View vGA) {
        startActivity(new Intent(this, GraduateAssistantship.class));
    }
    //back to homepage when click the logo
    public void backHome (View home) {
        startActivity(new Intent(this, MainActivity.class));
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

    //AboutTheApp
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settingsicon, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settingsIcon:
                Intent intent = new Intent(this, LoadSettingsFragment.class);
                startActivity(intent);
                //finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
