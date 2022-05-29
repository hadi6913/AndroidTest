package com.example.androidtest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;




public class SendRecordHandler extends Service implements Runnable {

private final static String TAG = "xeagle6913";
    private Thread runningThread;
    private boolean stopConds = false;

    private int sleep = 2 * 60 * 1000;



    Callbacks activity;
    MainActivity mainActivity;
    private final IBinder mBinder = new LocalBinder();
    private static SendRecordHandler instance = null;


    public static SendRecordHandler getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        runningThread = new Thread(this);
        runningThread.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        stopConds = true;
        Log.d(TAG, "onDestroy > SendRecordHandler has been stopped");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void registerClient(MainActivity mainActivity) {
        this.activity = (Callbacks) mainActivity;
        this.mainActivity = mainActivity;
    }

    public class LocalBinder extends Binder {
        public SendRecordHandler getServiceInstance() {
            return SendRecordHandler.this;
        }
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(9);
        while (!stopConds) {
            try {

                Log.d(TAG, "run > SendRecordHandler background work called");





                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // write your code here



                        if (InternetConnection.checkConnection(getApplicationContext())) {
                            // Internet Available...
                            Log.d(TAG, "run > Internet Available...");
                            if (activity != null) {
                                activity.sendRecordServiceResult("Done");
                            }
                        } else {
                            // Internet Not Available...
                            Log.d(TAG, "run > Internet Not Available...");
                        }
                    }
                });










                    Thread.sleep(sleep);

            } catch (Exception ex) {


                Log.d(TAG, "run > SendRecordHandler exception :"+ex.getMessage());


            }
        }
    }




    public interface Callbacks {
        void sendRecordServiceResult(String message);
    }


}


