package com.cjt.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
    private OnItemClickListener listener;
    private int clickPosition=-1;

    public TypeAdapter(List<ShopInfo.CategoriesBean> mDatas, Context mContext,OnItemClickListener listener) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.listener=listener;
    }

    public void updatasItemState(int position) {
        this.clickPosition=position;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    @Override
    public TypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeHolder(LayoutInflater.from(mContext).inflate(R.layout.type_item,parent,false),listener);
    }

    @Override
    public void onBindViewHolder(TypeHolder holder, int position) {
        if (position==this.clickPosition){
            holder.layout_all.setBackgroundColor(0xFFFFFFFF);
            holder.view_select.setVisibility(View.VISIBLE);
        }else {
            holder.layout_all.setBackgroundColor(0xFFEEEEEE);
            holder.view_select.setVisibility(View.INVISIBLE);
        }
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
class TypeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TypeAdapter.OnItemClickListener listener;
    TextView tv_typename;
    RelativeLayout layout_all;
    View view_select;
    public TypeHolder(View itemView,TypeAdapter.OnItemClickListener listener) {
        super(itemView);
        tv_typename= (TextView) itemView.findViewById(R.id.tv_typename);
        layout_all= (RelativeLayout) itemView.findViewById(R.id.layout_all);
        view_select= (View) itemView.findViewById(R.id.view_select);
        itemView.setOnClickListener(this);
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v,getPosition());
    }
}
