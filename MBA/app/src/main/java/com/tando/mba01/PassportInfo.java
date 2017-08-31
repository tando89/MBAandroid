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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PassportInfo extends AppCompatActivity {



    //Declare variables
    Button bntfunc;
    EditText Date, Name, StudentID, Events;
    String server_url = "https://mobileapps.dev.csusb.edu/mba/PassportUsersData.php";

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport_info);

        bntfunc = (Button) findViewById(R.id.bnt_passport);
        Name = (EditText) findViewById(R.id.Name);
        StudentID = (EditText) findViewById(R.id.StudentID);
        Date = (EditText) findViewById(R.id.Date);
        Events = (EditText) findViewById(R.id.submit_Events);

        builder = new AlertDialog.Builder(PassportInfo.this);


        bntfunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hide virtual keyboard after click the button
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                final String date, name, studentID, events;
                //covert inputs to string
                date = Date.getText().toString();
                name = Name.getText().toString();
                studentID = StudentID.getText().toString();
                events = Events.getText().toString();
                //No empty fields
                if (date.equals("") || name.equals("") || studentID.equals("") || events.equals("")) {
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
                                            Date.setText("");
                                            Name.setText("");
                                            StudentID.setText("");
                                            Events.setText("");
                                        }
                                    });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(PassportInfo.this, "Error!!!", Toast.LENGTH_SHORT).show();
                                    error.printStackTrace();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            //send data to mySQL server
                            //the keys must be same as field names in mySQL server
                            params.put("Date", date);
                            params.put("Name", name);
                            params.put("StudentID", studentID);
                            params.put("Events", events);

                            return params;
                        }
                    };
                    MySingleton.getInstance(PassportInfo.this).addTorequestqueue(stringRequest);
                }
            }
        }); //end of submit function

    }//end of sending data methods

    //Exit button
    public void func_exit (View v) {
        startActivity(new Intent(this, Passport.class));
    }
}
