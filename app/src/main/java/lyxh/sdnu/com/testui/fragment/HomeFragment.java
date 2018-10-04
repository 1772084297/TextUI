package lyxh.sdnu.com.testui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import lyxh.sdnu.com.testui.Activity.MainActivity;
import lyxh.sdnu.com.testui.Adapter.HomeRecAdapter;
import lyxh.sdnu.com.testui.Data.ProfileList;
import lyxh.sdnu.com.testui.R;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<ProfileList> lists;
    private HomeRecAdapter homeRecAdapter;
    private Toolbar toolbar;
    private ImageView toolbarSets;
    private TextView toolbarTitle;
    private SmartRefreshLayout smartRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        bindViews(view);
        initData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.cardBac));
        }
        setClick();
        setActionBar();
        setRecyclerView();
        return view;
    }

    private void setClick() {

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(500);
            }
        });
    }

    private void setActionBar() {
        toolbar.setTitle("");
        toolbarTitle.setText("首页");
        MainActivity.instance.setSupportActionBar(toolbar);
    }

    private void initData() {
        lists=new ArrayList<>();
        lists.add(new ProfileList(0));
        lists.add(new ProfileList(3));
        lists.add(new ProfileList(1));
        lists.add(new ProfileList(2));
        lists.add(new ProfileList(4));
        lists.add(new ProfileList(5));
    }

    private void setRecyclerView() {
        homeRecAdapter=new HomeRecAdapter(getContext(),lists);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        ScaleInAnimationAdapter alphaAdapter = new ScaleInAnimationAdapter(homeRecAdapter);
        alphaAdapter.setInterpolator(new OvershootInterpolator());
        alphaAdapter.setFirstOnly(false);
        recyclerView.setAdapter(alphaAdapter);

    }

    private void bindViews(View view) {
        recyclerView=view.findViewById(R.id.fragment_home_rec);
        toolbar=view.findViewById(R.id.fragment_home_toolbar);
        toolbarTitle=view.findViewById(R.id.fragment_home_toolbar_title);
        toolbarSets=view.findViewById(R.id.fragment_home_toolbar_sets);
        smartRefreshLayout=view.findViewById(R.id.fragment_home_smartRefresh);
        setHasOptionsMenu(true);
    }

}
