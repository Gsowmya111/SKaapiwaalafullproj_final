package com.example.sowmyaram.tablelayoutsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sowmya Ram on 11-02-2018.
 */

public class OperatorsPage extends Activity {
    Button half,full,others,test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operatorpage);
        half = (Button) findViewById(R.id.halfcup);
        full = (Button) findViewById(R.id.fullcup);
        others = (Button) findViewById(R.id.others);
        test = (Button) findViewById(R.id.test);

        half.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i1 = new Intent(OperatorsPage.this, Halfcup_page.class);
                startActivity(i1);
                finish();
            }
        });

        full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(OperatorsPage.this, FullCup_page.class);
                startActivity(i1);
                finish();
            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(OperatorsPage.this, Others_page.class);
                startActivity(i1);
                finish();
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(OperatorsPage.this, Test_page.class);
                startActivity(i1);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Logins.class);
        startActivity(i);
        finish();
    }
}
