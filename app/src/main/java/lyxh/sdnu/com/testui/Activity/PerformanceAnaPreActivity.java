package lyxh.sdnu.com.testui.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import lyxh.sdnu.com.testui.Adapter.PerformanceAdapter;
import lyxh.sdnu.com.testui.BaseApplication;
import lyxh.sdnu.com.testui.Data.Performance;
import lyxh.sdnu.com.testui.Data.ProfileList;
import lyxh.sdnu.com.testui.Data.SemesterPer;
import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.Utils.ImgUtils;

public class PerformanceAnaPreActivity extends AppCompatActivity {
    private ImageView back;
    private ImageView status;
    private ImageView semesterStatus;
    private TextView currentPer;
    private TextView currentTotalPer;
    private TextView needTotalPer;
    private TextView disparityPer;
    private RecyclerView recyclerView;
    private CardView outer;
    private CardView inner;

    private Performance performance;
    private PerformanceAdapter performanceAdapter;
    private List<SemesterPer> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_ana_pre);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.mainbg));
        bindViews();
        setClick();
        initData();
        initViews();
    }


    private void initViews() {
        //todo 需要有一定的逻辑进行判断学生是否能够毕业
        ImgUtils.load(getApplicationContext(),R.drawable.ic_normal,status);
        currentPer.setText(performance.getCurrentPer()+"");
        currentTotalPer.setText(performance.getCurrentTotalPer()+"");
        needTotalPer.setText(performance.getNeedTotalPer()+"");
        disparityPer.setText(performance.getDisparityPer()+"");
        switch (performance.getSemestersPer().size()){
            case 0:
                ImgUtils.load(getApplicationContext(),R.drawable.ic_a1,semesterStatus);
                break;
            case 1:
                ImgUtils.load(getApplicationContext(),R.drawable.ic_a2,semesterStatus);
                break;
            case 2:
                ImgUtils.load(getApplicationContext(),R.drawable.ic_a3,semesterStatus);
                break;
            case 3:
                ImgUtils.load(getApplicationContext(),R.drawable.ic_a4,semesterStatus);
                break;
            case 4:
                ImgUtils.load(getApplicationContext(),R.drawable.ic_a5,semesterStatus);
                break;
            case 5:
                ImgUtils.load(getApplicationContext(),R.drawable.ic_a6,semesterStatus);
                break;
            case 6:
                ImgUtils.load(getApplicationContext(),R.drawable.ic_a7,semesterStatus);
                break;
            case 7:
                ImgUtils.load(getApplicationContext(),R.drawable.ic_a8,semesterStatus);
                break;
            default:
                break;
        }
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(performanceAdapter);
        inner.setCardBackgroundColor(getResources().getColor(R.color.bac));
    }

    private void initData() {
//        ProfileList profileList;
//        if (BaseApplication.getApplication().getProfileList()==null)
//            profileList=new ProfileList();
//        else
//            profileList=BaseApplication.getApplication().getProfileList();
//        if (profileList.getPerformance()!=null)
//            performance=profileList.getPerformance();
//        else {
//            performance=new Performance();
//            performance.setCurrentPer(35);
//            performance.setCurrentTotalPer(114);
//            performance.setNeedTotalPer(165);
//            performance.setDisparityPer(51);
//            lists=new ArrayList<>();
//            lists.add(new SemesterPer(22,22));
//            lists.add(new SemesterPer(31,31));
//            lists.add(new SemesterPer(30,30));
//            lists.add(new SemesterPer(33,31));
//            performance.setSemestersPer(lists);
//        }
        performance = new Performance();
        performance.setCurrentPer(35);
        performance.setCurrentTotalPer(114);
        performance.setNeedTotalPer(165);
        performance.setDisparityPer(51);
        lists = new ArrayList<>();
        lists.add(new SemesterPer(22, 22));
        lists.add(new SemesterPer(31, 31));
        lists.add(new SemesterPer(30, 30));
        lists.add(new SemesterPer(33, 31));
        performance.setSemestersPer(lists);
        performanceAdapter=new PerformanceAdapter(getApplicationContext(),performance.getSemestersPer());
    }

    private void setClick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void bindViews() {
        back=findViewById(R.id.activity_performance_back);
        status=findViewById(R.id.activity_performance_status);
        semesterStatus=findViewById(R.id.activity_performance_semester);
        currentPer=findViewById(R.id.activity_performance_current_num);
        currentTotalPer=findViewById(R.id.activity_performance_total_current);
        needTotalPer=findViewById(R.id.activity_performance_total_need);
        disparityPer=findViewById(R.id.activity_performance_disparity_num);
        recyclerView=findViewById(R.id.activity_performance_rec);
        outer=findViewById(R.id.activity_performance_outer_cardView);
        inner=findViewById(R.id.activity_performance_inner_cardView);

    }
}
