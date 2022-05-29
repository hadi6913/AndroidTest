package com.example.androidtest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SendRecordHandler.Callbacks {
    private final static String TAG = "xeagle6913";


    Intent sendRecordHandlerIntent;
    SendRecordHandler sendRecordHandler;
    private ServiceConnection sendRecordHandlerConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            SendRecordHandler.LocalBinder binder = (SendRecordHandler.LocalBinder) service;
            sendRecordHandler = binder.getServiceInstance(); //Get instance of your service!
            sendRecordHandler.registerClient(MainActivity.this); //Activity register in the service as client for callabcks!
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
        }
    };





    Button btnSeccondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnSeccondActivity = findViewById(R.id.btn_seccond_activity);
        btnSeccondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SeccodActivity.class);
                startActivity(intent);
            }
        });




        sendRecordHandlerIntent = new Intent(getApplicationContext(), SendRecordHandler.class);
        Log.d(TAG, "onCreate: MainActivity >>>> Service has been started");
        startService(sendRecordHandlerIntent);
        getApplicationContext().bindService(sendRecordHandlerIntent, sendRecordHandlerConnection, Context.BIND_AUTO_CREATE);


    }

    @Override
    public void sendRecordServiceResult(String message) {
        Log.d(TAG, "sendRecordServiceResult MainActivity >>> message : "+message);
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onCreate: MainActivity >>>> Service has been stoped");
        stopService(sendRecordHandlerIntent);
        getApplicationContext().unbindService( sendRecordHandlerConnection);


    }


}