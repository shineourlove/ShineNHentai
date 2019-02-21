package shineourlove.shine.com.shinenhentai;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.orhanobut.hawk.Hawk;

/**
 * created by shineourlove on 2019/2/20
 * application
 */
public class ShineApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        Hawk.init(this).build();
    }
}
