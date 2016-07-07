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

import com.cjt.shopping.R;
import com.cjt.shopping.bean.AddressList;
import com.cjt.shopping.bean.ShopCartList;
import com.cjt.shopping.ui.acitivity.AddressEditActivity;
import com.cjt.shopping.ui.acitivity.AddressManageActivity;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/7/6
 * 邮箱: 445263848@qq.com.
 */
public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemHolder>{
    private List<ShopCartList.ShopCartBean.ShopCartItemsBean> mDatas;
    private Context mContext;
    public OrderItemAdapter(List<ShopCartList.ShopCartBean.ShopCartItemsBean> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public OrderItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderItemHolder(LayoutInflater.from(mContext).inflate(R.layout.orderitem_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final OrderItemHolder holder, int position) {
        holder.tv_goodsname.setText(mDatas.get(position).getGood().getName());
        holder.tv_goodnumber.setText("X"+mDatas.get(position).getCount());
        holder.tv_goodsprice.setText("X"+mDatas.get(position).getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void updata(List<ShopCartList.ShopCartBean.ShopCartItemsBean> orderitemlist) {
        this.mDatas.clear();
        this.mDatas=orderitemlist;
        notifyDataSetChanged();
    }
}

class OrderItemHolder extends RecyclerView.ViewHolder {

    TextView tv_goodsname;
    TextView tv_goodnumber;
    TextView tv_goodsprice;

    public OrderItemHolder(View itemView) {
        super(itemView);
        tv_goodsname= (TextView) itemView.findViewById(R.id.tv_goodsname);
        tv_goodnumber= (TextView) itemView.findViewById(R.id.tv_goodnumber);
        tv_goodsprice= (TextView) itemView.findViewById(R.id.tv_goodsprice);
    }

}
