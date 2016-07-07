package com.cjt.shopping.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjt.shopping.bean.AddressList;
import com.cjt.shopping.ui.acitivity.AddressEditActivity;
import com.cjt.shopping.ui.acitivity.AddressManageActivity;
import com.cjt.shopping.R;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/6/17
 * 邮箱: 445263848@qq.com.
 */
public class AddressAdapter extends RecyclerView.Adapter<AddressHolder> implements AddressManageActivity.onMoveAndSwipedListener {
    private int flagPositon = -1;
    private List<AddressList.UserBean.MyAddressesBean> mDatas;
    private Context mContext;
    boolean isShow = false;
    private OnItemClickListener listener;
    public AddressAdapter(List<AddressList.UserBean.MyAddressesBean> mDatas, Context mContext,OnItemClickListener listener) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.listener=listener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    @Override
    public AddressHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressHolder(LayoutInflater.from(mContext).inflate(R.layout.address_item, parent, false),listener);
    }

    @Override
    public void onBindViewHolder(final AddressHolder holder, int position) {
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, AddressEditActivity.class));

            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float curTranslationX = holder.layout_content.getTranslationX();
                ObjectAnimator animator = ObjectAnimator.ofFloat(holder.layout_content, "translationX", curTranslationX, -holder.btn_suredelete.getWidth());
                animator.setDuration(500);
                animator.start();
                flagPositon=holder.getPosition();
                Log.i("CJT",flagPositon+" ");
            }
        });
        if (isShow) {
            holder.btn_delete.setVisibility(View.VISIBLE);
            holder.btn_edit.setVisibility(View.VISIBLE);
        } else {
            holder.btn_delete.setVisibility(View.GONE);
            holder.btn_edit.setVisibility(View.GONE);
        }

        holder.tv_name.setText(mDatas.get(position).getConsignee());
        holder.tv_phone.setText(mDatas.get(position).getPhone());
        holder.tv_address.setText(mDatas.get(position).getPath());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void showEditBtn() {
        isShow = !isShow;
        notifyDataSetChanged();
    }

    @Override
    public void onItemDismiss(int position) {
        //删除mItems数据
        mDatas.remove(position);
        //删除RecyclerView列表对应item
        notifyItemRemoved(position);
    }

    public void updata(List<AddressList.UserBean.MyAddressesBean> myAddresses) {
        this.mDatas.clear();
        this.mDatas=myAddresses;
        notifyDataSetChanged();
    }
}

class AddressHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private AddressAdapter.OnItemClickListener listener;

    Button btn_delete;
    Button btn_suredelete;
    Button btn_edit;
    RelativeLayout layout_content;

    TextView tv_name;
    TextView tv_phone;
    TextView tv_address;

    public AddressHolder(View itemView,AddressAdapter.OnItemClickListener listener) {
        super(itemView);

        this.listener=listener;

        btn_delete = (Button) itemView.findViewById(R.id.btn_delete);
        btn_suredelete = (Button) itemView.findViewById(R.id.btn_suredelete);
        btn_edit = (Button) itemView.findViewById(R.id.btn_edit);
        layout_content = (RelativeLayout) itemView.findViewById(R.id.layout_content);


        tv_name= (TextView) itemView.findViewById(R.id.tv_name);
        tv_phone= (TextView) itemView.findViewById(R.id.tv_phone);
        tv_address= (TextView) itemView.findViewById(R.id.tv_address);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v,getPosition());
    }
}
