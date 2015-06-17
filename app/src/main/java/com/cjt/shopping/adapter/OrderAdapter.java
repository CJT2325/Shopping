package com.cjt.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjt.shopping.R;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/6/12
 * 邮箱: 445263848@qq.com.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private Context mContext;
    private List datas;

    public OrderAdapter(List datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.order_item,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


}
class OrderViewHolder extends RecyclerView.ViewHolder {
    ImageView iv_shopcover;
    TextView tv_shopname;
    TextView tv_evaluate;
    TextView tv_price;
    TextView tv_ordertime;
    TextView tv_describe;

    public OrderViewHolder(View itemView) {
        super(itemView);
        iv_shopcover = (ImageView) itemView.findViewById(R.id.iv_shopcover);
        tv_shopname = (TextView) itemView.findViewById(R.id.tv_shopname);
        tv_evaluate = (TextView) itemView.findViewById(R.id.tv_evaluate);
        tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        tv_ordertime = (TextView) itemView.findViewById(R.id.tv_ordertime);
        tv_describe = (TextView) itemView.findViewById(R.id.tv_describe);
    }
}

