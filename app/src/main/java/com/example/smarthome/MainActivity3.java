package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity3.this, MainActivity1.class);
        startActivity(intent);finish();
    }
    public void ButtoClick(View view) {
        Intent intent1 = new Intent(MainActivity3.this, MainActivity1.class);
        startActivity(intent1);
        finish();
    }
}