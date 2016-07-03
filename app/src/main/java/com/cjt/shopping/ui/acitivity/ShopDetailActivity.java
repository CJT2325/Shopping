package com.cjt.shopping.ui.acitivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.cjt.shopping.R;
import com.cjt.shopping.adapter.ShopTabAdapter;
import com.cjt.shopping.fragment.EvaluateFragment;
import com.cjt.shopping.fragment.SelectFragment;
import com.cjt.shopping.fragment.ShopInfoFragment;

import java.util.ArrayList;
import java.util.List;

public class ShopDetailActivity extends AppCompatActivity {
    private String vendorID="";
    private TabLayout tablayout_shop;
    private ViewPager vp_info;
    private List<Fragment> list_fragment;
    private List<String> list_title;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private SelectFragment mSelectFragment;
    private EvaluateFragment mEvaluateFragment;
    private ShopInfoFragment mShopInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("商店名称");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        vendorID=getIntent().getStringExtra("vendorID");
        Log.i("CJT","商家ID ====="+vendorID);
        initData();
    }

    private void initData() {
        tablayout_shop = (TabLayout) findViewById(R.id.tablayout_shop);
        vp_info = (ViewPager) findViewById(R.id.vp_info);

        //初始化Fragment
        list_fragment = new ArrayList<Fragment>();
        mSelectFragment = SelectFragment.newInstance(vendorID);
        mEvaluateFragment = EvaluateFragment.newInstance();
        mShopInfoFragment = ShopInfoFragment.newInstance();
        list_fragment.add(mSelectFragment);
        list_fragment.add(mEvaluateFragment);
        list_fragment.add(mShopInfoFragment);

        //初始化标题
        list_title = new ArrayList<String>();
        list_title.add("点菜");
        list_title.add("评价");
        list_title.add("商家");

        tablayout_shop.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        for (int i = 0; i < list_title.size(); i++) {
            tablayout_shop.addTab(tablayout_shop.newTab().setText(list_title.get(i)));
        }
        mFragmentPagerAdapter = new ShopTabAdapter(getSupportFragmentManager(), list_fragment, list_title);
        vp_info.setAdapter(mFragmentPagerAdapter);
        tablayout_shop.setupWithViewPager(vp_info);
    }

}
