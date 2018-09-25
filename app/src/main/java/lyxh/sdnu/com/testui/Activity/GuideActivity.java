package lyxh.sdnu.com.testui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;

import lyxh.sdnu.com.testui.Adapter.Guide_Adapter;
import lyxh.sdnu.com.testui.R;

//引导界面
public class GuideActivity extends AppCompatActivity {
    private ViewPager mGuide_viewpager;
    private int[] mImages;
    private Button mGuide_begin;
    private ArrayList<ImageView> mImageViewArrayList;
    private LinearLayout mLlContainear;
    private ImageView mGuide_red_point;
    private int mPointWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_guide);

        mGuide_viewpager = findViewById(R.id.guide_viewpager);
        mGuide_begin = findViewById(R.id.guide_begin);
        mLlContainear = findViewById(R.id.llContainer);
        mGuide_red_point = findViewById(R.id.guide_red_point);
        mImages = new int[]{R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3};

        mImageViewArrayList = new ArrayList<ImageView>();
        for (int i = 0; i < mImages.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(mImages[i]);
            mImageViewArrayList.add(iv);

            ImageView circleView = new ImageView(this);
            circleView.setBackgroundResource(R.drawable.guide_circle_shape_select);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                params.leftMargin = 40;
            }
            circleView.setLayoutParams(params);
            mLlContainear.addView(circleView);
        }


        mGuide_red_point.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mGuide_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        mPointWidth = mLlContainear.getChildAt(1).getLeft() -
                                mLlContainear.getChildAt(0).getLeft();
                    }
                });

        Guide_Adapter ga = new Guide_Adapter(mImageViewArrayList, this);
        mGuide_viewpager.setAdapter(ga);

        mGuide_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                int leftMargin = (int) (position * mPointWidth + mPointWidth * positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                        mGuide_red_point.getLayoutParams();
                params.leftMargin = leftMargin;
                mGuide_red_point.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mImages.length - 1) {

//                    AlphaAnimation aniAlpha = new AlphaAnimation(0.0f, 1.0f);
//                    aniAlpha.setDuration(2000);
//                    aniAlpha.setRepeatMode(Animation.REVERSE);
//                    aniAlpha.setRepeatCount(1);
                    TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                            1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    mShowAction.setDuration(1000);
                    mGuide_begin.setVisibility(View.VISIBLE);
                    mGuide_begin.startAnimation(mShowAction);
                } else {
                    mGuide_begin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mGuide_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //修改SP

                startActivity(new Intent(GuideActivity.this,
                        MainActivity.class));
                finish();
            }
        });
    }
}
