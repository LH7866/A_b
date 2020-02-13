package com.lenovo.manufacture.czx;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;
import com.lenovo.manufacture.zhy.ShopBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//3：人才市场
public class Item8 extends AppCompatActivity implements View.OnClickListener {
    private ScrollView scrollView;
    private TableLayout tableRow;
    private Timer t = new Timer();
    private Button mbg;
    List<ShopBean> list8 = new ArrayList<>();
    String a , b= "1";
    private ImageView mIvShopping1;
    private ImageView mIy;
    private ImageView mIg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item8);
        initView();
        init();
        getData();
    }

    //获取数据
    private void getData() {
        HashMap<String, String> r = new HashMap<>();
        MyRe.re(r, "/Interface/index/getMaterial");
        t.schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                JSONObject j = MyRe.re(r, "/Interface/index/getMaterial");
                if (j != null) {
                    try {

                        if (j.getString("message").equals("获取原材料详情成功")) {
                            JSONArray data = j.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject js = data.getJSONObject(i);
                                ShopBean sb = new ShopBean();
                                sb.setId(js.getString("id"));
                                sb.setMaterialName(js.getString("materialName"));
                                sb.setContent(js.getString("content"));
                                sb.setPrice(js.getString("price"));
                                sb.setNum(js.getString("num"));
                                list8.add(sb);
                            }
                            send(1, "");

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 1200);

    }

    public void send(int what, Object obj) {
        Message message = Message.obtain();
        message.what = what;
        message.obj = obj;
        Item8.this.handler.sendMessage(message);
    }

    Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    addView(list8);
                    t.cancel();
                    break;
            }
        }
    };

    @SuppressLint("WrongViewCast")
    private void init() {
        mbg = findViewById(R.id.bg);
        mbg.setOnClickListener(this);
        scrollView = findViewById(R.id.sv_4);
        tableRow = findViewById(R.id.tl_4);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bg:
                break;
            case R.id.iv_shopping1:
             Intent intent = new Intent(this,Shopping.class);
                startActivity(intent);
                break;
            case R.id.iy:
                a="0";
                break;
            case R.id.ig:
                b="0";
                break;
        }
    }


    //动态加表格
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addView(List<ShopBean> i) {
        int x = 1;
        //清除残余表格
        tableRow.removeAllViews();
        if(b == "1") {
            list8.sort(new Comparator<ShopBean>() {
                @Override
                public int compare(ShopBean t1, ShopBean t2) {
                    return Integer.valueOf(t2.getNum()).compareTo(Integer.valueOf(t1.getNum()));
                }
            });
        }else{
            list8.sort(new Comparator<ShopBean>() {
                @Override
                public int compare(ShopBean t1, ShopBean t2) {
                    return Integer.valueOf(t1.getNum()).compareTo(Integer.valueOf(t2.getNum()));
                }
            });
        }
        for (ShopBean shopBean : i) {
            View view1 = View.inflate(Item8.this, R.layout.table_item, null);
            TextView textView1 = view1.findViewById(R.id.t_1);
            TextView textView2 = view1.findViewById(R.id.t_2);
            TextView textView3 = view1.findViewById(R.id.t_3);
            TextView textView4 = view1.findViewById(R.id.t_4);
            TextView textView5 = view1.findViewById(R.id.t_5);
            textView1.setText(shopBean.getId());
            textView2.setText(shopBean.getMaterialName());
            textView3.setText(shopBean.getContent());
            textView4.setText(shopBean.getPrice());
            textView5.setText(shopBean.getNum());
            tableRow.addView(view1);
//            int as = xjb(shopBean.getPrice(),shopBean.getNum(),x);
//            Log.d("SODSJID",as+"");
//            x++;
        }
        list8.clear();
    }

    //性价比
    private int xjb(String p ,String n,int x) {
        int y = 300;
        int z = 0 ;
        int a = Integer.parseInt(p)/Integer.parseInt(n);//200
        if(x>0){
            if(a<y){
                 z = a;
            }else{
                z = y ;
            }
        }
        return z ;
    }

    private void initView() {
        mIvShopping1 = (ImageView) findViewById(R.id.iv_shopping1);
        mIvShopping1.setOnClickListener(this);
        mIy = (ImageView) findViewById(R.id.iy);
        mIy.setOnClickListener(this);
        mIg = (ImageView) findViewById(R.id.ig);
        mIg.setOnClickListener(this);
    }
}
