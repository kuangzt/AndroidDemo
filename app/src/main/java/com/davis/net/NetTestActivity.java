package com.davis.net;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.davis.R;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class NetTestActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = NetTestActivity.class.getSimpleName();
    private static final String BASE_URL = "http://apis.baidu.com";
    private static final String API_KEY = "8e13586b86e4b7f3758ba3bd6c9c9135";
    private Button mTest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        init();
    }

    private void init() {
        mTest = (Button)findViewById(R.id.test);
        mTest.setOnClickListener(this);
    }

    private void query(){
        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .baseUrl(BASE_URL)//主机地址
                .build();

        //2.创建访问API的请求
        PhoneService service = retrofit.create(PhoneService.class);
        Call<PhoneResult> call = service.getResult(API_KEY, "18312517322");

        //3.发送请求
        call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Response<PhoneResult> response) {
                //4.处理结果
                if (response.isSuccess()){
                    PhoneResult result = response.body();
                    if (result != null){
                        PhoneResult.RetDataEntity entity = result.getRetData();
                        Log.e(TAG,entity.toString());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.test:
                query();
                break;
        }
    }
}
