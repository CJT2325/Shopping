package com.cjt.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cjt.shopping.R;
import com.cjt.shopping.bean.ShopInfo;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/6/16
 * 邮箱: 445263848@qq.com.
 */
public class TypeAdapter extends RecyclerView.Adapter<TypeHolder> {
    private List<ShopInfo.CategoriesBean> mDatas;
    private Context mContext;

    public TypeAdapter(List<ShopInfo.CategoriesBean> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public TypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeHolder(LayoutInflater.from(mContext).inflate(R.layout.type_item,parent,false));
    }

    @Override
    public void onBindViewHolder(TypeHolder holder, int position) {
        holder.tv_typename.setText(mDatas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void updatas(List<ShopInfo.CategoriesBean> categoriesList) {
        this.mDatas.clear();
        this.mDatas=categoriesList;
        notifyDataSetChanged();
    }
}
class TypeHolder extends RecyclerView.ViewHolder{
    TextView tv_typename;
    public TypeHolder(View itemView) {
        super(itemView);
        tv_typename= (TextView) itemView.findViewById(R.id.tv_typename);
    }
}
