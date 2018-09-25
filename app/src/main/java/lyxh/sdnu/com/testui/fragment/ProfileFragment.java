package lyxh.sdnu.com.testui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.xiangcheng.ofomenuview.OfoMenuManager;
import com.xiangcheng.ofomenuview.drawable.MenuBrawable;
import com.xiangcheng.ofomenuview.view.OfoContentLayout;
import com.xiangcheng.ofomenuview.view.OfoMenuLayout;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import lyxh.sdnu.com.testui.Activity.LoginActivity;
import lyxh.sdnu.com.testui.Activity.MainActivity;
import lyxh.sdnu.com.testui.BaseApplication;
import lyxh.sdnu.com.testui.PicChooserHelper;
import lyxh.sdnu.com.testui.R;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProfileFragment extends Fragment {
    public static ProfileFragment fragment;
    OfoMenuManager ofoMenuManager;
    private PicChooserHelper mPicChooserHelper;
    public final static String REGISTER_URL = "alipays://platformapi/startApp?" +
            "appId=2013062600000474&sourceId=merchantApp&logonId=";
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        fragment=this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.perbg));
        }
        getWindow();
        init(view);
        setOnListener();
        return view;
    }

    private void init(View view) {
        ofoMenuManager = new OfoMenuManager.Builder(getContext())
                //CONCAVE：凹进去；CONVEX：凸出来
                .setRadian(MenuBrawable.CONVEX)
                .setOfoBackColor(android.R.color.holo_blue_light)
                .setOfoPosition(R.dimen.ofo_menu_height)
                .addItemContentView(R.layout.item_card_recharge)
                .addItemContentView(R.layout.item_school_hospital)
                .addItemContentView(R.layout.item_call_for_help)
                .addItemContentView(R.layout.item_logot)
                .build();
        ((ViewGroup)view).addView(ofoMenuManager.getRootView());
    }

    private void setOnListener() {
        ofoMenuManager.setOfoUsesrIconListener(new OfoMenuLayout.OfoUserIconListener() {
            @Override
            public void onClick() {
                //todo 点击修改头像
                if(ContextCompat.checkSelfPermission(MainActivity.instance, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.instance,new String[]{Manifest.permission.CAMERA},1);
                }
                if(ContextCompat.checkSelfPermission(MainActivity.instance,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.instance,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
                if(ContextCompat.checkSelfPermission(MainActivity.instance,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.instance,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }
                if (mPicChooserHelper == null) {
                    mPicChooserHelper = new PicChooserHelper(fragment, PicChooserHelper.PicType.Avatar);
                    mPicChooserHelper.setOnChooseResultListener(new PicChooserHelper.OnChooseResultListener() {
                        @Override
                        public void onSuccess(String url) {
                            //todo 上传更新头像
                            updateAvatar(url);
                        }
                        @Override
                        public void onFail(String msg) {
                            Toast.makeText(getActivity(), "选择失败：" + msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                }else
                    mPicChooserHelper.showPicChooserDialog();
            }
        });
        ofoMenuManager.setOnItemClickListener(new OfoContentLayout.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                switch (position){
                    case 0:
                        //todo 跳转一卡通充值
                        //                使用支付宝充值
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Uri uri = Uri.parse(REGISTER_URL);
                                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                                } catch (Exception e) {
                                    Toast.makeText(getContext(), "请先安装支付宝", Toast.LENGTH_LONG).show();
                                }
                            }
                        }).start();
                        break;
                    case 1:
                        //todo 跳转拨打校医院
                        call("tel:"+89610120);
                        break;
                    case 2:
                        //todo 跳转一键报警
                        call("tel:"+86181110);
                        break;
                    case 3:
                        //todo 登出
                        Intent intent=new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        MainActivity.instance.finish();
                        break;
                        default:
                            break;
                }
            }
        });
        ofoMenuManager.getRootView().post(new Runnable() {
            @Override
            public void run() {
                ofoMenuManager.open();
            }
        });
    }



    private void updateAvatar(final String url) {
        //..................................................................................................
        Log.e("上传头像",url);
        //"148.70.111.56:8085\\api\\photo?StuId="+"pho="
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (BaseApplication.getApplication().getProfileList()!=null)
                    putWebInfo(BaseApplication.getApplication().getProfileList().getSchoolId(),url);
                else
                    putWebInfo("201611010530",url);
            }
        }).start();
        getBitmap(url);
    }

    private void putWebInfo(String id,String ava) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("StuId", id)//添加键值对
                .add("pho", ava)
                .build();
        Request request = new Request.Builder()
                .url("http://148.70.111.56:8055/api/photo")
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response.isSuccessful())
        {
            String str = null;
            try {
                str = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("服务器响应为: " , str);
        }else
            Log.e("??","ohoh");
        Log.e("yes",response.code()+"");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mPicChooserHelper != null) {
            mPicChooserHelper.onActivityResult(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void getBitmap(final String ImgUrl) {
        //todo 将转换成的url图片转换为bitmap格式
        new Thread(new Runnable() {
            Bitmap bitmap = null;
            @Override
            public void run() {
                try {
                    URL url = new URL(ImgUrl);
                    InputStream is = null;
                    BufferedInputStream bis = null;
                    try {
                        is = url.openConnection().getInputStream();
                        bis = new BufferedInputStream(is);
                        bitmap = BitmapFactory.decodeStream(bis);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                MainActivity.instance.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ofoMenuManager.setUserIcon(bitmap);
                    }
                });
            }
        }).start();

    }

    private void getSelfInfo() {
        //todo 获取个人信息
    }

    private void getWindow() {
        Window window = MainActivity.instance.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    public static final int REQUEST_CALL_PERMISSION = 10111; //拨号请求码
    /**
     * 判断是否有某项权限
     * @param string_permission 权限
     * @param request_code 请求码
     * @return
     */
    public boolean checkReadPermission(String string_permission,int request_code) {
        boolean flag = false;
        if (ContextCompat.checkSelfPermission(MainActivity.instance, string_permission) == PackageManager.PERMISSION_GRANTED) {//已有权限
            flag = true;
        } else {//申请权限
            ActivityCompat.requestPermissions(MainActivity.instance, new String[]{string_permission}, request_code);
        }
        return flag;
    }
    /**
     * 检查权限后的回调
     * @param requestCode 请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL_PERMISSION: //拨打电话
                if (permissions.length != 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {//失败
                    Toast.makeText(getContext(),"请允许拨号权限后再试",Toast.LENGTH_SHORT).show();
                } else {//成功
                    call("tel:"+"10086");
                }
                break;
        }
    }
    /**
     * 拨打电话（直接拨打）
     * @param telPhone 电话
     */
    public void call(String telPhone){
        if(checkReadPermission(Manifest.permission.CALL_PHONE,REQUEST_CALL_PERMISSION)){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(telPhone));
            startActivity(intent);
        }
    }


}
