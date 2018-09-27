package lyxh.sdnu.com.testui.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.cleveroad.fanlayoutmanager.FanLayoutManager;
import com.cleveroad.fanlayoutmanager.FanLayoutManagerSettings;

import java.util.ArrayList;
import java.util.List;

import lyxh.sdnu.com.testui.Adapter.Adapter_card;
import lyxh.sdnu.com.testui.Data.CardsModel;
import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.Utils.ImgUtils;
import lyxh.sdnu.com.testui.Utils.Utils;

//扇形页面展示
public class StraightStuFragment extends DialogFragment {

    private RecyclerView recyclerView;
    private FanLayoutManager fanLayoutManager;
    private ImageView top_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_straight_stu, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        initView(view);


        return view;
    }

    //设置了背景透明，fragment背景不是透明
    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = 0.0f;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        top_image = view.findViewById(R.id.image_top10);

        FanLayoutManagerSettings setting = FanLayoutManagerSettings
                .newBuilder(getContext())
                .withFanRadius(true)
                .withAngleItemBounce(5)
                .withViewHeightDp(160)
                .withViewWidthDp(120)
                .build();
        fanLayoutManager = new FanLayoutManager(getContext(), setting);

        fanLayoutManager.scrollToPosition(4);
        recyclerView.setLayoutManager(fanLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Adapter_card adapter = new Adapter_card(getContext());
        adapter.addAll(generateSportCards());
        adapter.setOnItemClickListener(new Adapter_card.OnItemClickListener() {
            @Override
            public void onItemClicked(int pos, View view) {
                if (!Utils.isFastDoubleClick()) {
                    //点击频率过高会造成偏移 可能时间间隔会更小，再改
                    if (fanLayoutManager.getSelectedItemPosition() != pos) {
                        fanLayoutManager.switchItem(recyclerView, pos);
                    } else {
                        fanLayoutManager.deselectItem();

                    }
                }

            }
        });


        recyclerView.setAdapter(adapter);

        ImgUtils.load(getContext(), R.drawable.top_10, top_image);
        top_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
            }
        });
    }


    //生成数据，需要从服务器中获取
    private List<CardsModel> generateSportCards() {
        List<CardsModel> sportCardModels = new ArrayList<>(5);
        {
            sportCardModels.add(new CardsModel("李秀",
                    "9",
                    "http://p5tgr5sc2.bkt.clouddn.com/BA858AC647C9DF1BB5C82C0D485DC9DC.jpg",
                    "信息科学与工程学院",
                    "计算机1604",
                    R.color.usc_gold));
        }

        {
            sportCardModels.add(new CardsModel("郑乐平",
                    "7",
                    "http://p5tgr5sc2.bkt.clouddn.com/0083CD1A4804CA2F5797E1F27EF47971.jpg",
                    "信息科学与工程学院",
                    "通信1603",
                    R.color.dark_orchid));
        }

        {
            sportCardModels.add(new CardsModel("蒋俊",
                    "5",
                    "http://p5tgr5sc2.bkt.clouddn.com/77E5F8E9B2BC4F0DE0D1A50E8261B684.jpg",
                    "信息科学与工程学院",
                    "计算机1603",
                    R.color.usc_gold));
        }
        {
            sportCardModels.add(new CardsModel("詹慧萍",
                    "3",
                    "http://p5tgr5sc2.bkt.clouddn.com/0F7875DC37009882DCECF92DB419B17B.jpg",
                    "信息科学与工程学院",
                    "计算机1602",
                    R.color.dark_cerulean));
        }

        {
            sportCardModels.add(new CardsModel("陈常收",
                    "1",
                    "http://p5tgr5sc2.bkt.clouddn.com/A679D273437DEFA841557A895BFE2AD3.jpg",
                    "信息科学与工程学院",
                    "计算机1604",
                    R.color.mantis));
        }
        {
            sportCardModels.add(new CardsModel("别同",
                    "2",
                    "http://p5tgr5sc2.bkt.clouddn.com/7c41b7e9bd775cae08273559c8269451.png",
                    "信息科学与工程学院",
                    "计算机1602",
                    R.color.dark_orchid));
        }

        {
            sportCardModels.add(new CardsModel("张田",
                    "4",
                    "http://p5tgr5sc2.bkt.clouddn.com/timg%281%29.jpg",
                    "信息科学与工程学院",
                    "通信1602",
                    R.color.portland_orange));
        }
        {
            sportCardModels.add(new CardsModel("孙清雪",
                    "6",
                    "http://p5tgr5sc2.bkt.clouddn.com/540DF4027AA6951CE1B9239220F1D56B.jpg",
                    "信息科学与工程学院",
                    "计师本1601",
                    R.color.manatee));
        }

        {
            sportCardModels.add(new CardsModel("张晓明",
                    "8",
                    "http://p5tgr5sc2.bkt.clouddn.com/6E60FF6FEC4E25B58E42F73F661CCA89.jpg",
                    "信息科学与工程学院",
                    "物联网1601",
                    R.color.dodger_blue));
        }
        {
            sportCardModels.add(new CardsModel("孙浩",
                    "10",
                    "http://p5tgr5sc2.bkt.clouddn.com/hellohello_1528124855577_avatar",
                    "信息科学与工程学院",
                    "计算机1605",
                    R.color.mantis));
        }

        return sportCardModels;
    }


}
