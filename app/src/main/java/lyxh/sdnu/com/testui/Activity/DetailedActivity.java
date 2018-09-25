package lyxh.sdnu.com.testui.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import lyxh.sdnu.com.testui.fragment.AchievementStuFragment;
import lyxh.sdnu.com.testui.fragment.CardSolutionFragment;
import lyxh.sdnu.com.testui.ProfileList;
import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.fragment.CommonlyUrlFragment;
import lyxh.sdnu.com.testui.fragment.NumStuFragment;

public class DetailedActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private ImageView imageBack;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initViews();
        setFragment();
        setClick();
    }

    private void setClick() {
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        toolbar = findViewById(R.id.detailed_toolbar);
        frameLayout = findViewById(R.id.detailed_frameLayout);
        imageBack = findViewById(R.id.detailed_back);
        title = findViewById(R.id.detailed_title);
    }

    private void setFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        int status = getIntent().getIntExtra("key", 0);
        Fragment fragment = null;
        switch (status) {
            //一卡通
            case ProfileList.VIEW_CARD_SOLUTION:
                title.setText("消费信息");
                fragment = CardSolutionFragment.getInstance();
                break;
            //学生数量
            case ProfileList.VIEW_NUM_STU:
                title.setText("学生数量");
                fragment = new NumStuFragment();
                break;
            case ProfileList.VIEW_ACHIEVEMENT_STU:
                title.setText("学习成绩");
                fragment = new AchievementStuFragment();
                break;
            case ProfileList.VIEW_COMMONLY_URL:
                title.setText("常用网址");
                fragment = new CommonlyUrlFragment();
                break;
            default:
                break;
        }
        transaction.replace(R.id.detailed_frameLayout, fragment);
        //todo 还要动态修改toolbar的名字.
        transaction.commit();

    }
}
