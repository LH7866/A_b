package com.lenovo.manufacture.b;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBi;
    /**
     *
     */
    private TextView mBt;
    private TableLayout mTb3;
    private Timer t = new Timer();
    private List<People> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
        a();
    }

    private void a() {
        HashMap<String,String> m = new HashMap<>();
        m.put("id","1");
        MyRe.re(m,"/dataInterface/People/getAll");

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                JSONObject j = MyRe.re(m,"/dataInterface/People/getAll");
                try {
                    if (j != null){
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject js = data.getJSONObject(i);
                                People p = new People();
                                p.setName(js.getString("peopleName"));
                                p.setStatus(js.getString("status"));
                                p.setGold(js.getString("gold"));
                                list.add(p);
                            }
                            send(1,"");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },0,500);
    }

    private void send(int what,Object obj){
        Message message = Message.obtain();
        message.what = what;
        message.obj = obj;
        Main3Activity.this.handler.sendMessage(message);
    }

    Handler handler = new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    addView(list);
                    t.cancel();
                    break;
            }
        }
    };

    //动态加表格
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addView(List<People> list) {
        //清除残余表格
        mTb3.removeAllViews();
        for (People p: list) {
            View v = View.inflate(Main3Activity.this,R.layout.table_item4,null);
            TextView t1 = v.findViewById(R.id.tq_1);
            TextView t2 = v.findViewById(R.id.tq_2);
            TextView t3 = v.findViewById(R.id.tq_3);

            t1.setText(p.getName());
            if (Integer.parseInt(p.getStatus()) == 0){
                t2.setText("工程师");
            }else if (Integer.parseInt(p.getStatus()) == 1){
                t2.setText("工人");
            }else if (Integer.parseInt(p.getStatus()) == 2){
                t2.setText("技术人员");
            }else{
                t2.setText("检测人员");

            }
            t3.setText(p.getGold());

            mTb3.addView(v);

        }
    }

    private void initView() {
        mBi = (ImageView) findViewById(R.id.bi);
        mBi.setOnClickListener(this);
        mBt = (TextView) findViewById(R.id.bt);
        mTb3 = (TableLayout) findViewById(R.id.tb_3);
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
        }
    }
}
