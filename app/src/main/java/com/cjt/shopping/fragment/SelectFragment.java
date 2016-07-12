package com.cjt.shopping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cjt.shopping.R;
import com.cjt.shopping.adapter.GoodsAdapter;
import com.cjt.shopping.adapter.ShopCartAdapter;
import com.cjt.shopping.adapter.TypeAdapter;
import com.cjt.shopping.bean.ShopCartList;
import com.cjt.shopping.bean.ShopInfo;
import com.cjt.shopping.fragment.view.SelectView;
import com.cjt.shopping.presenter.SelectPresenter;
import com.cjt.shopping.ui.acitivity.GoodDetailActivity;
import com.cjt.shopping.ui.acitivity.SettlementActivity;
import com.cjt.shopping.util.Config;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Url;


public class SelectFragment extends BaseFragment<SelectFragment,SelectPresenter> implements SelectView,View.OnClickListener{
    private TextView tv_totalprice,tv_order;

    private RecyclerView rv_type,rv_goods,rv_shopcart;
    private TypeAdapter mTypeAdapter;
    private GoodsAdapter mGoodsAdapter;
    private ShopCartAdapter mShopCartAdapter;
    private List<ShopInfo.CategoriesBean> mDatasCategoriesBean;
    private List<ShopInfo.GoodsBean> mDatasGoodsBean;

    private List<ShopCartList.ShopCartBean.ShopCartItemsBean> mShopCartItemBean;

    private PopupWindow mPopupWindow;
    private ImageView iv_shopcart;

    public static SelectFragment newInstance(String vendorID,String storeID) {
        SelectFragment fragment = new SelectFragment();
        Bundle args = new Bundle();
        args.putString("vendorID", vendorID);
        args.putString("storeID", storeID);
        fragment.setArguments(args);
        return fragment;
    }

