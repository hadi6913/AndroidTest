package com.example.androidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




//
//        byte SN[] = new byte[]{(byte)0xBC,(byte)0xB8,(byte)0xB5,(byte)0x22};
//
//
//
//        int firstPart = (SN[1]&0xff);
//        byte temp[] = new byte[] { SN[2], SN[3]};
//
//
//        long secondPart  = getValue(temp);
//
//
//        Log.d("xeagle6913", "output : "+firstPart+secondPart);




//        String General_Qr_Code_String = "!@^###4834388266#64909!@^";
        String General_Qr_Code_String = "!@^#1380130265321###042911022#62339!@^";
        String TAG  = "xeagle";





        try {

            String actualStringFromQrcode = General_Qr_Code_String.substring(3, General_Qr_Code_String.length() - 3);
            Log.d(TAG, "actualStringFromQrcode >>> " + actualStringFromQrcode);
            int crc = Integer.parseInt(actualStringFromQrcode.charAt(3) + "");
            int calculatedCrc = 0;
            int sumForCalculatedCrc = 0;
            String actualStringFromQrcodeWithOutSharp = actualStringFromQrcode.replace("#", "").trim();
            Log.d(TAG, "actualStringFromQrcodeWithOutSharp >>> " + actualStringFromQrcodeWithOutSharp);
            for (int i = 0; i < actualStringFromQrcodeWithOutSharp.length(); i++) {
                Log.d(TAG, "" + Integer.parseInt(actualStringFromQrcodeWithOutSharp.charAt(i) + ""));
                sumForCalculatedCrc = sumForCalculatedCrc + Integer.parseInt(actualStringFromQrcodeWithOutSharp.charAt(i) + "");
            }
            Log.d(TAG, "sumForCalculatedCrc befor minus crc >>> " + sumForCalculatedCrc);
            sumForCalculatedCrc = sumForCalculatedCrc - Integer.parseInt(actualStringFromQrcode.charAt(3) + "");
            Log.d(TAG, "sumForCalculatedCrc >>> " + sumForCalculatedCrc);
            calculatedCrc = (sumForCalculatedCrc % 9);
            Log.d(TAG, "calculatedCrc >>> " + calculatedCrc);
            Log.d(TAG, "Crc >>> " + crc);
            if (calculatedCrc == crc) {
                Log.d(TAG, "crc is correct");
            } else {
                Log.d(TAG, "crc is in-correct");
                throw new Exception();
            }
//







//                        !@^#1380130265321###042911022#62339!@^
//                        !@^###4834388266#64909!@^



            StringBuffer stringBuffer1 = new StringBuffer(actualStringFromQrcode);
            String acutalStringFromQrCodeWithOutCrc = stringBuffer1.replace(3, 4, "").toString().trim();
//                          #130130265321
//                          #
//                          #
//                          #042911022
//                          #62339
            Log.d(TAG, "acutalStringFromQrCodeWithOutCrc >>>" + acutalStringFromQrCodeWithOutCrc);
            List<String> listString = new ArrayList<String>(Arrays.asList(acutalStringFromQrCodeWithOutCrc.split("#")));
            String reverse_id = listString.get(5);
            Log.d(TAG, "reverse_id >>> " + reverse_id);
            String reverse_pelak_pure = listString.get(4);
            Log.d(TAG, "reverse_pelak_pure >>> " + reverse_pelak_pure);
            String reverse_ozv = listString.get(3);
            Log.d(TAG, "reverse_ozv >>> " + reverse_ozv);
            String cardCrc = listString.get(2);
            Log.d(TAG, "cardCrc >>> " + cardCrc);
            String reverse_card = null;
            if (cardCrc.equals("")) {
                reverse_card = "";
            } else {
//                            reverse_card = cardCrc.substring(0, 3) + cardCrc.substring(4, cardCrc.length());
                reverse_card = cardCrc;
            }
            Log.d(TAG, "reverse_card >>> " + reverse_card);
//            String id = new StringBuilder(reverse_id).reverse().toString();
            String pelak_pure = new StringBuilder(reverse_pelak_pure).reverse().toString();
            String ozv = new StringBuilder(reverse_ozv).reverse().toString();
            String card = new StringBuilder(reverse_card).reverse().toString();
            boolean isCar;
            if (pelak_pure.length() == 8) {
                isCar = false;
            } else {
                isCar = true;
            }
            Log.d(TAG, "pure_pelak with original enum >>> " + pelak_pure);
            String motor_pelak_pure = pelak_pure;
            Log.d(TAG, "original enum" + pelak_pure.substring(2, 4));
            String actualPurePelak = null;
            StringBuffer stringBuffer;
            switch (pelak_pure.substring(2, 4)) {
                case "00":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "الف").toString();
                    break;
                case "01":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ب").toString();
                    break;
                case "02":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "پ").toString();
                    break;
                case "03":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ت").toString();
                    break;
                case "04":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ث").toString();
                    break;
                case "05":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ج").toString();
                    break;
                case "06":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "چ").toString();
                    break;
                case "07":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ح").toString();
                    break;
                case "08":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "خ").toString();
                    break;
                case "09":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "د").toString();
                    break;
                case "10":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ذ").toString();
                    break;
                case "11":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ر").toString();
                    break;
                case "12":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ز").toString();
                    break;
                case "13":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ژ").toString();
                    break;
                case "14":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "س").toString();
                    break;
                case "15":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ش").toString();
                    break;
                case "16":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ص").toString();
                    break;
                case "17":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ض").toString();
                    break;
                case "18":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ط").toString();
                    break;
                case "19":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ظ").toString();
                    break;
                case "20":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ع").toString();
                    break;
                case "21":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "غ").toString();
                    break;
                case "22":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ف").toString();
                    break;
                case "23":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ق").toString();
                    break;
                case "24":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ک").toString();
                    break;
                case "25":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "گ").toString();
                    break;
                case "26":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ل").toString();
                    break;
                case "27":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "م").toString();
                    break;
                case "28":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ن").toString();
                    break;
                case "29":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "و").toString();
                    break;
                case "30":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ه").toString();
                    break;
                case "31":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "ی").toString();
                    break;
                case "32":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "♿").toString();
                    break;
                case "33":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "D").toString();
                    break;
                case "34":
                    stringBuffer = new StringBuffer(pelak_pure);
                    actualPurePelak = stringBuffer.replace(2, 4, "S").toString();
                    break;
            }
            Log.d(TAG, "actual plate >>> " + actualPurePelak);
            if (isCar) {
                //car
//                viewModel.getCar().setValue(true);
//                viewModel.getMotor().setValue(false);
//                viewModel.getPlate__0().setValue(actualPurePelak.substring(0, 2));
//                viewModel.getPlate__2().setValue(actualPurePelak.substring(actualPurePelak.length() - 5, actualPurePelak.length() - 2));
//                viewModel.getPlate__3().setValue(actualPurePelak.substring(actualPurePelak.length() - 2, actualPurePelak.length()));
//                if (actualPurePelak.length() > 8) {
//                    //الف pelak
//                    viewModel.getPlate__1().setValue(actualPurePelak.substring(2, 5));
//                } else {
//                    //بدون الف
//                    viewModel.getPlate__1().setValue(actualPurePelak.substring(2, 3));
//                }
//                String[] alphabetArray = getResources().getStringArray(R.array.image_array);
//                for (int i = 1; i < alphabetArray.length; i++) {
//                    if (alphabetArray[i].equals(viewModel.getPlate__1().getValue()))
//                        binding.spinner.setSelection(i);
//                }
//                Log.d(TAG, "car?????????????????????????????????????????????????????");
            } else {
                //motor
//                viewModel.getCar().setValue(false);
//                viewModel.getMotor().setValue(true);
//                viewModel.getMplate__0().setValue(motor_pelak_pure.substring(0, 3));
//                viewModel.getMplate__1().setValue(motor_pelak_pure.substring(3));
//                Log.d(TAG, "motor?????????????????????????????????????????????????????");
            }
        } catch (Exception e) {
            Log.d("xeagle6913", e.getMessage());
            return;
        }



























    }


    public static long getValue(byte[] input)
    {
        long val = 0;
        for (int i = 0; i < input.length ; i++)
        {
            val = (val * 256) + (input[i] & 0xFF);
        }
        return val;
    }



}