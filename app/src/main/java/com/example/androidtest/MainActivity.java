package com.example.androidtest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements MifareCardHandler.Callbacks {

    private final static String TAG = "xeagle6913";

    private Intent mifareCardHandlerIntent;
    private MifareCardHandler mifareCardHandler;
    private ServiceConnection mifareCardHandlerConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            MifareCardHandler.LocalBinder binder = (MifareCardHandler.LocalBinder) service;
            mifareCardHandler = binder.getServiceInstance();
            mifareCardHandler.registerClient(MainActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mifareCardHandlerIntent = new Intent(getApplicationContext(), MifareCardHandler.class);
        startService(mifareCardHandlerIntent);
        getApplicationContext().bindService(mifareCardHandlerIntent, mifareCardHandlerConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void showOnUi(String serialNumber ,  String block8, String block9,String block10 , String block11) {

        Log.d(TAG, "showOnUi > serialNumber : "+serialNumber);
        Log.d(TAG, "showOnUi > block 8 : "+block8);
        Log.d(TAG, "showOnUi > block 9 : "+block9);
        Log.d(TAG, "showOnUi > block 10 : "+block10);
        Log.d(TAG, "showOnUi > block 11 : "+block11);


    }
}