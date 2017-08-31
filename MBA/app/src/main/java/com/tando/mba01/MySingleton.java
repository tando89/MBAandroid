package com.tando.mba01;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by tando on 2/14/17.
 */

public class MySingleton {
    private static  MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private MySingleton (Context context)
    {
        //Initialize context and request queue
        mCtx =context;
        requestQueue = getRequestQueue();
    }
    //synchronized method
    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance==null){
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }
    //method for request queue
    public RequestQueue getRequestQueue() {
        //check to see the requestQueue is initialized or not
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T>void addTorequestqueue(Request<T> request){
        requestQueue.add(request);
    }

}
