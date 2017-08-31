package com.tando.mba01;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestAppointment extends AppCompatActivity {
    //Declare button and textview
    Button buttonRequest;
    TextView textDates;
    //string for server url https://cse455.tk/~mbapassport/dates.php
    String server_url = "https://mobileapps.dev.csusb.edu/mba/AvailableDate.php";
    //back to homepage logo
    public void backHome (View home) {
        startActivity(new Intent(this, MainActivity.class));
    }
    //declare textedits
    Button buttonSubmit;
    EditText FirstName, LastName, StudentID, CoyoteEmail, Date;
    //php server https://cse455.tk/~mbapassport/appointment.php
    String server_url02 = "https://mobileapps.dev.csusb.edu/mba/appointment.php";
    //Declare string response from json file
    private String JsonResponse;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_appointment);
        //Initialize button
        buttonRequest = (Button) findViewById(R.id.bn01);
        //Initialize TextView
        textDates = (TextView) findViewById(R.id.dates);
        //buttonRequest function
        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Request queue using Volley library
                final RequestQueue requestQueue01 = Volley.newRequestQueue(RequestAppointment.this);

                //String request from server
                JsonArrayRequest arrayRequest01 = new JsonArrayRequest(server_url,
                        //Response listener
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                //get response into textView
                                //textDates.setText(response);
                                //Stop response after receiving
                                try {
                                    // Parsing json array response
                                    // loop through each json object
                                    JsonResponse = "";
                                    for (int i = 0; i < response.length(); i++) {
                                        //parsing all json objects in 1 string named person
                                        JSONObject person = (JSONObject) response.get(i);
                                        //make string name and date to store the json files
                                        String name = person.getString("Name");
                                        String date = person.getString("Date");

                                        //set the string
                                        JsonResponse += "Name: " + name + "\n";
                                        JsonResponse += "Date: " + date + "\n\n";

                                    }
                                    //parsing the string into textview
                                    textDates.setText(JsonResponse);

                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }
                                requestQueue01.stop();
                            }
                            //Error Listener to check for errors
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Display error on TextView
                        textDates.setText("Not Found!");
                        error.printStackTrace();
                        requestQueue01.stop();
                    }
                });
                //add request string to request queue
                requestQueue01.add(arrayRequest01);
            }
        });
        //End buttonRequest function
        //Start submit appointment
        buttonSubmit = (Button) findViewById(R.id.bn02);
        FirstName = (EditText) findViewById(R.id.text01);
        LastName = (EditText) findViewById(R.id.text02);
        StudentID =(EditText) findViewById(R.id.text03);
        CoyoteEmail = (EditText) findViewById(R.id.text04);
        Date = (EditText) findViewById(R.id.text05);

        builder = new AlertDialog.Builder(RequestAppointment.this);
        //set function for the button bn02 buttonSubmit
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hide virtual keyboard after click the button
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                //Declare strings of data info
                final String firstName, lastName, studentID, coyoteEmail, date;
                //covert inputs to string
                firstName = FirstName.getText().toString();
                lastName = LastName.getText().toString();
                studentID = StudentID.getText().toString();
                coyoteEmail = CoyoteEmail.getText().toString();
                date = Date.getText().toString();
                //No empty fields
                if (firstName.equals("") || lastName.equals("") || studentID.equals("")
                        || coyoteEmail.equals("") || date.equals("")) {
                    builder.setTitle("Error!!!");
                    //Creating a AlertDialog to display errors
                    AlertDialog alertDialog01 = builder.create();
                    alertDialog01.setMessage("Please Enter All Required Fields*");
                    alertDialog01.show();
                } else {
                    //request with POST method and string
                    StringRequest stringRequest02 = new StringRequest(Request.Method.POST, server_url02,
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
                                            Date.setText("");
                                        }
                                    });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(RequestAppointment.this, "Error!!!", Toast.LENGTH_SHORT).show();
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
                            params.put("DATE", date);
                            return params;
                        }
                    };
                    MySingleton.getInstance(RequestAppointment.this).addTorequestqueue(stringRequest02);
                }
            }
        }); //end of button submit fucntion
    }

}
