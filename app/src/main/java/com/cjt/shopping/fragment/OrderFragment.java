package com.cjt.shopping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.cjt.shopping.R;
import com.cjt.shopping.adapter.OrderAdapter;
import com.cjt.shopping.bean.OrderList;
import com.cjt.shopping.fragment.view.OrderView;
import com.cjt.shopping.presenter.OrderPresenter;
import com.cjt.shopping.ui.acitivity.LoginActivity;
import com.cjt.shopping.util.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/6/12
 * 邮箱: 445263848@qq.com.
 */
public class OrderFragment extends BaseFragment<OrderFragment, OrderPresenter> implements OrderView, View.OnClickListener {
    private RecyclerView mRecyclerView;
    private RelativeLayout layout_login;
    private Button btn_loginorregister;
    private OrderAdapter mOderAdapter;
    private List<OrderList.OrdersBean> mDatas;


    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", "text");
        fragment.setArguments(args);
        return fragment;
    }

    public OrderFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        initDatas();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_orderlist);
        layout_login = (RelativeLayout) view.findViewById(R.id.layout_login);
        btn_loginorregister = (Button) view.findViewById(R.id.btn_loginorregister);
        btn_loginorregister.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mOderAdapter = new OrderAdapter(mDatas, getActivity());
        mRecyclerView.setAdapter(mOderAdapter);
        this.mPresenter = creatPresenter();
        return view;
    }

    @Override
    protected OrderPresenter creatPresenter() {
        return new OrderPresenter(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("CJT", "这里开始获取订单信息");
        if ("".equals(Config.getUserId(this.getContext()))) {
            mRecyclerView.setVisibility(View.INVISIBLE);
            layout_login.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            layout_login.setVisibility(View.INVISIBLE);
            getPresenter().getOrderList(Config.getUserId(this.getContext()));
        }
    }

    private void initDatas() {
        mDatas = new ArrayList<OrderList.OrdersBean>();
    }


    @Override
    public void updatas(List<OrderList.OrdersBean> ordersBeanlist) {
        mOderAdapter.updatas(ordersBeanlist);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_loginorregister:
                startActivity(new Intent(this.getActivity(), LoginActivity.class));
                break;
        }
    }
}
