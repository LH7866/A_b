package com.lenovo.manufacture.zhy;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.lenovo.manufacture.MainActivity;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;
import com.lenovo.manufacture.zhy.Bean.PeopleBean;
import com.lenovo.manufacture.zhy.Bean.problem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Problem extends AppCompatActivity implements View.OnClickListener{
    private TableLayout tableLayout;
    List<problem> list=new ArrayList<>();
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        init();
        getData();
    }

    private void getData() {
        HashMap<String,String> r=new HashMap<>();
        MyRe.re(r,"/dataInterface/UserQuestion/getAll");
        new Timer().schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                JSONObject j = MyRe.re(r, "/dataInterface/UserQuestion/getAll");
                if (j !=null) {
                    try {
                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                            for(int i=0;i<data.length();i++){
                                JSONObject jsonObject=data.getJSONObject(i);
//                                Log.d("11321",jsonObject+"");
                                problem p=new problem();
                                p.getId(jsonObject.getString("id"));
                                p.getUserProductionLineId(jsonObject.getString("userProductionLineId"));
                                list.add(p);
                            }
                            send(1,"");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },0,120000);
    }
    public void send(int what,Object obj){
      Message message=Message.obtain();
      message.what=what;
      message.obj=obj;
      Problem.this.handler.sendMessage(message);
    }
    Handler handler=new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    break;
            }
        }
    };
    private void init() {
        tableLayout=findViewById(R.id.tl_1);
        back=findViewById(R.id.back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

        }
    }
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public void addView(List<PeopleBean> i){
//        tableRow.removeAllViews();
//        List<PeopleBean> q=new ArrayList<>();
//        if (i.equals(0)) {
//            q.sort(new Comparator<PeopleBean>() {
//                @Override
//                public int compare(PeopleBean o1, PeopleBean o2) {
//                    return o1.getGold().compareTo(o2.getGold());
//                }
//            });
//        }else {
//            q.sort(new Comparator<PeopleBean>() {
//                @Override
//                public int compare(PeopleBean o1, PeopleBean o2) {
//                    return o2.getGold().compareTo(o1.getGold());
//                }
//            });
//        }

//        for (PeopleBean bean :i) {
//            View view1 = View.inflate (Market.this, R.layout.table_item, null);
//            TextView textView1 = view1.findViewById (R.id.tv_1);
//            TextView textView2 = view1.findViewById (R.id.tv_2);
//            TextView textView3 = view1.findViewById (R.id.tv_3);
//            textView1.setText(bean.getPeopleName());
//            textView2.setText(bean.getStatus());
//            textView3.setText(bean.getGold());
//            tableRow.addView (view1);



}
