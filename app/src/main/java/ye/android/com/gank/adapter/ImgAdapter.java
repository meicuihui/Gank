package ye.android.com.gank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import ye.android.com.gank.R;
import ye.android.com.gank.data.Results;

/**
 * Created by yb on 2017-11-21.
 * @author yb
 */

public class ImgAdapter extends BaseAdapter {
    private Context context;
    private List<Results> resultses;
    private LayoutInflater inflater;

    public ImgAdapter(Context context_,List<Results> resultses) {
        this.context = context_;
        this.resultses = resultses;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {

        return resultses.size();
    }

    @Override
    public Results getItem(int position) {

        return resultses.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImgHloder imgHloder;
        if (convertView == null) {
            imgHloder=new ImgHloder();
            convertView = inflater.inflate(R.layout.main_ls_item, parent, false);
            imgHloder.imageView=(ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(imgHloder);
        }else{
            imgHloder= (ImgHloder)convertView.getTag();
        }
        Glide.with(context)
                .load(resultses.get(position).getUrl())
                .into(imgHloder.imageView);
        return convertView;
    }
    class ImgHloder{
        ImageView imageView;
    }
}