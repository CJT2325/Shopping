package com.cjt.shopping.ui.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.cjt.shopping.R;

import java.util.ArrayList;

public class AddressEditActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_getaddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_getaddress= (TextView) findViewById(R.id.tv_getaddress);
        tv_getaddress.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address_edit, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_getaddress:
                Intent intent=new Intent(this,GetAddressByMapActivity.class);
                startActivity(intent);
                break;
        }
    }
}
