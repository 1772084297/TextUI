package lyxh.sdnu.com.testui.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;

import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.fragment.HomeFragment;
import lyxh.sdnu.com.testui.fragment.NewsFragment;
import lyxh.sdnu.com.testui.fragment.ProfileFragment;

//https://blog.csdn.net/lin_dianwei/article/details/78914046 优雅的返回
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    public static MainActivity instance;
    private ArrayList<Fragment> fragments;
    private BottomNavigationBar bottomNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.mainbg));
        bindViews();
        createBottomBar();
    }


    private void createBottomBar() {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);//mode为非固定
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar.setBarBackgroundColor(R.color.black);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home, "首页").setInActiveColorResource(R.color.black).setActiveColorResource(R.color.cardBac))
                .addItem(new BottomNavigationItem(R.drawable.news, "新闻").setInActiveColorResource(R.color.black).setActiveColorResource(R.color.cardBac))
                .addItem(new BottomNavigationItem(R.drawable.profile, "个人").setInActiveColorResource(R.color.black).setActiveColorResource(R.color.cardBac))
                .setFirstSelectedPosition(0)
                .initialise();
        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment, fragments.get(0));
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new NewsFragment());
        fragmentArrayList.add(new ProfileFragment());
        return fragmentArrayList;
    }


    private void bindViews() {
        instance = this;


        fragments = new ArrayList<>();
        bottomNavigationBar = findViewById(R.id.main_bottom_navigation_bar);
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm;
        FragmentTransaction ft = null;
        Fragment from = null;
        Fragment fragment = null;
        if (fragments != null) {
            if (position < fragments.size()) {
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                //当前的fragment
                from = (Fragment) fm.findFragmentById(R.id.fragment);
                //点击即将跳转的fragment
                fragment = fragments.get(position);

                if (fragment.isAdded()) {
                    // 隐藏当前的fragment，显示下一个
                    ft.hide(from).show(fragment);
                } else {
                    // 隐藏当前的fragment，add下一个到Activity中
                    ft.hide(from).add(R.id.fragment, fragment);
                }
                ft.commitAllowingStateLoss();
            }
        } else {
            // 隐藏当前的fragment，add下一个到Activity中
            ft.hide(from).add(R.id.fragment, fragment);
            if (fragment.isHidden()) {
                ft.show(fragment);
            }
        }
        switch (position) {
            case 0:
                StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.mainbg));
                break;
            case 1:
                StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.newsbg));
                break;
            case 2:
                StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.perbg));
                break;
            default:
                break;

        }
    }

    @Override
    public void onTabUnselected(int position) {
        //这儿也要操作隐藏，否则Fragment会重叠
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                // 隐藏当前的fragment
                ft.hide(fragment);
                ft.commitAllowingStateLoss();
            }
        }

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
