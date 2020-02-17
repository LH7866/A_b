package com.lenovo.manufacture.b.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;
import com.lenovo.manufacture.b.Main8Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    private Timer t ;
    List<OrderBean> list=new ArrayList<>();
    private ScrollView mSv1;
    private TableLayout mTl1;
    Activity mActivity;

//    @Override
//    public void onAttach(@NonNull Activity activity) {
//        super.onAttach(activity);
//        mActivity = (Main8Activity)activity;
//        mActivity.setHandler(mHandler);
//    }
//    public Handler mHandler = new Handler();
//    public void handlerMessage(android.os.Message msg){
//        switch (msg.what){
//            default:
//                break;
//            case 1:
//                addView(list);
//                t.cancel();
//        }
//    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.b8_f2,container,false);
            mSv1 = view.findViewById(R.id.sv_b8);
            mTl1 = view.findViewById(R.id.tl_b8);
            getData();
            return view;
        }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getData() {
            HashMap<String,String> m =new HashMap<>();
            MyRe.re(m,"/dataInterface/UserAppointment/getAll");
            t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    JSONObject j = MyRe.re(m,"/dataInterface/UserAppointment/getAll");
                    if(j!=null){
                        try {
                            if(j.getString("message").equals("SUCCESS")){
                                //Log.i("++", String.valueOf(j));
                                JSONArray data = j.getJSONArray("data");
                                //Log.i("==",data+"");
                                //for(int i = 0;i < data.length();i++){
                                    JSONObject js = data.getJSONObject(0);
                                    Log.i("---",js+""); // --有数据1条
                                    OrderBean ob =new OrderBean();

                                    ob.setCarId(js.getString("carId"));
                                    ob.setTime(js.getString("time"));
                                    ob.setNum(js.getString("num"));
                                    ob.setEnagine(js.getString("engine"));
                                    ob.setSpeed(js.getString("speed"));
                                    ob.setWheel(js.getString("wheel"));
                                    ob.setControl(js.getString("control"));
                                    ob.setBrake(js.getString("brake"));
                                    ob.setHang(js.getString("hang"));
                                    Log.i("===================",ob.toString());
                                    list.add(ob);
                                    Log.i("==++", list+"");
                               // }
                                send(1,"");
//                                t.cancel();
//                                addView(list);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },0,500);
            //Log.i("==", String.valueOf(list));
    }

    private void send(int what,Object obj){
        Fragment2.this.handler.sendMessage(MyRe.getMessage(what,obj));
    }

    Handler handler = new Handler(){
      @RequiresApi(api = Build.VERSION_CODES.N)
      public void handleMessage(@NonNull Message msg){
          super.handleMessage(msg);
          switch (msg.what){
              case 1:
                  t.cancel();
                  addView(list);
                  default:
                      break;
          }
      }
    };
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addView(List<OrderBean> ob) {
        //清除残留记录
        //mTl1.removeAllViews();
        //获取记录
        int sum = 0;
        Log.i("ob=======",ob+"");
        for(OrderBean orderBean : ob){
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.table_b8,null);
            TextView t1 = view.findViewById(R.id.b8_1);
            TextView t2 = view.findViewById(R.id.b8_2);
            TextView t3 = view.findViewById(R.id.b8_3);
            TextView t4 = view.findViewById(R.id.b8_4);
            TextView t5 = view.findViewById(R.id.b8_5);
            TextView t6 = view.findViewById(R.id.b8_6);
            TextView t7 = view.findViewById(R.id.b8_7);
            TextView t8 = view.findViewById(R.id.b8_8);
            TextView t9 = view.findViewById(R.id.b8_9);
            t1.setText(orderBean.getCarId());
            t2.setText(orderBean.getTime());
            t3.setText(orderBean.getNum());
            t4.setText(orderBean.getEnagine());
            t5.setText(orderBean.getSpeed());
            t6.setText(orderBean.getWheel());
            t7.setText(orderBean.getControl());
            t8.setText(orderBean.getBrake());
            t9.setText(orderBean.getHang());
            Log.i("view",t1.getText()+"");
            mTl1.addView(view);
        }
    }
//    public Fragment2(){}
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
//        super.onViewCreated(view,savedInstanceState);
//
//
//    }
}
