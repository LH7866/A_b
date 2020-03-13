package com.lenovo.manufacture.zhy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Time;
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
     * SUV生产线
     */
    private Button mSuv;
    /**
     * 位置1
     */
    private Button mW1;
    /**
     * 位置2
     */
    private Button mW2;
    /**
     * 位置3
     */
    private Button mW3;
    /**
     * 位置4
     */
    private Button mW4;
    /**
     * 创建
     */
    private Button mCreate;
    int lineId;
    int pos=1;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c8);
        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mMpv = (Button) findViewById(R.id.mvp);
        mMpv.setOnClickListener(this);
        mCar = (Button) findViewById(R.id.car);
        mCar.setOnClickListener(this);
        mSuv = (Button) findViewById(R.id.suv);
        mSuv.setOnClickListener(this);
        mW1 = (Button) findViewById(R.id.w1);
        mW1.setOnClickListener(this);
        mW2 = (Button) findViewById(R.id.w2);
        mW2.setOnClickListener(this);
        mW3 = (Button) findViewById(R.id.w3);
        mW3.setOnClickListener(this);
        mW4 = (Button) findViewById(R.id.w4);
        mW4.setOnClickListener(this);
        mCreate = (Button) findViewById(R.id.create);
        mCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.back:
                finish();
                break;

            case R.id.btn_mpv:
                lineId = 1;
                b(1);
               getData();
                break;
            case R.id.btn_car:
                lineId = 2;
                b(2);//生产线和位置初始化
               getData();
                break;
            case R.id.btn_suv:
                lineId = 3;
                b(3);
               getData();
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
private  void b(int i){
    if(i == 1){
        mMpv.setBackgroundColor(Color.parseColor("#069E78"));
        mCar.setBackgroundColor(Color.parseColor("#00CC99"));
        mSuv.setBackgroundColor(Color.parseColor("#00CC99"));
    }else if(i == 2){
        mMpv.setBackgroundColor(Color.parseColor("#00CC99"));
        mCar.setBackgroundColor(Color.parseColor("#069E78"));
        mSuv.setBackgroundColor(Color.parseColor("#00CC99"));
    }else {
        mMpv.setBackgroundColor(Color.parseColor("#00CC99"));
        mCar.setBackgroundColor(Color.parseColor("#00CC99"));
        mSuv.setBackgroundColor(Color.parseColor("#069E78"));
    }
    mW1.setBackgroundColor(Color.parseColor("#169BD5"));
    mW2.setBackgroundColor(Color.parseColor("#169BD5"));
    mW3.setBackgroundColor(Color.parseColor("#169BD5"));
    mW4.setBackgroundColor(Color.parseColor("#169BD5"));
}

    private  void getData(){
        HashMap<String,String>map=new HashMap<>();
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject= MyRe.re(map,"/dataInterface/UserProductionLine/getAll");
                    if(jsonObject!=null){
                        if("SUCCESS".equals(jsonObject.getString("message"))){
                            JSONArray jsonArray=jsonObject.getJSONArray("data");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                String  p=jsonObject1.getString("postion");
                                if(p.equals("0")){
                                    no(p);
                                }else  if(p.equals("1")){
                                    no(p);
                                }else if(p.equals("2")){
                                    no(p);
                                }else  if(p.equals("3")){
                                    no(p);
                                }
                            }
                    timer.cancel();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },0,500);

    }

private  void no(String p){
    if(p.equals("0")){
        mW1.setBackgroundColor(Color.GRAY);
        mW1.setEnabled(false);
    }else if(p.equals("1")){
        mW2.setBackgroundColor(Color.GRAY);
        mW2.setEnabled(false);
    }else if(p.equals("2")){
        mW3.setBackgroundColor(Color.GRAY);
        mW3.setEnabled(false);
    }else if(p.equals("3")) {
        mW4.setBackgroundColor(Color.GRAY);
        mW4.setEnabled(false);
    }
}
private  void c(int lineId,int pos){
        HashMap<String,String>map=new HashMap<>();
        map.put("lineId",lineId+"");
        map.put("pos",pos+"");
          Log.d("999",map+"");
        MyRe.re(map,"/Interface/index/createStudentLine");
    Toast.makeText(this,"已经存在",Toast.LENGTH_SHORT).show();


}

}
