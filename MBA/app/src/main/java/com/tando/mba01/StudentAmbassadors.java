package com.tando.mba01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StudentAmbassadors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_ambassadors);
    }
    //Back to the homepage when click CSUSB logo
    public void backHome (View home) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
