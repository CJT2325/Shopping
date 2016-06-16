package com.cjt.shopping;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.cjt.shopping.fragment.HomeFragment;
import com.cjt.shopping.fragment.MyFragment;
import com.cjt.shopping.fragment.OrderFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView tv_address;
    private BottomNavigationBar bottomNavigationBar;

    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private MyFragment myFragment;

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_address.setOnClickListener(this);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        fm = getFragmentManager();


        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "Home"))
                .addItem(new BottomNavigationItem(R.drawable.ic_content_paste_white_24dp, "Order"))
                .addItem(new BottomNavigationItem(R.drawable.ic_perm_identity_white_24dp, "My"))
                .initialise();
        setDefaultFragment();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                FragmentTransaction transaction=fm.beginTransaction();
                switch (position) {
                    case 0:
                        if (homeFragment==null){
                            homeFragment=HomeFragment.newInstance();
                        }
                        toolbar.setVisibility(View.VISIBLE);
                        transaction.replace(R.id.layout_frame,homeFragment);
                        break;
                    case 1:
                        if (orderFragment==null){
                            orderFragment=OrderFragment.newInstance();
                        }
                        toolbar.setVisibility(View.VISIBLE);
                        tv_address.setText("订单");
                        transaction.replace(R.id.layout_frame,orderFragment);
                        break;
                    case 2:
                        if (myFragment==null){
                            myFragment=MyFragment.newInstance();
                        }
                        toolbar.setVisibility(View.GONE);
                        transaction.replace(R.id.layout_frame,myFragment);
                        break;
                }
                transaction.commit();
            }

            @Override
            public void onTabUnselected(int position) {
                Log.i("CJT", position + " onTabUnselected");
            }

            @Override
            public void onTabReselected(int position) {
                Log.i("CJT", position + " onTabReselected");
            }
        });
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = fm.beginTransaction();
        homeFragment = HomeFragment.newInstance();
        transaction.replace(R.id.layout_frame, homeFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_seacher) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_address:
                Log.i("CJT", "tv_address click");
                startActivity(new Intent(MainActivity.this,SetAddressActivity.class));
                break;
        }
    }
}
