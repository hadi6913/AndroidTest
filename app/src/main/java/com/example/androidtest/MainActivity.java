package com.example.androidtest;

import android.app.Activity;
import android.content.Context;
import android.device.PrinterManager;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnPrint;
    private Bitmap mBitmap;
    PrinterManager mPrinterManager;
    //Printer gray value 0-4
    private final static int DEF_PRINTER_HUE_VALUE = 0;
    private final static int MIN_PRINTER_HUE_VALUE = 0;
    private final static int MAX_PRINTER_HUE_VALUE = 4;

    //Print speed value 0-9
    private final static int DEF_PRINTER_SPEED_VALUE = 9;
    private final static int MIN_PRINTER_SPEED_VALUE = 0;
    private final static int MAX_PRINTER_SPEED_VALUE = 9;

    // Printer status
    private final static int PRNSTS_OK = 0;                //OK
    private final static int PRNSTS_OUT_OF_PAPER = -1;    //Out of paper
    private final static int PRNSTS_OVER_HEAT = -2;        //Over heat
    private final static int PRNSTS_UNDER_VOLTAGE = -3;    //under voltage
    private final static int PRNSTS_BUSY = -4;            //Device is busy
    private final static int PRNSTS_ERR = -256;            //Common error
    private final static int PRNSTS_ERR_DRIVER = -257;    //Printer Driver error

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrint = findViewById(R.id.btn_print);
        new CustomThread().start();
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBitmap = generateBitmapByLayout(MainActivity.this);
                Message msg = mPrintHandler.obtainMessage(PRINT_BITMAP);
                msg.obj = mBitmap;
                msg.sendToTarget();
                mPrintHandler.obtainMessage(PRINT_FORWARD).sendToTarget();


            }
        });







    }




    public Bitmap generateBitmapByLayout(Context context) {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.receipt_payment, null);
        TextView entry_date_txt = view.findViewById(R.id.entry_date_ttttttt);
        TextView duration_date_txt = view.findViewById(R.id.duration_date_ttttttttt);
        TextView cost_txt = view.findViewById(R.id.cost_txt_ttttttttttt);
        TextView ozv_txt = view.findViewById(R.id.ozv_txt_ttttttttt);
        TextView card_txt = view.findViewById(R.id.cart_txt_tttttttt);
        TextView plate_txt_0 = view.findViewById(R.id.p0_ttttttttttt);
        TextView plate_txt_1 = view.findViewById(R.id.p1_ttttttttt);
        TextView plate_txt_2 = view.findViewById(R.id.p2_tttttttt);
        TextView plate_txt_3 = view.findViewById(R.id.p3_tttttttt);
        TextView m_plate_txt_0 = view.findViewById(R.id.m0_ttttttttt);
        TextView m_plate_txt_1 = view.findViewById(R.id.m1_tttttttt);
        TextView peygiri_txt = view.findViewById(R.id.txt_peygiri_ttttt);
        RelativeLayout pelak_main_layoout = view.findViewById(R.id.plate_main_layout_ttttttttttt);
        LinearLayout cart_main_layout = view.findViewById(R.id.date_layout_ttttttttt);
        LinearLayout ozv_main_layout = view.findViewById(R.id.ozv_recipit_layout_ttttttttttt);
        LinearLayout motor_main_layout = view.findViewById(R.id.motor_layout_ttttttt);
        LinearLayout car_main_layout = view.findViewById(R.id.plate_layout_tttttttttt);
        entry_date_txt.setText("20022/02/30");
        duration_date_txt.setText("پنج ساعت");
        cost_txt.setText("2000" + " ریال");
        peygiri_txt.setText("1454546546546");
        boolean cartStatus = true;
        boolean ozvStatus = true;
        boolean pelakStatus = true;


        pelak_main_layoout.setVisibility(View.VISIBLE);

        car_main_layout.setVisibility(View.VISIBLE);
        motor_main_layout.setVisibility(View.GONE);



        return PrinterUtils.convertViewToBitmap(view);
    }


    /**
     * Execution printing
     * To print data with this class, use the following steps:
     * Obtain an instance of Printer with PrinterManager printer = new PrinterManager().
     * Call setupPage(int, int) to initialize the page size.
     * If necessary, append a line in the current page with drawLine(int , int , int , int , int ).
     * If necessary, append text in the current page with drawTextEx(String , int, int , int , int , String ,int , int , int , int ).
     * If necessary, append barcode data in the current page with drawBarcode(String , int , int , int ,int , int , int ).
     * If necessary, append picture data in the current page with drawBitmap(Bitmap , int , int ).
     * To begin print the current page session, call printPage(int).
     *
     * @param printerManager printerManager
     * @param type           PRINT_TEXT PRINT_BITMAP PRINT_BARCOD PRINT_FORWARD
     * @param content        content
     */
    private void doPrint(PrinterManager printerManager, int type, Object content) {
        int ret = printerManager.getStatus();

        int finalRet = ret;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updatePrintStatus(finalRet);
            }
        });


        Log.d("xeagle696969", "printer status : "+ret);


        //Get printer status
        if (ret == PRNSTS_OK) {
            printerManager.setupPage(384, -1);   //Set paper size
            switch (type) {
                case PRINT_BITMAP:
                    Bitmap bitmap = (Bitmap) content;
                    if (bitmap != null) {
                        printerManager.drawBitmap(bitmap, 30, 0);  //print pictures
                    } else {


                        Toast.makeText(MainActivity.this, R.string.bitmap_null,Toast.LENGTH_LONG).show();



                    }
                    break;
            }
            ret = printerManager.printPage(0);  //Execution printing
            printerManager.paperFeed(16);  //paper feed
        }

    }

    private final int PRINT_TEXT = 0;   //Printed text
    private final int PRINT_BITMAP = 1;   //print pictures
    private final int PRINT_BARCOD = 2;   //Print bar code
    private final int PRINT_FORWARD = 3;   //Forward (paper feed)

    private Handler mPrintHandler;

    class CustomThread extends Thread {
        @Override
        public void run() {
            //To create a message loop
            Looper.prepare();   //1.Initialize looper
            mPrintHandler = new Handler() {   //2.Bind handler to looper object of customthread instance
                public void handleMessage(Message msg) {   //3.Define how messages are processed
                    switch (msg.what) {
                        case PRINT_TEXT:
                        case PRINT_BITMAP:
                        case PRINT_BARCOD:
                            doPrint(getPrinterManager(), msg.what, msg.obj);   //Print
                            break;
                        case PRINT_FORWARD:
//                            getPrinterManager().paperFeed(20);
                            getPrinterManager().paperFeed(1200);
//                            getPrinterManager().paperFeed(1200);
                            updatePrintStatus(100);
                            break;
                    }
                }
            };
            Looper.loop();   //4.Start message loop
        }
    }

    //Update printer status, toast reminder in case of exception
    private void updatePrintStatus(final int status) {
        if (status == PRNSTS_OUT_OF_PAPER) {

            Toast.makeText(MainActivity.this,R.string.tst_info_paper,Toast.LENGTH_LONG).show();



        } else if (status == PRNSTS_OVER_HEAT) {


            Toast.makeText(MainActivity.this,R.string.tst_info_temperature,Toast.LENGTH_LONG).show();


        } else if (status == PRNSTS_UNDER_VOLTAGE) {



            Toast.makeText(MainActivity.this,R.string.tst_info_voltage,Toast.LENGTH_LONG).show();



        } else if (status == PRNSTS_BUSY) {


            Toast.makeText(MainActivity.this,R.string.tst_info_busy,Toast.LENGTH_LONG).show();



        } else if (status == PRNSTS_ERR) {




            Toast.makeText(MainActivity.this,R.string.tst_info_error,Toast.LENGTH_LONG).show();


        } else if (status == PRNSTS_ERR_DRIVER) {




            Toast.makeText(MainActivity.this,R.string.tst_info_driver_error,Toast.LENGTH_LONG).show();



        }
    }

    public static boolean isNumeric(String string) {
        if (string != null && !string.equals("") && string.matches("\\d*")) {
            if (String.valueOf(Integer.MAX_VALUE).length() < string.length()) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    //Instantiate printermanager
    private PrinterManager getPrinterManager() {
        if (mPrinterManager == null) {
            mPrinterManager = new PrinterManager();
            mPrinterManager.open();
        }
        return mPrinterManager;
    }
}





