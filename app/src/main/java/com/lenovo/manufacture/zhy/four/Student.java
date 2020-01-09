package com.lenovo.manufacture.zhy.four;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.lenovo.manufacture.ReUse.MyRe;
import com.lenovo.manufacture.zhy.Bean.four.StuBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Student {
  
    static void getStuLine() {
        new Timer().schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                Map<String, String> r1 = new HashMap<>();
                r1.put("id","2453");
                JSONObject j2 = MyRe.re(r1, "/dataInterface/UserProductionLine/getInfo");
                Log.i("学生生产线2", MyRe.re(r1, "/dataInterface/UserProductionLine/getInfo")+"");
                if (j2 != null) {
                    try {
                        if (j2.getString("message").equals("SUCCESS")) {
                            JSONArray data = j2.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject = data.getJSONObject(i);
                                StuBean s=new StuBean();
                                s.setProductionLineId(jsonObject.getString("productionLineId"));
                                Log.i("学生生产线",s+"");

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 2000);
    }
}
