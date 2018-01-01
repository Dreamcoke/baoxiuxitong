package com.example.a1.myapplication;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2016/5/22.
 */
public class ActivityCollector extends Activity {
    public static List<Activity>activities=new ArrayList<Activity>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finshAll(){
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
