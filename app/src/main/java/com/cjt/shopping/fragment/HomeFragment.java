package com.cjt.shopping.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjt.shopping.R;
import com.cjt.shopping.bean.ShopList;
import com.cjt.shopping.fragment.view.HomeView;
import com.cjt.shopping.presenter.BasePresenter;
import com.cjt.shopping.presenter.HomePresenter;
import com.cjt.shopping.ui.acitivity.ShopDetailActivity;
import com.cjt.shopping.adapter.ShopAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/6/12
 * 邮箱: 445263848@qq.com.
 */
public class HomeFragment extends BaseFragment<HomeFragment,HomePresenter> implements HomeView{
    private RecyclerView mRecyclerView;
    private ShopAdapter mShopAdapter;
    private List<ShopList.VendorsBean> mDatas;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", "text");
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {

    }

    @Override
    protected HomePresenter creatPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initDatas();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_shoplist);
        mShopAdapter = new ShopAdapter(mDatas, getActivity(), new ShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), ShopDetailActivity.class);
                Log.i("CJT","获取商家ID"+mDatas.get(position).getId());
                intent.putExtra("vendorID",mDatas.get(position).getId()+"");
                startActivity(intent);
                Log.i("CJT","Item click");
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mShopAdapter);
        this.mPresenter=creatPresenter();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getPresenter()!=null) {
            getPresenter().getShopList();
        }else{
            Log.i("CJT","presenter is null");
        }
    }

    private void initDatas() {
        mDatas = new ArrayList<ShopList.VendorsBean>();
//        for (int i = 0; i < 10; i++) {
//            mDatas.add("Stirng " + i);
//        }
    }


    @Override
    public void updata(List<ShopList.VendorsBean> list) {
        mDatas.clear();
        mDatas=list;
        mShopAdapter.updata(list);
    }
}
