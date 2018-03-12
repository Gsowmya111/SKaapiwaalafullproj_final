package com.example.sowmyaram.tablelayoutsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class Others_page extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otherspage);

        boolean intstatus=Btconnection.in();
        boolean oustats=Btconnection.out();

        Button  brewcoff1 = (Button) findViewById(R.id.buttonbrewope);
        Button  cupdelay1 = (Button) findViewById(R.id.buttoncupdelayope);
        Button  temp1 = (Button) findViewById(R.id.buttontempope);
        Button  revrese_delay = (Button) findViewById(R.id.reverse_delay_btn);
        Button  refresh = (Button) findViewById(R.id.refresh_btn);

        Button  date1 = (Button) findViewById(R.id.buttondateope);
        Button  brewcups1 = (Button) findViewById(R.id.buttonbrewcupsope);

        Button  mptor_rev = (Button) findViewById(R.id.btn_motr_rev);
        Button bback = (Button) findViewById(R.id.buttonback);
        Button  brewtea = (Button) findViewById(R.id.buttonbrewope1);

        revrese_delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i11 = new Intent(Others_page.this,Reverse_Delay.class);
                startActivity(i11);
                finish();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(Others_page.this,Refresh.class);
                startActivity(i3);
                finish();
            }
        });


        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Others_page.this, OperatorsPage.class);
                startActivity(i);
                finish();
            }
        });


        brewcoff1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(Others_page.this,BrewCoffe.class);
                startActivity(i3);
                finish();
            }
        });


        cupdelay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(Others_page.this,Cupdelay.class);
                startActivity(i4);
                finish();
            }
        });



        temp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(Others_page.this,Temperature.class);
                startActivity(i5);
                finish();
            }
        });

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i6 = new Intent(Others_page.this,Datepage.class);
                startActivity(i6);
                finish();
            }
        });

        brewcups1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i7 = new Intent(Others_page.this,BrewCups.class);
                startActivity(i7);
                finish();
            }
        });




        mptor_rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i15 = new Intent(Others_page.this,Reversemilk.class);
                startActivity(i15);
                finish();
            }
        });


        brewtea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i16 = new Intent(Others_page.this,Brewtea.class);
                startActivity(i16);
                finish();
            }
        });




    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), OperatorsPage.class);
        startActivity(i);
        finish();
    }


}