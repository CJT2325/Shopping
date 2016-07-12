package com.cjt.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cjt.shopping.R;
import com.cjt.shopping.bean.ShopCartList;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/7/5
 * 邮箱: 445263848@qq.com.
 */
public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartHolder> {
    private List<ShopCartList.ShopCartBean.ShopCartItemsBean> mDatas;
    private Context mContext;

    public ShopCartAdapter(List<ShopCartList.ShopCartBean.ShopCartItemsBean> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public ShopCartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopCartHolder(LayoutInflater.from(mContext).inflate(R.layout.shopcart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ShopCartHolder holder, int position) {
        holder.tv_goodsname.setText(mDatas.get(position).getGood().getName());
        holder.tv_allprice.setText("￥"+mDatas.get(position).getTotalPrice());
        holder.tv_number.setText(mDatas.get(position).getCount()+"");
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void updatas(List<ShopCartList.ShopCartBean.ShopCartItemsBean> shopCartItems) {
        this.mDatas.clear();
        this.mDatas=shopCartItems;
        notifyDataSetChanged();
    }
}

class ShopCartHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView tv_goodsname;
    TextView tv_allprice;
    TextView tv_number;
    public ShopCartHolder(View itemView) {
        super(itemView);
        tv_goodsname= (TextView) itemView.findViewById(R.id.tv_goodsname);
        tv_allprice= (TextView) itemView.findViewById(R.id.tv_allprice);
        tv_number= (TextView) itemView.findViewById(R.id.tv_number);
    }

    @Override
    public void onClick(View v) {

    }
}
