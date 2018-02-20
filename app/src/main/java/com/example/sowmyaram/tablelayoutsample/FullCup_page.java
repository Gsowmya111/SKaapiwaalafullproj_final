package com.example.sowmyaram.tablelayoutsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.sowmyaram.tablelayoutsample.Halfcup_page.half_back;
import static com.example.sowmyaram.tablelayoutsample.Halfcup_page.half_full_cmd;
import static com.example.sowmyaram.tablelayoutsample.Halfcup_page.set_cmd_full_half;

/**
 * Created by Sowmya Ram on 11-02-2018.
 */
public class FullCup_page extends Activity {
    boolean intstatus=Btconnection.in();
    boolean oustats=Btconnection.out();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullcup);

        Button  coffee1 = (Button) findViewById(R.id.buttoncoffeope);
        Button  blacktea1 = (Button) findViewById(R.id.buttonblkteaope);
        Button  strongtea1 = (Button) findViewById(R.id.buttonstrngteaope);
        Button   tea1 = (Button) findViewById(R.id.buttonteaope);
        Button  milk1 = (Button) findViewById(R.id.buttonmilkope);
        Button blackcoffe1 = (Button) findViewById(R.id.buttonblackcoffope);
        Button   stroncofe1 = (Button) findViewById(R.id.buttonstrngcoffeope);
        Button  hotwater = (Button) findViewById(R.id.butn_hotwater);
        Button bback = (Button) findViewById(R.id.buttonback);
        half_back="fullcup_page";
        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FullCup_page.this, OperatorsPage.class);
                startActivity(i);
                finish();
            }
        });


        blacktea1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RL#\n";
                set_cmd_full_half="*L,";
                Intent i11 = new Intent(FullCup_page.this,Blacktea.class);
                startActivity(i11);
                finish();
            }
        });


        strongtea1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RM#\n";
                set_cmd_full_half="*M,";
                Intent i12 = new Intent(FullCup_page.this,StrongTea.class);
                startActivity(i12);
                finish();
            }
        });

        tea1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RN#\n";
                set_cmd_full_half="*N,";
                Intent i13 = new Intent(FullCup_page.this,Tea.class);
                startActivity(i13);
                finish();
            }
        });



        milk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RO#\n";
                set_cmd_full_half="*O,";
                Intent i14 = new Intent(FullCup_page.this,Milk.class);
                startActivity(i14);
                finish();
            }
        });

        hotwater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RP#\n";
                set_cmd_full_half="*P,";
                Intent i16 = new Intent(FullCup_page.this,Hotwater.class);
                startActivity(i16);
                finish();
            }
        });


        blackcoffe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RI#\n";
                set_cmd_full_half="*I,";
                Intent i8 = new Intent(FullCup_page.this,Blackcofee.class);
                startActivity(i8);
                finish();
            }
        });

        stroncofe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RJ#\n";
                set_cmd_full_half="*J,";
                Intent i9 = new Intent(FullCup_page.this,Strongcofee.class);
                startActivity(i9);
                finish();
            }
        });


        coffee1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                half_full_cmd="*RK#\n";
                set_cmd_full_half="*K,";
                Intent i10 = new Intent(FullCup_page.this,Coffee.class);
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
