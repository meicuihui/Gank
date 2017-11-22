package ye.android.com.gank.activty;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;

import ye.android.com.gank.R;
import ye.android.com.gank.utils.PictureUtil;
import ye.android.com.gank.utils.ToastUtil;


/**
 * Created by yb on 2017-11-22.
 * @author yb
 */

public class ImageActivty extends Activity implements View.OnClickListener,View.OnLongClickListener{
    ImageView imageView;
    private View inflate;
    Context context;
    private Dialog dialog;
    TextView choosePhoto,cancel;
    public Bitmap bitmap;
    String url;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        context=getApplication();
        imageView=(ImageView)findViewById(R.id.image);
        imageView.setOnClickListener(this);
        imageView.setOnLongClickListener(this);
        url=getIntent().getStringExtra("url");
        Glide.with(ImageActivty.this).load(url).into(imageView);

    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.image:
             finish();
             break;
         case R.id.cancel:
             dialog.dismiss();
             break;
         case R.id.choosePhoto:
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         bitmap = Glide.with(context)
                                 .load(url)
                                 .asBitmap()
                                 .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                 .get();
                         PictureUtil.saveImageToGallery(context,bitmap);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     } catch (ExecutionException e) {
                         e.printStackTrace();
                     }
                 }
             }).start();
             ToastUtil.toast(context,"图片以保存至"+ Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "yb/"+"文件夹");
             dialog.dismiss();
             break;
         default:
             break;

     }



    }

    @Override
    public boolean onLongClick(View v) {
        show(v);
        return true;
    }

    public void show(View view){
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null);
        //初始化控件
        choosePhoto = (TextView) inflate.findViewById(R.id.choosePhoto);
//        takePhoto = (TextView) inflate.findViewById(R.id.takePhoto);
        cancel = (TextView) inflate.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        choosePhoto.setOnClickListener(this);
//        takePhoto.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

}
