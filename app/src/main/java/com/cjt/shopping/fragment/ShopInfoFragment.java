package com.cjt.shopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjt.shopping.R;
import com.cjt.shopping.fragment.view.ShopInfoView;
import com.cjt.shopping.presenter.BasePresenter;
import com.cjt.shopping.presenter.ShopInfoPresenter;

public class ShopInfoFragment extends BaseFragment<ShopInfoFragment,ShopInfoPresenter> implements ShopInfoView{

    public static ShopInfoFragment newInstance() {
        ShopInfoFragment fragment = new ShopInfoFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", "text");
        fragment.setArguments(args);
        return fragment;
    }

    public ShopInfoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shoinfo, container, false);
        return view;
    }

    @Override
    protected ShopInfoPresenter creatPresenter() {
        return new ShopInfoPresenter(this);
    }

    private void initDatas() {
    }


}
