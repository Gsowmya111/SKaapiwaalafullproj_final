package com.example.sowmyaram.tablelayoutsample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

import static com.example.sowmyaram.tablelayoutsample.Halfcup_page.half_back;
import static com.example.sowmyaram.tablelayoutsample.Halfcup_page.half_full_cmd;
import static com.example.sowmyaram.tablelayoutsample.Halfcup_page.set_cmd_full_half;
import static com.example.sowmyaram.tablelayoutsample.Spinner.arraySpinner;
import static com.example.sowmyaram.tablelayoutsample.Spinner.arraySpinner_alernate_values;
import static com.example.sowmyaram.tablelayoutsample.Spinner.arraySpinner_alernate_values_milk;
import static com.example.sowmyaram.tablelayoutsample.Spinner.arraySpinner_alernate_values_sugar;
import static com.example.sowmyaram.tablelayoutsample.Spinner.arraySpinner_milk;
import static com.example.sowmyaram.tablelayoutsample.Spinner.arraySpinner_sugar;
import static com.example.sowmyaram.tablelayoutsample.Spinner.arraySpinner_tea;
import static com.example.sowmyaram.tablelayoutsample.Spinner.et_milk_ml;
import static com.example.sowmyaram.tablelayoutsample.Spinner.et_sug_ml;
import static com.example.sowmyaram.tablelayoutsample.Spinner.s;
import static com.example.sowmyaram.tablelayoutsample.Spinner.spinertextval;
import static com.example.sowmyaram.tablelayoutsample.Spinner.val;


public class Milk extends Activity {


