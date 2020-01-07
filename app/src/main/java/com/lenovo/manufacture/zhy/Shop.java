package com.lenovo.manufacture.zhy;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;
import com.lenovo.manufacture.zhy.Bean.PeopleBean;
import com.lenovo.manufacture.zhy.Bean.ShopBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Shop extends AppCompatActivity implements View.OnClickListener {
    private TableLayout tableLayout;
    private Timer t = new Timer();
    private ImageView mIvShopping;
    List<ShopBean> list = new ArrayList<>();
    private TableLayout mTl3;
    private LinearLayout mLl3;
    private ScrollView mSv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();

    }

    private void getdata() {
        HashMap<String, String> m = new HashMap<>();

        t.schedule(new TimerTask() {
            @Override
            public void run() {


                try {
                    JSONObject j = MyRe.re(m, "/Interface/index/getMaterial");
                    //  Log.d("sss", j + "");
                    if (j != null) {

                        if (j.getString("message").equals("获取原材料详情成功")) {
                            JSONArray data = j.getJSONArray("data");
                            for (int i = 0; i <= data.length(); i++) {
                                JSONObject js = data.getJSONObject(i);
                                ShopBean sb = new ShopBean();
                                sb.setId(js.getString("id"));
                                sb.setMaterialName(js.getString("materialName"));
                                sb.setContent(js.getString("content"));
                                sb.setPrice(js.getString("price"));
                                sb.setNum(js.getString("num"));
                                list.add(sb);
                            }
                            send(1, "");

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 100, 1000000);

    }

    private void send(int what, Object obj) {

        Shop.this.handler.sendMessage(MyRe.getMessage(what, obj));

    }

    Handler handler = new Handler() {

        @SuppressLint("NewApi")
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void handleMessage(@NonNull Message msg) {

            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    addView(list);
                    break;
                default:
                    break;

            }
        }
    };

    //动态加表格
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addView(List<ShopBean> s) {
            mTl3.removeAllViews();
            for (ShopBean shopBean : s) {
                View view1 = View.inflate (Shop.this, R.layout.table_item, null);
                TextView textView1 = view1.findViewById (R.id.t_1);
                TextView textView2 = view1.findViewById (R.id.t_2);
                TextView textView3 = view1.findViewById (R.id.t_3);
                TextView textView4 = view1.findViewById (R.id.t_4);
                TextView textView5 = view1.findViewById (R.id.t_5);
                textView1.setText(shopBean.getId());
                textView2.setText(shopBean.getMaterialName());
                textView3.setText(shopBean.getContent());
                textView4.setText(shopBean.getPrice());
                textView5.setText(shopBean.getNum());
                mTl3.addView(view1);
            }
    }

    private void initView() {
        mIvShopping = (ImageView) findViewById(R.id.iv_shopping);
        mIvShopping.setOnClickListener(this);
        mTl3 = (TableLayout) findViewById(R.id.tl_3);
        mLl3 = (LinearLayout) findViewById(R.id.ll_3);
        mSv3 = (ScrollView) findViewById(R.id.sv_3);
    }

    @Override
    public void onClick(View view) {
        getdata();
    }
}
