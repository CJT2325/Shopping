package com.cjt.shopping.ui.acitivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cjt.shopping.R;
import com.cjt.shopping.adapter.AddressAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddressManageActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AddressAdapter mAddressAdapter;
    private List<String> mDatas;
    ItemTouchHelper mItemTouchHelper;

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
        SimpleItemTouchHelperCallback callback=new SimpleItemTouchHelperCallback(mAddressAdapter);
        mRecyclerView.setAdapter(mAddressAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mItemTouchHelper = new ItemTouchHelper(callback);
       // mItemTouchHelper.attachToRecyclerView(mRecyclerView);
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
            } else {
                item.setTitle("管理");
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
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


