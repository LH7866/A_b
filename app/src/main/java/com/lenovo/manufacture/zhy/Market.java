package com.lenovo.manufacture.zhy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.manufacture.MainActivity;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.widget.Toast.LENGTH_LONG;

//3：人才市场
public class Market extends AppCompatActivity implements View.OnClickListener{
        private ImageView back,up,down;
        private TextView name,leixing,money;
       private ScrollView scrollView;
       private TableRow tableRow;
       List list=new ArrayList();
       ArrayAdapter arrayAdapter;
       int select=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        init();
        getData();

    }
//获取数据
    private void getData() {
        HashMap<String,String> r=new HashMap<>();
        MyRe.re(r,"/datalnterface/People/getAll");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                JSONObject j = MyRe.re(r, "/datalnterface/People/getAll");
                if (j !=null) {
                    try {
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },0,1000);
    }

    private void init() {
        back=findViewById(R.id.back);
        up=findViewById(R.id.iv_type);
        down=findViewById(R.id.iv_pay);
        back.setOnClickListener(this);
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        leixing=findViewById(R.id.tv_2);
        name=findViewById(R.id.tv_1);
        money=findViewById(R.id.tv_3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
                default:
                    break;
            case R.id.back:
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case  R.id.iv_type:
                up.setImageResource(R.drawable.triangle0002);
                break;
            case  R.id.iv_pay:
                down.setImageResource(R.drawable.triangle0001);
                break;
        }
    }
    //动态加表格
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addView(int i){
        tableRow.removeAllViews();

    }

}
