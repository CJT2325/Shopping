package com.cjt.shopping.ui.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjt.shopping.R;
import com.cjt.shopping.model.server.ServerAPI;
import com.squareup.picasso.Picasso;

public class GoodDetailActivity extends AppCompatActivity {
    private ImageView iv_goodscover;
    private TextView tv_goodsname;
    private TextView tv_sale;
    private TextView tv_price;
    private TextView tv_goodinfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("商品详情");

        iv_goodscover= (ImageView) findViewById(R.id.iv_goodscover);
        tv_goodsname= (TextView) findViewById(R.id.tv_goodsname);
        tv_sale= (TextView) findViewById(R.id.tv_sale);
        tv_price= (TextView) findViewById(R.id.tv_price);
        tv_goodinfos= (TextView) findViewById(R.id.tv_goodinfos);

        Intent intent=getIntent();

        Picasso.with(this).load(ServerAPI.baseUrl+intent.getStringExtra("goodImageUrl")).into(iv_goodscover);
        tv_goodsname.setText(intent.getStringExtra("goodName"));
        tv_sale.setText("月售"+intent.getStringExtra("goodSellCount"));
        tv_price.setText("￥"+intent.getStringExtra("goodPrice"));
        tv_goodinfos.setText(intent.getStringExtra("goodDetail"));
        Log.i("cjt",intent.getStringExtra("goodPrice")+" =====");
    }

}
