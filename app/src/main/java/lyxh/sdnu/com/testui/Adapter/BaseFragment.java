package lyxh.sdnu.com.testui.Adapter;

import android.support.v4.app.Fragment;
import lyxh.sdnu.com.testui.HandleBackInterface;

public class BaseFragment extends Fragment implements HandleBackInterface {

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
