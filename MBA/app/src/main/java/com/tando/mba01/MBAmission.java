package com.tando.mba01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MBAmission extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbamission);
    }
    //Back to the homepage when click CSUSB logo
    public void backHome (View home) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
