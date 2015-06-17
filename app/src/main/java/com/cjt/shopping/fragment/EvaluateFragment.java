package com.cjt.shopping.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjt.shopping.R;
import com.cjt.shopping.adapter.EvaluateAdapter;

import java.util.ArrayList;
import java.util.List;


public class EvaluateFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private EvaluateAdapter mEvaluateAdapter;
    private List<String> mDatas;

    public static EvaluateFragment newInstance() {
        EvaluateFragment fragment = new EvaluateFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", "text");
        fragment.setArguments(args);
        return fragment;
    }

    public EvaluateFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluate, container, false);
        initDatas();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_evaluatelist);
        mEvaluateAdapter = new EvaluateAdapter(mDatas, getActivity());
        mRecyclerView.setAdapter(mEvaluateAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            mDatas.add("Stirng " + i);
        }
    }

}
