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

import lyxh.sdnu.com.testui.Data.Performance;
import lyxh.sdnu.com.testui.Data.SemesterPer;
import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.Utils.ImgUtils;

public class PerformanceAdapter extends RecyclerView.Adapter<PerformanceAdapter.PerformanceHolder>{
    private Context context;
    private List<SemesterPer> lists;

    public PerformanceAdapter(Context context, List<SemesterPer> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public PerformanceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_performance,viewGroup,false);
        return new PerformanceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerformanceHolder performanceHolder, int i) {
        performanceHolder.targetPer.setText(lists.get(i).getTargetPer()+"");
        performanceHolder.actualPer.setText(lists.get(i).getActualPer()+"");
        switch (i){
            case 0:
                ImgUtils.load(context,R.drawable.ic_a1,performanceHolder.semesterStatus);
                break;
            case 1:
                ImgUtils.load(context,R.drawable.ic_a2,performanceHolder.semesterStatus);
                break;
            case 2:
                ImgUtils.load(context,R.drawable.ic_a3,performanceHolder.semesterStatus);
                break;
            case 3:
                ImgUtils.load(context,R.drawable.ic_a4,performanceHolder.semesterStatus);
                break;
            case 4:
                ImgUtils.load(context,R.drawable.ic_a5,performanceHolder.semesterStatus);
                break;
            case 5:
                ImgUtils.load(context,R.drawable.ic_a6,performanceHolder.semesterStatus);
                break;
            case 6:
                ImgUtils.load(context,R.drawable.ic_a7,performanceHolder.semesterStatus);
                break;
            case 7:
                ImgUtils.load(context,R.drawable.ic_a8,performanceHolder.semesterStatus);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class PerformanceHolder extends RecyclerView.ViewHolder {
        private ImageView semesterStatus;
        private TextView actualPer;
        private TextView targetPer;
        PerformanceHolder(@NonNull View itemView) {
            super(itemView);
            semesterStatus=itemView.findViewById(R.id.item_performance_semester_status);
            actualPer=itemView.findViewById(R.id.item_performance_actual_num);
            targetPer=itemView.findViewById(R.id.item_performance_target_num);
        }
    }
}