    public SelectFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select, container, false);

        tv_totalprice= (TextView) view.findViewById(R.id.tv_totalprice);
        tv_order= (TextView) view.findViewById(R.id.tv_order);
        tv_order.setOnClickListener(this);

        rv_type= (RecyclerView) view.findViewById(R.id.rv_type);
        rv_goods= (RecyclerView) view.findViewById(R.id.rv_goods);
        iv_shopcart= (ImageView) view.findViewById(R.id.iv_shopcart);
        iv_shopcart.setOnClickListener(this);
        initDatas();
        mTypeAdapter=new TypeAdapter(mDatasCategoriesBean, getActivity(), new TypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mTypeAdapter.updatasItemState(position);
                mGoodsAdapter.updataType(mDatasCategoriesBean.get(position).getName());
            }
        });
        rv_type.setAdapter(mTypeAdapter);
        rv_type.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        mGoodsAdapter=new GoodsAdapter(mShopCartItemBean,mDatasGoodsBean, getActivity(), new GoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(SelectFragment.this.getActivity(), GoodDetailActivity.class);
                intent.putExtra("goodName",mDatasGoodsBean.get(position).getName());
                intent.putExtra("goodSellCount",mDatasGoodsBean.get(position).getSellCount()+"");
                intent.putExtra("goodPrice",mDatasGoodsBean.get(position).getBasePrice()+"");
                intent.putExtra("goodDetail",mDatasGoodsBean.get(position).getDetails());
                intent.putExtra("goodImageUrl",mDatasGoodsBean.get(position).getGoodImage().getShopFile().getUrl());
                intent.putExtra("vendorId",mDatasGoodsBean.get(position).getStore().getId());
                startActivity(intent);
            }

            @Override
            public void addGoodClick(int position) {
                if (!Config.getUserId(SelectFragment.this.getContext()).equals("")) {
                    getPresenter().addGoodToShopCart(mDatasGoodsBean.get(position).getId() + "", Config.getUserId(SelectFragment.this.getContext()));
                    Log.i("CJT",mDatasGoodsBean.get(position).getId() +"==="+Config.getUserId(SelectFragment.this.getContext()));
                }else{
                    Toast.makeText(SelectFragment.this.getActivity(),"未登录",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void reduceGoodeClic(int position, String number) {
                if (!Config.getUserId(SelectFragment.this.getContext()).equals("")) {
                    if (!number.equals("0")) {
                        getPresenter().reduceGoodToShopCart((Integer.parseInt(number)-1)+"",mDatasGoodsBean.get(position).getId() + "",Config.getUserId(SelectFragment.this.getContext()));
                        Log.i("CJT", mDatasGoodsBean.get(position).getId() + "===" + Config.getUserId(SelectFragment.this.getContext()));
                    }else{
                        Toast.makeText(SelectFragment.this.getActivity(),"数量为0",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SelectFragment.this.getActivity(),"未登录",Toast.LENGTH_SHORT).show();
                }
            }

        });
        mShopCartAdapter=new ShopCartAdapter(mShopCartItemBean,getActivity());

        rv_goods.setAdapter(mGoodsAdapter);
        rv_goods.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        this.mPresenter=creatPresenter();
        initPopupWindow();
        return view;
    }
    private void initPopupWindow() {
        LayoutInflater mLayoutInflater = (LayoutInflater) this.getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View photo_popunwindwow = mLayoutInflater.inflate(R.layout.pupwindow_shopcart, null);
        mPopupWindow = new PopupWindow(photo_popunwindwow, Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(getActivity().getDrawable(R.drawable.click_selector));
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        //点击其他地方消失
        rv_shopcart= (RecyclerView) photo_popunwindwow.findViewById(R.id.rv_showcart);
        rv_shopcart.setAdapter(mShopCartAdapter);
        rv_shopcart.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("CJT","VendorID="+getArguments().getString("vendorID"));
        getPresenter().getSelect(getArguments().getString("vendorID"));
//        getPresenter().getShopCart("1010",getArguments().getString("vendorID"));
        if (!Config.getUserId(getContext()).equals("")) {
            getPresenter().getShopCart(Config.getUserId(getContext()), getArguments().getString("storeID"));
        }else{
            iv_shopcart.setClickable(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().getShopCart(Config.getUserId(getContext()), getArguments().getString("storeID"));
    }

    @Override
    protected SelectPresenter creatPresenter() {
        return new SelectPresenter(this);
    }

    private void initDatas() {
        mDatasCategoriesBean=new ArrayList<ShopInfo.CategoriesBean>();
        mDatasGoodsBean=new ArrayList<ShopInfo.GoodsBean>();
        mShopCartItemBean=new ArrayList<ShopCartList.ShopCartBean.ShopCartItemsBean>();
    }


    @Override
    public void updata(List<ShopInfo.CategoriesBean> categoriesList, List<ShopInfo.GoodsBean> goodsList) {
        this.mDatasCategoriesBean.clear();
        this.mDatasCategoriesBean=categoriesList;
        this.mDatasGoodsBean.clear();
        this.mDatasGoodsBean=goodsList;
        mTypeAdapter.updatas(categoriesList);
        mGoodsAdapter.updatas(goodsList);
    }

    @Override
    public void updataShopCart(List<ShopCartList.ShopCartBean.ShopCartItemsBean> shopCartItems) {
        this.mShopCartItemBean.clear();
        this.mShopCartItemBean=shopCartItems;
        if (shopCartItems.size()>0){
            iv_shopcart.setClickable(true);
        }else{
            iv_shopcart.setClickable(false);
        }
        mShopCartAdapter.updatas(shopCartItems);
        mGoodsAdapter.updataShopCart(shopCartItems);
    }

    @Override
    public void addGoodSuccess() {
        Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_SHORT).show();
        getPresenter().getShopCart(Config.getUserId(getContext()), getArguments().getString("storeID"));
    }

    @Override
    public void addGoodFail() {
        Toast.makeText(getActivity(),"添加失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updataTotalPrice(double price) {
        tv_totalprice.setText("￥"+price);
        if (price>10){
            tv_order.setText("去结算");
            tv_order.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else{
            tv_order.setText("￥10起送");
            tv_order.setBackgroundColor(0xff888888);
        }
    }

    @Override
    public void goToSettlement(int orderId) {
        Intent intent=new Intent(getActivity(), SettlementActivity.class);
        intent.putExtra("orderId",orderId+"");
        intent.putExtra("storeId",getArguments().getString("storeID"));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_shopcart:
                if (null != mPopupWindow && !mPopupWindow.isShowing()) {
                    mPopupWindow.showAtLocation(getView().findViewById(R.id.layout_main), Gravity.RIGHT | Gravity.BOTTOM, 55, 110);
                }
                break;
            case R.id.tv_order:
                String id="";
                for (int i=0;i<mShopCartItemBean.size();i++){
                    id+=mShopCartItemBean.get(i).getId()+"&";
                }
                id=id.substring(0,id.length()-1);
                getPresenter().addToOrder(id,Config.getUserId(getContext()));
                Log.i("CJT",id);
                break;
        }
    }
}
