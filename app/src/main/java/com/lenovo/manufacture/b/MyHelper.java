package com.lenovo.manufacture.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.lenovo.smarttraffic.bean.Balance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyHelper extends SQLiteOpenHelper {

    private static Context context;
    public MyHelper(Context context){
        super(context,"car_info.db",null,2);
        this.context=context;
    }

    //数据库第一次被创建时调用该方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        //初始化数据库的表结构，执行一条建表的SQL语句
        db.execSQL("create table information(id integer primary key autoincrement,car_num varchar(20),car_name varchar(20),price integer,manager varchar(20),date_time varchar(30))");
    }

    //当数据库的版本号增加时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String car_num,String price){
        /*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date=df.format(new Date());// new Date()为获取当前系统时间*/

        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        String date_time=year+"年"+(month+1)+"月"+date+"日"+(hour+8)+"时"+minute+"分"+second+"秒";

        MyHelper helper=new MyHelper(context);
        SQLiteDatabase db=helper.getWritableDatabase();//获取可读写SQLiteDatabase对象(即itcast.db)
        ContentValues values=new ContentValues();//创建ContentValues对象
        values.put("car_num",car_num);//将数据添加到ContentValues对象
        values.put("price",price);
        values.put("manager","user1");
        values.put("date_time",date_time);
        db.insert("information",null,values);//插入一条数据到information表
        db.close();//关闭数据库
//        Toast.makeText(this, ""+car_num+", "+price+", "+date, Toast.LENGTH_SHORT).show();
//        Log.d("Item4Activity", ""+car_num+", "+price+", "+date_time);
    }

    public void delete(){
        MyHelper helper=new MyHelper(context);
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("delete from information");
        db.close();
    }

//    public List<Balance> find(){
//        List<Balance> balances=new ArrayList<Balance>();
//        MyHelper helper=new MyHelper(context);
//        SQLiteDatabase db=helper.getReadableDatabase();//获取可读SQLiteDatabase对象
//        Cursor cursor=db.rawQuery("select * from information where id>?",new String[]{"0"});
//        if (cursor.getCount()!=0){//判断cursor有多少个数据，如果没有就不要进入循环了
//            while (cursor.moveToNext()){
//                Balance balance=new Balance();
//                balance.setCarId(cursor.getString(cursor.getColumnIndex("car_num")));
//                balance.setBalance(cursor.getString(cursor.getColumnIndex("price")));
//                balance.setUser(cursor.getString(cursor.getColumnIndex("manager")));
//                balance.setDate(cursor.getString(cursor.getColumnIndex("date_time")));
//                balances.add(balance);
//            }
//        }
//        cursor.close();//关闭游标
//        db.close();
//        return balances;
//    }
}
