package lyxh.sdnu.com.testui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;
import lyxh.sdnu.com.testui.Data.ProfileList;
import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.view.CommonlyUrlView;
import lyxh.sdnu.com.testui.view.SexView;
import lyxh.sdnu.com.testui.view.AchievementStuView;
import lyxh.sdnu.com.testui.view.CardSolutionView;
import lyxh.sdnu.com.testui.view.NumStuView;
import lyxh.sdnu.com.testui.view.StraightStuView;


public class HomeRecAdapter extends RecyclerView.Adapter<HomeRecAdapter.HomeRecHolder>{
    private Context context;
    private List<ProfileList> lists;

    public HomeRecAdapter(Context context,List<ProfileList> lists){
        this.context=context;
        this.lists=lists;
    }

    @NonNull
    @Override
    public HomeRecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_home_rec,parent,false);
        return new HomeRecHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecHolder holder, int position) {
        switch (lists.get(position).getStatus()){
            case ProfileList.VIEW_CARD_SOLUTION:
                holder.sexView.setVisibility(View.GONE);
                holder.straightStuView.setVisibility(View.GONE);
                holder.numStuView.setVisibility(View.GONE);
                holder.achievementStuView.setVisibility(View.GONE);
                holder.commonlyUrlView.setVisibility(View.GONE);
                break;
            case ProfileList.VIEW_SEX:
                holder.cardSolutionView.setVisibility(View.GONE);
                holder.straightStuView.setVisibility(View.GONE);
                holder.numStuView.setVisibility(View.GONE);
                holder.achievementStuView.setVisibility(View.GONE);
                holder.commonlyUrlView.setVisibility(View.GONE);
                break;
            case ProfileList.VIEW_STRAIGHT_STU:
                holder.sexView.setVisibility(View.GONE);
                holder.cardSolutionView.setVisibility(View.GONE);
                holder.numStuView.setVisibility(View.GONE);
                holder.achievementStuView.setVisibility(View.GONE);
                holder.commonlyUrlView.setVisibility(View.GONE);
                break;
            case ProfileList.VIEW_NUM_STU:
                holder.sexView.setVisibility(View.GONE);
                holder.straightStuView.setVisibility(View.GONE);
                holder.cardSolutionView.setVisibility(View.GONE);
                holder.achievementStuView.setVisibility(View.GONE);
                holder.commonlyUrlView.setVisibility(View.GONE);
                break;
            case ProfileList.VIEW_ACHIEVEMENT_STU:
                holder.numStuView.setVisibility(View.GONE);
                holder.sexView.setVisibility(View.GONE);
                holder.straightStuView.setVisibility(View.GONE);
                holder.cardSolutionView.setVisibility(View.GONE);
                holder.commonlyUrlView.setVisibility(View.GONE);
                break;
            case ProfileList.VIEW_COMMONLY_URL:
                holder.numStuView.setVisibility(View.GONE);
                holder.sexView.setVisibility(View.GONE);
                holder.straightStuView.setVisibility(View.GONE);
                holder.cardSolutionView.setVisibility(View.GONE);
                holder.achievementStuView.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class HomeRecHolder extends RecyclerView.ViewHolder implements AnimateViewHolder {
        private SexView sexView;
        private CardSolutionView cardSolutionView;
        private StraightStuView straightStuView;
        private NumStuView numStuView;
        private AchievementStuView achievementStuView;
        private CommonlyUrlView commonlyUrlView;
        HomeRecHolder(View itemView) {
            super(itemView);
            sexView=itemView.findViewById(R.id.list_item_sexView);
            cardSolutionView=itemView.findViewById(R.id.list_item_cardSolutionView);
            straightStuView=itemView.findViewById(R.id.list_item_straightStuView);
            numStuView=itemView.findViewById(R.id.list_item_numStuView);
            achievementStuView=itemView.findViewById(R.id.list_item_achievementStuView);
            commonlyUrlView=itemView.findViewById(R.id.list_item_commonlyUrlView);
        }

        @Override
        public void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {

        }
        @Override
        public void animateRemoveImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(-itemView.getHeight() * 0.3f)
                    .alpha(0)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }
        @Override
        public void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
            ViewCompat.setTranslationY(itemView, -itemView.getHeight() * 0.3f);
            ViewCompat.setAlpha(itemView, 0);
        }
        @Override
        public void animateAddImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(0)
                    .alpha(1)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }
    }
}
