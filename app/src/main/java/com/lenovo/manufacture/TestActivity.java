package com.lenovo.manufacture;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.manufacture.ReUse.MyRe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Amoly
 * @date 2019/10/24.
 * GitHub：
 * email：
 * description：
 */

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mI1;
    /**
     * 开启
     */
    private Button mB1;
    /**
     * 关闭
     */
    private Button mB2;
    private ImageView mBi;
    /**
     *
     */
    private TextView mBt;
    private Timer t;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();

        HashMap<String,String> m = new HashMap<>();

        m.put("id","1");
        MyRe.re(m, "/dataInterface/UserWorkEnvironmental/getInfo");
        t = new Timer();
                t.schedule(new TimerTask() {
            @Override
            public void run() {
                JSONObject j = MyRe.re(m, "/dataInterface/UserWorkEnvironmental/getInfo");
                if (j !=null) {
                    try {
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                            JSONObject js = data.getJSONObject(0);
                            final String lightSwitch = js.getString("lightSwitch");
                            if (lightSwitch.equals("1")) {
                                mI1.setBackgroundResource(R.drawable.gongchang_01);
                            } else if (lightSwitch.equals("0")) {
                                mI1.setBackgroundResource(R.drawable.gongchang02);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },0,1000);

    }


    private void initView() {
        mI1 = (ImageView) findViewById(R.id.I1);
        mB1 = (Button) findViewById(R.id.b1);
        mB1.setOnClickListener(this);
        mB2 = (Button) findViewById(R.id.b2);
        mB2.setOnClickListener(this);
        mBi = (ImageView) findViewById(R.id.bi);
        mBi.setOnClickListener(this);
        mBt = (TextView) findViewById(R.id.bt);
        mBt.setText("灯光控制");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.b1:
                mI1.setBackgroundResource(R.drawable.gongchang_01);
                openLight();
                break;
            case R.id.b2:
                mI1.setBackgroundResource(R.drawable.gongchang02);
                closeLight();
                break;
            case R.id.bi:
                finish();
                t.cancel();
                break;
        }
    }

    private void openLight() {

        HashMap<String,String>  m = new HashMap<>();
        m.put("id","1");
        m.put("lightSwitch","1");
        MyRe.re(m,"/dataInterface/UserWorkEnvironmental/updateLightSwitch");
    }

    private void closeLight() {
        HashMap<String,String>  m = new HashMap<>();
        m.put("id","1");
        m.put("lightSwitch","0");
        MyRe.re(m,"/dataInterface/UserWorkEnvironmental/updateLightSwitch");
    }

}
