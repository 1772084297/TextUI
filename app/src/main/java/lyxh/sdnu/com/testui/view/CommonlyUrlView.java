package lyxh.sdnu.com.testui.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import lyxh.sdnu.com.testui.Activity.DetailedActivity;
import lyxh.sdnu.com.testui.Activity.MainActivity;
import lyxh.sdnu.com.testui.ProfileList;
import lyxh.sdnu.com.testui.R;

public class CommonlyUrlView extends LinearLayout {
    private LinearLayout linearLayout;
    private TextView textWeb;
    private ProfileList profileList;

    public CommonlyUrlView(Context context) {
        super(context);
        init();
    }

    public CommonlyUrlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommonlyUrlView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_commonly_url, this
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
                intent.putExtra("key",ProfileList.VIEW_COMMONLY_URL);
                MainActivity.instance.overridePendingTransition(R.anim.zoomin,android.R.anim.fade_out);
                getContext().startActivity(intent);
            }
        });
    }

    private void initViews() {
        linearLayout=findViewById(R.id.view_commonly_url_linearLayout);
        textWeb=findViewById(R.id.view_commonly_url_address);
        CardView outerCard=findViewById(R.id.view_commonly_url_outer_cardView);
        CardView innerCard=findViewById(R.id.view_commonly_url_inner_cardView);

        outerCard.setCardBackgroundColor(getResources().getColor(R.color.cardBac));
        innerCard.setCardBackgroundColor(getResources().getColor(R.color.cardBac));
    }
}
