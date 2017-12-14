package ye.android.com.gank.api;

/**
 * Created by yb on 2017-11-21.
 * @author yb
 */

public class Api {
    public static String getData(String type,String count,String page){
        
        return "http://gank.io/api/data/"+type+"/"+count+"/"+page;
    }
}
