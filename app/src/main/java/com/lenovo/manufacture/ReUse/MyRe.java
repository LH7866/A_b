package com.lenovo.manufacture.ReUse;

import android.os.Message;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class MyRe {
    static JSONObject jsonObject;
    public interface API {
        @FormUrlEncoded
        @POST(".")
        Call<ResponseBody> getpost(@FieldMap Map<String, String> a);
    }
    public static JSONObject re(Map<String,String> a,String b) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.112:8085"+b+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> task = api.getpost(a);
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String a=response.body().string();
                    jsonObject=new JSONObject(a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {}
        });

        return jsonObject;
    }
    public static Message getMessage(int what, Object obj){
        Message message = new Message();
        message.what = what;
        message.obj = obj;
        return message;
    }
}
