package com.cjt.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjt.shopping.R;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/6/17
 * 邮箱: 445263848@qq.com.
 */
public class EvaluateAdapter extends RecyclerView.Adapter{
    private List<String> mDatas;
    private Context mContext;

    public EvaluateAdapter(List<String> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EvaluateHolder(LayoutInflater.from(mContext).inflate(R.layout.evaluate_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
class EvaluateHolder extends RecyclerView.ViewHolder {
    public EvaluateHolder(View itemView) {
        super(itemView);
    }
}
