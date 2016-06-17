package com.cjt.shopping;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cjt.shopping.adapter.AddressAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddressManageActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AddressAdapter mAddressAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_addresslist);
        initDatas();
        mAddressAdapter = new AddressAdapter(mDatas, this);
        mRecyclerView.setAdapter(mAddressAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 2; i++) {
            mDatas.add("Stirng " + i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address_manage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_manage) {
            mAddressAdapter.showEditBtn();
            if (item.getTitle().equals("管理")) {
                item.setTitle("完成");
            }else{
                item.setTitle("管理");
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
