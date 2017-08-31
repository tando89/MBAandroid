package com.tando.mba01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class AboutTheApp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_the_app);
    }
    //back to homepage when click the logo
    public void backHome (View home) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
