package lyxh.sdnu.com.testui.Activity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import lyxh.sdnu.com.testui.BaseApplication;
import lyxh.sdnu.com.testui.Data.ProfileList;
import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.Utils.NetClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static LoginActivity instance;
    private EditText etUsername;
    private EditText etPassword;
    private Button btGo;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setStatusBar();
        initView();
    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void initView() {
        instance = this;
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btGo = findViewById(R.id.bt_go);
        btGo.setOnClickListener(this);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);


        //在输入框中提示测试账号
        etUsername.setText("201611010530");
        etPassword.setText("0530");
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(null);
                    getWindow().setEnterTransition(null);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    //跳转到注册界面
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            case R.id.bt_go:
                if (etUsername.getText() == null || etUsername.getText().length() == 0) {
                    Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_LONG).show();
                    break;
                }
                if (etPassword.getText() == null || etPassword.getText().length() == 0) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_LONG).show();
                    break;
                }
                //登陆请求
                NetClient.getInstance().startRequest("http://148.70.111.56:8055/api/login?id=" +
                        etUsername.getText() + "&password=" + etPassword.getText(), callback);
                break;
        }
    }


    private NetClient.MyCallBack callback = new NetClient.MyCallBack() {
        @Override
        public void onSuccess(String result) {
            checkAccount(result);
        }

        @Override
        public void onFailure(int code) {
            Toast.makeText(LoginActivity.this, "网络异常，请稍后再试", Toast.LENGTH_SHORT).show();
        }
    };

    //判断账号密码是否正确 若正确则存储下来，下次直接跳转到主界面
    private void checkAccount(String result) {
        Log.e("TAGTAG", result);
        if (!result.contains("Wrong account or password")) {

            Gson gson = new Gson();

            ProfileList profileList = gson.fromJson(result, ProfileList.class);
            BaseApplication.getApplication().setProfileList(profileList);

            SharedPreferences sp = getSharedPreferences("account", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.putString("usr", etUsername.getText().toString());
            editor.putString("psd", etPassword.getText().toString());
            editor.apply();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            });

        } else {
            etPassword.setText("");
            Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
        }
    }
}
