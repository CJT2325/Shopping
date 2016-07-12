package com.cjt.shopping.ui.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cjt.shopping.R;
import com.cjt.shopping.adapter.AddressAdapter;
import com.cjt.shopping.bean.AddressList;
import com.cjt.shopping.presenter.AddressManagePresenter;
import com.cjt.shopping.ui.view.AddressEditView;
import com.cjt.shopping.ui.view.AddressManageView;
import com.cjt.shopping.util.Config;

import java.util.ArrayList;
import java.util.List;

public class AddressManageActivity extends BaseActivity<AddressManageActivity,AddressManagePresenter> implements AddressManageView,View.OnClickListener {
    private String orderId="";
    private int addressPositon=-1;
    private RecyclerView mRecyclerView;
    private AddressAdapter mAddressAdapter;
    private List<AddressList.UserBean.MyAddressesBean> mDatas;
    ItemTouchHelper mItemTouchHelper;

    TextView tv_addaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        orderId=getIntent().getStringExtra("orderId");
        Log.i("CJT","==="+orderId);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_addresslist);
        initDatas();
        mAddressAdapter = new AddressAdapter(mDatas, this, new AddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Intent intent=new Intent();
//                intent.putExtra("AddressId",mDatas.get(position).getId());
//                setResult(1234,intent);
//                finish();
                Log.i("CJT","AddressId="+mDatas.get(position).getId());
                if (orderId!=null) {
                    addressPositon=position;
                    getPresenter().subAddress(mDatas.get(position).getId() + "", Config.getUserId(AddressManageActivity.this), orderId);
                }
//                mDatas.get(position).getId();
            }
        });
        SimpleItemTouchHelperCallback callback=new SimpleItemTouchHelperCallback(mAddressAdapter);
        mRecyclerView.setAdapter(mAddressAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mItemTouchHelper = new ItemTouchHelper(callback);

        tv_addaddress= (TextView) findViewById(R.id.tv_addaddress);
        tv_addaddress.setOnClickListener(this);
        // mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected AddressManagePresenter creatPresenter() {
        return new AddressManagePresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!"".equals(Config.getUserId(this))) {
            getPresenter().getAddress(Config.getUserId(this));
        }else {
            Toast.makeText(this,"未登录",Toast.LENGTH_SHORT).show();
        }
    }

    private void initDatas() {
        mDatas = new ArrayList<AddressList.UserBean.MyAddressesBean>();
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
            } else {
                item.setTitle("管理");
            }
            return true;
        }
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void getAddressSuccess(List<AddressList.UserBean.MyAddressesBean> myAddresses) {
        this.mDatas.clear();
        this.mDatas=myAddresses;
        this.mAddressAdapter.updata(myAddresses);
    }

    @Override
    public void getAddressFail() {

    }

    @Override
    public void subAddressSuccess() {
        Intent intent=new Intent();
        intent.putExtra("userName",mDatas.get(addressPositon).getConsignee());
        intent.putExtra("userPhone",mDatas.get(addressPositon).getPhone());
        intent.putExtra("userPath",mDatas.get(addressPositon).getPath());
        setResult(1234,intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_addaddress:
                startActivity(new Intent(this, AddressEditActivity.class));
                break;
        }
    }


    public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

        private onMoveAndSwipedListener mAdapter;
        SimpleItemTouchHelperCallback(onMoveAndSwipedListener listener){
            mAdapter=listener;
        }
        /**
         * 这个方法是用来设置我们拖动的方向以及侧滑的方向的
         */
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                //设置拖拽方向为上下
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                //设置侧滑方向为从左到右和从右到左都可以
                final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                //将方向参数设置进去
                return makeMovementFlags(dragFlags, swipeFlags);
            } else {
                return 0;
            }
        }

        /**
         * 当我们拖动item时会回调此方法
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        /**
         * 当我们侧滑item时会回调此方法
         */
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
        }
    }

    public interface onMoveAndSwipedListener {
        //boolean onItemMove(int fromPosition , int toPosition);
        void onItemDismiss(int position);
    }
}


