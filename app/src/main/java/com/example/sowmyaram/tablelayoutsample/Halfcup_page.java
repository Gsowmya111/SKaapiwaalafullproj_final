package com.example.sowmyaram.tablelayoutsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sowmya Ram on 11-02-2018.
 */
public class Halfcup_page extends Activity {
    boolean intstatus=Btconnection.in();
    boolean oustats=Btconnection.out();
 static  String half_full_cmd;
  static   String half_back;
    static String set_cmd_full_half;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halfcup);

        Button  coffee1 = (Button) findViewById(R.id.buttoncoffeope);
        Button  blacktea1 = (Button) findViewById(R.id.buttonblkteaope);
        Button  strongtea1 = (Button) findViewById(R.id.buttonstrngteaope);
        Button   tea1 = (Button) findViewById(R.id.buttonteaope);
        Button  milk1 = (Button) findViewById(R.id.buttonmilkope);
        Button blackcoffe1 = (Button) findViewById(R.id.buttonblackcoffope);
        Button   stroncofe1 = (Button) findViewById(R.id.buttonstrngcoffeope);
        Button  hotwater = (Button) findViewById(R.id.butn_hotwater);
        Button bback = (Button) findViewById(R.id.buttonback);

        half_back="halfcup_page";

        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Halfcup_page.this, OperatorsPage.class);
                startActivity(i);
                finish();
            }
        });


        blacktea1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RD#\n";
                set_cmd_full_half="*D,";
                Intent i11 = new Intent(Halfcup_page.this,Blacktea.class);
                startActivity(i11);
                finish();
            }
        });


        strongtea1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RE#\n";
                set_cmd_full_half="*E,";
                Intent i12 = new Intent(Halfcup_page.this,StrongTea.class);
                startActivity(i12);
                finish();
            }
        });

        tea1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RF#\n";
                set_cmd_full_half="*F,";
                Intent i13 = new Intent(Halfcup_page.this,Tea.class);
                startActivity(i13);
                finish();
            }
        });



        milk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RG#\n";
                set_cmd_full_half="*G,";
                Intent i14 = new Intent(Halfcup_page.this,Milk.class);
                startActivity(i14);
                finish();
            }
        });

        hotwater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RH#\n";
                set_cmd_full_half="*H,";
                Intent i16 = new Intent(Halfcup_page.this,Hotwater.class);
                startActivity(i16);
                finish();
            }
        });


        blackcoffe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RA#\n";
                set_cmd_full_half="*A,";
                Intent i8 = new Intent(Halfcup_page.this,Blackcofee.class);
                startActivity(i8);
                finish();
            }
        });

        stroncofe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RB#\n";
                set_cmd_full_half="*B,";
                Intent i9 = new Intent(Halfcup_page.this,Strongcofee.class);
                startActivity(i9);
                finish();
            }
        });


        coffee1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RC#\n";
                set_cmd_full_half="*C,";
                Intent i10 = new Intent(Halfcup_page.this,Coffee.class);
                startActivity(i10);
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
