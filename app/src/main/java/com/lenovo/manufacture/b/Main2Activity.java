package com.lenovo.manufacture.b;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.service.autofill.SaveCallback;
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
import com.lenovo.manufacture.zhy.three.Market;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        LitePal.initialize(Main2Activity.this);
        initView();
        a();
    }

    private void a() {
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
                                Temp temp=new Temp();
                                temp.setWorkshopTemp(js.getString("workshopTemp"));
                                temp.setOutTemp(js.getString("outTemp"));
                                Log.i("233333","getInfo"+temp.getOutTemp()+temp.getWorkshopTemp());

                                temp.saveAsync().listen(new org.litepal.crud.callback.SaveCallback() {
                                    @Override
                                    public void onFinish(boolean success) {
                                        if (success){
                                            Log.d("=======", "getInfo: 保存成功 ");
                                        }else{
                                            Log.d("=======", "getInfo: 保存失败 ");
                                        };
                                    }
                                });
                                    send(1,"");
                        }

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, 0, 5000);

    }

    public void send(int what,Object obj){
        Message message = Message.obtain();
        message.what=what;
        message.obj=obj;
       Main2Activity.this.handler.sendMessage(message);
    }

    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        public void handleMessage(@NonNull Message message){
            super.handleMessage(message);
            switch (message.what){
                case 1:
                    mpch();
                    break;
            }
        }
    };




    private void mpch() {
        mLc.setNoDataText("nodata");
        Description description = new Description();
        description.setEnabled(false);
        mLc.setDescription(description);
        mLc.setBackgroundColor(Color.WHITE);


        List<Entry> list = new ArrayList<>();
        List<Entry> list1 = new ArrayList<>();

        //data
        List<Temp> temp = LitePal.order("id desc").limit(12).find(Temp.class);
        Log.i("99999999",temp+"");
        int i = 0;
        for (Temp t : temp) {
            String s = t.getWorkshopTemp();
            s = s.substring(0, s.length() - 1);
            String ss = t.getOutTemp();
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


        LineData data = new LineData();
        data.addDataSet(dataSet1);
        data.addDataSet(dataSet2);


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
        mLc.setData(data);
        mLc.invalidate();


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
