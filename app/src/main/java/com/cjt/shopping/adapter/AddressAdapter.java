package com.cjt.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cjt.shopping.AddressEditActivity;
import com.cjt.shopping.R;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/6/17
 * 邮箱: 445263848@qq.com.
 */
public class AddressAdapter extends RecyclerView.Adapter<AddressHolder>{
    private List<String> mDatas;
    private Context mContext;
    boolean isShow=false;

    public AddressAdapter(List<String> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public AddressHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressHolder(LayoutInflater.from(mContext).inflate(R.layout.address_item,parent,false));
    }

    @Override
    public void onBindViewHolder(AddressHolder holder, int position) {
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, AddressEditActivity.class));
            }
        });
        if (isShow) {
            holder.btn_delete.setVisibility(View.VISIBLE);
            holder.btn_edit.setVisibility(View.VISIBLE);
        }else{
            holder.btn_delete.setVisibility(View.GONE);
            holder.btn_edit.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void showEditBtn() {
        isShow=!isShow;
        notifyDataSetChanged();
    }
}
class AddressHolder extends RecyclerView.ViewHolder{
    Button btn_delete;
    Button btn_edit;
    public AddressHolder(View itemView) {
        super(itemView);
        btn_delete= (Button) itemView.findViewById(R.id.btn_delete);
        btn_edit= (Button) itemView.findViewById(R.id.btn_edit);
    }

}
