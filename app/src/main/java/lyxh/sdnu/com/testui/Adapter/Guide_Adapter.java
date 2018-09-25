package lyxh.sdnu.com.testui.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class Guide_Adapter extends PagerAdapter {

    private Context context;
    private ArrayList<ImageView> arrayImages;

    public Guide_Adapter(ArrayList<ImageView> images, Context context){
        this.arrayImages = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = arrayImages.get(position);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}