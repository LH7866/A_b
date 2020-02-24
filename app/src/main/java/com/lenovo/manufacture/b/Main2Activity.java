package com.lenovo.manufacture.b;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
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
import com.lenovo.manufacture.b.fragment.Temp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mBi;
    /**
     * 生产线环节
     */
    private TextView mBt;
    /**
     * 21.3℃
     */
    private TextView mT2;
    /**
     * 32.6℃
     */
    private TextView mT3;
    private LineChart mLc;
    private Timer t;
    List<Temp> temps=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        LitePal.initialize(Main2Activity.this);

        initView();
        HashMap<String, String> m = new HashMap<>();
        m.put("id", "1");
        t = new Timer();
        t.schedule(new TimerTask() {



            @Override
            public void run() {
                JSONObject j = MyRe.re(m, "/dataInterface/UserWorkEnvironmental/getInfo");
                try {
                    if (j != null) {
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                            JSONObject js = data.getJSONObject(0);
                            String workshopTemp = js.getString("workshopTemp");
                            String outTemp = js.getString("outTemp");
                            Log.i("666666", outTemp + "");
//                            mT2.setText(outTemp);
//                            mT3.setText(workshopTemp);

                            for(int i=0;i<temps.size();i++){
                                Temp temp=new Temp();
                                temp.setOutTemp(js.getString("workshopTemp"));
                                temp.setWorkshopTemp(js.getString("outTemp"));
                                temps.add(temp);
                            }
                            mLc.setNoDataText("nodata");
                            Description description = new Description();
                            description.setEnabled(false);
                            mLc.setDescription(description);
                            mLc.setBackgroundColor(Color.WHITE);


                            List<Entry> list = new ArrayList<>();
                            List<Entry> list1 = new ArrayList<>();

                            //data
                            List<Temp> temps = LitePal.order("id desc").limit(12).find(Temp.class);
                            Log.i("99999999",temps+"");
                            int i = 0;
                            for (Temp temp : temps) {
                                String s = temp.getWorkshopTemp();
                                s = s.substring(0, s.length() - 1);
                                String ss = temp.getOutTemp();
                                ss = ss.substring(0, ss.length() - 1);
                                list1.add(new Entry(i, Integer.parseInt(s)));
                                list.add(new Entry(i++, Integer.parseInt(ss)));
                            }

                            LineDataSet dataSet1 = new LineDataSet(list, "");
                            dataSet1.setDrawCircles(false);
                            dataSet1.setColor(Color.parseColor("#FF5B5B"));
                            dataSet1.setDrawValues(false);


                            LineDataSet dataSet2 = new LineDataSet(list1, "");
                            dataSet2.setDrawValues(false);
                            dataSet2.setColor(Color.parseColor("#82AAFF"));
                            dataSet2.setDrawValues(false);


                            LineData data1 = new LineData();
                            data1.addDataSet(dataSet1);
                            data1.addDataSet(dataSet2);


                            XAxis axis = mLc.getXAxis();
                            axis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            axis.setLabelCount(12);
                            String[] time = new String[]{"00:00", "02:00", "04:00", "06:00", "08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "22:00", "24:00"};
                            axis.setValueFormatter(new ValueFormatter() {
                                @Override
                                public String getFormattedValue(float value, AxisBase axis) {
                                    return time[(int) value];
                                }
                            });

                            //关闭右
                            mLc.getAxisRight().setEnabled(false);
                            YAxis yAxis = mLc.getAxisLeft();
                            yAxis.setLabelCount(10);
                            yAxis.setValueFormatter(new ValueFormatter() {
                                @Override
                                public String getFormattedValue(float value, AxisBase axis) {
                                    return "";

                                }
                            });

                            mLc.getLegend().setEnabled(false);
                            mLc.setData(data1);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, 0, 5000);



    }

    private void a() {

    }



    private void mpch() {



//
//    private void initLine() {
//        mLc.setExtraOffsets(24f,24f,24,0f);
//        mLc.setBackgroundColor(Color.parseColor("#ffffff"));
//        mLc.animateXY(100,100);
//        setLegend();
//        setDescription("");
//        setX();
//        setY();
//        setData();
//    }
//
//    private void setLegend() {
//        //设置样例
//        Legend legend = new Legend();
//        legend.setForm(Legend.LegendForm.LINE);
//        legend.setFormSize(14f);
//        legend.setTextColor(Color.BLACK);
//    }
//
//    private void setDescription(String s) {
//        Description description = new Description();
//        description.setText(s);
//        mLc.setDescription(description);
//
//    }
//
//    private void setX() {
//        //设置x轴
//        final XAxis xAxis = mLc.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(true);
//        xAxis.setLabelCount(13);
//        xAxis.setTextSize(12f);
//        xAxis.setDrawAxisLine(false);
//        xAxis.setGranularity(2f);
//        xAxis.setAxisMaximum(24.00f);
//        xAxis.setAxisMinimum(00.00f);
//
//    }
//
//    private void setY() {
//        //设置y轴
//        final YAxis yAxis = mLc.getAxisLeft();
////            yAxis.setDrawAxisLine(false);
//        yAxis.setLabelCount(7);
////            yAxis.setDrawLabels(false);
//        yAxis.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                return "";
//            }
//        });
//        mLc.getAxisRight().setEnabled(false); // 右侧Y轴不启用
//
//    }
//
//    private void setData() {
//        List<Entry> yVals1 = new ArrayList<>();
//        float[] ys1 = new float[] {
//                9f, 19f, 6f, 8f, 10f, 18f, 11f, 16f, 19f, 3f,
//                21f, 2f, 24f};
//        for (int i = 0; i < ys1.length; i++) {
//            yVals1.add(new Entry(i * 2,ys1[i]));
//        }
//
//        // 分别通过每一组Entry对象集合的数据创建折线数据集
//        LineDataSet lineDataSet1 = new LineDataSet(yVals1, "");
//        lineDataSet1.setColor(Color.RED);
//        lineDataSet1.setDrawCircles(false);
//        lineDataSet1.setDrawValues(false);
//        lineDataSet1.setLineWidth(1f); // 设置线宽为1
//        // 3.将每一组折线数据集添加到折线数据中
//        LineData lineData = new LineData(lineDataSet1);
//        // 4.将折线数据设置给图表
//        mLc.setData(lineData);
   }

    private void initView() {
        mBi = (ImageView) findViewById(R.id.bi);
        mBt = (TextView) findViewById(R.id.bt);
        mBt.setText("温度统计");
        mBi.setOnClickListener(this);
        mT2 = (TextView) findViewById(R.id.t2);
        mT3 = (TextView) findViewById(R.id.t3);
        mLc = (LineChart) findViewById(R.id.lc);
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
