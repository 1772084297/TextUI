package lyxh.sdnu.com.testui.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lyxh.sdnu.com.testui.Data.CardsModel;
import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.Utils.ImgUtils;

//学霸指数界面的适配器
public class Adapter_card extends RecyclerView.Adapter<Adapter_card.ViewHolder> {

    private Context context;
    private List<CardsModel> itemList;
    private OnItemClickListener itemClickListener;


    public interface OnItemClickListener {
        void onItemClicked(int pos, View view);
    }


    public Adapter_card(Context context) {
        this.context = context;
        itemList = new ArrayList<>();
    }

    //添加单条数据
    public boolean add(CardsModel item) {
        boolean isAdded = itemList.add(item);
        if (isAdded) {
            notifyDataSetChanged();
        }
        return isAdded;
    }

    //添加多条数据
    public boolean addAll(List<CardsModel> items) {
        boolean isAdded = itemList.addAll(items);
        if (isAdded) {
            notifyDataSetChanged();
        }
        return isAdded;
    }

    //清除数据
    public void clear() {
        itemList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        CardsModel item = itemList.get(position);
        holder.card_stuName.setText(item.getStuName());
        holder.rank.setText("Top "+item.getRank());
//        holder.icon_image.setImageResource(item.getImageResId());
        ImgUtils.loadRound(context,item.getImageResId(),holder.icon_image);

        holder.card_stuCollege.setText(item.getStuCollege());
        holder.card_stuClass.setText(item.getStuClass());

        ((CardView) holder.itemView).setCardBackgroundColor(ContextCompat.getColor(context,
                item.getBackgroundColorResId()));

        //共享元素，很高深的样子 用不到
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.icon_image.setTransitionName("shared" + String.valueOf(position));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClicked(holder.getAdapterPosition(), holder.icon_image);
            }
        });


    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }



    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView card_stuName;
        private final TextView rank;
        private final TextView card_stuCollege;
        private final TextView card_stuClass;
//        private CircleImageView icon_image;

        private final ImageView icon_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card_stuName = itemView.findViewById(R.id.card_stuName);
            rank = itemView.findViewById(R.id.card_rank);
            icon_image = itemView.findViewById(R.id.icon_image);
            card_stuCollege = itemView.findViewById(R.id.card_stuCollege);
            card_stuClass = itemView.findViewById(R.id.card_stuClass);
        }
    }
}


