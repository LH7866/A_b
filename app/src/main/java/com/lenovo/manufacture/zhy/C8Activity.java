package com.lenovo.manufacture.zhy;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;



public class C8Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mBack;
    /**
     * MPV生产线
     */
    private Button mMpv;
    /**
     * 轿车生产线
     */
    private Button mCar;
    /**
     *
     */
    private TextView mBt;
    /**
     * 位置1
     */
    private Button mBtn1;
    /**
     * 位置2
     */
    private Button mBtn2;
    /**
     * 位置3
     */
    private Button mBtn3;
    /**
     * 位置4
     */
    private Button mBtn4;
    private Timer t;
    /**
     * MPV生产线
     */
    private Button mBtnMpv;
    /**
     * 轿车生产线
     */
    private Button mBtnCar;
    /**
     * SUV生产线
     */
    private Button mBtnSuv;
    /**
     * 创建
     */
    private Button mBtnCreate;

    int lineId;
    int pos = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c8);
        initView();

    }

    private void a() {
        HashMap<String, String> m = new HashMap<>();
        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    JSONObject j = MyRe.re(m, "/dataInterface/UserProductionLine/getAll");
                    if (j != null) {
//                        Log.d("ssss",j+"");
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject js = data.getJSONObject(i);
                                String p = js.getString("position");

                                if (p.equals("1")) {
                                    no(p);
                                } else if (p.equals("2")) {
                                    no(p);
                                } else if (p.equals("3")) {
                                    no(p);
                                } else if (p.equals("0")) {
                                    no(p);
                                }
                            }
                            t.cancel();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 500);
    }

    //置灰
    private void no(String p) {
//        Log.d("sss",p);
        if (p.equals("0")) {
            mBtn1.setBackgroundColor(Color.GRAY);
            mBtn1.setEnabled(false);
        } else if (p.equals("1")) {
            mBtn2.setBackgroundColor(Color.GRAY);
            mBtn2.setEnabled(false);
        } else if (p.equals("2")) {
            mBtn3.setBackgroundColor(Color.GRAY);
            mBtn3.setEnabled(false);
        } else {
            mBtn4.setBackgroundColor(Color.GRAY);
            mBtn4.setEnabled(false);
        }
    }

    private void b(int i){
        if(i == 1){
            mBtnMpv.setBackgroundColor(Color.parseColor("#069E78"));
            mBtnCar.setBackgroundColor(Color.parseColor("#00CC99"));
            mBtnSuv.setBackgroundColor(Color.parseColor("#00CC99"));
        }else if(i == 2){
            mBtnMpv.setBackgroundColor(Color.parseColor("#00CC99"));
            mBtnCar.setBackgroundColor(Color.parseColor("#069E78"));
            mBtnSuv.setBackgroundColor(Color.parseColor("#00CC99"));
        }else {
            mBtnMpv.setBackgroundColor(Color.parseColor("#00CC99"));
            mBtnCar.setBackgroundColor(Color.parseColor("#00CC99"));
            mBtnSuv.setBackgroundColor(Color.parseColor("#069E78"));
        }
        mBtn1.setBackgroundColor(Color.parseColor("#169BD5"));
        mBtn2.setBackgroundColor(Color.parseColor("#169BD5"));
        mBtn3.setBackgroundColor(Color.parseColor("#169BD5"));
        mBtn4.setBackgroundColor(Color.parseColor("#169BD5"));
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mBt.setText("创建生产线");
        mBtn1 = (Button) findViewById(R.id.w1);
        mBtn1.setOnClickListener(this);
        mBtn2 = (Button) findViewById(R.id.w2);
        mBtn2.setOnClickListener(this);
        mBtn3 = (Button) findViewById(R.id.w3);
        mBtn3.setOnClickListener(this);
        mBtn4 = (Button) findViewById(R.id.w4);
        mBtn4.setOnClickListener(this);
        mBtnMpv = (Button) findViewById(R.id.mvp);
        mBtnMpv.setOnClickListener(this);
        mBtnCar = (Button) findViewById(R.id.car);
        mBtnCar.setOnClickListener(this);
        mBtnSuv = (Button) findViewById(R.id.suv);
        mBtnSuv.setOnClickListener(this);
        mBtnCreate = (Button) findViewById(R.id.create);
        mBtnCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bi:
                finish();
                break;
            case R.id.btn_mpv:
                lineId = 1;
                b(1);
                a();
                break;
            case R.id.btn_car:
                lineId = 2;
                b(2);//生产线和位置初始化
                a();//请求
                break;
            case R.id.btn_suv:
                lineId = 3;
                b(3);
                a();
                break;
            case R.id.btn_1:
                pos = 0;
                break;
            case R.id.btn_2:
                pos = 1;
                break;
            case R.id.btn_3:
                pos = 2;
                break;
            case R.id.btn_4:
                pos = 3;
                break;
            case R.id.btn_create:
                c(lineId,pos);
                break;
        }
    }

    private void c(int lineId, int pos) {
        HashMap<String,String> m2 = new HashMap<>();
        m2.put("lineId",lineId+"");
        m2.put("pos",pos+"");
//        Log.d("ssss",m2+"");
        MyRe.re(m2,"/Interface/index/createStudentLine");
        Toast.makeText(this,"该位置已存在生产线",Toast.LENGTH_SHORT).show();
    }

}
