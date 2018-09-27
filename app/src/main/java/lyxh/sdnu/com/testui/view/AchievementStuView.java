package lyxh.sdnu.com.testui.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lyxh.sdnu.com.testui.Data.AchievementInfo;
import lyxh.sdnu.com.testui.Activity.DetailedActivity;
import lyxh.sdnu.com.testui.Activity.MainActivity;
import lyxh.sdnu.com.testui.Adapter.AchievementAdapter;
import lyxh.sdnu.com.testui.BaseApplication;
import lyxh.sdnu.com.testui.Data.ProfileList;
import lyxh.sdnu.com.testui.R;

public class AchievementStuView extends LinearLayout {
    private LinearLayout linearLayout;
    private LinearLayout linearLayoutHide;
    private TextView textScore;
    private ImageView imageView;
    private ProfileList profileList;
    private RecyclerView recyclerView;
    private AchievementAdapter achievementAdapter;
    private Button button;
    private List<AchievementInfo> lists;
    private boolean isHide=false;

    public AchievementStuView(Context context) {
        super(context);
        init();
    }

    public AchievementStuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AchievementStuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_achievement_stu, this
                , true);
        initViews();
        setClick();
    }

    private void setClick() {
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHide){
                    linearLayoutHide.setVisibility(GONE);
                    lists.clear();
                    isHide=false;
                }else {
                    linearLayoutHide.setVisibility(VISIBLE);
                    initData();
                    isHide=true;
                }

            }
        });
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.instance, DetailedActivity.class);
                intent.putExtra("key",ProfileList.VIEW_ACHIEVEMENT_STU);
                getContext().startActivity(intent);
            }
        });
    }

    private void initData() {

        if (BaseApplication.getApplication().getProfileList()==null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lists.clear();
                    getWebInfo("http://148.70.111.56:8055/api/StuScore?stuNum=201611010530");
                }
            }).start();
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lists.clear();
                    getWebInfo("http://148.70.111.56:8055/api/StuScore?stuNum="+BaseApplication.getApplication().getProfileList().getSchoolId());
                }
            }).start();
        }
        Log.e("test_size",lists.size()+"");
    }

    private void getWebInfo(String urlRoot) {
        try {
            URL url=new URL(urlRoot);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(8000);
            httpURLConnection.setReadTimeout(8000);
            InputStream inputStream=httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer=new StringBuffer();
            String temp=null;
            while ((temp=bufferedReader.readLine())!=null){
                stringBuffer.append(temp);
            }
            final String data=stringBuffer.toString().substring(1,stringBuffer.toString().length()-1);
            MainActivity.instance.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    responseJson(data);
                }
            });
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void responseJson(String s) {
        s = "{\n" +
                "\t\"StuScore\": [{\n" +
                "\t\t\"StudentNum\": \"201611010530\",\n" +
                "\t\t\"CourseName\": \"C语言\",\n" +
                "\t\t\"Score\": \"90\"\n" +
                "\t}, {\n" +
                "\t\t\"StudentNum\": \"201611010530\",\n" +
                "\t\t\"CourseName\": \"数据结构\",\n" +
                "\t\t\"Score\": \"91\"\n" +
                "\t}, {\n" +
                "\t\t\"StudentNum\": \"201611010530\",\n" +
                "\t\t\"CourseName\": \"操作系统\",\n" +
                "\t\t\"Score\": \"86\"\n" +
                "\t}, {\n" +
                "\t\t\"StudentNum\": \"201611010530\",\n" +
                "\t\t\"CourseName\": \"高等数学\",\n" +
                "\t\t\"Score\": \"95\"\n" +
                "\t}, {\n" +
                "\t\t\"StudentNum\": \"201611010530\",\n" +
                "\t\t\"CourseName\": \"马克思主义原理\",\n" +
                "\t\t\"Score\": \"88\"\n" +
                "\t}, {\n" +
                "\t\t\"StudentNum\": \"201611010530\",\n" +
                "\t\t\"CourseName\": \"计算机组成原理\",\n" +
                "\t\t\"Score\": \"83\"\n" +
                "\t}]\n" +
                "}";
        JsonObject jsonObject = new JsonParser().parse(s).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("StuScore");
        Gson gson = new Gson();
        List<AchievementInfo> jsonList = new ArrayList<>();
        for (JsonElement element:jsonArray){
            AchievementInfo data = gson.fromJson(element,new TypeToken<AchievementInfo>(){}.getType());
            jsonList.add(data);
        }
        for (int i=0;i<3;i++){
            lists.add(jsonList.get(i));
        }
        achievementAdapter=new AchievementAdapter(getContext(),lists);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(achievementAdapter);

        //todo 刷新Application中的信息
        if (BaseApplication.getApplication().getProfileList()==null){
            BaseApplication.getApplication().setProfileList(new ProfileList());
            BaseApplication.getApplication().getProfileList().setAchievementInfoList(jsonList);
        }else
            BaseApplication.getApplication().getProfileList().setAchievementInfoList(jsonList);
    }

    private void initViews() {
        linearLayout=findViewById(R.id.view_achievement_stu_linearLayout);
        textScore=findViewById(R.id.view_achievement_stu_score);
        imageView=findViewById(R.id.view_achievement_stu_medal);
        recyclerView=findViewById(R.id.view_achievement_stu_rec);
        linearLayoutHide=findViewById(R.id.view_achievement_stu_linearLayout_hide);
        button=findViewById(R.id.view_achievement_stu_btn);
        CardView outerCard=findViewById(R.id.view_achievement_stu_outer_cardView);
        CardView innerCard=findViewById(R.id.view_achievement_stu_inner_cardView);

        outerCard.setCardBackgroundColor(getResources().getColor(R.color.cardBac));
        innerCard.setCardBackgroundColor(getResources().getColor(R.color.cardBac));

        lists=new ArrayList<>();

//        textScore.setText(profileList.getScore());
//        ImgUtils.load(getContext(),R.drawable.ic_gold,imageView);
    }
}
