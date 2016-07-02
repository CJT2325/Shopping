package com.cjt.shopping.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjt.shopping.presenter.BasePresenter;

/**
 * 作者: 陈嘉桐 on 2016/7/1
 * 邮箱: 445263848@qq.com.
 */
public abstract class BaseFragment<V,T extends BasePresenter<V>> extends Fragment{
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
    }

    //获取Presenter
    protected T getPresenter() {
        return mPresenter;
    }
    //创建Presenter
    protected abstract T creatPresenter();
}
