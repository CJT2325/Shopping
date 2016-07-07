package com.cjt.shopping.ui.acitivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cjt.shopping.R;
import com.cjt.shopping.adapter.OrderItemAdapter;
import com.cjt.shopping.bean.ShopCartList;
import com.cjt.shopping.presenter.BasePresenter;
import com.cjt.shopping.presenter.SettlementPresenter;
import com.cjt.shopping.ui.view.AddressManageView;
import com.cjt.shopping.ui.view.SettlementView;
import com.cjt.shopping.util.Config;

import java.util.ArrayList;
import java.util.List;

public class SettlementActivity extends BaseActivity<SettlementActivity, SettlementPresenter> implements View.OnClickListener, SettlementView {
    private String orderId = "";
    private String storeId = "";
    TextView tv_username;
    TextView tv_phone;
    TextView tv_address;
    TextView tv_shopname;
    TextView tv_totalprice;
    TextView tv_order;

    RelativeLayout layout_address;
    List<ShopCartList.ShopCartBean.ShopCartItemsBean> mData;
    RecyclerView rc_orderitems;
    OrderItemAdapter mOrderItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("确认订单");

        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_shopname = (TextView) findViewById(R.id.tv_shopname);
        tv_totalprice = (TextView) findViewById(R.id.tv_totalprice);
        tv_order = (TextView) findViewById(R.id.tv_order);
        tv_order.setOnClickListener(this);

        orderId = getIntent().getStringExtra("orderId");
        storeId = getIntent().getStringExtra("storeId");

        layout_address = (RelativeLayout) findViewById(R.id.layout_addresss);
        layout_address.setOnClickListener(this);
        mData = new ArrayList<ShopCartList.ShopCartBean.ShopCartItemsBean>();
        mOrderItemAdapter = new OrderItemAdapter(mData, this);
        rc_orderitems = (RecyclerView) findViewById(R.id.rc_orderitems);
        rc_orderitems.setAdapter(mOrderItemAdapter);
        rc_orderitems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!"".equals(Config.getUserId(this))) {
            getPresenter().getShopCart(Config.getUserId(this), storeId);
        }
    }

    @Override
    protected SettlementPresenter creatPresenter() {
        return new SettlementPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_order:
                new AlertDialog.Builder(this).setTitle("提交订单").
                        setMessage("确认提交订单")//设置显示的内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!"".equals(Config.getUserId(SettlementActivity.this))) {
                                    getPresenter().subCart(orderId, Config.getUserId(SettlementActivity.this));
                                } else {
                                    Toast.makeText(SettlementActivity.this, "未登录", Toast.LENGTH_SHORT).show();
                            }
                            }
                        }).setNegativeButton("返回", new DialogInterface.OnClickListener() {//添加返回按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//响应事件
                                Log.i("alertdialog", " 请保存数据！");
                            }

                }).show();

                break;
            case R.id.layout_addresss:
                Intent intent=new Intent(this, AddressManageActivity.class);
                intent.putExtra("orderId",orderId);
                startActivityForResult(intent,1234);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tv_username.setText(data.getStringExtra("userName"));
        tv_phone.setText(data.getStringExtra("userPhone"));
        tv_address.setText(data.getStringExtra("userPath"));
        Log.i("CJT","跳回来了");
    }

    @Override
    public void subCartSuccess() {
        Toast.makeText(SettlementActivity.this, "提交订单成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void subCartFail() {

    }

    @Override
    public void getShopCartSuccess(List<ShopCartList.ShopCartBean.ShopCartItemsBean> shopCartItems, double totalPrice) {
        mOrderItemAdapter.updata(shopCartItems);
        Log.i("CJT", "数量=====" + shopCartItems.size() + " ");
        tv_shopname.setText(shopCartItems.get(0).getStore().getName());
        tv_totalprice.setText("待支付￥" + totalPrice);
    }

}
