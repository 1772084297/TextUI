package lyxh.sdnu.com.testui.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qiniu.android.utils.Json;
import com.tencent.smtt.sdk.QbSdk;

import lyxh.sdnu.com.testui.BaseApplication;
import lyxh.sdnu.com.testui.Bean;
import lyxh.sdnu.com.testui.NetClient;
import lyxh.sdnu.com.testui.ProfileList;
import lyxh.sdnu.com.testui.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        //TBS x5初始化 异步执行
        QbSdk.initX5Environment(this, null);

        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        final String username = sharedPreferences.getString("usr", "");
        final String password = sharedPreferences.getString("psd", "");

        if (username.equals("") || username.length() == 0) {
            //延迟登陆
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1500);
        }else {
            //        在获取数据结束时跳转页面
        new Thread(new Runnable() {
            @Override
            public void run() {
                //在这里执行耗时操作 获取数据等
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NetClient.getInstance().startRequest("",callBack);
                    }
                });
            }
        }).start();

        }
    }


    private NetClient.MyCallBack callBack = new NetClient.MyCallBack() {
        @Override
        public void onSuccess(String result) {

            result = "{\"login\":"+result+"}";

            if (!result.contains("Wrong account or password")){
                //Ap保存一下信息

                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            }
        }

        @Override
        public void onFailure(int code) {
            Toast.makeText(SplashActivity.this,"网络连接异常",Toast.LENGTH_SHORT).show();
        }
    };
}