    byte[] bytesToSend, theByteArray, a2, a1, out, theByteArray3, bytesToSend2,bytesToSend3,theByteArray2;
    Button button;
    BufferedReader br;
    String incoming_data;
    Button bset, bback, bread;
    String strReceived = null;
    private static final int REQUEST_ENABLE_BT = 1;
    InputStream in = null;
    BluetoothAdapter bluetoothAdapter;
    EditText etsugarv,etmilkval,etmilkspeed;
    String bytesToSend4;
    ByteArrayOutputStream output;
    String digits;
    LinearLayout lcon;
    String bfull1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.milk);
        Btconnection.handler=handler;

        //initialising ID's
        TextView tvmilk= (TextView) findViewById(R.id.tvmilk);
        Button value= (Button) findViewById(R.id.tvvalue);
        Button tvsugarv= (Button) findViewById(R.id.tvsugarv);
        Button tvmilkv= (Button) findViewById(R.id.tvmilkv);
        Button tvmilks= (Button) findViewById(R.id.tvmilks);
        etsugarv= (EditText) findViewById(R.id.etsugarval);
        etmilkval= (EditText) findViewById(R.id.etmilkval);
        etmilkspeed= (EditText) findViewById(R.id.etmilkspeed);
        bread = (Button) findViewById(R.id.buttonread);
        bset = (Button) findViewById(R.id.buttonset);
        bback = (Button) findViewById(R.id.buttonback);
        lcon = (LinearLayout) findViewById(R.id.lcon);


        //To send *RG# cmd while opening blacktea page and if bluetooth is connected then kaapiwaala tab will turn to green
        try{
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(Btconnection.btconnected=true&& Btconnection.bs.isConnected()) {
                    lcon.setBackgroundResource(R.drawable.kapiconect);
                }
                else
                {
                    lcon.setBackgroundResource(R.drawable.kaapiwala_tab);
                }
                String bytesToSend1 = half_full_cmd;
                byte[] theByteArray = bytesToSend1.getBytes();
                String data = new String(theByteArray);
                Btconnection.sendbt(data);
            }
        });
        } catch (Exception e) {
            e.printStackTrace();
           // Toast.makeText(Milk.this, "Connection Problem", Toast.LENGTH_SHORT).show();
        }




        //making edittext to take data in 0.0 format
        etsugarv.setFilters(new InputFilter[] {
                new DigitsKeyListener(Boolean.FALSE, Boolean.TRUE) {
                    int beforeDecimal = 2, afterDecimal = 1;

                    @Override
                    public CharSequence filter(CharSequence source, int start, int end,
                                               Spanned dest, int dstart, int dend) {
                        String temp = etsugarv.getText() + source.toString();

                        if (temp.equals(".")) {
                            return "0.0";
                        }
                        else if (temp.toString().indexOf(".") == -1) {
                            // no decimal point placed yet
                            if (temp.length() > beforeDecimal) {
                                return "";
                            }
                        }  else if (temp.length() > 5) {
                            temp = null;
                            temp = (String) source;
                        }else {
                            temp = temp.substring(temp.indexOf(".") + 1);
                            if (temp.length() > afterDecimal) {
                                return "";
                            }
                        }

                        return super.filter(source, start, end, dest, dstart, dend);
                    }
                }
        });
        //making edittext to take data in 0.0 format
        etmilkval.setFilters(new InputFilter[] {
                new DigitsKeyListener(Boolean.FALSE, Boolean.TRUE) {





                    int beforeDecimal = 3, afterDecimal = 1;

                    @Override
                    public CharSequence filter(CharSequence source, int start, int end,
                                               Spanned dest, int dstart, int dend) {
                        String temp = etmilkval.getText() + source.toString();

                        if (temp.equals(".")) {
                            return "0.0";
                        }
                        else if (temp.toString().indexOf(".") == -1) {
                            // no decimal point placed yet
                            if (temp.length() > beforeDecimal) {
                                return "";
                            }
                        }
                        else if (temp.length() > 5) {
                            temp = null;
                            temp = (String) source;
                        }else {
                            temp = temp.substring(temp.indexOf(".") + 1);
                            if (temp.length() > afterDecimal) {
                                return "";
                            }
                        }

                        return super.filter(source, start, end, dest, dstart, dend);
                    }













                    /*int beforeDecimal = 2, afterDecimal = 1;

                    @Override
                    public CharSequence filter(CharSequence source, int start, int end,
                                               Spanned dest, int dstart, int dend) {
                        String temp = etmilkval.getText() + source.toString();

                        if (temp.equals(".")) {
                            return "0.0";
                        }
                        else if (temp.toString().indexOf(".") == -1) {
                            // no decimal point placed yet
                            if (temp.length() > beforeDecimal) {
                                return "";
                            }
                        }  else if (temp.length() > 5) {
                            temp = null;
                            temp = (String) source;
                        }else {
                            temp = temp.substring(temp.indexOf(".") + 1);
                            if (temp.length() > afterDecimal) {
                                return "";
                            }
                        }

                        return super.filter(source, start, end, dest, dstart, dend);
                    }*/
                }
        });



        //SET button click event
        //Sending data to the machine
        bset.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        try{
                                        if (etsugarv.length() != 0 && etmilkval.length() != 0 ) {

                                          //  if (etsugarv.getText().toString().contains(".")) {


                                                String bytesToSend1 = set_cmd_full_half;
                                                theByteArray = bytesToSend1.getBytes();
                                               //  bytesToSend = et_sug_ml.getBytes();
                                                bytesToSend = etsugarv.getText().toString().getBytes();
                                                zero();
                                                bytesToSend4 = ",";
                                                theByteArray3 = bytesToSend4.getBytes();
                                                output = new ByteArrayOutputStream();
                                                //*G,
                                                try {
                                                    output.write(theByteArray);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                //00
                                                try {
                                                    output.write(a1);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                //,
                                                try {
                                                    output.write(theByteArray3);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }

                                                byte[] out = output.toByteArray();
                                                String data = new String(out);
                                                Btconnection.sendbt(data);


                                                /////////////////////////////////////////////////


                                                bytesToSend = et_milk_ml.getBytes();
                                                zero();


                                                bytesToSend4 = "#\n";
                                                theByteArray3 = bytesToSend4.getBytes();

                                                output = new ByteArrayOutputStream();

                                                //00
                                                try {
                                                    output.write(a1);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                //,
                                                try {
                                                    output.write(theByteArray3);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }

                                                out = output.toByteArray();
                                                String data1 = new String(out);
                                                Btconnection.sendbt(data1);

/////////////////////////////////////////////////////////////////////*B,00.0,00.0,00.0,00.0,000#


                                            }
/*
                                            bytesToSend = etmilkspeed.getText().toString().getBytes();
                                            zero2();


                                            bytesToSend4 = "#\n";
                                            theByteArray3 = bytesToSend4.getBytes();
                                            output = new ByteArrayOutputStream();

                                            //00.0
                                            try {
                                                output.write(a1);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            //,
                                            try {
                                                output.write(theByteArray3);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }

                                            out = output.toByteArray();
                                            String data4 = new String(out);
                                            Btconnection.sendbt(data4);*/

                                     //   }

/////////////////////////////////////////////////////////////////

                                        else {
                                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Milk.this);
                                            alertBuilder.setTitle("Invalid Data");
                                            alertBuilder.setMessage("Please, Enter data in 00.0 format");
                                            alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();

                                                }
                                            });
                                            alertBuilder.create().show();
                                        }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                           // Toast.makeText(Milk.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });


        //READ button click event
        //To send *RG# cmd to the machine
        bread.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                String bytesToSend1 = half_full_cmd;
                byte[] theByteArray = bytesToSend1.getBytes();

                String data = new String(theByteArray);
                Btconnection.sendbt(data);
                } catch (Exception e) {
                    e.printStackTrace();
                  //  Toast.makeText(Milk.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //BACK button click event
        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(half_back.equals("halfcup_page")){
                    Intent  i=new Intent(Milk.this, Halfcup_page.class);
                    startActivity(i);
                    finish();
                }else if(half_back.equals("fullcup_page")) {

                    Intent i = new Intent(Milk.this, FullCup_page.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        // send CON cmd if click on Kaapiwaala tab
        lcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                String bytesToSend1 = "*CON#";
                byte[] theByteArray = bytesToSend1.getBytes();
                String data = new String(theByteArray);
                Btconnection.sendbt(data);
                } catch (Exception e) {
                    e.printStackTrace();
                   // Toast.makeText(Milk.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                }

            }
        });

        etmilkval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etmilkval.setText("");
                spinertextval="etmilkval";
                spinnermethod();


            }
        });

       /* etsugarv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etsugarv.setText("");
                spinertextval="etsugarv";
                spinnermethod();


            }
        });*/

    }

    public  void spinnermethod()
    {
        View alertLayout;
        LayoutInflater inflater1 = getLayoutInflater();
        alertLayout = inflater1.inflate(R.layout.popup, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(Milk.this);

        alert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        int val1 = s.getSelectedItemPosition();
                        val = s.getSelectedItem().toString();

                        if(spinertextval.equals("etmilkval")){
                            etmilkval.setText(val);
                            et_milk_ml=(arraySpinner_alernate_values_milk[val1]);
                        }/*if(spinertextval.equals("etsugarv")){
                            etsugarv.setText(val);
                            et_sug_ml=(arraySpinner_alernate_values_sugar[val1]);
                        }*/

                    }
                });




        alert.setView(alertLayout);
        alert.setCancelable(true);
        final AlertDialog dialog1 = alert.create();
        dialog1.show();

        s = (android.widget.Spinner) alertLayout.findViewById(R.id.spiner);
        if(spinertextval.equals("etmilkval")){
            s.setAdapter(new MyAdapter(Milk.this, R.layout.spinner_item, arraySpinner_milk));
        }/*else if(spinertextval.equals("etsugarv")){
            s.setAdapter(new MyAdapter(Milk.this, R.layout.spinner_item, arraySpinner_sugar));
        }*/
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.65);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.35);
        dialog1.getWindow().setLayout(width, height);
        dialog1.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(half_back.equals("halfcup_page")){
            Intent  i=new Intent(Milk.this, Halfcup_page.class);
            startActivity(i);
            finish();
        }else if(half_back.equals("fullcup_page")) {

            Intent i = new Intent(Milk.this, FullCup_page.class);
            startActivity(i);
            finish();
        }
    }

    //To receive data from machine
    private Handler handler=new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    try {
                        final String message = (String) msg.obj;
                        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                        if ((message.startsWith("*")) && (message.endsWith("#")) && message.length() <= 5) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    // Toast.makeText(Logins.this,"incom1 "+message,Toast.LENGTH_LONG).show();

                                    if (message.equals("*CON#")) {
                                      //  Toast.makeText(Milk.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        lcon.setBackgroundResource(R.drawable.kapiconect);
                                    } else if (message.equals("*NOC#")) {
                                       // Toast.makeText(Milk.this, "Device Busy,not connected", Toast.LENGTH_SHORT).show();
                                        lcon.setBackgroundResource(R.drawable.kaapiwala_tab);
                                    } else if (message.equals("*DCN#")) {
                                       // Toast.makeText(Milk.this, "Device disconnected", Toast.LENGTH_SHORT).show();
                                        lcon.setBackgroundResource(R.drawable.kaapiwala_tab);
                                    }

                                    else if(message.equals("*O7#")) {
                                        Toast.makeText(Milk.this, "Data Received", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                            break;
                        }
                        else if (message != null ) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String bhalf = message.substring(3, 7);
                                    etsugarv.setText(bhalf.replaceAll("[^0-9.]", ""));

                                    String bfull = message.substring(8);
                                    String   bfull2= bfull.replaceAll("[^0-9.]", "");
                                    String   bhalf1= bhalf.replaceAll("[^0-9.]", "");
                                    //setting the ml values to edittext from respective spinner values
                                    for(int j=0;j<=arraySpinner_alernate_values_milk.length;j++){
                                        if (arraySpinner_alernate_values_milk[j].equals(bfull2)) {
                                            etmilkval.setText(arraySpinner_milk[j]);
                                        }/* if (arraySpinner_alernate_values_sugar[j].equals(bhalf1)) {
                                            etsugarv.setText(arraySpinner_sugar[j]);
                                        }*/
                                    }


                              //      etmilkval.setText(bfull.replaceAll("[^0-9.]", ""));

                                   /* String shalf = message.substring(13);
                                    etmilkspeed.setText(shalf.replaceAll("[^0-9.]", ""));*/
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        //Toast.makeText(Milk.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }
    };

    //Adding zero if input datalength less than 4
    void zero() {
        String a = null;
        a1 = new byte[0];
        if (bytesToSend.length==0) {
            String value = new String(bytesToSend);
            a ="";
            a1 = a.getBytes();
        }
        else if (bytesToSend.length==1) {
            String value = new String(bytesToSend);
            a = "0" + value;
            a1 = a.getBytes();
        }
        else if (bytesToSend.length==2) {
            String value = new String(bytesToSend);
            a = "0" + value;
            a1 = a.getBytes();
        }
        else if (bytesToSend.length==3) {
            String value = new String(bytesToSend);
            a = "0" + value;
            a1 = a.getBytes();
        }
        else if (bytesToSend.length==4) {
            String value = new String(bytesToSend);
            a =  value;
            a1 = a.getBytes();
        }
    }
    void zero2() {
        String a = null;
        a1 = new byte[0];
        if (bytesToSend.length==3) {
            String value = new String(bytesToSend);
            a =  value;
            a1 = a.getBytes();
        }
        else if (bytesToSend.length==2) {
            String value = new String(bytesToSend);
            a = "0"+ value;
            a1 = a.getBytes();
        }
        else if (bytesToSend.length==1) {
            String value = new String(bytesToSend);
            a = "00"+ value;
            a1 = a.getBytes();
        }

    }

    public class MyAdapter extends ArrayAdapter {
        private Context context;
        public MyAdapter(Context context, int textViewResourceId, String[] objects) {

            super(context, textViewResourceId, objects);

        }
        public View getCustomView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.spinner_item, parent, false);
            TextView tvLanguage = (TextView) layout.findViewById(R.id.textView);
            if(spinertextval.equals("etmilkval")){
                tvLanguage.setText(arraySpinner_milk[position]);
            }/*else if(spinertextval.equals("etsugarv")){
                tvLanguage.setText(arraySpinner_sugar[position]);
            }*/

            // tvLanguage.setText(spinner2_arr.get(position));
            //tvLanguage.setTextColor(Color.rgb(75, 180, 225));
            return layout;
        }

        // It gets a View that displays in the drop down popup the data at the specified position
        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }
        // It gets a View that displays the data at the specified position
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }
    }



}














