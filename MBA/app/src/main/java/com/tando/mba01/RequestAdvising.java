package com.tando.mba01;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class RequestAdvising extends AppCompatActivity {

    //Back to homepage logo
    public void backHome (View home) {
        startActivity(new Intent(this, MainActivity.class));
    }

    //Declare variables
    Button bntfunc;
    EditText FirstName, LastName, StudentID, CoyoteEmail, Messages;
    //php server  "https://cse455.tk/~mbapassport/advising.php"
    String server_url ="https://mobileapps.dev.csusb.edu/mba/advising.php";

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_advising);
        bntfunc = (Button) findViewById(R.id.bnt01);
        FirstName = (EditText) findViewById(R.id.text01);
        LastName = (EditText) findViewById(R.id.text02);
        StudentID =(EditText) findViewById(R.id.text03);
        CoyoteEmail = (EditText) findViewById(R.id.text04);
        Messages = (EditText) findViewById(R.id.text05);

        builder = new AlertDialog.Builder(RequestAdvising.this);

        //set function for the button btn01
        bntfunc.setOnClickListener(new View.OnClickListener() {
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
                                    builder.setMessage("Congratulation" + " " + response);
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
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(RequestAdvising.this, "Error!!!", Toast.LENGTH_SHORT).show();
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
                    MySingleton.getInstance(RequestAdvising.this).addTorequestqueue(stringRequest);
                }
            }
        }); //end of submit function
    }//end of sending data methods
}
