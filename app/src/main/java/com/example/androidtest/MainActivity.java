package com.example.androidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        byte SN[] = new byte[]{(byte)0xBC,(byte)0xB8,(byte)0xB5,(byte)0x22};



        int firstPart = SN[1];
        byte temp[] = new byte[] { SN[2], SN[3]};
        BigInteger secondPart = new BigInteger(temp);

        Log.d("xeagle6913", "output : "+firstPart+secondPart.toString());















    }
}