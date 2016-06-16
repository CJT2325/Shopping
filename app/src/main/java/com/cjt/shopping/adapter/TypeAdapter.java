package com.cjt.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjt.shopping.R;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/6/16
 * 邮箱: 445263848@qq.com.
 */
public class TypeAdapter extends RecyclerView.Adapter<TypeHolder> {
    private List<String> mDatas;
    private Context mContext;

    public TypeAdapter(List<String> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public TypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeHolder(LayoutInflater.from(mContext).inflate(R.layout.type_item,parent,false));
    }

    @Override
    public void onBindViewHolder(TypeHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
class TypeHolder extends RecyclerView.ViewHolder{

    public TypeHolder(View itemView) {
        super(itemView);
    }
}
