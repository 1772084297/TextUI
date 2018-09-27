package lyxh.sdnu.com.testui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lyxh.sdnu.com.testui.Data.AchievementInfo;
import lyxh.sdnu.com.testui.Adapter.AchievementAdapter;
import lyxh.sdnu.com.testui.BaseApplication;
import lyxh.sdnu.com.testui.Data.ProfileList;
import lyxh.sdnu.com.testui.R;

public class AchievementStuFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<AchievementInfo> lists;
    private AchievementAdapter achievementAdapter;
    private ProfileList profileList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_achievement_stu,container,false);
        recyclerView=view.findViewById(R.id.fragment_achievement_stu_rec);
        initData();
        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        achievementAdapter=new AchievementAdapter(getContext(),lists);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(achievementAdapter);
    }

    private void initData() {
        lists=new ArrayList<>();
        profileList= BaseApplication.getApplication().getProfileList();
        if (profileList!=null)
            lists.addAll(profileList.getAchievementInfoList());
    }
}
