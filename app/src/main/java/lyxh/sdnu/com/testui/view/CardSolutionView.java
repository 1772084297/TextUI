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
import lyxh.sdnu.com.testui.Data.ProfileList;
import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.Utils.ImgUtils;
import lyxh.sdnu.com.testui.Utils.Utils;

// 一卡通
public class CardSolutionView extends LinearLayout {
    private LinearLayout linearLayout;
    private CardView outerCard;
    private CardView innerCard;
    private ImageView imageAva;
    private TextView schoolId;
    private TextView cardId;
    private TextView balance;
    private TextView transitionBalance;
    private ImageView cardStatus;

    public CardSolutionView(Context context) {
        super(context);
        init();
    }

    public CardSolutionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardSolutionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_card_solution, this
                , true);
        initViews();
        setClick();
    }

    private void setClick() {
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), DetailedActivity.class);
                intent.putExtra("key", ProfileList.VIEW_CARD_SOLUTION);
                MainActivity.instance.overridePendingTransition(R.anim.zoomin, android.R.anim.fade_out);
                getContext().startActivity(intent);
            }
        });
    }


    private void initViews() {
        outerCard = findViewById(R.id.view_card_outer_cardView);
        innerCard = findViewById(R.id.view_card_inner_cardView);
        linearLayout = findViewById(R.id.view_card_linearLayout);
        imageAva = findViewById(R.id.view_card_ava);
        schoolId = findViewById(R.id.view_card_school_id);
        cardId = findViewById(R.id.view_card_id);
        balance = findViewById(R.id.view_card_balance);
        transitionBalance = findViewById(R.id.view_card_transition_balance);
        cardStatus = findViewById(R.id.view_card_status);

        innerCard.setCardBackgroundColor(getResources().getColor(R.color.bac));
        outerCard.setCardBackgroundColor(getResources().getColor(R.color.cardBac));

        ImgUtils.loadRound(getContext(), R.mipmap.default_avatar_img, imageAva);//todo load ava
        ImgUtils.load(getContext(), R.drawable.card_normal, cardStatus); //todo load card_status

    }
}
