package com.cjt.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjt.shopping.R;
import com.cjt.shopping.bean.ShopCartList;
import com.cjt.shopping.bean.ShopInfo;
import com.cjt.shopping.model.server.ServerAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/6/16
 * 邮箱: 445263848@qq.com.
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsHolder>{
    private List<ShopInfo.GoodsBean> mDatas;
    private List<ShopInfo.GoodsBean> mSelectGoodsBean;
    private List<ShopCartList.ShopCartBean.ShopCartItemsBean> mCartDatas;

    private Context mContext;
    private OnItemClickListener listener;
    private String type="";

    public GoodsAdapter(List<ShopCartList.ShopCartBean.ShopCartItemsBean> mCartDatas,List<ShopInfo.GoodsBean> mDatas, Context mContext,OnItemClickListener listener) {
        this.mCartDatas=mCartDatas;
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.listener=listener;
        mSelectGoodsBean= mDatas;
    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void addGoodClick(int position);
        public void reduceGoodeClic(int position,String number);
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsHolder(LayoutInflater.from(mContext).inflate(R.layout.goods_item,parent,false),listener) ;
    }

    @Override
    public void onBindViewHolder(GoodsHolder holder, int position) {

        for (int i=0;i<mCartDatas.size();i++){
            if (mDatas.get(position).getName().equals(mCartDatas.get(i).getGood().getName())){
                holder.btn_reduce.setVisibility(View.VISIBLE);
                holder.tv_number.setVisibility(View.VISIBLE);
                holder.tv_number.setText(""+mCartDatas.get(i).getCount());
            }else{

            }
        }
        Log.i("CJT", mDatas.get(position).getSellCount() + " ====");
        holder.tv_goodsname.setText(mDatas.get(position).getName());
        holder.tv_price.setText("￥" + mDatas.get(position).getBasePrice());
        holder.tv_sale.setText("月售" + mDatas.get(position).getSellCount());
        Picasso.with(mContext).load(ServerAPI.baseUrl + mDatas.get(position).getGoodImage().getShopFile().getUrl()).resize(100, 100).into(holder.iv_goodscover);

        if (this.type.equals(mDatas.get(position).getCategory().getName())||this.type.equals("")) {
            holder.layout_all.setVisibility(View.VISIBLE);
            holder.tv_goodsname.setVisibility(View.VISIBLE);
            holder.tv_sale.setVisibility(View.VISIBLE);
            holder.tv_price.setVisibility(View.VISIBLE);
            holder.tv_number.setVisibility(View.VISIBLE);
            holder.iv_goodscover.setVisibility(View.VISIBLE);
            holder.btn_add.setVisibility(View.VISIBLE);
            holder.btn_reduce.setVisibility(View.VISIBLE);
        }else {
            holder.tv_goodsname.setVisibility(View.GONE);
            holder.tv_sale.setVisibility(View.GONE);
            holder.tv_price.setVisibility(View.GONE);
            holder.tv_number.setVisibility(View.GONE);
            holder.iv_goodscover.setVisibility(View.GONE);
            holder.btn_add.setVisibility(View.GONE);
            holder.btn_reduce.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void updatas(List<ShopInfo.GoodsBean> goodsList) {
        this.mDatas.clear();
        this.mDatas=goodsList;
        notifyDataSetChanged();
    }

    public void updataType(String type) {
        this.type=type;
        notifyDataSetChanged();
    }

    public void updataShopCart(List<ShopCartList.ShopCartBean.ShopCartItemsBean> mCartDatas){
        this.mCartDatas.clear();
        this.mCartDatas=mCartDatas;
        notifyDataSetChanged();
    }
}
class GoodsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    RelativeLayout layout_all;
    TextView tv_goodsname;
    TextView tv_sale;
    TextView tv_price;
    TextView tv_number;
    ImageView iv_goodscover;

    Button btn_add;
    Button btn_reduce;


    GoodsAdapter.OnItemClickListener listener;
    public GoodsHolder(View itemView,GoodsAdapter.OnItemClickListener listener) {
        super(itemView);
        layout_all= (RelativeLayout) itemView.findViewById(R.id.layout_all);
        tv_goodsname= (TextView) itemView.findViewById(R.id.tv_goodsname);
        tv_sale= (TextView) itemView.findViewById(R.id.tv_sale);
        tv_price= (TextView) itemView.findViewById(R.id.tv_price);
        tv_number= (TextView) itemView.findViewById(R.id.tv_number);
        iv_goodscover= (ImageView) itemView.findViewById(R.id.iv_goodscover);

        btn_add= (Button) itemView.findViewById(R.id.btn_add);
        btn_reduce= (Button) itemView.findViewById(R.id.btn_reduce);
        btn_add.setOnClickListener(this);
        btn_reduce.setOnClickListener(this);
        this.listener=listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                Log.i("CJT","ADD Click");
                listener.addGoodClick(getPosition());
                break;
            case R.id.btn_reduce:
                Log.i("CJT","btn_reduce Click");
                listener.reduceGoodeClic(getPosition(),tv_number.getText().toString());
                break;
            default:
                listener.onItemClick(v,getPosition());
                break;
        }

    }
}