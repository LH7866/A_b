package com.lenovo.manufacture.zhy.five;

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
import com.lenovo.manufacture.zhy.Bean.five.ProductBean;
import com.lenovo.manufacture.zhy.Bean.four.CardBean;
import com.lenovo.manufacture.zhy.Bean.four.CardInfiBean;
import com.lenovo.manufacture.zhy.Bean.four.ProductLine;
import com.lenovo.manufacture.zhy.Bean.four.StuBean;
import com.lenovo.manufacture.zhy.Bean.four.problem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Problem extends AppCompatActivity implements View.OnClickListener {
    private TableLayout tableLayout;
    private ImageView back;
    private Timer t1,t2,t3,t4,t5 ;
    problem p;
    StuBean s ;
    CardBean c;
    ProductLine line;
    CardInfiBean money;
    Map<String, String> b = new HashMap<>();
    List<problem> listp = new ArrayList<>();
    List<StuBean> liststu = new ArrayList<>();
    List<ProductLine> listline = new ArrayList<>();
    List<CardBean> listCard = new ArrayList<>();
    List<CardInfiBean> listmoney = new ArrayList<>();
    int u = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        init();
        getData1("");
    }
    private void getData1(String str) {
        Log.d("ssss",str);
        HashMap<String,String> m1  = new HashMap<>();

        new Thread(){
            public void run() {
                super.run();
                t1 = new Timer();
                t1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                                //全部学生问题车辆
                                m1.put("", str);
                                JSONObject j1 = MyRe.re(m1, "/dataInterface/UserQuestion/getAll");
                                if (j1 != null) {
                                    if (j1.getString("message").equals("SUCCESS")) {
                                        JSONArray data = j1.getJSONArray("data");
                                        for (int i = 0; i < data.length(); i++) {
                                            JSONObject jsonObject = data.getJSONObject(i);
                                            p = new problem();
                                            p.setId(jsonObject.getString("id"));
                                            p.setCarId(jsonObject.getString("carId"));
                                            p.setUserProductionLineId(jsonObject.getString("userProductionLineId"));
                                            listp.add(p);
//                                            Log.i("学生问题车辆1", p + "");
                                        }
                                        send(1, 1);
                                    }
                                }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }, 0, 500);
            }
        }.start();
    }

    private void getData2(String str) {
        Log.d("ssss",str);
        HashMap<String, String> m2 = new HashMap<>();

        new Thread() {
            public void run() {
                super.run();
                t2 = new Timer();
                t2.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                                //获取生产线
                                m2.put("id", str);
                                JSONObject j2 = MyRe.re(m2, "/dataInterface/UserProductionLine/getInfo");
                                if (j2 != null ) {
                                    if (j2.getString("message").equals("SUCCESS")) {
                                        JSONArray data = j2.getJSONArray("data");
//                                        Log.i("232", data + "");
                                        for (int i = 0; i < data.length(); i++) {
                                            JSONObject jsonObject = data.getJSONObject(i);
                                            s = new StuBean();
                                            if (jsonObject.optString("userProductionLineId") == "") {
                                                s.setProductionLineId(jsonObject.optString("productionLineId"));
                                                s.setId(jsonObject.getString("id"));
                                                liststu.add(s);
                                                send(2, 1);

                                            }

                                        }

                                    }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }, 0, 500);
            }
        }.start();
    }

    private void getData3(String str) {
        Log.d("ssss",str);
        HashMap<String, String> m3 = new HashMap<>();

        new Thread() {
            public void run() {
                super.run();
                t3 = new Timer();
                t3.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                                //获取生产线
                                m3.put("id", str);
                                JSONObject j3 = MyRe.re(m3, "/dataInterface/ProductionLine/getInfo");
                                if (j3 != null) {
                                    if (j3.getString("message").equals("SUCCESS")) {
                                        JSONArray data1 = j3.getJSONArray("data");
                                        for (int i = 0; i < data1.length(); i++) {
                                            JSONObject jsp = data1.getJSONObject(i);
                                            line = new ProductLine();
                                            if (jsp.optString("isAI") == ""){
                                                line.setProductionLineName(jsp.optString("productionLineName"));
                                                line.setContent(jsp.optString("content"));
                                                line.setCarId(jsp.optString("carId"));
                                                line.setId(jsp.optString("id"));
                                                listline.add(line);
//                                                Log.i("=========666", line + "");
                                                send(3, 1);
                                            }

                                        }

                                    }

                                }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }, 0, 500);
            }
        }.start();
    }

    private void getData4(String str) {
        HashMap<String, String> m4 = new HashMap<>();

        new Thread() {
            public void run() {
                super.run();
                t4 = new Timer();
                t4.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                                //查询车辆
                                m4.put("id", str);
                                JSONObject j3 = MyRe.re(m4, "/dataInterface/Car/getInfo");
                                if (j3 != null) {

                                    if (j3.getString("message").equals("SUCCESS")) {
                                        JSONArray data2 = j3.getJSONArray("data");
                                        for (int i = 0; i < data2.length(); i++) {
                                            JSONObject jspc = data2.getJSONObject(i);
                                            c = new CardBean();

                                            if (jspc.optString("userProductionLineId") == "") {
                                           // Log.i("=========555", jspc + "");
                                                c.setCarName(jspc.getString("carName"));
                                                c.setContent(jspc.getString("content"));
                                                listCard.add(c);
                                                send(4, 1);
                                            }

                                        }

                                    }
                                }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }, 0, 500);
            }
        }.start();
    }

    private void getData5(String str) {
        HashMap<String, String> m5 = new HashMap<>();
        new Thread() {
            public void run() {
                super.run();
                t5 = new Timer();
                t5.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                                //查询车辆的维修费
                                m5.put("id", str);
                                JSONObject j5 = MyRe.re(m5, "/dataInterface/CarInfo/getAll");
                                if (j5 != null) {
                                    if (j5.getString("message").equals("SUCCESS")) {
                                        JSONArray datam = j5.getJSONArray("data");
                                        for (int m = 0; m < datam.length(); m++) {
                                            JSONObject jc = datam.getJSONObject(m);
                                            money = new CardInfiBean();

                                            if(jc.optString("carName") == ""){
                                                u++;
//                                                Log.i("=====8666", datam + "");
                                                money.setRepairGold(jc.optString("repairGold"));
                                                listmoney.add(money);
                                                if(u == 1) {
                                                    send(5, 1);
                                                }

                                            }

                                        }

                                    }

                                }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }, 0, 500);
            }
        }.start();
    }


    public void send(int what, Object obj) {
        Message message = Message.obtain();
        message.what = what;
        message.obj = obj;
        Problem.this.handler.sendMessage(message);
    }

    Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    t1.cancel();
                    getData2(p.getUserProductionLineId());
                    break;
                case 2:
                    t2.cancel();
                    getData3(s.getProductionLineId());
                    break;
                case 3:
                    t3.cancel();
                    getData4(p.getCarId());
                    break;
                case 4:
                    t4.cancel();
                    getData5(p.getCarId());
                    break;
                case 5:
                    t5.cancel();
                    addView();
                    break;
            }
        }
    };



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addView() {
        tableLayout.removeAllViews();
        Log.d("sss1",listp+"");
        Log.d("sss2",listCard+"");
        Log.d("sss3",listline+"");
        Log.d("sss4",liststu+"");
        Log.d("sss5",listmoney +"");

        for (problem bean : this.listp) {
            View view1 = View.inflate(Problem.this, R.layout.table_product, null);
            TextView textView1 = view1.findViewById(R.id.tp_1);
            TextView textView2 = view1.findViewById(R.id.tp_2);
            TextView textView3 = view1.findViewById(R.id.tp_3);
            TextView textView4 = view1.findViewById(R.id.tp_4);
            TextView textView5 = view1.findViewById(R.id.tp_5);
            textView1.setText(bean.getId());
            for(CardBean cardBean: this.listCard){

                textView2.setText(cardBean.getCarName());
                textView3.setText(cardBean.getContent());
                for (ProductLine productLine: this.listline){
                    for (StuBean stuBean: this.liststu){
                        textView4.setText(productLine.getProductionLineName()+"("+stuBean.getId()+")");
                        for (CardInfiBean cardInfiBean: this.listmoney){
                            textView5.setText(cardInfiBean.getRepairGold());
                        }
                    }
                }
            }
            tableLayout.addView(view1);
        }

    }


    //全部学生问题车辆
    private void getProblem() {
        new Thread() {
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    t1 = new Timer();
                    t1.schedule(new TimerTask() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            HashMap<String, String> r = new HashMap<>();
                            listp=new ArrayList<>();
                            JSONObject j1 = MyRe.re(r, "/dataInterface/UserQuestion/getAll");
                            if (j1 != null) {
                                try {
                                    if (j1.getString("message").equals("SUCCESS")) {
                                        JSONArray data = j1.getJSONArray("data");
                                        for (int i = 0; i < data.length(); i++) {
                                            JSONObject jsonObject = data.getJSONObject(i);
                                            p.setId(jsonObject.getString("id"));
                                            p.setCarId(jsonObject.getString("carId"));
                                            p.setUserProductionLineId(jsonObject.getString("userProductionLineId"));
                                            listp.add(p);
                                            Log.i("学生问题车辆1", p + "");
                                        }
                                        send(1,1);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, 0, 5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    //学生生产线
    private void getStuLine() {
        new Thread() {
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                    new Timer().schedule(new TimerTask() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            Map<String, String> r1 = new HashMap<>();
                            liststu=new ArrayList<>();
                            r1.put("id", p.getUserProductionLineId());
                            JSONObject j2 = MyRe.re(r1, "/dataInterface/UserProductionLine/getInfo");

                            if (j2 != null) {
                                try {
                                    if (j2.getString("message").equals("SUCCESS")) {
                                        JSONArray data = j2.getJSONArray("data");
                                        for (int i = 0; i < data.length(); i++) {
                                            JSONObject jsonObject = data.getJSONObject(i);
                                            s.setProductionLineId(jsonObject.getString("productionLineId"));
                                            s.setId(jsonObject.getString("id"));
                                            liststu.add(s);
                                            Log.i("==========232", s + "");
                                        }
                                        send(1,1);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, 0, 2000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

   //获取生产线
    private void getProduct() {
        new Thread() {
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                    new Timer().schedule(new TimerTask() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            Map<String, String> r2 = new HashMap<>();
                            listline =new ArrayList<>();
                            r2.put("id", s.getProductionLineId());
                            JSONObject j3 = MyRe.re(r2, "/dataInterface/ProductionLine/getInfo");

                            if (j3 != null) {
                                try {
                                    if (j3.getString("message").equals("SUCCESS")) {
                                        JSONArray data1 = j3.getJSONArray("data");
                                        for (int i = 0; i < data1.length(); i++) {
                                            JSONObject jsp = data1.getJSONObject(i);
                                            line.setProductionLineName(jsp.getString("productionLineName"));
                                            line.setContent(jsp.getString("content"));
                                            line.setCarId(jsp.getString("carId"));
                                            line.setId(jsp.getString("id"));
                                            listline.add(line);
                                            Log.i("=========666", line + "");
                                        }
                                        send(1,1);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, 0, 2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    //查询车辆
    private void getCard() {
        new Thread() {
            public void run() {
                super.run();
                try {

                    Thread.sleep(5000);
                    new Timer().schedule(new TimerTask() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            Map<String, String> r3 = new HashMap<>();
                            listCard=new ArrayList<>();
                            r3.put("id", p.getCarId());
                            JSONObject j3 = MyRe.re(r3, "/dataInterface/Car/getInfo");
                            if (j3 != null) {
                                try {
                                    if (j3.getString("message").equals("SUCCESS")) {
                                        JSONArray data2 = j3.getJSONArray("data");
                                        for (int i = 0; i < data2.length(); i++) {
                                            JSONObject jspc = data2.getJSONObject(i);
                                            c.setCarName(jspc.getString("carName"));
                                            c.setContent(jspc.getString("content"));
                                            listCard.add(c);
                                            Log.i("=========555", c + "");
                                        }
                                        send(1,1);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, 0, 2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

   //查询车辆的维修费
    private void getMoney() {
        new Thread() {
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Map<String, String> w = new HashMap<>();
                            listmoney=new ArrayList<>();
                            w.put("id",p.getCarId());
                            JSONObject jw = MyRe.re(w, "/dataInterface/CarInfo/getAll");
                            if (jw != null) {
                                try {
                                    if (jw.getString("message").equals("SUCCESS")) {
                                        JSONArray datam = jw.getJSONArray("data");
                                        for (int m = 0; m < datam.length(); m++) {
                                            JSONObject jc = datam.getJSONObject(m);
                                            money.setRepairGold(jc.getString("repairGold"));
                                            listmoney.add(money);
                                            Log.i("=====8666", money + "");
                                        }
                                        send(1,1);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }


                        }
                    }, 0, 2000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }


    private void init() {
        tableLayout = findViewById(R.id.tl_1);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                t1.cancel();
                finish();
                break;
        }
    }


}