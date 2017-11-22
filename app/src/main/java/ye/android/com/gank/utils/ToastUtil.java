package ye.android.com.gank.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yb on 2017-11-22.
 * @author yb
 */

public class ToastUtil {
    public static void  toast(Context context, String info){
        Toast.makeText(context,info, Toast.LENGTH_SHORT).show();
    }
}
