package com.cjt.shopping.ui.acitivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cjt.shopping.R;
import com.cjt.shopping.presenter.RegisterPresenter;
import com.cjt.shopping.ui.view.RegisterView;

public class RegisterActivity extends BaseActivity<RegisterActivity,RegisterPresenter> implements RegisterView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("用户注册");
    }

    @Override
    protected RegisterPresenter creatPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void RegisterSuccess() {

    }

    @Override
    public void RegisterFail() {

    }
}
