package lyxh.sdnu.com.testui.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class ImgUtils {
    public static void load(Context context,Object url, ImageView targetView) {
        Glide.with(context)
                .load(url)
                .dontAnimate()
                .into(targetView);
    }

    public static void loadRound(Context context,Object url, ImageView targetView) {
        Glide.with(context)
                .load(url)
                .bitmapTransform(new CropCircleTransformation(context))
                .into(targetView);
    }

}
