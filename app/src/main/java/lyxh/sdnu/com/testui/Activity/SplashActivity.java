package lyxh.sdnu.com.testui.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.smtt.sdk.QbSdk;

import lyxh.sdnu.com.testui.BaseApplication;
import lyxh.sdnu.com.testui.Data.ProfileList;
import lyxh.sdnu.com.testui.Utils.NetClient;
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
        Log.e("TAGTAG", username + " " + password);
        if (username.equals("") || username.length() == 0) {
            Log.e("TAGTAG","没有登陆信息");
            //本地没有账号数据时 调转到登陆界面
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1500);

        } else {
            //有账号数据时请求结束后跳转到主界面
            Log.e("TAGTAG","有登陆信息");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //在这里执行耗时操作 获取数据等
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            NetClient.getInstance().startRequest("http://148.70.111.56:8055/api/login?id=" +
                                    username + "&password=" + password, callBack);
                        }
                    });
                }
            }).start();

        }
    }


    private NetClient.MyCallBack callBack = new NetClient.MyCallBack() {
        @Override
        public void onSuccess(String result) {

            if (!result.contains("Wrong account or password")) {
                Gson gson = new Gson();
                ProfileList profileList = gson.fromJson(result, ProfileList.class);
                BaseApplication.getApplication().setProfileList(profileList);

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                //账号密码错误
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
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }
    };
}
