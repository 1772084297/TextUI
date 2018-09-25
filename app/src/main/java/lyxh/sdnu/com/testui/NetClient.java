package lyxh.sdnu.com.testui;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


//OkHttp的简单封装
//参考自https://www.jianshu.com/p/92b37a5f414d
public class NetClient {
    private static NetClient netClient;

    public NetClient() {
        okHttpClient = initNetClient();
    }

    private final OkHttpClient okHttpClient;

    private OkHttpClient initNetClient() {
        //初始化client
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)//设置读取超时为10秒
                .connectTimeout(10000, TimeUnit.MILLISECONDS)//设置链接超时为10秒
                .build();
        return okHttpClient;
    }

    public static NetClient getInstance() {
        if (netClient == null) {
            netClient = new NetClient();
        }
        return netClient;
    }

    public void startRequest(String url, final MyCallBack callback) {
        Request request = new Request.Builder().url(url).build();
        Call call = getInstance().initNetClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求网络成功说明服务器有响应，但返回的是什么我们无法确定，可以根据响应码判断
                if (response.code() == 200) {
                    //如果是200说明正常，调用MyCallBack的成功方法，传入数据
                    callback.onSuccess(response.body().string());
                }else{
                    //如果不是200说明异常，调用MyCallBack的失败方法将响应码传入
                    callback.onFailure(response.code());
                }

            }
        });
    }


    public interface MyCallBack{
        void onSuccess(String result);
        void onFailure(int code);
    }
}



