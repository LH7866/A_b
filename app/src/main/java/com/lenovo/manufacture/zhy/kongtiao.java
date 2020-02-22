package com.lenovo.manufacture.zhy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class kongtiao extends AppCompatActivity implements View.OnClickListener {

    Timer timer;
    private ImageView mI1;
    private ImageView mI2;
    private ImageView mOff;
    /**
     * 冷风
     */
    private Button mB1;
    /**
     * 暖风
     */
    private Button mB2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kongtiao);
        initView();
        getData();
    }

    private void getData() {
        HashMap<String, String> m = new HashMap<>();
        m.put("id", "1");
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                JSONObject jsonObject = MyRe.re(m, "/dataInterface/UserWorkEnvironmental/getInfo");
                if (jsonObject != null) {
                    try {
                        if ("SUCCESS".equals(jsonObject.getString("message"))) {
                            JSONArray data = jsonObject.getJSONArray("data");
                            JSONObject js = data.getJSONObject(0);
                            final String acOnff=js.getString("acOnff");
                            if("0".equals(acOnff)){
                                mI1.setImageResource(R.drawable.cold0002);
                                mI2.setImageResource(R.drawable.hot0001);
                            }else  if("1".equals(acOnff)){
                                mI1.setImageResource(R.drawable.cold0001);
                                mI2.setImageResource(R.drawable.hot0001);

                            }else  if("2".equals(acOnff)){
                                mI2.setImageResource(R.drawable.hot0002);
                                mI1.setImageResource(R.drawable.cold0002);
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        }, 0, 3000);
    }






    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;

            case R.id.off:
                mI1.setImageResource(R.drawable.cold0002);
                mI2.setImageResource(R.drawable.hot0001);
                off();
                break;
            case R.id.b1:
                mI1.setImageResource(R.drawable.cold0001);
                mI2.setImageResource(R.drawable.hot0001);
                cool();
                break;
            case R.id.b2:
                mI2.setImageResource(R.drawable.hot0002);
                mI1.setImageResource(R.drawable.cold0002);
                hot();
                break;
        }
    }



    private void initView() {
        mI1 = (ImageView) findViewById(R.id.i1);
        mI2 = (ImageView) findViewById(R.id.i2);
        mOff = (ImageView) findViewById(R.id.off);
        mOff.setOnClickListener(this);
        mB1 = (Button) findViewById(R.id.b1);
        mB1.setOnClickListener(this);
        mB2 = (Button) findViewById(R.id.b2);
        mB2.setOnClickListener(this);
    }
    public void off(){
        HashMap<String,String> m=new HashMap<>();
        m.put("id","1");
        m.put("acOnff","0");
        MyRe.re(m,"/dataInterface/UserWorkEnvironmental/updateAcOnOff");

    }
    public void cool(){
        HashMap<String,String> m=new HashMap<>();
        m.put("id","1");
        m.put("acOnff","1");
        MyRe.re(m,"/dataInterface/UserWorkEnvironmental/updateAcOnOff");
    }

    public void hot(){
        HashMap<String,String> m=new HashMap<>();
        m.put("id","1");
        m.put("acOnff","2");
        MyRe.re(m,"/dataInterface/UserWorkEnvironmental/updateAcOnOff");

    }


}
