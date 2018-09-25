package lyxh.sdnu.com.testui;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    private static BaseApplication app;
    private static Context appContext;
    private ProfileList profileList;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        appContext=getApplicationContext();
        QnUploadHelper.init("9x1vj4hGtOF-88pZa4y7CeuKxVveDNA2e-a_V8cr",
                "Wji4atjijqtOlesrZaXFIlUyB_dfHUvQThy3I34X",
                "http://p5tgr5sc2.bkt.clouddn.com/",
                "bear");

    }
    public static Context getContext() {
        return appContext;
    }

    public static BaseApplication getApplication() {
        return app;
    }



    public ProfileList getProfileList() {
        return profileList;
    }

    public void setProfileList(ProfileList profileList) {
        this.profileList = profileList;
    }

}
