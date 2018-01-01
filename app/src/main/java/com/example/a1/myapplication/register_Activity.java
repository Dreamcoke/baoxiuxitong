package com.example.a1.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2016/10/20.
 */
public class register_Activity extends AppCompatActivity implements View.OnClickListener{

    private EditText num;
    private EditText password;
    JSONArray jArray;
    String result = null;
    InputStream is = null;
    StringBuilder sb=null;
   private Toast toast;
    protected void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.register);
        Button button=(Button)findViewById(R.id.button4) ;
        num = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.editText4);
        button.setOnClickListener(this);
    }


            public void onClick(View v) {
                if (v.getId()==R.id.button4){
                    buttonWithHttpURLConnection();
                    toast.makeText(register_Activity.this, "注册成功", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(register_Activity.this,TestActivity.class);
                    startActivity(intent);

                }
            }
         private void buttonWithHttpURLConnection(){

             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     String num1=num.getText().toString();
                     String password1=password.getText().toString();
                     Log.d("n",num1);
                     Log.d("n1",password1);
                     try {
                     HttpClient httpclient = new DefaultHttpClient();
                     HttpPost httppost = new HttpPost("http://123.207.163.85/insert.php");
                     List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                     nameValuePairs.add(new BasicNameValuePair("id",num1));
                     nameValuePairs.add(new BasicNameValuePair("password",password1));
                         UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
                         httppost.setEntity(entity);
                         httpclient.execute(httppost);
                     }catch (Exception e)
                     {
                         Log.e("e","error");
                 }}
             }).start();


            }


}


