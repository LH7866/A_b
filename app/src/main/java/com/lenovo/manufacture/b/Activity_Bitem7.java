package com.lenovo.manufacture.b;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Bitem7 extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout l_m;
    private LinearLayout l_c;
    private LinearLayout l_s;

    private Timer t;
    private ImageView bi;
    private TextView bt;
    private Button btnMpv;
    private Button btnCar;
    private Button btnSuv;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btnCreate;

    private LinearLayout i_1;
    private int lineId;//生产线
    private int pos = 4;//位置
    private LinearLayout po1;
    private LinearLayout po2;
    private LinearLayout po3;
    private LinearLayout po4;
    private LinearLayout lCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__bitem7);
        initView();
    }

    private void initView() {
        bi = (ImageView) findViewById(R.id.bi);
        bi.setOnClickListener(this);
        bt = (TextView) findViewById(R.id.bt);
        bt.setText("创建学生生产线");

        btnMpv = (Button) findViewById(R.id.btn_mpv);
        btnMpv.setOnClickListener(this);
        btnCar = (Button) findViewById(R.id.btn_car);
        btnCar.setOnClickListener(this);
        btnSuv = (Button) findViewById(R.id.btn_suv);
        btnSuv.setOnClickListener(this);

        btn1 = (Button) findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);

        btnCreate = (Button) findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(this);

        po1 = (LinearLayout) findViewById(R.id.po1);
        po2 = (LinearLayout) findViewById(R.id.po2);
        po3 = (LinearLayout) findViewById(R.id.po3);
        po4 = (LinearLayout) findViewById(R.id.po4);
        lCreate = (LinearLayout) findViewById(R.id.l_create);

        l_m =findViewById(R.id.l_m);
        l_c =findViewById(R.id.l_c);
        l_s = findViewById(R.id.l_s);
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
                newP();//初始化车间按钮
                changeBtn(1);
                findData("1");
                lineId = 1;
                //Toast.makeText(Activity_Bitem7.this, lineId + "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_car:
                newP();
                changeBtn(2);
                findData("2");
                lineId = 2;
                Toast.makeText(Activity_Bitem7.this, lineId + "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_suv:
                newP();
                changeBtn(3);
                findData("3");
                lineId = 3;
                //Toast.makeText(Activity_Bitem7.this, lineId + "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_1:
                changeBtn(4);
                pos = 0;
                Toast.makeText(Activity_Bitem7.this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_2:
                changeBtn(5);
                pos = 1;
                Toast.makeText(Activity_Bitem7.this, "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_3:
                changeBtn(6);
                pos = 2;
                Toast.makeText(Activity_Bitem7.this, "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_4:
                changeBtn(7);
                pos =3;
                Toast.makeText(Activity_Bitem7.this, "4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_create:
                changeBtn(8);
                createBtn(pos,lineId);
                break;
        }
    }

    //选中状态1
    private void changeBtn(int i) {
        if (i == 1) {
            l_m.setElevation(0);
            l_c.setElevation(10);
            l_s.setElevation(10);
            lCreate.setElevation(10);
        } else if (i == 2) {
            l_m.setElevation(10);
            l_c.setElevation(0);
            l_s.setElevation(10);
            lCreate.setElevation(10);
        } else if (i == 3) {
            lCreate.setElevation(10);
            l_m.setElevation(10);
            l_c.setElevation(10);
            l_s.setElevation(0);
        }else if(i == 4){
            lCreate.setElevation(10);
            po1.setElevation(0);
            po2.setElevation(10);
            po3.setElevation(10);
            po4.setElevation(10);
        }else if(i == 5){
            lCreate.setElevation(10);
            po1.setElevation(10);
            po2.setElevation(0);
            po3.setElevation(10);
            po4.setElevation(10);
        }else if(i == 6){
            lCreate.setElevation(10);
            po1.setElevation(10);
            po2.setElevation(10);
            po3.setElevation(0);
            po4.setElevation(10);
        }else if(i == 7){
            lCreate.setElevation(10);
            po1.setElevation(10);
            po2.setElevation(10);
            po3.setElevation(10);
            po4.setElevation(0);
        }else if(i == 8){
            lCreate.setElevation(0);
        }


    }

    private void createBtn(int pos, int lineId) {
        HashMap<String, String> m = new HashMap<>();
        m.put("lineId", lineId + "");
        m.put("pos", pos + "");
        //Log.i("-------",m+"");
        MyRe.re(m, "/Interface/index/createStudentLine");
    }


    public void findData(String lineIdnum) {
        HashMap<String, String> m = new HashMap<>();
        MyRe.re(m, "/dataInterface/UserProductionLine/getAll");
        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                JSONObject j;
                j = MyRe.re(m, "/dataInterface/UserProductionLine/getAll");
                if(j !=null){
                    try {
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject js = data.getJSONObject(i);
                                    String position = js.getString("position");
                                    if (position.equals("0")) {
                                        dont(1);
                                    }
                                    if (position.equals("1")) {
                                        dont(2);
                                    }
                                    if (position.equals("2")) {
                                        dont(3);
                                    }
                                    if (position.equals("3")) {
                                        dont(4);
                                    }
                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }t.cancel();
            }
        }, 0, 500);
    }

    private void newP() {//按钮状态重置
        pos = 4;
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn1.setBackgroundColor(Color.parseColor("#169BD5"));
        btn2.setBackgroundColor(Color.parseColor("#169BD5"));
        btn3.setBackgroundColor(Color.parseColor("#169BD5"));
        btn4.setBackgroundColor(Color.parseColor("#169BD5"));
    }

    //生产线已占用，按钮无法点击
    private void dont(int num) {
        if (num == 1) {
            btn1.setBackgroundColor(Color.GRAY);
            btn1.setEnabled(false);
        }
        if (num == 2) {
            btn2.setBackgroundColor(Color.GRAY);
            btn2.setEnabled(false);
        }
        if (num == 3) {
            btn3.setBackgroundColor(Color.GRAY);
            btn3.setEnabled(false);
        }
        if (num == 4) {
            btn4.setBackgroundColor(Color.GRAY);
            btn4.setEnabled(false);
        }
    }
}
