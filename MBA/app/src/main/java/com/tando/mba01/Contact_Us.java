package com.tando.mba01;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Contact_Us extends AppCompatActivity {

    public void backHome (View home) {
        startActivity(new Intent(this, MainActivity.class));
    }

    Button button;
    EditText FirstName, LastName, StudentID, CoyoteEmail, Messages;
    //php server  "https://cse455.tk/~mbapassport/insert.php";
    String server_url = "https://mobileapps.dev.csusb.edu/mba/feedback.php";
            //"https://feedback-server-tand089.c9users.io/feedback.php";
    //https://feedback-server-tand089.c9users.io/feedbackEmail.php

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us01);
        button = (Button) findViewById(R.id.btn01);
        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        StudentID =(EditText) findViewById(R.id.StudentID);
        CoyoteEmail = (EditText) findViewById(R.id.CoyoteEmail);
        Messages = (EditText) findViewById(R.id.Messages);

        builder = new AlertDialog.Builder(Contact_Us.this);
        //set function for the button btn01
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Hide virtual keyboard after click the button
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                final String firstName, lastName, studentID, coyoteEmail, messages;
                //covert inputs to string
                firstName = FirstName.getText().toString();
                lastName = LastName.getText().toString();
                studentID = StudentID.getText().toString();
                coyoteEmail = CoyoteEmail.getText().toString();
                messages = Messages.getText().toString();
                //No empty fields
                if (firstName.equals("") || lastName.equals("") || studentID.equals("")
                        || coyoteEmail.equals("") || messages.equals("")) {
                    builder.setTitle("Error!!!");
                    //Creating a AlertDialog to display errors
                    AlertDialog alertDialog01 = builder.create();
                    alertDialog01.setMessage("Please Enter All Required Fields*");
                    alertDialog01.show();
                } else {
                    //request with POST method and string
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //get response form server to check if it is successfully submitted
                                    builder.setTitle("Server Response");
                                    builder.setMessage(response);
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //clear the text fields after submit
                                            FirstName.setText("");
                                            LastName.setText("");
                                            StudentID.setText("");
                                            CoyoteEmail.setText("");
                                            Messages.setText("");
                                        }
                                    });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }
                            }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Contact_Us.this, "Error!!!", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            //send data to mySQL server
                            //the keys must be same as field names in mySQL server
                            params.put("FIRSTNAME", firstName);
                            params.put("LASTNAME", lastName);
                            params.put("STUDENTID", studentID);
                            params.put("COYOTEEMAIL", coyoteEmail);
                            params.put("MESSAGES", messages);
                            return params;
                        }
                    };

                    MySingleton.getInstance(Contact_Us.this).addTorequestqueue(stringRequest);
                }
            }
        });
    }
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
