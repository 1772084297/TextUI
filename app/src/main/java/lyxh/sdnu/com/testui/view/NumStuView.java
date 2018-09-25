package lyxh.sdnu.com.testui.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import lyxh.sdnu.com.testui.Activity.DetailedActivity;
import lyxh.sdnu.com.testui.Activity.MainActivity;
import lyxh.sdnu.com.testui.ImgUtils;
import lyxh.sdnu.com.testui.ProfileList;
import lyxh.sdnu.com.testui.R;

//
public class NumStuView extends LinearLayout {
    private ProfileList profileList;
    private LinearLayout linearLayout;
    private TextView numStu;
    private ImageView imageStatus;


    public NumStuView(Context context) {
        super(context);
        init();
    }

    public NumStuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumStuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_num_stu, this
                , true);
        initViews();
        setClick();
    }

    private void setClick() {
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), DetailedActivity.class);
                intent.putExtra("key",ProfileList.VIEW_NUM_STU);
                MainActivity.instance.overridePendingTransition(R.anim.zoomin,android.R.anim.fade_out);
                getContext().startActivity(intent);
            }
        });
    }

    private void initViews() {
        linearLayout=findViewById(R.id.view_num_stu_linearLayout);
        numStu=findViewById(R.id.view_num_stu_number);
        imageStatus=findViewById(R.id.view_num_stu_status);
        CardView outerCard=findViewById(R.id.view_num_stu_outer_cardView);
        CardView innerCard=findViewById(R.id.view_num_stu_inner_cardView);

        outerCard.setCardBackgroundColor(getResources().getColor(R.color.cardBac));
        innerCard.setCardBackgroundColor(getResources().getColor(R.color.cardBac));

//        numStu.setText(profileList.getNumStu());
//        switch (profileList.getStatusNum()){
//            case ProfileList.NUM_CLASS:
//                ImgUtils.loadRound(getContext(),R.drawable.ic_class,imageStatus);
//                break;
//            case ProfileList.NUM_DEPARTMENT:
//                ImgUtils.loadRound(getContext(),R.drawable.ic_department,imageStatus);
//                break;
//            case ProfileList.NUM_SCHOOL:
//                ImgUtils.loadRound(getContext(),R.drawable.ic_school,imageStatus);
//                break;
//                default:
//                    break;
//        }
    }
}
