package com.lenovo.manufacture.b;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBi;
    /**
     *
     */
    private TextView mBt;
    /**
     * 生产环节
     */
    private TextView mTe1;
    private FrameLayout mLi1;
    /**
     * 生产环节
     */
    private TextView mTe2;
    private FrameLayout mLi2;
    /**
     * 生产环节
     */
    private TextView mTe3;
    private FrameLayout mLi3;
    /**
     * 生产环节
     */
    private TextView mTe4;
    private FrameLayout mLi4;
    /**
     * 生产环节
     */
    private TextView mTe5;
    private FrameLayout mLi5;
    /**
     * 生产环节
     */
    private TextView mTe6;
    private FrameLayout mLi6;
    /**
     * 生产环节
     */
    private TextView mTe7;
    private FrameLayout mLi7;
    /**
     * 生产环节
     */
    private TextView mTe8;
    private FrameLayout mLi8;
    /**
     * 生产环节
     */
    private TextView mTe9;
    private FrameLayout mLi9;
    /**
     * 生产环节
     */
    private TextView mTe10;
    private FrameLayout mLi10;
    /**
     * 生产环节
     */
    private TextView mTe11;
    private FrameLayout mLi11;
    /**
     * 生产环节
     */
    private TextView mTe12;
    private FrameLayout mLi12;
    /**
     * 生产环节
     */
    private TextView mTe13;
    private FrameLayout mLi13;
    /**
     * 生产环节
     */
    private TextView mTe14;
    private FrameLayout mLi14;
    /**
     * 生产环节
     */
    private TextView mTe15;
    private FrameLayout mLi15;
    /**
     * 生产环节
     */
    private TextView mTe16;
    private FrameLayout mLi16;
    /**
     * 生产环节
     */
    private TextView mTe17;
    private FrameLayout mLi17;
    /**
     * 生产环节
     */
    private TextView mTe18;
    private FrameLayout mLi18;
    /**
     * 生产环节
     */
    private TextView mTe19;
    private FrameLayout mLi19;
    /**
     * 生产环节
     */
    private TextView mTe20;
    private FrameLayout mLi20;
    private Timer t;
    private List<Product> lb6;
    /**
     * HP:100/100
     */
    private TextView mTx1;
    /**
     * HP:100/100
     */
    private TextView mTx2;
    /**
     * HP:100/100
     */
    private TextView mTx3;
    /**
     * HP:100/100
     */
    private TextView mTx4;
    /**
     * HP:100/100
     */
    private TextView mTx5;
    /**
     * HP:100/100
     */
    private TextView mTx6;
    /**
     * HP:100/100
     */
    private TextView mTx7;
    /**
     * HP:100/100
     */
    private TextView mTx8;
    /**
     * HP:100/100
     */
    private TextView mTx9;
    /**
     * HP:100/100
     */
    private TextView mTx10;
    /**
     * HP:100/100
     */
    private TextView mTx11;
    /**
     * HP:100/100
     */
    private TextView mTx12;
    /**
     * HP:100/100
     */
    private TextView mTx13;
    /**
     * HP:100/100
     */
    private TextView mTx14;
    /**
     * HP:100/100
     */
    private TextView mTx15;
    /**
     * HP:100/100
     */
    private TextView mTx16;
    /**
     * HP:100/100
     */
    private TextView mTx17;
    /**
     * HP:100/100
     */
    private TextView mTx18;
    /**
     * HP:100/100
     */
    private TextView mTx19;
    /**
     * HP:100/100
     */
    private TextView mTx20;
    private ImageView mIma1;
    private ImageView mIma2;
    private ImageView mIma3;
    private ImageView mIma4;
    private ImageView mIma5;
    private ImageView mIma6;
    private ImageView mIma7;
    private ImageView mIma8;
    private ImageView mIma9;
    private ImageView mIma10;
    private ImageView mIma11;
    private ImageView mIma12;
    private ImageView mIma13;
    private ImageView mIma14;
    private ImageView mIma15;
    private ImageView mIma16;
    private ImageView mIma17;
    private ImageView mIma18;
    private ImageView mIma19;
    private ImageView mIma20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        initView();
        a();
    }


    private void a() {
        HashMap<String, String> m = new HashMap<>();
        lb6 = new ArrayList<>();
        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    JSONObject j = MyRe.re(m, "/dataInterface/UserPlStepInfo/getAll");
                    if (j != null) {
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                            for (int i = 0; i < 60; i++) {
                                JSONObject js = data.getJSONObject(i);
//                               Log.d("ssss",js.getString("plStepName"));
                                Product p = new Product();
                                p.setPlStepName(js.getString("plStepName"));
                                p.setStageId(js.getString("stageId"));
                                p.setPower(js.getString("power"));
                                p.setConsume(js.getString("consume"));
                                p.setId(js.getString("id"));
                                lb6.add(p);
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
        Main4Activity.this.handler.sendMessage(message);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    sz(lb6);
                    t.cancel();
                    break;
            }
        }
    };

    private void sz(List<Product> lb6) {
        int i = 0;

        for (Product p : lb6) {

//            Log.d("sss",p.getStageId()+"  "+p.getPlStepName()+"  "+i);
            sj(p.getStageId(), p.getPlStepName(), p.getPower(), p.getConsume());
//            Log.d("sss",p.getId()+"          "+i++);
        }
        lb6.clear();

    }


    private void sj(String stageId, String plStepName, String power, String consume) {
        if (stageId.equals("5")) {
            mTe1.setText(plStepName);
            mTx1.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi1.setBackgroundColor(Color.GRAY);
                mIma1.setVisibility(View.VISIBLE);
            }
            mLi1.setBackgroundColor(Color.parseColor("#82AAFF"));
        }
        if (stageId.equals("6")) {
            mTe2.setText(plStepName);
            mTx2.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi2.setBackgroundColor(Color.GRAY);
                mIma2.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("7")) {
            mTe3.setText(plStepName);
            mTx3.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi3.setBackgroundColor(Color.GRAY);
                mIma3.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("8")) {
            mTe4.setText(plStepName);
            mTx4.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi4.setBackgroundColor(Color.GRAY);
                mIma4.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("9")) {
            mTe5.setText(plStepName);
            mTx5.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi5.setBackgroundColor(Color.GRAY);
                mIma5.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("10")) {
            mTe6.setText(plStepName);
            mTx6.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi6.setBackgroundColor(Color.GRAY);
                mIma6.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("11")) {
            mTe7.setText(plStepName);
            mTx7.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi7.setBackgroundColor(Color.GRAY);
                mIma7.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("12")) {
            mTe8.setText(plStepName);
            mTx8.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi8.setBackgroundColor(Color.GRAY);
                mIma8.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("13")) {
            mTe9.setText(plStepName);
            mTx9.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi9.setBackgroundColor(Color.GRAY);
                mIma9.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("14")) {
            mTe10.setText(plStepName);
            mTx10.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi10.setBackgroundColor(Color.GRAY);
                mIma10.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("15")) {
            mTe11.setText(plStepName);
            mTx11.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi11.setBackgroundColor(Color.GRAY);
                mIma11.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("16")) {
            mTe12.setText(plStepName);
            mTx12.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi12.setBackgroundColor(Color.GRAY);
                mIma12.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("17")) {
            mTe13.setText(plStepName);
            mTx13.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi13.setBackgroundColor(Color.GRAY);
                mIma13.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("18")) {
            mTe14.setText(plStepName);
            mTx14.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi14.setBackgroundColor(Color.GRAY);
                mIma14.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("19")) {
            mTe15.setText(plStepName);
            mTx15.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi15.setBackgroundColor(Color.GRAY);
                mIma15.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("20")) {
            mTe16.setText(plStepName);
            mTx16.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi16.setBackgroundColor(Color.GRAY);
                mIma16.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("21")) {
            mTe17.setText(plStepName);
            mTx17.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi17.setBackgroundColor(Color.GRAY);
                mIma17.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("22")) {
            mTe18.setText(plStepName);
            mTx18.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi18.setBackgroundColor(Color.GRAY);
                mIma18.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("23")) {
            mTe19.setText(plStepName);
            mTx19.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi19.setBackgroundColor(Color.GRAY);
                mIma19.setVisibility(View.VISIBLE);
            }
        }
        if (stageId.equals("24")) {
            mTe20.setText(plStepName);
            mTx20.setText("HP : " + power + "/100");
            if (Integer.parseInt(power) < Integer.parseInt(consume)) {
                mLi20.setBackgroundColor(Color.GRAY);
                mIma20.setVisibility(View.VISIBLE);
            }
        }
    }


    private void initView() {
        mBi = (ImageView) findViewById(R.id.bi);
        mBi.setOnClickListener(this);
        mBt = (TextView) findViewById(R.id.bt);
        mBt.setText("生产环节");
        mTe1 = (TextView) findViewById(R.id.te1);
        mLi1 = (FrameLayout) findViewById(R.id.li1);
        mTe2 = (TextView) findViewById(R.id.te2);
        mLi2 = (FrameLayout) findViewById(R.id.li2);
        mTe3 = (TextView) findViewById(R.id.te3);
        mLi3 = (FrameLayout) findViewById(R.id.li3);
        mTe4 = (TextView) findViewById(R.id.te4);
        mLi4 = (FrameLayout) findViewById(R.id.li4);
        mTe5 = (TextView) findViewById(R.id.te5);
        mLi5 = (FrameLayout) findViewById(R.id.li5);
        mTe6 = (TextView) findViewById(R.id.te6);
        mLi6 = (FrameLayout) findViewById(R.id.li6);
        mTe7 = (TextView) findViewById(R.id.te7);
        mLi7 = (FrameLayout) findViewById(R.id.li7);
        mTe8 = (TextView) findViewById(R.id.te8);
        mLi8 = (FrameLayout) findViewById(R.id.li8);
        mTe9 = (TextView) findViewById(R.id.te9);
        mLi9 = (FrameLayout) findViewById(R.id.li9);
        mTe10 = (TextView) findViewById(R.id.te10);
        mLi10 = (FrameLayout) findViewById(R.id.li10);
        mTe11 = (TextView) findViewById(R.id.te11);
        mLi11 = (FrameLayout) findViewById(R.id.li11);
        mTe12 = (TextView) findViewById(R.id.te12);
        mLi12 = (FrameLayout) findViewById(R.id.li12);
        mTe13 = (TextView) findViewById(R.id.te13);
        mLi13 = (FrameLayout) findViewById(R.id.li13);
        mTe14 = (TextView) findViewById(R.id.te14);
        mLi14 = (FrameLayout) findViewById(R.id.li14);
        mTe15 = (TextView) findViewById(R.id.te15);
        mLi15 = (FrameLayout) findViewById(R.id.li15);
        mTe16 = (TextView) findViewById(R.id.te16);
        mLi16 = (FrameLayout) findViewById(R.id.li16);
        mTe17 = (TextView) findViewById(R.id.te17);
        mLi17 = (FrameLayout) findViewById(R.id.li17);
        mTe18 = (TextView) findViewById(R.id.te18);
        mLi18 = (FrameLayout) findViewById(R.id.li18);
        mTe19 = (TextView) findViewById(R.id.te19);
        mLi19 = (FrameLayout) findViewById(R.id.li19);
        mTe20 = (TextView) findViewById(R.id.te20);
        mLi20 = (FrameLayout) findViewById(R.id.li20);
        mBt.setOnClickListener(this);
        mTe1.setOnClickListener(this);
        mTx1 = (TextView) findViewById(R.id.tx1);
        mLi1.setOnClickListener(this);
        mTe2.setOnClickListener(this);
        mTx2 = (TextView) findViewById(R.id.tx2);
        mLi2.setOnClickListener(this);
        mTe3.setOnClickListener(this);
        mTx3 = (TextView) findViewById(R.id.tx3);
        mLi3.setOnClickListener(this);
        mTe4.setOnClickListener(this);
        mTx4 = (TextView) findViewById(R.id.tx4);
        mLi4.setOnClickListener(this);
        mTe5.setOnClickListener(this);
        mTx5 = (TextView) findViewById(R.id.tx5);
        mLi5.setOnClickListener(this);
        mTe6.setOnClickListener(this);
        mTx6 = (TextView) findViewById(R.id.tx6);
        mLi6.setOnClickListener(this);
        mTe7.setOnClickListener(this);
        mTx7 = (TextView) findViewById(R.id.tx7);
        mLi7.setOnClickListener(this);
        mTe8.setOnClickListener(this);
        mTx8 = (TextView) findViewById(R.id.tx8);
        mLi8.setOnClickListener(this);
        mTe9.setOnClickListener(this);
        mTx9 = (TextView) findViewById(R.id.tx9);
        mLi9.setOnClickListener(this);
        mTe10.setOnClickListener(this);
        mTx10 = (TextView) findViewById(R.id.tx10);
        mLi10.setOnClickListener(this);
        mTe11.setOnClickListener(this);
        mTx11 = (TextView) findViewById(R.id.tx11);
        mLi11.setOnClickListener(this);
        mTe12.setOnClickListener(this);
        mTx12 = (TextView) findViewById(R.id.tx12);
        mLi12.setOnClickListener(this);
        mTe13.setOnClickListener(this);
        mTx13 = (TextView) findViewById(R.id.tx13);
        mLi13.setOnClickListener(this);
        mTe14.setOnClickListener(this);
        mTx14 = (TextView) findViewById(R.id.tx14);
        mLi14.setOnClickListener(this);
        mTe15.setOnClickListener(this);
        mTx15 = (TextView) findViewById(R.id.tx15);
        mLi15.setOnClickListener(this);
        mTe16.setOnClickListener(this);
        mTx16 = (TextView) findViewById(R.id.tx16);
        mLi16.setOnClickListener(this);
        mTe17.setOnClickListener(this);
        mTx17 = (TextView) findViewById(R.id.tx17);
        mLi17.setOnClickListener(this);
        mTe18.setOnClickListener(this);
        mTx18 = (TextView) findViewById(R.id.tx18);
        mLi18.setOnClickListener(this);
        mTe19.setOnClickListener(this);
        mTx19 = (TextView) findViewById(R.id.tx19);
        mLi19.setOnClickListener(this);
        mTe20.setOnClickListener(this);
        mTx20 = (TextView) findViewById(R.id.tx20);
        mLi20.setOnClickListener(this);
        mIma1 = (ImageView) findViewById(R.id.ima1);
        mTx2.setOnClickListener(this);
        mIma2 = (ImageView) findViewById(R.id.ima2);
        mTx3.setOnClickListener(this);
        mIma3 = (ImageView) findViewById(R.id.ima3);
        mTx4.setOnClickListener(this);
        mIma4 = (ImageView) findViewById(R.id.ima4);
        mTx5.setOnClickListener(this);
        mIma5 = (ImageView) findViewById(R.id.ima5);
        mTx6.setOnClickListener(this);
        mIma6 = (ImageView) findViewById(R.id.ima6);
        mTx7.setOnClickListener(this);
        mIma7 = (ImageView) findViewById(R.id.ima7);
        mTx8.setOnClickListener(this);
        mIma8 = (ImageView) findViewById(R.id.ima8);
        mTx9.setOnClickListener(this);
        mIma9 = (ImageView) findViewById(R.id.ima9);
        mTx10.setOnClickListener(this);
        mIma10 = (ImageView) findViewById(R.id.ima10);
        mTx11.setOnClickListener(this);
        mIma11 = (ImageView) findViewById(R.id.ima11);
        mTx12.setOnClickListener(this);
        mIma12 = (ImageView) findViewById(R.id.ima12);
        mTx13.setOnClickListener(this);
        mIma13 = (ImageView) findViewById(R.id.ima13);
        mTx14.setOnClickListener(this);
        mIma14 = (ImageView) findViewById(R.id.ima14);
        mTx15.setOnClickListener(this);
        mIma15 = (ImageView) findViewById(R.id.ima15);
        mTx16.setOnClickListener(this);
        mIma16 = (ImageView) findViewById(R.id.ima16);
        mTx17.setOnClickListener(this);
        mIma17 = (ImageView) findViewById(R.id.ima17);
        mTx18.setOnClickListener(this);
        mIma18 = (ImageView) findViewById(R.id.ima18);
        mTx19.setOnClickListener(this);
        mIma19 = (ImageView) findViewById(R.id.ima19);
        mTx20.setOnClickListener(this);
        mIma20 = (ImageView) findViewById(R.id.ima20);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
            case R.id.bi:
                finish();
                t.cancel();
                break;
            case R.id.bt:
                break;
        }
    }
}
