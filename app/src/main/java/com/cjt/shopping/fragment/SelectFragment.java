package com.cjt.shopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjt.shopping.R;
import com.cjt.shopping.adapter.GoodsAdapter;
import com.cjt.shopping.adapter.TypeAdapter;
import com.cjt.shopping.bean.ShopInfo;
import com.cjt.shopping.fragment.view.SelectView;
import com.cjt.shopping.presenter.SelectPresenter;

import java.util.ArrayList;
import java.util.List;


public class SelectFragment extends BaseFragment<SelectFragment,SelectPresenter> implements SelectView{
    private RecyclerView rv_type,rv_goods;
    private TypeAdapter mTypeAdapter;
    private GoodsAdapter mGoodsAdapter;
    private List<ShopInfo.CategoriesBean> mDatasCategoriesBean;
    private List<ShopInfo.GoodsBean> mDatasGoodsBean;
    public static SelectFragment newInstance(String vendorID) {
        SelectFragment fragment = new SelectFragment();
        Bundle args = new Bundle();
        args.putString("vendorID", vendorID);
        fragment.setArguments(args);
        return fragment;
    }

    public SelectFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select, container, false);
        rv_type= (RecyclerView) view.findViewById(R.id.rv_type);
        rv_goods= (RecyclerView) view.findViewById(R.id.rv_goods);
        initDatas();
        mTypeAdapter=new TypeAdapter(mDatasCategoriesBean,getActivity());
        rv_type.setAdapter(mTypeAdapter);
        rv_type.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        mGoodsAdapter=new GoodsAdapter(mDatasGoodsBean,getActivity());
        rv_goods.setAdapter(mGoodsAdapter);
        rv_goods.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        this.mPresenter=creatPresenter();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("CJT","VendorID="+getArguments().getString("vendorID"));
        getPresenter().getSelect(getArguments().getString("vendorID"));
    }

    @Override
    protected SelectPresenter creatPresenter() {
        return new SelectPresenter(this);
    }

    private void initDatas() {
        mDatasCategoriesBean=new ArrayList<ShopInfo.CategoriesBean>();
        mDatasGoodsBean=new ArrayList<ShopInfo.GoodsBean>();
//        for (int i=0;i<10;i++){
//            mDatas.add("123"+i);
//        }
    }


    @Override
    public void updata(List<ShopInfo.CategoriesBean> categoriesList, List<ShopInfo.GoodsBean> goodsList) {
        mTypeAdapter.updatas(categoriesList);
        mGoodsAdapter.updatas(goodsList);
    }
}
