package com.cjt.shopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjt.shopping.bean.ShopList;
import com.cjt.shopping.presenter.BasePresenter;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/7/1
 * 邮箱: 445263848@qq.com.
 */
public abstract class BaseFragment<V,T extends BasePresenter<V>> extends Fragment {
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = creatPresenter();
        mPresenter.attachView((V) this);
        mPresenter.onViewCreate();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("BaseActivity", "BaseActivity onStart");
        mPresenter.onViewStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("BaseActivity", "BaseActivity onResume");
        mPresenter.onViewResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("BaseActivity", "BaseActivity onStop");
        mPresenter.onViewStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("BaseActivity", "BaseActivity onDestroy");
        mPresenter.onViewDestroy();
    }

    //获取Presenter
    protected T getPresenter() {
        return mPresenter;
    }
    //创建Presenter
    protected abstract T creatPresenter();

}
