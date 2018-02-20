package com.example.sowmyaram.tablelayoutsample;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.example.sowmyaram.tablelayoutsample.Halfcup_page.half_full_cmd;

/**
 * Created by Sowmya Ram on 11-02-2018.
 */
public class Test_page extends Activity implements View.OnClickListener{
    Button milkF,milkR,coffeeF,coffeeR,teaF,teaR,sugaronoff,heateronoff,brewonoff,hotwateronoff,coldwateron,stop;
    String comm_send;

    byte[] bytesToSend, theByteArray, a2, a1, out, theByteArray3, bytesToSend2,bytesToSend3,theByteArray2;
    Button button;
    BufferedReader br;
    String incoming_data;
    Button bset, bback, bread;
    String strReceived = null;
    private static final int REQUEST_ENABLE_BT = 1;
    InputStream in = null;
    BluetoothAdapter bluetoothAdapter;
    EditText etsugarv,etmilkval,etmilkspeed,etcoffeev,etmilkdelay;
    String bytesToSend4;
    ByteArrayOutputStream output;
    String digits;
    LinearLayout lcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testpage);

        Btconnection.handler=handler;

       milkF = findViewById(R.id.button_milk_far);
        milkR = findViewById(R.id.button_milk_rev);
        coffeeF = findViewById(R.id.button_coffe_fard);
        coffeeR = findViewById(R.id.button_coffe_rev);
        teaF = findViewById(R.id.buttont_tea_farward);
        teaR = findViewById(R.id.buttont_tea_rev);
        sugaronoff = findViewById(R.id.button_sugaronoff);
        heateronoff = findViewById(R.id.button_het_onoff);
        brewonoff = findViewById(R.id.button_brew_onoff);
        hotwateronoff = findViewById(R.id.button_hot_onoff);
        coldwateron = findViewById(R.id.button_col_onoff);
        stop = findViewById(R.id.button_stop);
        lcon=(LinearLayout) findViewById(R.id.icon);


        milkF.setOnClickListener(this);
        milkR.setOnClickListener(this);
        coffeeF.setOnClickListener(this);
        coffeeR.setOnClickListener(this);
        teaF.setOnClickListener(this);
        teaR.setOnClickListener(this);
        sugaronoff.setOnClickListener(this);
        heateronoff.setOnClickListener(this);
        brewonoff.setOnClickListener(this);
        hotwateronoff.setOnClickListener(this);
        coldwateron.setOnClickListener(this);
        stop.setOnClickListener(this);





        //To send *RF# cmd while opening blacktea page and if bluetooth is connected then kaapiwaala tab will turn to green

        try{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(Btconnection.btconnected=true&& Btconnection.bs.isConnected()) {
                        //lcon.setBackgroundResource(R.drawable.kapiconect);
                    }
                    else
                    {
                        lcon.setBackgroundResource(R.drawable.kaapiwala_tab);
                    }

                    String bytesToSend1 = "*CON#";
                    byte[] theByteArray = bytesToSend1.getBytes();
                    String data = new String(theByteArray);
                    Btconnection.sendbt(data);


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(Tea.this, "Connection Problem", Toast.LENGTH_SHORT).show();
        }





      /*  // send CON cmd if click on Kaapiwaala tab
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
        });*/
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(getApplicationContext(), OperatorsPage.class);
        startActivity(i);
        finish();
    }


   public void sample(){
       try{
           String bytesToSend1 = comm_send;
           theByteArray = bytesToSend1.getBytes();
           output = new ByteArrayOutputStream();
           try {
               output.write(theByteArray);
           } catch (IOException e) {
               e.printStackTrace();
           }

           byte[] out = output.toByteArray();
           String data = new String(out);
           Btconnection.sendbt(data);

       } catch (Exception e) {
           e.printStackTrace();
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

                        if ((message.startsWith("*")) && (message.endsWith("#"))) {
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
                                    }

                                    else if(message.equals("*O6#")) {
                                        Toast.makeText(Test_page.this, "Data Received", Toast.LENGTH_SHORT).show();

                                    }

                                    else if (message.equals("*MMO#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        milkF.setBackgroundResource(R.color.colorAccent_green);
                                    } else if (message.equals("*MMF#")) {
                                        // Toast.makeText(Tea.this, "Device Busy,not connected", Toast.LENGTH_SHORT).show();
                                        milkF.setBackgroundResource(R.drawable.optbck1);
                                    }


                                    else if (message.equals("*MRMO#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        milkR.setBackgroundResource(R.color.colorAccent_green);
                                    } else if (message.equals("*MRMF#")) {
                                        // Toast.makeText(Tea.this, "Device Busy,not connected", Toast.LENGTH_SHORT).show();
                                        milkR.setBackgroundResource(R.drawable.optbck1);
                                    }



                                    else if (message.equals("*MCO#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        coffeeF.setBackgroundResource(R.color.colorAccent_green);
                                    }    else if (message.equals("*MCF#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        coffeeF.setBackgroundResource(R.drawable.optbck1);
                                    }


                                    else if (message.equals("*MRCO#")) {
                                        // Toast.makeText(Tea.this, "Device Busy,not connected", Toast.LENGTH_SHORT).show();
                                        coffeeR.setBackgroundResource(R.color.colorAccent_green);
                                    }
                                    else if (message.equals("*MRCF#")) {
                                        // Toast.makeText(Tea.this, "Device Busy,not connected", Toast.LENGTH_SHORT).show();
                                        coffeeR.setBackgroundResource(R.drawable.optbck1);
                                    }


                                    else if (message.equals("*MTO#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        teaF.setBackgroundResource(R.color.colorAccent_green);
                                    }  else if (message.equals("*MTF#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        teaF.setBackgroundResource(R.drawable.optbck1);
                                    }


                                    else if (message.equals("*MRTO#")) {
                                        // Toast.makeText(Tea.this, "Device Busy,not connected", Toast.LENGTH_SHORT).show();
                                        teaR.setBackgroundResource(R.color.colorAccent_green);
                                    }
                                    else if (message.equals("*MRTF#")) {
                                        // Toast.makeText(Tea.this, "Device Busy,not connected", Toast.LENGTH_SHORT).show();
                                        teaR.setBackgroundResource(R.drawable.optbck1);
                                    }


                                    else if (message.equals("*MSO#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        sugaronoff.setBackgroundResource(R.color.colorAccent_green);
                                    } else if (message.equals("*MSF#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        sugaronoff.setBackgroundResource(R.drawable.optbck1);
                                    }



                                    else if (message.equals("*MHO#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        heateronoff.setBackgroundResource(R.color.colorAccent_green);
                                    }
                                    else if (message.equals("*MHF#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        heateronoff.setBackgroundResource(R.drawable.optbck1);
                                    }

                                    else if (message.equals("*MBRO#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        brewonoff.setBackgroundResource(R.color.colorAccent_green);
                                    } else if (message.equals("*MBRF#")) {
                                        // Toast.makeText(Tea.this, "Device Busy,not connected", Toast.LENGTH_SHORT).show();
                                        brewonoff.setBackgroundResource(R.drawable.optbck1);
                                    }


                                    else if (message.equals("*MHWO#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        hotwateronoff.setBackgroundResource(R.color.colorAccent_green);
                                    } else if (message.equals("*MHWF#")) {
                                        // Toast.makeText(Tea.this, "Device Busy,not connected", Toast.LENGTH_SHORT).show();
                                        hotwateronoff.setBackgroundResource(R.drawable.optbck1);
                                    }


                                    else if (message.equals("*MCWO#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        coldwateron.setBackgroundResource(R.color.colorAccent_green);
                                    } else if (message.equals("*MCWF#")) {
                                        // Toast.makeText(Tea.this, "Device Busy,not connected", Toast.LENGTH_SHORT).show();
                                        coldwateron.setBackgroundResource(R.drawable.optbck1);

                                    }

                                    else if (message.equals("*MS#")) {
                                        // Toast.makeText(Tea.this, "Device Connected", Toast.LENGTH_SHORT).show();
                                        milkF.setBackgroundResource(R.drawable.optbck1);
                                        milkR.setBackgroundResource(R.drawable.optbck1);
                                        coffeeF.setBackgroundResource(R.drawable.optbck1);
                                        coffeeR.setBackgroundResource(R.drawable.optbck1);
                                        teaF.setBackgroundResource(R.drawable.optbck1);
                                        teaR.setBackgroundResource(R.drawable.optbck1);
                                        sugaronoff.setBackgroundResource(R.drawable.optbck1);
                                        heateronoff.setBackgroundResource(R.drawable.optbck1);
                                        brewonoff.setBackgroundResource(R.drawable.optbck1);
                                        hotwateronoff.setBackgroundResource(R.drawable.optbck1);
                                        coldwateron.setBackgroundResource(R.drawable.optbck1);
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



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_milk_far:
                comm_send="*M7#\n";
                sample();
                break;
            case R.id.button_milk_rev:
                comm_send="*M8#\n";
                sample();
                break;
            case R.id.button_coffe_fard:
                comm_send="*M5#\n";
                sample();
                break;
            case R.id.button_coffe_rev:
                comm_send="*M0#\n";
                sample();
                break;
            case R.id.buttont_tea_farward:
                comm_send="*M4#\n";
                sample();
                break;
            case R.id.buttont_tea_rev:
                comm_send="*M9#\n";
                sample();
                break;
            case R.id.button_sugaronoff:
                comm_send="*M1#\n";
                sample();
                break;
            case R.id.button_het_onoff:
                comm_send="*MW#\n";
                sample();
                break;
            case R.id.button_brew_onoff:
                comm_send="*M2#\n";
                sample();
                break;
            case R.id.button_hot_onoff:
                comm_send="*M3#\n";
                sample();
                break;
            case R.id.button_col_onoff:
                comm_send="*M6#\n";
                sample();
                break;
            case R.id.button_stop:
                comm_send="*MS#\n";
                sample();
                break;
        }
    }
}





