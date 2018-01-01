package com.example.a1.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by zhuomian on 2016/5/14.
 */
public class RepairActivity extends AppCompatActivity {
    private TextView tv;
    private Spinner errorSpinner;

    private String []errorList={"门锁故障","灯不亮","桌凳损坏","电脑故障","其他"};
    private static final int TAKE_PHOTO=1;
    private static final int CROP_PHOTO=2;
    private Button takePhoto;
    private ImageView picture;
    private Uri imageUri;
    private TextView textView ,textView1;
    private long lastBackTime = 0;
    private long currentBackTime = 0;
    String n;
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.repair);
        TextView button6=(TextView)findViewById(R.id.textView4);
        Button button7=(Button)findViewById(R.id.title_set);
        textView=(TextView)findViewById(R.id.textView);
        final EditText editText=(EditText)findViewById(R.id.text);
        textView1=(TextView)findViewById(R.id.textView5);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RepairActivity.this,SetActivity.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RepairActivity.this,RepairActivity.class);
                startActivity(intent);
            }
        });
        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(editText.getText().toString().equals(""))
                    Toast.makeText(RepairActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                else
                Toast.makeText(RepairActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
            }});

        tv=(TextView)findViewById(R.id.tv);
        errorSpinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>spinnerAda=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,errorList);
        errorSpinner.setAdapter(spinnerAda);
        errorSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?>arg0, View arg1,int arg2,long arg3){
        tv.setText("您选择的故障类型为:"+errorList[arg2]);
            n=(String)errorSpinner.getSelectedItem();
            SharedPreferences.Editor editor1 = getSharedPreferences("data1", MODE_PRIVATE).edit();
            editor1.putString("error",n );
            SharedPreferences pref=getSharedPreferences("data1",MODE_PRIVATE);
            String name=pref.getString("error",n);


        }
            public void onNothingSelected(AdapterView<?>arg0){}
        });
        takePhoto =(Button)findViewById(R.id.title_photo);
        picture =(ImageView)findViewById(R.id.imageView);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage=new File(Environment.getExternalStorageDirectory(),"tempImage.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();}
                catch (IOException e){
                    e.printStackTrace();
                }
                imageUri=Uri.fromFile(outputImage);
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
                }

        });

        textView1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setContentView(R.layout.state);

                TextView textView3=(TextView)findViewById(R.id.textView12);
                TextView textView2=(TextView)findViewById(R.id.textView10);


                SharedPreferences.Editor editor2 = getSharedPreferences("data2", MODE_PRIVATE).edit();


                editor2.putString("place",editText.getText().toString() );

                SharedPreferences pref1=getSharedPreferences("data2",MODE_PRIVATE);

                String pass=pref1.getString("place",editText.getText().toString() );
                textView3.setText(pass);
                textView2.setText(n);
            }
        });

    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    Intent intent=new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri,"image/*");
                    intent.putExtra("scale",true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    startActivityForResult(intent,CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                if(resultCode==RESULT_OK){
                    try{
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    }
                    catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
              break;
            default:
                break;
        }
    }

}
