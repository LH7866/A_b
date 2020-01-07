package com.lenovo.manufacture.zhy;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.lenovo.manufacture.MainActivity;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;
import com.lenovo.manufacture.zhy.Bean.PeopleBean;
import com.lenovo.manufacture.zhy.Bean.problem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Problem extends AppCompatActivity implements View.OnClickListener {
    private TableLayout tableLayout;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        init();
        getProblem();
        getStuLine();
        getProduct();
        seleCard();
    }


    //全部学生问题车辆
  private void getProblem() {
        HashMap<String, String> r = new HashMap<>();
        MyRe.re(r, "/dataInterface/UserQuestion/getAll");
        new Timer().schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                JSONObject j = MyRe.re(r, "/dataInterface/UserQuestion/getAll");
                if (j != null) {
                    try {
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject = data.getJSONObject(i);
                                List<problem> list = new ArrayList<>();
                                problem p = new problem();
                                p.setId(jsonObject.getString("id"));
                                p.setUserProductionLineId(jsonObject.getString("userProductionLineId"));
                                list.add(p);
                            }
                            send(1, "");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 5000);
    }
    //学生生产线
    private void getStuLine() {
        HashMap<String, String> r1 = new HashMap<>();
        MyRe.re(r1, "/dataInterface/UserProductionLine/getInfo");
        r1.put("id","" );
        new Timer().schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                JSONObject j = MyRe.re(r1, "/dataInterface/UserProductionLine/getInfo");
                if (j != null) {
                    try {
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject = data.getJSONObject(i);
                            }
                            send(1, "");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 5000);
    }


    //获取生产线
    private void getProduct() {
        HashMap<String, String> r2 = new HashMap<>();
        MyRe.re(r2, "/ProductionLine/getInfo");
        r2.put("id","/dataInterface/ProductionLine/getInfo");
        new Timer().schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                JSONObject j = MyRe.re(r2, "/dataInterface/ProductionLine/getInfo");
                if (j != null) {
                    try {
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject = data.getJSONObject(i);
                            }
                            send(1, "");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 5000);
    }
    //查询车辆
    private void seleCard() {
        HashMap<String, String> r2 = new HashMap<>();
        MyRe.re(r2, "/dataInterface/Car/getInfo");
        r2.put("id","");
        new Timer().schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                JSONObject j = MyRe.re(r2, "/dataInterface/Car/getInfo");
                if (j != null) {
                    try {
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject = data.getJSONObject(i);
                            }
                            send(1, "");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 5000);
    }

    public void send(int what, Object obj) {
        Message message = Message.obtain();
        message.what = what;
        message.obj = obj;
        Problem.this.handler.sendMessage(message);
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    break;
            }
        }
    };

    private void init() {
        tableLayout = findViewById(R.id.tl_1);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addView(List<PeopleBean> i) {
        tableLayout.removeAllViews();
        for (PeopleBean bean : i) {
            View view1 = View.inflate(Problem.this, R.layout.table_item, null);
            TextView textView1 = view1.findViewById(R.id.tv_1);
            TextView textView2 = view1.findViewById(R.id.tv_2);
            TextView textView3 = view1.findViewById(R.id.tv_3);
            textView1.setText(bean.getPeopleName());
            textView2.setText(bean.getStatus());
            textView3.setText(bean.getGold());
            tableLayout.addView(view1);

        }
    }
}