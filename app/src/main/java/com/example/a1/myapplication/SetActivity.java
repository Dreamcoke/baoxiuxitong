package com.example.a1.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 1 on 2016/5/21.
 */
public class SetActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.set);
        TextView tv=(TextView) findViewById(R.id.textView2);
        TextView tv1=(TextView) findViewById(R.id.textView);
        TextView tv2=(TextView) findViewById(R.id.titleText);
        tv1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(SetActivity.this,VersionActivity.class);
                startActivity(intent);
            }
        });
        tv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                AlertDialog alert=new AlertDialog.Builder(SetActivity.this).create();
                alert.setTitle("退出？");
                alert.setMessage("确认退出？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE,"No",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which){}
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCollector.finshAll();
                    }
                });
                alert.show();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setContentView(R.layout.informations);
                TextView textView=(TextView)findViewById(R.id.textView) ;
                TextView textView1=(TextView)findViewById(R.id.textView1) ;
                SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
                String name=pref.getString("name","");
                String pass=pref.getString("password","");
                textView.setText(name);
                textView1.setText(pass);
            }
        });
}}
