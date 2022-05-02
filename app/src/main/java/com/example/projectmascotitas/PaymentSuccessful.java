package com.example.projectmascotitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class PaymentSuccessful extends AppCompatActivity {
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successful);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), History.class));
            }
        }, 5000);
    }
}