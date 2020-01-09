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
    private Timer timer = new Timer();
    problem p = new problem();
    StuBean s = new StuBean();
    CardBean c = new CardBean();
    ProductLine line = new ProductLine();
    Map<String, String> b = new HashMap<>();
    CardInfiBean money = new CardInfiBean();
    List<problem> listp;
    List<StuBean> liststu;
    List<ProductLine> listline;
    List<CardBean> listCard;
    List<CardInfiBean> listmoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        init();
        getProblem();
        getStuLine();
        getProduct();
        getCard();
        getMoney();


    }

    //全部学生问题车辆
    private void getProblem() {
        new Thread() {
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    timer.schedule(new TimerTask() {
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

//   //获取生产线
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
                    addView();
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
    public void addView() {
        tableLayout.removeAllViews();
        loop:for (problem bean : listp) {
            View view1 = View.inflate(Problem.this, R.layout.table_product, null);
            TextView textView1 = view1.findViewById(R.id.tp_1);
            TextView textView2 = view1.findViewById(R.id.tp_2);
            TextView textView3 = view1.findViewById(R.id.tp_3);
            TextView textView4 = view1.findViewById(R.id.tp_4);
            TextView textView5 = view1.findViewById(R.id.tp_5);
            textView1.setText(bean.getId());
            for(CardBean cardBean: listCard){
                textView2.setText(cardBean.getCarName());
                textView3.setText(cardBean.getContent());
                for (ProductLine productLine: listline){
                    for (StuBean stuBean:liststu){
                        textView4.setText(productLine.getProductionLineName()+"("+stuBean.getId()+")");
                        for (CardInfiBean cardInfiBean:listmoney){
                            textView5.setText(cardInfiBean.getRepairGold());
                            continue loop;
                        }
                    }
                }
            }
            tableLayout.addView(view1);
        }
    }
}