package lyxh.sdnu.com.testui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lyxh.sdnu.com.testui.Adapter.CommonlyUrlAdapter;
import lyxh.sdnu.com.testui.R;
import lyxh.sdnu.com.testui.Data.WebMap;

public class CommonlyUrlFragment extends Fragment{
    private RecyclerView recyclerView;
    private List<WebMap> list;
    private CommonlyUrlAdapter commonlyUrlAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_commonly_url,container,false);
        recyclerView=view.findViewById(R.id.fragment_commonly_url_rec);
        initData();
        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        commonlyUrlAdapter=new CommonlyUrlAdapter(getContext(),list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(commonlyUrlAdapter);
    }

    private void initData() {
        list=new ArrayList<>();
        list.add(new WebMap("山东师范大学官网","http://www.sdnu.edu.cn/","http://p5tgr5sc2.bkt.clouddn.com/%E5%AE%98%E7%BD%91.png"));
        list.add(new WebMap("山东师范大学教务处","http://www.bkjy.sdnu.edu.cn/","http://p5tgr5sc2.bkt.clouddn.com/%E7%BE%8E%E6%99%AF1.png"));
        list.add(new WebMap("山东师范大学招生网","http://www.zsb.sdnu.edu.cn/","http://p5tgr5sc2.bkt.clouddn.com/sdnu_zhaoshengwang.png"));
        list.add(new WebMap("山东师范大学研究生院","http://www.yjs.sdnu.edu.cn/","http://p5tgr5sc2.bkt.clouddn.com/%E7%BE%8E%E6%99%AF2.png"));
        list.add(new WebMap("山东师范大学图书馆","http://www.elib.sdnu.edu.cn/","http://p5tgr5sc2.bkt.clouddn.com/sdnu_yanjiusheng.png"));
        list.add(new WebMap("山东师范大学贴吧","https://www.baidu.com/link?url=CxjSpQmGCSyzuMfQOstTh07JZXRB00EtZKDb88qGza2KlfUMBGw3d1Pj03f43ATnebE9YAcRlGVBG2OxbimD1U49t9bHB0cEiy1GW3Fy3SwMvjHQJMZQVe3YRCAA6RlJ&wd=&eqid=86a391290000e226000000035ba5adbf","http://p5tgr5sc2.bkt.clouddn.com/baidutieba.jpg"));
        list.add(new WebMap("山东师范大学财务处","http://www.caiwuchu.sdnu.edu.cn/","http://p5tgr5sc2.bkt.clouddn.com/sdnu_caiwu.png"));
        list.add(new WebMap("智慧山师官网","http://i.sdnu.edu.cn/home/","http://p5tgr5sc2.bkt.clouddn.com/sdnu_smart.png"));
    }

}
