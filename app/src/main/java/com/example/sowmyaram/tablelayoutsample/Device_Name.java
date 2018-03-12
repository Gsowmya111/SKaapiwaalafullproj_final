package com.example.sowmyaram.tablelayoutsample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
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
import java.text.DecimalFormat;
import java.util.UUID;

import static com.example.sowmyaram.tablelayoutsample.Halfcup_page.half_back;
import static com.example.sowmyaram.tablelayoutsample.Halfcup_page.half_full_cmd;
import static com.example.sowmyaram.tablelayoutsample.Halfcup_page.set_cmd_full_half;


public class Device_Name extends Activity {


    byte[] bytesToSend, theByteArray, a2, a1, out, theByteArray3, bytesToSend2,bytesToSend3,theByteArray2;
    Button btn_devsend;
    BufferedReader br;
    String incoming_data;
    Button bset, bback, bread;
    String strReceived = null;
    private static final int REQUEST_ENABLE_BT = 1;
    InputStream in = null;
    BluetoothAdapter bluetoothAdapter;
    EditText edit_dev_name;
    String bytesToSend4;
    ByteArrayOutputStream output;
    String digits;
    LinearLayout lcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_name);
        Btconnection.handler=handler;

        //initialising ID's
        edit_dev_name= (EditText) findViewById(R.id.dev_name);
        // etmilkspeed= (EditText) findViewById(R.id.etmilkspeed);
        btn_devsend = (Button) findViewById(R.id.btn_devname);
        lcon = (LinearLayout) findViewById(R.id.lcon);
        //To send *RF# cmd while opening blacktea page and if bluetooth is connected then kaapiwaala tab will turn to green
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

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(Tea.this, "Connection Problem", Toast.LENGTH_SHORT).show();
        }



        //SET button click event
        //Sending data to the machine
        btn_devsend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    if ( edit_dev_name.length()!=0){
                            String bytesToSend1 = "*XX,";
                            theByteArray = bytesToSend1.getBytes();

                            bytesToSend = edit_dev_name.getText().toString().getBytes();
                        String bytesToSend2 = "#\n";
                        theByteArray3 = bytesToSend2.getBytes();

                            output = new ByteArrayOutputStream();
                            //*G,
                            try {
                                output.write(theByteArray);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //00
                            try {
                                output.write(bytesToSend);
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

                    }
                    else
                    {
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Device_Name.this);
                        alertBuilder.setTitle("Invalid Data");
                        alertBuilder.setMessage("Please Enter Name");
                        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                            }
                        });
                        alertBuilder.create().show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(Tea.this, "Connection Problem", Toast.LENGTH_SHORT).show();
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
                    // Toast.makeText(Tea.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();


            Intent  i=new Intent(Device_Name.this, Settingsadmin.class);
            startActivity(i);
            finish();

    }

    //To receive data from machine
    private Handler handler=new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    try {
                        final String message = (String) msg.obj;
                        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                        if ((message.startsWith("*")) && (message.endsWith("#"))  && message.length() <= 5) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    // Toast.makeText(Logins.this,"incom1 "+message,Toast.LENGTH_LONG).show();

                                    if (message.equals("*CON#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        lcon.setBackgroundResource(R.drawable.kapiconect);
                                    } else if (message.equals("*NOC#")) {
                                        // Toast.makeText(Tea.this, "Device Busy,not connected", Toast.LENGTH_SHORT).show();
                                        lcon.setBackgroundResource(R.drawable.kaapiwala_tab);
                                    } else if (message.equals("*DCN#")) {
                                        // Toast.makeText(Tea.this, "Device disconnected", Toast.LENGTH_SHORT).show();
                                        lcon.setBackgroundResource(R.drawable.kaapiwala_tab);
                                    }else if (message.equals("*OK#")) {
                                         Toast.makeText(Device_Name.this, "Device name successfully changed", Toast.LENGTH_SHORT).show();
                                        edit_dev_name.setText("");
                                    }



                                }
                            });
                            break;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        // Toast.makeText(Tea.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }
    };





}





