package lyxh.sdnu.com.testui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lyxh.sdnu.com.testui.AchievementInfo;
import lyxh.sdnu.com.testui.ImgUtils;
import lyxh.sdnu.com.testui.R;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementHolder>{
    private Context context;
    private List<AchievementInfo> lists;

    public AchievementAdapter(Context context, List<AchievementInfo> lists) {
        this.context = context;
        this.lists = lists;
    }
    @NonNull
    @Override
    public AchievementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_achievement_stu,parent,false);
        return new AchievementHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AchievementHolder holder, int position) {
        holder.title.setText(lists.get(position).getCourseName());
        holder.score.setText(lists.get(position).getScore());
        //todo 需要根据成绩多少来进行判定牌子
        if (lists.get(position).getScoreInt() >=90)
            ImgUtils.load(context, R.drawable.ic_gold, holder.status);
        else if (lists.get(position).getScoreInt()>60&&lists.get(position).getScoreInt()<90)
            ImgUtils.load(context, R.drawable.ic_silver, holder.status);
        else
            ImgUtils.load(context, R.drawable.ic_copper, holder.status);
    }
    @Override
    public int getItemCount() {
        return lists.size();
    }

    class AchievementHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView score;
        private ImageView status;
        AchievementHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.item_achievementStuView_title);
            score=itemView.findViewById(R.id.item_achievementStuView_score);
            status=itemView.findViewById(R.id.item_achievementStuView_status);
        }
    }
}
