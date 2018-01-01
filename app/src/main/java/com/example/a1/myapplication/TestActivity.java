package com.example.a1.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by zhuomian on 2016/5/8.
 */
public class TestActivity extends AppCompatActivity {
    private EditText num;
    private EditText password;

    protected void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.main);
        Button button = (Button) findViewById(R.id.button2);
        num = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TestActivity.this,register_Activity.class);
                startActivity(intent);
            }


        });
    }}