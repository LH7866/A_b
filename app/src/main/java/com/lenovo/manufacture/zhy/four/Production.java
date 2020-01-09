package com.lenovo.manufacture.zhy.four;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;
import com.lenovo.manufacture.zhy.Bean.five.ProductBean;
import com.lenovo.manufacture.zhy.four.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Production extends AppCompatActivity {
    private Timer timer=new Timer();
   private  Timer timer1=new Timer();
    ProductBean p=new ProductBean();
    List<ProductBean>  list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);
        getData();
    }

    private void getData() {
        HashMap<String ,String> h=new HashMap<>();
        h.put("position","3");
        list=new ArrayList<>();
        //按位置查询生产线
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = MyRe.re(h, "/dataInterface/UserProductionLine/search");
                    if (jsonObject != null) {
                        if (jsonObject.getString("message").equals("SUCCESS")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject j = jsonArray.getJSONObject(i);
                                p.setId(j.getString("id"));
                                p.setStageId(j.getString("stageId"));
                                p.setProductionLineId(j.getString("productionLineId"));
                                list.add(p);
                                Log.i("===================",list+"");
                            }
                        }
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        },0,1000);
    }


}
