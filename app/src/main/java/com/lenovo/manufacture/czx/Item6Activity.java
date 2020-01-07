package com.lenovo.manufacture.czx;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Item6Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBi;
    /**
     *
     */
    private TextView mBt;
    private ImageView mIm1;
    /**
     * 补货
     */
    private Button mBt1;
    private ImageView mIm2;
    /**
     * 补货
     */
    private Button mBt2;
    private ImageView mIm3;
    /**
     * 补货
     */
    private Button mBt3;
    private ImageView mIm4;
    /**
     * 补货
     */
    private Button mBt4;
    private Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item6);
        initView();
        product();

    }

    private void product() {
        HashMap<String,String>  m = new HashMap<>();
        m.put("id", "1");
        t = new Timer();
        t.schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                JSONObject j = MyRe.re(m, "/dataInterface/UserProductionLine/getAll");

                try {
                    if (j != null) {
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                            for (int i=0; i<=data.length(); i++) {

                                JSONObject js = data.getJSONObject(i);
                                int position = js.getInt("position");
//                                Log.d("sss",position+"");
                                if(position != 0 && position != 1 && position != 2 && position != 3 && i==1){
                                    Log.d("sss","sssaaaa");
                                    mIm1.setImageResource(R.drawable.goods0002);
                                    mIm1.setForeground(getDrawable(R.drawable.exclamation));
                                }
                                if(position != 1 && position != 0 && position != 2 && position != 3 && i==0){
                                    Log.d("ssszzzzzzz","sssaaaaa");
                                    mIm2.setImageResource(R.drawable.goods0002);
                                    mIm2.setForeground(getDrawable(R.drawable.exclamation));
                                }
                                send(position,"");

                            }


                        }
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }

            }
        },0,100);

    }

    private void send(int what, Object obj) {

        Item6Activity.this.handler.sendMessage(MyRe.getMessage(what,obj));

    }
    Handler handler = new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mIm1.setImageResource(R.drawable.goods0001);
                    mIm1.setForeground(getDrawable(R.color.cardview_shadow_end_color));
                    break;
                case 1:
                    mIm2.setImageResource(R.drawable.goods0001);
                    mIm2.setForeground(getDrawable(R.color.cardview_shadow_end_color));
                    break;
                case 2:
                    mIm3.setImageResource(R.drawable.goods0001);
                    mIm3.setForeground(getDrawable(R.color.cardview_shadow_end_color));
                    break;
                case 3:
                    mIm4.setImageResource(R.drawable.goods0001);
                    mIm4.setForeground(getDrawable(R.color.cardview_shadow_end_color));
                    break;
                default:

                    break;

            }
        }
    };

    private void initView() {
        mBi = (ImageView) findViewById(R.id.bi);
        mBi.setOnClickListener(this);
        mBt = (TextView) findViewById(R.id.bt);
        mBt.setText("生产线补货");
        mIm1 = (ImageView) findViewById(R.id.im1);
        mBt1 = (Button) findViewById(R.id.bt1);
        mBt1.setOnClickListener(this);
        mIm2 = (ImageView) findViewById(R.id.im2);
        mBt2 = (Button) findViewById(R.id.bt2);
        mBt2.setOnClickListener(this);
        mIm3 = (ImageView) findViewById(R.id.im3);
        mBt3 = (Button) findViewById(R.id.bt3);
        mBt3.setOnClickListener(this);
        mIm4 = (ImageView) findViewById(R.id.im4);
        mBt4 = (Button) findViewById(R.id.bt4);
        mBt4.setOnClickListener(this);
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
            case R.id.bt1:
                break;
            case R.id.bt2:
                break;
            case R.id.bt3:
                break;
            case R.id.bt4:
                break;
        }
    }
}
