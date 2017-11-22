package ye.android.com.gank.data;

import java.io.Serializable;

/**
 * Created by yb on 2017-11-21.
 * @author yb
 */

public class Results implements Serializable {

    /**
     * _id : 5a123f78421aa90fe50c0221
     * createdAt : 2017-11-20T10:35:36.599Z
     * desc : Android 借助 Python 实现自动打包上传 fir
     * publishedAt : 2017-11-20T12:42:06.454Z
     * source : web
     * type : Android
     * url : http://mp.weixin.qq.com/s/BUht6BR0T0yAi6RrAVIwQA
     * used : true
     * who : null
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private Object who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Object getWho() {
        return who;
    }

    public void setWho(Object who) {
        this.who = who;
    }

    @Override
    public String toString() {
        return "Results{" +
                "_id='" + _id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who=" + who +
                '}';
    }
}
