package com.lenovo.manufacture.b.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.ReUse.MyRe;
import com.lenovo.manufacture.b.Main8Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment1 extends Fragment implements View.OnClickListener {
    private TextView tvTime;
    private Activity mActivity;
    private String time;
    private LinearLayout manageView;
    private RecyclerView orderListView;
    private LinearLayout orderView;
    private RadioGroup rgType;
    private RadioButton rbCar1;
    private RadioButton rbCar2;
    private RadioButton rbCar3;
    private EditText etNumber;
    private Spinner spinner2;
    private RadioGroup rgBsx;
    private RadioButton rbBsx1;
    private RadioButton rbBsx2;
    private RadioGroup rgLy;
    private RadioButton rbLy1;
    private RadioButton rbLy2;
    private RadioGroup rgZk;
    private RadioButton rbZk1;
    private RadioButton rbZk2;
    private RadioGroup rgSc;
    private RadioButton rbSc1;
    private RadioButton rbSc2;
    private Spinner spinner;
    private Button btnOk;

    private HashMap<String,String> m;
    private String color="0";
    private String enagine="2.0";
    private String hang = "1";
    private String brake ="0";
    private String wheel ="0";
    private String control ="0";
    private String speed ="0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.b8_f1, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
//有问题就恢复
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        mActivity = (Activity) context;
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }

//    public Fragment1() {
//
//    }

    private void initView(View view) {
        manageView = (LinearLayout) view.findViewById(R.id.manageView);
        orderListView = (RecyclerView) view.findViewById(R.id.orderListView);
        orderView = (LinearLayout) view.findViewById(R.id.orderView);

        //color
        rgType = (RadioGroup) view.findViewById(R.id.rgType);
        rbCar1 = (RadioButton) view.findViewById(R.id.rbCar1);
        rbCar2 = (RadioButton) view.findViewById(R.id.rbCar2);
        rbCar3 = (RadioButton) view.findViewById(R.id.rbCar3);
        rbCar1.setChecked(true);
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbCar1)
                    color = "0";
                else if (checkedId == R.id.rbCar2)
                    color = "1";
                else if (checkedId == R.id.rbCar3)
                    color = "2";
            }
        });

        //num
        etNumber = (EditText) view.findViewById(R.id.etNumber);
        etNumber.setText("1");

        //enagine
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        spinner2.setSelection(2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                enagine = position + "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //speed
        rgBsx = (RadioGroup) view.findViewById(R.id.rgBsx);
        rbBsx1 = (RadioButton) view.findViewById(R.id.rbBsx1);
        rbBsx2 = (RadioButton) view.findViewById(R.id.rbBsx2);
        rgBsx.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbBsx1)
                    speed = "0";
                else if (checkedId == R.id.rbBsx2)
                    speed = "1";
            }
        });


        //wheel
        rgLy = (RadioGroup) view.findViewById(R.id.rgLy);
        rbLy1 = (RadioButton) view.findViewById(R.id.rbLy1);
        rbLy2 = (RadioButton) view.findViewById(R.id.rbLy2);
        rgLy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbLy1)
                    wheel = "0";
                else if (checkedId == R.id.rbLy2)
                    wheel = "1";
            }
        });

        //control
        rgZk = (RadioGroup) view.findViewById(R.id.rgZk);
        rbZk1 = (RadioButton) view.findViewById(R.id.rbZk1);
        rbZk2 = (RadioButton) view.findViewById(R.id.rbZk2);
        rgZk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbZk1)
                    control = "0";
                else if (checkedId == R.id.rbZk2)
                    control = "1";
            }
        });

        //brake
        rgSc = (RadioGroup) view.findViewById(R.id.rgSc);
        rbSc1 = (RadioButton) view.findViewById(R.id.rbSc1);
        rbSc2 = (RadioButton) view.findViewById(R.id.rbSc2);
        rgSc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbSc1)
                    brake = "0";
                else if (checkedId == R.id.rbSc2)
                    brake = "1";
            }
        });

        //hang
        spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hang = position + "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnOk = (Button) view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        //time
        tvTime = view.findViewById(R.id.tvTime);
        tvTime.setOnClickListener(this);


        rbBsx1.setChecked(true);
        rbLy1.setChecked(true);
        rbZk1.setChecked(true);
        rbSc1.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                break;
            case R.id.tvTime:
                data();
                break;
            case R.id.btnOk:
                ok();
                break;
        }
    }

    private void ok() {//新增订单
        getData();
        //Log.i("==",m+"");
        MyRe.re(m,"/dataInterface/UserAppointment/create");
        Toast.makeText(getActivity(),"订购成功",Toast.LENGTH_SHORT).show();
    }

    private void getData() {//获取页面上的参数
        m =new HashMap<>();

        m.put("userWorkId","1");
        m.put("userAppointmentName","1");
        m.put("content","1");
        m.put("type","0");
        m.put("carId",color);
        m.put("num", String.valueOf(etNumber.getText()));
        m.put("gold","1");
        m.put("engine",enagine);
        m.put("speed",speed);
        m.put("wheel",wheel);
        m.put("control",control);
        m.put("brake",brake);
        m.put("hang",hang);
        //m.put("color",color);
        m.put("time",time);
    }

    public void data(){//日期：time
        TimePickerView pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                tvTime.setText(simpleDateFormat.format(date));
                String time1 = (String) tvTime.getText();
                time=time1.replaceAll("[^0-9]","");
                //Toast.makeText(getActivity(),time,Toast.LENGTH_SHORT).show();
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .build();
        pvTime.show();
    }
}
