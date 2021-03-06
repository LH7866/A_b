package com.lenovo.manufacture.b;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Main6Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBi;
    /**
     *
     */
    private TextView mBt;
    /**
     * 工厂资金：30000
     */
    private TextView mTvMoney;
    /**
     * 第一车间
     */
    private Button mBtn1;
    /**
     * 第二车间
     */
    private Button mBtn2;
    /**
     * 第三车间
     */
    private Button mBtn3;
    /**
     * 第四车间
     */
    private Button mBtn4;
    private LinearLayout mTb6;

    private Timer t;
    List<Material> list6 = new ArrayList<>();
    int x = 0, y = 0;
    private ImageView mJg;
    private ImageView mSl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        initView();
        a();
    }

    // 定时器
    private void a() {
        HashMap<String, String> m = new HashMap<>();
        MyRe.re(m, "/Interface/index/getMaterial");
        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    JSONObject j = MyRe.re(m, "/Interface/index/getMaterial");
                    if (j != null) {
                        if (j.getString("message").equals("获取原材料详情成功")) {
                            JSONArray data = j.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject js = data.getJSONObject(i);
                                Material m = new Material();
                                m.setMaterialName(js.getString("materialName"));
                                m.setPrice(js.getString("price"));
                                m.setNum(js.getString("num"));
                                m.setSupplyName(js.getString("supplyName"));
                                list6.add(m);
                            }
                            send(1, "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 500);

    }

    private void send(int what, Object obj) {
        Message message = Message.obtain();
        message.what = what;
        message.obj = obj;
        Main6Activity.this.handler.sendMessage(message);
    }

    Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    addView(list6);
                    t.cancel();
                    break;
                case 2:
                    mTvMoney.setText(msg.obj + "");
                    t.cancel();
                    break;
            }
        }
    };

    //动态加表格
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addView(List<Material> list) {
        //清除残余表格
        mTb6.removeAllViews();
        int i = 0;
        // 价格排序
        if ( x == 0) {
            i = 0;
            jg(i);
        } else if (x % 2 == 1){
            i = 1;
            jg(i);
            i = 0;
        }else {
            i = 2;
            jg(i);
            i = 0;
        }

        // 类型排序
        if ( y == 0) {
            i = 0;
            sl(i);
        } else if (y % 2 == 1){
            i = 1;
            sl(i);
            i = 0;
        }else {
            i = 2;
            sl(i);
            i = 0;
        }

        for (Material m : list) {
            View v = View.inflate(Main6Activity.this, R.layout.table_item5, null);
            TextView t1 = v.findViewById(R.id.tq6_1);
            TextView t2 = v.findViewById(R.id.tq6_2);
            TextView t3 = v.findViewById(R.id.tq6_3);
            TextView t4 = v.findViewById(R.id.tq6_4);
            t1.setText(m.getMaterialName());
            t2.setText(m.getPrice());
            t3.setText(m.getNum());
            t4.setText(m.getSupplyName());
            mTb6.addView(v);
            i++;
            if (i > 25) {
                break;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void jg(int str) {
        // 1降  2升
        if (str == 1) {
            list6.sort(new Comparator<Material>() {
                @Override
                public int compare(Material t1, Material t2) {
                    return Integer.valueOf(t2.getPrice()).compareTo(Integer.valueOf(t1.getPrice()));
                }
            });
            mJg.setImageResource(R.drawable.triangle0003);
        } else if (str == 2){
            list6.sort(new Comparator<Material>() {
                @Override
                public int compare(Material t1, Material t2) {
                    return Integer.valueOf(t1.getPrice()).compareTo(Integer.valueOf(t2.getPrice()));
                }
            });
            mJg.setImageResource(R.drawable.triangle0001);
        }else {
            mJg.setImageResource(R.drawable.triangle0002);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sl(int str) {
        // 1降  2升
        if (str == 1) {
            list6.sort(new Comparator<Material>() {
                @Override
                public int compare(Material t1, Material t2) {
                    return Integer.valueOf(t2.getNum()).compareTo(Integer.valueOf(t1.getNum()));
                }
            });
            mSl.setImageResource(R.drawable.triangle0003);
        } else if (str == 2){
            list6.sort(new Comparator<Material>() {
                @Override
                public int compare(Material t1, Material t2) {
                    return Integer.valueOf(t1.getNum()).compareTo(Integer.valueOf(t2.getNum()));
                }
            });
            mSl.setImageResource(R.drawable.triangle0001);
        }else {
            mSl.setImageResource(R.drawable.triangle0002);
        }
    }

    private void initView() {
        mBi = (ImageView) findViewById(R.id.bi);
        mBi.setOnClickListener(this);
        mBt = (TextView) findViewById(R.id.bt);
        mBt.setText("购买原材料");
        mTvMoney = (TextView) findViewById(R.id.tv_money);
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setOnClickListener(this);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn3.setOnClickListener(this);
        mBtn4 = (Button) findViewById(R.id.btn4);
        mBtn4.setOnClickListener(this);
        mTb6 = (LinearLayout) findViewById(R.id.tb_6);

        mJg = (ImageView) findViewById(R.id.jg);
        mJg.setOnClickListener(this);
        mSl = (ImageView) findViewById(R.id.sl);
        mSl.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bi:
                finish();
                t.cancel();
                break;
            case R.id.btn1:
                bs(1);
                break;
            case R.id.btn2:
                bs(2);
                break;
            case R.id.btn3:
                bs(3);
                break;
            case R.id.btn4:
                bs(4);
                break;
            case R.id.jg:
                x++;
                y=0;
                addView(list6);
                break;
            case R.id.sl:
                y++;
                x=0;
                addView(list6);
                break;
        }
    }

    private void bs(int i) {
        if (i == 1) {
            mBtn1.setBackgroundColor(Color.RED);
            mBtn2.setBackgroundColor(Color.WHITE);
            mBtn3.setBackgroundColor(Color.WHITE);
            mBtn4.setBackgroundColor(Color.WHITE);
            zj(i);
        }
        if (i == 2) {
            mBtn2.setBackgroundColor(Color.RED);
            mBtn1.setBackgroundColor(Color.WHITE);
            mBtn3.setBackgroundColor(Color.WHITE);
            mBtn4.setBackgroundColor(Color.WHITE);
            zj(i);
        }
        if (i == 3) {
            mBtn3.setBackgroundColor(Color.RED);
            mBtn2.setBackgroundColor(Color.WHITE);
            mBtn1.setBackgroundColor(Color.WHITE);
            mBtn4.setBackgroundColor(Color.WHITE);
            zj(i);
        }
        if (i == 4) {
            mBtn4.setBackgroundColor(Color.RED);
            mBtn2.setBackgroundColor(Color.WHITE);
            mBtn3.setBackgroundColor(Color.WHITE);
            mBtn1.setBackgroundColor(Color.WHITE);
            zj(i);
        }
    }

    private void zj(int i) {
        t = new Timer();
        HashMap<String, String> m = new HashMap<>();
        m.put("id", i + "");
        Log.d("ss", i + "");
        MyRe.re(m, "/dataInterface/UserWorkInfo/getInfo");
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    JSONObject j = MyRe.re(m, "/dataInterface/UserWorkInfo/getInfo");
                    if (j != null) {

                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                            if (data.toString().equals("[]")) {
                                t.cancel();
                            } else {
                                JSONObject js = data.getJSONObject(0);
                                if (js.getString("id").equals(i + "")) {
                                    send(2, js.getString("price"));
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
}
