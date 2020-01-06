package com.lenovo.manufacture.czx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Item2Activity extends AppCompatActivity implements View.OnClickListener{
    private ImageView bi;
    private TextView bt;
    private String workshopTemp;
    private String outTemp;
    /**
     * 25.6
     */
    private TextView mT1;
    /**
     * +
     */
    private Button mB1;
    /**
     * -
     */
    private Button mB2;
    private ImageView mI1;
    private ImageView mI2;
    private Timer t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item2);
        initView();
        temp();
    }

    private void initView() {
        bi = (ImageView) findViewById(R.id.bi);
        bt = (TextView) findViewById(R.id.bt);
        bi.setOnClickListener(this);
        bt.setText("空调温度设置");
        mT1 = (TextView) findViewById(R.id.t1);
        mB1 = (Button) findViewById(R.id.b1);
        mB1.setOnClickListener(this);
        mB2 = (Button) findViewById(R.id.b2);
        mB2.setOnClickListener(this);
        mI1 = (ImageView) findViewById(R.id.i1);
        mI2 = (ImageView) findViewById(R.id.i2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bi:
                finish();
                t.cancel();
                break;
            case R.id.b1:
                int a = 1;
                setting(a);

                break;
            case R.id.b2:
                int l = -1;
                setting(l);
                break;
        }

    }


    //获取json 室外和车间温度
    private void temp() {
        HashMap<String, String> m = new HashMap<>();
        m.put("id", "1");
//        JSONObject jo =MyRe.re(m,"/dataInterface/UserWorkEnvironmental/getInfo");
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
                            workshopTemp = js.getString("workshopTemp");
                            outTemp = js.getString("outTemp");
                            mT1.setText(workshopTemp);
                            automatic();

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5000);
    }

    // 自动空调开关功能
    private void automatic(){
        HashMap<String,String> m = new HashMap<>();

        if(qudiao(workshopTemp) > qudiao(outTemp)){
            mI1.setImageResource(R.drawable.cold0001);
            mI2.setImageResource(R.drawable.hot0001);
            m.put("id","1");
            m.put("acOnOff","1");
            JSONObject j = MyRe.re(m,"/dataInterface/UserWorkEnvironmental/updateAcOnOff");
//            Log.d("dd",j+"");
        }else if(qudiao(workshopTemp) < qudiao(outTemp)){
            mI1.setImageResource(R.drawable.cold0002);
            mI2.setImageResource(R.drawable.hot0002);
            m.put("id","1");
            m.put("acOnOff","2");
            MyRe.re(m,"/dataInterface/UserWorkEnvironmental/updateAcOnOff");
        }else{
            m.put("id","1");
            MyRe.re(m,"/dataInterface/UserWorkEnvironmental/updateAcOnOff");
            m.put("acOnOff","0");
            mI1.setImageResource(R.drawable.cold0002);
            mI2.setImageResource(R.drawable.hot0001);
        }

    }

    // 设置空调（上调或下调）
    private void setting(int i) {
        int s = qudiao(mT1.getText().toString().trim());
        Double sum = Double.valueOf(s);
        sum = sum + i;
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        mT1.setText(decimalFormat.format(sum)+"℃");

//        Log.d("=======",Integer.toString(sum));
    }


    // 去掉字符串中的摄氏度,并且强转为整形（也可以改double类型)
    private int qudiao(String string) {
        String b = null;
        String[] str = string.split("\\℃");
        for (int i = 0; i < str.length; ++i) {
            b = str[i];
        }
        int s = Integer.parseInt(b);
        return s;
    }

}
