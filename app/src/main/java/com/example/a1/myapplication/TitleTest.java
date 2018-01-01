package com.example.a1.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by zhuomian on 2016/5/13.
 */
public class TitleTest extends Activity {
    protected void onCreate(Bundle saveIntanceState){
        super.onCreate(saveIntanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title);
    }
}
