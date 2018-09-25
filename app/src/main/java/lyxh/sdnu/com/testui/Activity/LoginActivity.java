package lyxh.sdnu.com.testui.Activity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lyxh.sdnu.com.testui.BaseApplication;
import lyxh.sdnu.com.testui.Bean;
import lyxh.sdnu.com.testui.NetClient;
import lyxh.sdnu.com.testui.ProfileList;
import lyxh.sdnu.com.testui.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    EditText etUsername;
    EditText etPassword;
    Button btGo;
    public static LoginActivity instance;
    CardView cv;
    FloatingActionButton fab;
    TextInputLayout usrInputLayout;
    TextInputLayout psdInputLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        instance = this;
        initView();
    }

    private void initView() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btGo = findViewById(R.id.bt_go);
        btGo.setOnClickListener(this);
        cv = findViewById(R.id.cv);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        usrInputLayout = findViewById(R.id.usrInputLayout);
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence == null || charSequence.length() == 0) {
                    usrInputLayout.setErrorEnabled(true);
                    Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_LONG).show();
                } else {
                    usrInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        psdInputLayout = findViewById(R.id.psdInputLayout);
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence == null || charSequence.length() == 0) {
                    psdInputLayout.setErrorEnabled(true);
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
                } else {
                    psdInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etUsername.setText("201611010218");
        etPassword.setText("0218");
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
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
                NetClient.getInstance().startRequest("http://148.70.111.56:8055/api/login?id=" + etUsername.getText() + "&password=" + etPassword.getText(), callback);

//                    }
//                }).start();
                break;

        }
    }


    private NetClient.MyCallBack callback = new NetClient.MyCallBack() {
        @Override
        public void onSuccess(String result) {
//        判断账号密码是否正确 正确的话存储
            checkAccount(result);
        }

        @Override
        public void onFailure(int code) {
            Toast.makeText(LoginActivity.this, "网络异常，请稍后再试", Toast.LENGTH_SHORT).show();
        }
    };

    //检查账号是否正确，正确的话跳转，并保存信息
    private void checkAccount(String result) {
//        .substring(1,result.length()-1)

        if (!result.contains("Wrong account or password")) {
//            //解析为profileList存储，保存通过账号密码申请到的信息
////            Gson gson = new Gson();
////            ProfileList profileList = gson.fromJson(result,ProfileList.class);


//            JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
//            JsonElement element = jsonObject.get("login");
//
//            Gson gson = new Gson();
//            ProfileList bean = gson.fromJson(element, ProfileList.class);
//
//            BaseApplication.getApplication().setProfileList(bean);
//
            SharedPreferences sp = getSharedPreferences("account", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.putString("usr", null);
            editor.putString("psd", null);
            editor.apply();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Explode explode = new Explode();
                        explode.setDuration(500);

                        getWindow().setExitTransition(explode);
                        getWindow().setEnterTransition(explode);
                        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
                        Intent i2 = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i2, oc2.toBundle());
                    } else {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

                }
            });

        } else {
            etPassword.setText("");
            Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
        }
    }
}
