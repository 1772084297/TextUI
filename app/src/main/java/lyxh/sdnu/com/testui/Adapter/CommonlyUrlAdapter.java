package lyxh.sdnu.com.testui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.Data.WebMap;
import lyxh.sdnu.com.testui.Utils.ImgUtils;

public class CommonlyUrlAdapter extends RecyclerView.Adapter<CommonlyUrlAdapter.CommonlyUrlHolder>{
    private Context context;
    private List<WebMap> lists;
    public CommonlyUrlAdapter(Context context,List<WebMap> lists){
        this.context=context;
        this.lists=lists;
    }

    @NonNull
    @Override
    public CommonlyUrlHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_commonly_url,parent,false);
        return new CommonlyUrlHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonlyUrlHolder holder, final int position) {
        holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.cardBac));
        holder.webUrl.setText(lists.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setData(Uri.parse(lists.get(position).getUrl()));
                context.startActivity(intent);
            }
        });
        ImgUtils.load(context,lists.get(position).getImageUrl(),holder.imageView);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class CommonlyUrlHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView webUrl;
        private ImageView imageView;
        CommonlyUrlHolder(View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.item_commonly_url_cardView);
            webUrl=itemView.findViewById(R.id.item_commonly_url_address);
            imageView=itemView.findViewById(R.id.item_commonly_url_image);
        }
    }
}
