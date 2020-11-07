package com.example.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {
Roundprogress bar;
int progress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bar=(Roundprogress)findViewById(R.id.round_probar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progress <= 100){
                    progress += 3;
                    System.out.println(progress);
                    bar.setProgress(progress);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}