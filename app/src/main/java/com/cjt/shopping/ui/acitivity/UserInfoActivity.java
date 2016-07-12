package com.cjt.shopping.ui.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cjt.shopping.R;
import com.cjt.shopping.util.Config;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private CircleImageView mCircleImageView;

    private RelativeLayout layout_usercover;
    private RelativeLayout layout_username;
    private RelativeLayout layout_pwd;
    private RelativeLayout layout_phone;

    private TextView tv_username;
    private TextView tv_phone;

    private TextView tv_logout;

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initPopupWindow();

        mCircleImageView= (CircleImageView) findViewById(R.id.iv_usercover);

        layout_usercover = (RelativeLayout) findViewById(R.id.layout_usercover);
        layout_username = (RelativeLayout) findViewById(R.id.layout_username);
        layout_pwd = (RelativeLayout) findViewById(R.id.layout_pwd);
        layout_phone = (RelativeLayout) findViewById(R.id.layout_phone);

        tv_logout= (TextView) findViewById(R.id.tv_logout);
        tv_logout.setOnClickListener(this);

        layout_usercover.setOnClickListener(this);
        layout_username.setOnClickListener(this);
        layout_pwd.setOnClickListener(this);
        layout_phone.setOnClickListener(this);

        tv_username= (TextView) findViewById(R.id.tv_username);
        tv_phone= (TextView) findViewById(R.id.tv_phone);
    }

    private void initPopupWindow() {
        LayoutInflater mLayoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View photo_popunwindwow = mLayoutInflater.inflate(R.layout.pupwindow_text, null);
        mPopupWindow = new PopupWindow(photo_popunwindwow, Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(getDrawable(R.drawable.click_selector));
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        photo_popunwindwow.findViewById(R.id.tv_photograph).setOnClickListener(this);
        photo_popunwindwow.findViewById(R.id.tv_cancel).setOnClickListener(this);
        photo_popunwindwow.findViewById(R.id.tv_photo).setOnClickListener(this);
        //点击其他地方消失
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK){//result is not correct
            Log.i("CJT","111"+requestCode);
            return;
        }else{
            Log.i("CJT","333"+requestCode);
            if(requestCode == 1 ){
                Log.i("CJT","222"+requestCode);
                //将保存在本地的图片取出并缩小后显示在界面上
                //Bitmap camorabitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/workupload.jpg");
                File file=new File(Environment.getExternalStorageDirectory()+"/workupload.jpg");
                Log.i("CJT",file.getAbsolutePath());
                Picasso.with(this).load(file).resize(100, 100).into(mCircleImageView);
                //mCircleImageView.setImageBitmap(camorabitmap);
            }else if (requestCode==2){
                Picasso.with(this).load(data.getData()).resize(100, 100).into(mCircleImageView);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Config.getUserId(this).equals("")){
            tv_username.setText("");
            tv_phone.setText("");
        }else{
            tv_username.setText(Config.getUserName(this));
            tv_phone.setText("已绑定手机号 "+Config.getPhone(this));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_usercover:
                if (null != mPopupWindow && !mPopupWindow.isShowing()) {
                    mPopupWindow.showAtLocation(findViewById(R.id.layout_main), Gravity.RIGHT | Gravity.BOTTOM, 0, 0);
                }
                break;
            case R.id.layout_username:
                break;
            case R.id.layout_pwd:
                break;
            case R.id.layout_phone:
                break;
            case R.id.tv_photograph:
                File file=new File(Environment.getExternalStorageDirectory()+"/workupload.jpg");
                if (file.exists()){
                    Log.i("CJT","删除"+file.getAbsolutePath());
                    file.delete();
                }
                Intent photograph = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"workupload.jpg"));
                photograph.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(photograph, 1);
                break;
            case R.id.tv_photo:
                Intent photointent = new Intent(Intent.ACTION_PICK);
                photointent.setType("image/*");
                startActivityForResult(photointent, 2);
                break;
            case R.id.tv_cancel:
                mPopupWindow.dismiss();
                break;
            case R.id.tv_logout:
                Config.saveUserName(this,"");
                Config.saveUserId(this,"");
                Config.savePhone(this,"");
                finish();
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


