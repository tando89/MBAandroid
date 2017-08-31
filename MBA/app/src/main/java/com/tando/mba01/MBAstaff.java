package com.tando.mba01;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MBAstaff extends AppCompatActivity {
    //Declare some ImageView variables
    ImageView drSeal, msDeborah, msKasandra;
    //Declare AlerDialog builder variable
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbastaff);
        //Cast the variables
        builder = new AlertDialog.Builder(MBAstaff.this);
        drSeal = (ImageView) findViewById(R.id.imageView4);
        msDeborah = (ImageView) findViewById(R.id.imageView02);
        msKasandra = (ImageView) findViewById(R.id.imageView3);

        //Dialog for image01
        drSeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Dr. Craig Seal");
                final AlertDialog alertDialog01 = builder.create();
                alertDialog01.setMessage("Welcome to your Graduate Programs,\n" +
                        "\n" +
                        "Where we work together (students, faculty, and staff) to provide an innovative, student-centered learning community; that integrates administrative theory with real world engagement; to make an impact in our lives and the lives of those around us.");
                alertDialog01.setButton(Dialog.BUTTON_NEGATIVE, "Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog01.show();
            }
        }); //End Dialog01
        //Dialog for image02
        msDeborah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Deborah Grijalva");
                final AlertDialog alertDialog02 = builder.create();
                alertDialog02.setMessage("Greetings Prospective and Current Students,\n" +
                        "\n" +
                        "As the MBA Graduate Coordinator I’m here to assist you in many different ways to ensure your success in your program of study. " +
                        "My purpose is to provide you with advisement services on a range of academic and related issues. Additionally, I serve as a liaison between you as a student with faculty and/or administration as necessary. One important responsibility of mine is that I ensure that all university policies and standards are met in relation to grades, graduation requirements, admissions, withdrawals, and associated issues.");
                alertDialog02.setButton(Dialog.BUTTON_NEGATIVE, "Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog02.show();
            }
        }); //End Dialog02
        //Dialog for image03
        msKasandra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Kasandra Adams");
                final AlertDialog alertDialog03 = builder.create();
                alertDialog03.setMessage("Hello FUTURE MBAs!\n" +
                        "\n" +
                        "I chose to pursue my MBA here at CSUSB. Now, as an alumni, I am here to help you decide if that same decision, is right for YOU! Please reach out to me with any questions or concerns you have regarding the MBA program.");
                alertDialog03.setButton(Dialog.BUTTON_NEGATIVE, "Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog03.show();
            }
        }); //End Dialog03
    }

    public void backHome (View home) {
        startActivity(new Intent(this, MainActivity.class));
    }


}
