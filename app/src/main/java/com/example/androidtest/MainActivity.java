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
    public void showOnUi(String serialNumber ,  String block20, String block21,String block22 ) {

        Log.d(TAG, "showOnUi > serialNumber : "+serialNumber);
        Log.d(TAG, "showOnUi > block 20 : "+block20);
        Log.d(TAG, "showOnUi > block 21 : "+block21);
        Log.d(TAG, "showOnUi > block 22 : "+block22);



    }
}