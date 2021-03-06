package com.lenovo.manufacture.zhy.three;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.lenovo.manufacture.MainActivity;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;
import com.lenovo.manufacture.zhy.Bean.three.PeopleBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//3：人才市场
public class Market extends AppCompatActivity implements View.OnClickListener{
    private ImageView back,up,down;
       private ScrollView scrollView;
       private TableLayout tableRow;
       List<PeopleBean> list;
       private Timer t = new Timer();
       String a="1",b="1";

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
        MyRe.re(r,"/dataInterface/People/getAll");
        t.schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                JSONObject j = MyRe.re(r, "/dataInterface/People/getAll");
                list=new ArrayList<>();
                if (j !=null) {
                    try {

                        if (j.getString("message").equals("SUCCESS")) {
                            JSONArray data = j.getJSONArray("data");
                           for(int i=0;i<data.length();i++){
                               JSONObject jsonObject=data.getJSONObject(i);
                               PeopleBean p=new PeopleBean();
                               p.setPeopleName(jsonObject.getString("peopleName"));
                               p.setStatus(jsonObject.getString("status"));
                               p.setGold(jsonObject.getString("gold"));
                               list.add(p);
                           }
                           send(1,"");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },0,10000);
    }

    public void send(int what,Object obj){
        Message message = Message.obtain();
        message.what=what;
        message.obj=obj;
        Market.this.handler.sendMessage(message);
    }

    Handler handler = new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    addView();
                    break;
            }
        }
    };

    @SuppressLint("WrongViewCast")
    private void init() {
        back=findViewById(R.id.back);
        up=findViewById(R.id.iv_type);
        down=findViewById(R.id.iv_pay);
        back.setOnClickListener(this);
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        scrollView=findViewById(R.id.sv_1);
        tableRow=findViewById(R.id.tl_1);

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
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
                hu(up,down);
                a="1";
                addView();
                break;
            case  R.id.iv_pay:
                hu(down,up);
                a="2";
                addView();
                break;
        }
    }

    //动态加表格
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addView(){
        tableRow.removeAllViews();
        if (list!=null){
            if (a.equals("1")){
                if(b.equals("1")){
                    list.sort(new Comparator<PeopleBean>() {
                        @Override
                        public int compare(PeopleBean o1, PeopleBean o2) {
                            return o1.getStatus().compareTo(o2.getStatus());
                        }
                    });
                }else {
                    list.sort(new Comparator<PeopleBean>() {
                        @Override
                        public int compare(PeopleBean o1, PeopleBean o2) {
                            return  o2.getStatus().compareTo(o1.getStatus());
                        }
                    });
                }
            }else {
                if (b.equals("1")){
                    list.sort(new Comparator<PeopleBean>() {
                        @Override
                        public int compare(PeopleBean o1, PeopleBean o2) {
                            Integer.valueOf(o1.getGold());
                            return Integer.valueOf(o1.getGold()).compareTo(Integer.valueOf(o2.getGold()));
                        }
                    });
                }else {
                    list.sort(new Comparator<PeopleBean>() {
                        @Override
                        public int compare(PeopleBean o1, PeopleBean o2) {
                            return Integer.valueOf(o2.getGold()).compareTo(Integer.valueOf(o1.getGold()));
                        }
                    });
                }
            }
            for (PeopleBean bean :list) {
                View view1 = View.inflate (Market.this, R.layout.table_item2, null);
                TextView textView1 = view1.findViewById (R.id.txv_1);
                TextView textView2 = view1.findViewById (R.id.txv_2);
                TextView textView3 = view1.findViewById (R.id.txv_3);
                textView1.setText(bean.getPeopleName());
                textView2.setText(bean.getStatus());
                textView3.setText(bean.getGold());
                tableRow.addView (view1);
            }
        }
    }



public  void hu(ImageView up,ImageView down){
    Drawable.ConstantState t=up.getDrawable().getCurrent().getConstantState();
    Drawable.ConstantState tt=down.getDrawable().getCurrent().getConstantState();
    Drawable.ConstantState t1=getDrawable(R.drawable.triangle0001).getConstantState();
    Drawable.ConstantState t2=getDrawable(R.drawable.triangle0002).getConstantState();
    Drawable.ConstantState t3=getDrawable(R.drawable.triangle0003).getConstantState();
    Drawable.ConstantState t4=getDrawable(R.drawable.triangle0004).getConstantState();
    if(t.equals(t1)||t.equals(t4)){
        b="2";
        up.setImageResource(R.drawable.triangle0003);
    }else {
        b="1";
        up.setImageResource(R.drawable.triangle0001);
    }if (tt.equals(t2)||tt.equals(t3)){
        down.setImageResource(R.drawable.triangle0002);
    }else  {
        down.setImageResource(R.drawable.triangle0004);
    }

}
}
