package com.tando.mba01;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Calendar extends AppCompatActivity {
    //returns the simple name of the underlying class, easier to track in the Android monitor
    private String TAG = Calendar.class.getSimpleName();
    //Prpgress bar while retrieving data
    private ProgressDialog pDialog;
    //Declare ListView
    private ListView calendarList;

    // URL to get calendar JSON
    private static String url ="https://mobileapps.dev.csusb.edu/mba/calendar.php";
    //Declare an array to store the list of items
    ArrayList<HashMap<String, String>> cList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        cList = new ArrayList<>();

        calendarList = (ListView) findViewById(R.id.calendarList);

        ////execute the GetEvents class
        new GetEvents().execute();

    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetEvents extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Calendar.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler httpHandler = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = httpHandler.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray calendarList = jsonObj.getJSONArray("Calendar");

                    // looping through All Events
                    for (int i = 0; i < calendarList.length(); i++) {
                        JSONObject c = calendarList.getJSONObject(i);
                        //get string from the json file
                        String Date = c.getString("Date");
                        String Note = c.getString("Note");


                        // tmp hash map for single event
                        HashMap<String, String> calendars = new HashMap<>();

                        // adding each child node to HashMap key => value
                        calendars.put("Date", Date);
                        calendars.put("Note", Note);


                        // adding contact to cList
                        cList.add(calendars);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } //End if
            else {
                Log.e(TAG, "Couldn't get json objects from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            return null;
        } //End doing background

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    Calendar.this, cList,
                    R.layout.customized_calendar_lv, new String[]{"Date", "Note"}, new int[]{R.id.cDate,
                    R.id.note});

            calendarList.setAdapter(adapter);

        }


    }//End AsyncTask

    //back to homepage when click the logo
        public void backHome (View home) {
            startActivity(new Intent(this, MainActivity.class));
        }
}


