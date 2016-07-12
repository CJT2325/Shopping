package com.cjt.shopping.ui.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cjt.shopping.R;
import com.cjt.shopping.presenter.AddressEditPresenter;
import com.cjt.shopping.ui.view.AddressEditView;
import com.cjt.shopping.util.Config;

import java.util.ArrayList;

public class AddressEditActivity extends BaseActivity<AddressEditActivity,AddressEditPresenter> implements View.OnClickListener,AddressEditView{
    ImageView iv_location;
    EditText et_name;
    EditText et_phone;
    EditText et_getaddress;
    EditText et_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_name= (EditText) findViewById(R.id.et_name);
        et_phone= (EditText) findViewById(R.id.et_phone);
        et_getaddress= (EditText) findViewById(R.id.et_getaddress);
        et_number= (EditText) findViewById(R.id.et_number);

        iv_location= (ImageView) findViewById(R.id.iv_location);
        iv_location.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected AddressEditPresenter creatPresenter() {
        return new AddressEditPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address_edit, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            String name=et_name.getText().toString();
            String phone=et_phone.getText().toString();
            String address=et_getaddress.getText().toString();
            String number=et_number.getText().toString();
            if (name.equals("")||phone.equals("")||address.equals("")||number.equals("")){
                Toast.makeText(this, "有参数为空", Toast.LENGTH_SHORT).show();
            }else {
                if (!"".equals(Config.getUserId(this))) {
                    getPresenter().addAddress(name, phone, address + number, Config.getUserId(this) + "");
                } else {
                    Toast.makeText(this, "未登录", Toast.LENGTH_SHORT).show();
                }
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_location:
                Intent intent=new Intent(this,GetAddressByMapActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void addAddressSuccess() {

    }

    @Override
    public void addAddressFail() {

    }
}
