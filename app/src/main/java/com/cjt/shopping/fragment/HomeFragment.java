package com.cjt.shopping.fragment;

import android.app.Fragment;
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
import com.cjt.shopping.ui.acitivity.ShopDetailActivity;
import com.cjt.shopping.adapter.ShopAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/6/12
 * 邮箱: 445263848@qq.com.
 */
public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ShopAdapter mShopAdapter;
    private List<String> mDatas;

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
                startActivity(new Intent(getActivity(), ShopDetailActivity.class));
                Log.i("CJT","Item click");
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mShopAdapter);
        return view;
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            mDatas.add("Stirng " + i);
        }
    }


}
