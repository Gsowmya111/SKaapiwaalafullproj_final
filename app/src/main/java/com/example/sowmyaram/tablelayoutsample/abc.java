/*
package in.cloud.www.counterex;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sowmyaram.tablelayoutsample.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class abc extends AppCompatActivity {
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_settings);

        //Get reference of the XML layout's widgets

        askSpeechInput();

    }
    private void askSpeechInput() {
        long millisInFuture = 25000;
        long countDownInterval = 1000;
        final TextView tView = (TextView) findViewById(R.id.tv);
        new CountDownTimer(millisInFuture, countDownInterval) {
            public void onTick(long millisUntilFinished) {

                Timer t = new Timer();

                t.scheduleAtFixedRate(
                        new TimerTask() {
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                                        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                                                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                                        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                                        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                                                "Hi speak something");
                                        try {

                                            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
                                        } catch (ActivityNotFoundException a) {

                                        }
                                    }
                                });

                            }
                        },
                        0,      // run first occurrence immediatetly
                        7000);
            }

            public void onFinish() {
                //Do something when count down finished
                tView.setText("Time over...");
            }
        }.start();

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final TextView b = (TextView) findViewById(R.id.textView);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {

                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String voiceInput =result.get(0);
                    if(voiceInput.contains("casio")){
                        b.setText("hii megha");
                        //  iv.setImageResource(R.drawable.abc);
                        String toSpeak = b.getText().toString();
                        Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                        // t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                        //  voiceInput.setText(result.get(0));
                        //if (voiceInput.getText().toString().trim().equals("megha")) {

                    }
                    break;

                }
            }
        }
    }

}*/
