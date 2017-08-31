package com.tando.mba01;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by tando on 5/13/17.
 */

public class HttpHandler {
    //returns the simple name of the underlying class, easier to track in the Android monitor
    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    public String makeServiceCall(String reqURL) {
        String response = null;
        try {
            //Creates a URL from the given String
            URL url = new URL(reqURL);
            //open the connection to the url object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //use GET method to get the JSON from server
            conn.setRequestMethod("GET");
            // read the response from server
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
            //some Exceptions
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }
    private String convertStreamToString(InputStream is) {
        //BufferedReader reads text from a character-input stream
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        //This constructs a string builder with no characters in it
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            //To convert the InputStream to String we use the BufferedReader.readLine()
            while ((line = reader.readLine()) != null) {
                //Each line will appended to a StringBuilder and returned as String.
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close when no more input data stream
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //return string
        return sb.toString();
    }
}
