package com.lenovo.manufacture.b;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.b.fragment.Fragment1;
import com.lenovo.manufacture.b.fragment.Fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class Main8Activity extends AppCompatActivity implements View.OnClickListener {
    private Fragment1 f1;
    private Fragment2 f2;
    private Button btnFram1;
    private Button btnFram2;
    private FrameLayout fram;
    private ImageView bi;
    private TextView bt;

    public void setHandler(Handler handler){
        Handler mHandler =handler;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        initView();
    }

    private void initView() {
        Resources r = getBaseContext().getResources();
        Drawable d = r.getDrawable(R.drawable.tiao);
        bi = (ImageView) findViewById(R.id.bi);
        bt = (TextView) findViewById(R.id.bt);
        bt.setBackground(d);
        bt.setText("车辆定制");
        btnFram1 = (Button) findViewById(R.id.btn_fram1);
        btnFram1.setBackgroundColor(Color.parseColor("#F2F2F2"));
        btnFram1.setOnClickListener(this);
        btnFram2 = (Button) findViewById(R.id.btn_fram2);
        btnFram2.setOnClickListener(this);

        bi.setOnClickListener(this);
        fram = findViewById(R.id.fram);
        initFragment1();


    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_fram1:
                btnFram1.setBackgroundColor(Color.parseColor("#F2F2F2"));
                btnFram2.setBackgroundColor(Color.WHITE);
                initFragment1();
                break;
            case R.id.bi:
                finish();
                break;
            case R.id.btn_fram2:
                btnFram2.setBackgroundColor(Color.parseColor("#F2F2F2"));
                btnFram1.setBackgroundColor(Color.WHITE);
                initFragment2();
                break;
        }
    }

    private void initFragment1() {//
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(f2 != null){
            transaction.hide(f2);
        }
        if (f1 == null) {
            f1 = new Fragment1();
        }
        transaction.replace(R.id.fram, f1);
        transaction.show(f1);
        transaction.commit();
    }

    private void initFragment2() {//
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(f1 != null){
            transaction.hide(f1);
        }
        if (f2 == null) {
            f2 = new Fragment2();
        }
        transaction.replace(R.id.fram, f2);
        //hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f2);
        transaction.commit();
    }
}
