package com.lenovo.manufacture.zhy;

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

import com.lenovo.manufacture.MainActivity;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;
import com.lenovo.manufacture.zhy.Bean.CardBean;
import com.lenovo.manufacture.zhy.Bean.CardInfiBean;
import com.lenovo.manufacture.zhy.Bean.ProductLine;
import com.lenovo.manufacture.zhy.Bean.StuBean;
import com.lenovo.manufacture.zhy.Bean.problem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Problem extends AppCompatActivity implements View.OnClickListener {
    private TableLayout tableLayout;
    private ImageView back;
    private  Timer timer=new Timer();
    problem p = new problem();
    StuBean s=new StuBean();
    CardBean c=new CardBean();
    ProductLine line=new ProductLine();
    Map<String,String> b=new HashMap<>();
    CardInfiBean money=new CardInfiBean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        init();
        getProblem();
        getStuLine();
       // getProduct();
//        getMoney();
//        getCard();

    }
    //全部学生问题车辆
  private void getProblem() {

        timer.schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                HashMap<String, String> r = new HashMap<>();
                JSONObject j1 = MyRe.re(r, "/dataInterface/UserQuestion/getAll");
                if (j1 != null) {
                    try {
                        if (j1.getString("message").equals("SUCCESS")) {
                            JSONArray data = j1.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject = data.getJSONObject(i);
                                p.setId(jsonObject.getString("id"));
                                p.setUserProductionLineId(jsonObject.getString("userProductionLineId"));
                                Log.i("学生问题车辆1",p+"");
                               b.put("id",p.getUserProductionLineId());
                                Log.d("学生问题车辆",b+"");
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 1000);
    }
  //学生生产线
    private void getStuLine() {
        new Timer().schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                Map<String, String> r1 = new HashMap<>();
                r1.put("id","2453");
                JSONObject j2 = MyRe.re(r1, "/dataInterface/UserProductionLine/getInfo");
                Log.i("学生生产线2",MyRe.re(r1, "/dataInterface/UserProductionLine/getInfo")+"");
                if (j2 != null) {
                    try {
                        if (j2.getString("message").equals("SUCCESS")) {
                            JSONArray data = j2.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject = data.getJSONObject(i);
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

////
//    //获取生产线
//    private void getProduct() {
//        HashMap<String, String> r2 = new HashMap<>();
//        MyRe.re(r2, "/ProductionLine/getInfo");
//        r2.put("id",s.getProductionLineId());
//        new Timer().schedule(new TimerTask() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void run() {
//                JSONObject j = MyRe.re(r2, "/dataInterface/ProductionLine/getInfo");
//                if (j != null) {
//                    try {
//                        if (j.getString("message").equals("SUCCESS")) {
//                            JSONArray data = j.getJSONArray("data");
//                            for (int i = 0; i < data.length(); i++) {
//                                JSONObject jsonObject = data.getJSONObject(i);
//                                line.setCarId(jsonObject.getString("id"));
//                                line.setContent(jsonObject.getString("content"));
//                                line.setProductionLineName("productionLineName");
//                                b.put("生产线信息描述",line.getContent());
//                                Log.i("获取生产线",b+"");
//                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, 0, 2000);
//    }
////    //查询车辆
//    private void getCard() {
//        HashMap<String, String> r3 = new HashMap<>();
//        MyRe.re(r3, "/dataInterface/Car/getInfo");
//        r3.put("id",line.getCarId());
//        new Timer().schedule(new TimerTask() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void run() {
//                JSONObject j = MyRe.re(r3, "/dataInterface/Car/getInfo");
//                if (j != null) {
//                    try {
//                        if (j.getString("message").equals("SUCCESS")) {
//                            JSONArray data = j.getJSONArray("data");
//                            for (int i = 0; i < data.length(); i++) {
//                                JSONObject jsonObject = data.getJSONObject(i);
//                                c.setCarName(jsonObject.getString("carName"));
//                                c.setContent(jsonObject.getString("content"));
//                                c.setId(jsonObject.getString("id"));
//                                b.put("车辆类型",c.getCarName());
//                                b.put("车辆描述",c.getContent());
//                                Log.i("查询车辆",b+"");
//                            }
//
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, 0, 2000);
//    }
//
//    //查询车辆的维修费
//    private void getMoney() {
//        HashMap<String, String> r4 = new HashMap<>();
//        MyRe.re(r4, "/dataInterface/CarInfo/getInfo");
//        r4.put("id",c.getId());
//        new Timer().schedule(new TimerTask() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void run() {
//                JSONObject j = MyRe.re(r4, "/dataInterface/CarInfo/getInfo");
//                if (j != null) {
//                    try {
//                        if (j.getString("message").equals("SUCCESS")) {
//                            JSONArray data = j.getJSONArray("data");
//                            for (int i = 0; i < data.length(); i++) {
//                                JSONObject jsonObject = data.getJSONObject(i);
//                                money.setRepairGold(jsonObject.getString("repairGold"));
//                                b.put("维修费",money.getRepairGold());
//                                Log.i("维修费",b+"");
//                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, 0, 2000);
//    }

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
                    timer.cancel();
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

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public void addView(Map<String,String> b) {
//        tableLayout.removeAllViews();
//        for (PeopleBean bean : b) {
//            View view1 = View.inflate(Problem.this, R.layout.table_item, null);
//            TextView textView1 = view1.findViewById(R.id.tv_1);
//            TextView textView2 = view1.findViewById(R.id.tv_2);
//            TextView textView3 = view1.findViewById(R.id.tv_3);
//            textView1.setText(bean.getPeopleName());
//            textView2.setText(bean.getStatus());
//            textView3.setText(bean.getGold());
//            tableLayout.addView(view1);
//        }
//    }
}