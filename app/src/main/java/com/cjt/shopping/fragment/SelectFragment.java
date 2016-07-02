package com.cjt.shopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjt.shopping.R;
import com.cjt.shopping.adapter.GoodsAdapter;
import com.cjt.shopping.adapter.TypeAdapter;

import java.util.ArrayList;
import java.util.List;


public class SelectFragment extends Fragment {
    private RecyclerView rv_type,rv_goods;
    private TypeAdapter mTypeAdapter;
    private GoodsAdapter mGoodsAdapter;
    private List<String> mDatas;
    public static SelectFragment newInstance() {
        SelectFragment fragment = new SelectFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", "text");
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
        mTypeAdapter=new TypeAdapter(mDatas,getActivity());
        rv_type.setAdapter(mTypeAdapter);
        rv_type.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        mGoodsAdapter=new GoodsAdapter(mDatas,getActivity());
        rv_goods.setAdapter(mGoodsAdapter);
        rv_goods.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        return view;
    }

    private void initDatas() {
        mDatas=new ArrayList<String>();
        for (int i=0;i<10;i++){
            mDatas.add("123"+i);
        }
    }


}
