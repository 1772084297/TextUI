package lyxh.sdnu.com.testui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import lyxh.sdnu.com.testui.Activity.MainActivity;
import lyxh.sdnu.com.testui.ImgUtils;
import lyxh.sdnu.com.testui.ProfileList;
import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.fragment.StraightStuFragment;

//学霸
public class StraightStuView extends LinearLayout {
    private ProfileList profileList;
    private LinearLayout linearLayout;
    private ImageView imageAva;
    private TextView name;
    public StraightStuView(Context context) {
        super(context);
        init();
    }

    public StraightStuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StraightStuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_straight_stu, this
                , true);
        initViews();
        setClick();
    }



    private void setClick() {
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                StraightStuFragment fragment = new StraightStuFragment();
                fragment.show(MainActivity.instance.getSupportFragmentManager(),"");
            }
        });
    }

    private void initViews() {
        imageAva=findViewById(R.id.view_straight_stu_ava);
        name=findViewById(R.id.view_straight_stu_name);
        linearLayout=findViewById(R.id.view_straight_stu_linearLayout);
        CardView outerCard=findViewById(R.id.view_straight_stu_outer_cardView);
        CardView innerCard=findViewById(R.id.view_straight_stu_inner_cardView);

        outerCard.setCardBackgroundColor(getResources().getColor(R.color.cardBac));
        innerCard.setCardBackgroundColor(getResources().getColor(R.color.cardBac));

        ImgUtils.loadRound(getContext(),"http://p5tgr5sc2.bkt.clouddn.com/A679D273437DEFA841557A895BFE2AD3.jpg",imageAva);//todo load ava
//        ImgUtils.loadRound(getContext(),profileList.getStraightAvaUrl(),imageAva);//todo load ava
//        name.setText(profileList.getStraightName());
    }

    public ProfileList getProfileList() {
        return profileList;
    }

    public void setProfileList(ProfileList profileList) {
        this.profileList = profileList;
    }
}
