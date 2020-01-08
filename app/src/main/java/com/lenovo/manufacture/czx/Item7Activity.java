package com.lenovo.manufacture.czx;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;
import com.lenovo.manufacture.czx.bean.sell;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Item7Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBi;
    /**
     *
     */
    private TextView mBt;
    private LineChart mLc2;
    private WebView mW1;
    private TableLayout mTl2;
    private LinearLayout mL2;
    private ScrollView mSv2;
    private Timer t = new Timer();
    List<sell> list;
    int a = 0,b = 0,c = 0,i = 1;
    /**
     * 18,150,000
     */
    private TextView mT4;
    /**
     * 120,000元
     */
    private TextView mT5;
    /**
     * 240,000元
     */
    private TextView mT6;
    /**
     * 80,000元
     */
    private TextView mT7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item7);
        initView();
        initLine();
        initTable();

//        mW1.getSettings().setJavaScriptEnabled(true);
//        mW1.loadUrl("file:///android_asset/l.html");
    }


    private void initTable() {
        HashMap<String, String> m = new HashMap<>();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                JSONObject j = MyRe.re(m, "/Interface/index/userSellInfoTEditer");
                if (j != null) {
                    try {
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
//                            Log.d("ssssdfdf",j.toString());
                            list = new ArrayList<>();
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject ja = data.getJSONObject(i);
                                Iterator<String> it = ja.keys();
                                JSONObject v = null;
                                sell se = null;
                                while (it.hasNext()) {
                                    String key = it.next();
                                    v = ja.getJSONObject(key);
                                    se = new sell();
                                    se.setName(v.getString("carTypeName"));
                                    se.setPrice(v.getString("price"));
                                    se.setTime(v.getString("time"));
                                    se.setNum(v.getString("num"));

                                }
                                list.add(se);
                            }
                            send(1, "");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }, 0, 500);

    }

    //动态添加表格
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void da(List<sell> l) {
        //清除残留记录
        mTl2.removeAllViews();
        int sum = 0;
        i++;
        for (sell s : l) {
            View view = LayoutInflater.from(Item7Activity.this).inflate(R.layout.table_item3, null);
            TextView cN = view.findViewById(R.id.tq_1);
            TextView p = view.findViewById(R.id.tq_2);
            TextView t = view.findViewById(R.id.tq_3);
            TextView n = view.findViewById(R.id.tq_4);

            cN.setText(s.getName());
            p.setText(s.getPrice());
            sum = sum + Integer.parseInt(s.getPrice());
            if(s.getName().equals("SUV汽车") || i==1){
                a = a +  Integer.parseInt(s.getPrice());
            }
            if(s.getName().equals("MPV汽车") || i==1){
                b = b +  Integer.parseInt(s.getPrice());
            }
            if(s.getName().equals("轿车汽车") || i==1){
                c = c +  Integer.parseInt(s.getPrice());
            }
            t.setText(tr(s.getTime()) + "");
            n.setText(s.getNum());
            mTl2.addView(view);
//            Log.d("sllslslss",tr(s.getTime())+"");
        }
        mT4.setText(sum+"");
        mT5.setText(a+" 元");
        mT6.setText(b+" 元");
        mT7.setText(c+" 元");
        a = 0;b = 0;c =0;
    }


    private static String tr(String millSecond) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(millSecond));
        return formatter.format(calendar.getTime());
    }

    private void send(int what, Object obj) {

        Item7Activity.this.handler.sendMessage(MyRe.getMessage(what, obj));

    }

    Handler handler = new Handler() {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    da(list);
                    t.cancel();
                default:
                    break;

            }
        }
    };

    private void initLine() {
        mLc2.setExtraOffsets(24f, 24f, 24, 0f);
        mLc2.animateXY(100, 100);
        setLegend();
        setDescription("");
        setX();
        setY();
        setData();
    }

    private void setLegend() {
        //设置样例
        Legend legend = new Legend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setFormSize(14f);
        legend.setTextColor(Color.BLACK);
    }

    private void setDescription(String s) {
        Description description = new Description();
        description.setText(s);
        mLc2.setDescription(description);

    }

    private void setX() {
        final XAxis xAxis = mLc2.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setLabelCount(7);
        xAxis.setTextSize(12f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "";
            }
        });
//        xAxis.setDrawAxisLine(false);
    }

    private void setY() {
        final YAxis yAxis = mLc2.getAxisLeft();
//            yAxis.setDrawAxisLine(false);
        yAxis.setLabelCount(7);
        yAxis.setAxisMaximum(350f);
        yAxis.setAxisMinimum(0f);
        yAxis.setGranularity(50);
//        yAxis.setDrawLabels(false);
        mLc2.getAxisRight().setEnabled(false); // 右侧Y轴不启用
    }

    private void setData() {
        List<Entry> yVals1 = new ArrayList<>();
        float[] ys1 = new float[]{
                65f, 85f, 125f, 145f, 205f, 265f, 285f};
        for (int i = 0; i < ys1.length; i++) {
            yVals1.add(new Entry(i * 2, ys1[i]));
        }

        // 分别通过每一组Entry对象集合的数据创建折线数据集
        LineDataSet lineDataSet1 = new LineDataSet(yVals1, "");
        lineDataSet1.setColor(Color.RED);
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setDrawValues(false);
        lineDataSet1.setLineWidth(1f); // 设置线宽为1
        // 3.将每一组折线数据集添加到折线数据中
        LineData lineData = new LineData(lineDataSet1);
        // 4.将折线数据设置给图表
        mLc2.setData(lineData);

    }

    private void initView() {
        mBi = (ImageView) findViewById(R.id.bi);
        mBi.setOnClickListener(this);
        mBt = (TextView) findViewById(R.id.bt);
        mBt.setText("销售报表");
        mLc2 = (LineChart) findViewById(R.id.lc2);
        mTl2 = (TableLayout) findViewById(R.id.tl2);
        mL2 = (LinearLayout) findViewById(R.id.li2);
        mSv2 = (ScrollView) findViewById(R.id.sv2);
        mT4 = (TextView) findViewById(R.id.t4);
        mT5 = (TextView) findViewById(R.id.t5);
        mT6 = (TextView) findViewById(R.id.t6);
        mT7 = (TextView) findViewById(R.id.t7);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bi:
                t.cancel();
                finish();
                break;
        }
    }

}
