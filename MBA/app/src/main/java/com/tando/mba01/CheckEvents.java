package com.tando.mba01;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CheckEvents extends AppCompatActivity {
    TextView txtName;
    Button login_button01;
    EditText StudentID;
    String studentID;
    String login_url01 = "https://mobileapps.dev.csusb.edu/mba/check_events.php";
    //Variable for Alert Dialog
    AlertDialog.Builder builder;
    //adapter to store the ListView
    ArrayAdapter<String> adapter;
    //items inside the array
    ArrayList<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_events);

        ListView listView=(ListView)findViewById(R.id.listv);
        items=new ArrayList<String>();
        //dates=new ArrayList<String>();
        adapter=new ArrayAdapter(this, R.layout.item_layout,R.id.txt, items);
        //adapter=new ArrayAdapter(this, R.layout.item_layout,R.id.txtDate, dates);
        listView.setAdapter(adapter);
        //listView.setAdapter(adapter2);
        //name of the activity of the dialog
        builder = new AlertDialog.Builder(CheckEvents.this);

        login_button01 = (Button) findViewById(R.id.button_id);
        StudentID = (EditText) findViewById(R.id.login_id);

        login_button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hide virtual keyboard after click the button
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                //validation for username and password. Ensure corrected input

                studentID = StudentID.getText().toString();

                if(studentID.equals(""))
                {
                    builder.setTitle("StudentID not found");
                    displayAlert("Enter a valid StudentID");
                }
                //validate data from server
                else
                {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url01,
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {

                                    //for (int i = 0; i < jsonArray.length(); i++) {
                                    try {

                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                                        //"code" is the key from json object on server
                                        String code = jsonObject.getString("code");

                                        if (code.equals("login_failed")) {
                                            builder.setTitle("Login Error");
                                            displayAlert(jsonObject.getString("message"));
                                            //builder.setMessage("Response" + response);

                                        }
                                        //login successfully
                                        else {
                                                /*Toast.makeText(Check_Events.this,"Yay!!!", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(Check_Events.this, Events.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("Events", jsonObject.getString("Events"));
                                                bundle.putString("Date", jsonObject.getString("Date"));
                                                intent.putExtras(bundle);
                                                startActivity(intent);*/
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                jsonObject = jsonArray.getJSONObject(i);

                                                items.add(jsonObject.getString("Events"));
                                                //display the name of the person
                                                        /*for (int j = 0; j < jsonArray.length(); j++) {
                                                            jsonObject = jsonArray.getJSONObject(j);
                                                            dates.add(jsonObject.getString("Date"));
                                                        }*/
                                            }

                                            txtName = (TextView) findViewById(R.id.txtName);
                                            txtName.setText("Hello" + " " + jsonObject.getString("Name") +"."+
                                                    " " + "Events You Have Attended:");



                                        }

                                    } catch(JSONException e){
                                        e.printStackTrace();
                                    }

                                    adapter.notifyDataSetChanged();


                                }

                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(CheckEvents.this, "Error login",Toast.LENGTH_LONG);
                            error.printStackTrace();
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            //"username" is the key name from mySQL server
                            params.put("StudentID", studentID);
                            return params;
                        }
                    };

                    MySingleton.getInstance(CheckEvents.this).addTorequestqueue(stringRequest);

                }

            }

        });
    }
    public void displayAlert(String message){
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //clear username and password fields
                StudentID.setText("");
            }
        });
        //display Alert
        //First create AlertDialog variable then display it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Exit button
    public void bnt_logout(View v) {
        startActivity(new Intent(this, Passport.class));
    }
}
