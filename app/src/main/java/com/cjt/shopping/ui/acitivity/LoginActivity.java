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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cjt.shopping.R;
import com.cjt.shopping.bean.UserInfo;
import com.cjt.shopping.presenter.BasePresenter;
import com.cjt.shopping.presenter.LoginPresenter;
import com.cjt.shopping.ui.view.LoginView;
import com.cjt.shopping.util.Config;

public class LoginActivity extends BaseActivity<LoginActivity, LoginPresenter> implements LoginView, View.OnClickListener {
    private EditText et_phone, et_passwrod;
    private Button btn_login;
    private ProgressBar pb_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("用户登录");
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_passwrod = (EditText) findViewById(R.id.et_passwrod);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        pb_login = (ProgressBar) findViewById(R.id.pb_login);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_register) {
            startActivity(new Intent(this,RegisterActivity.class));
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
    protected LoginPresenter creatPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void showProgress() {
        pb_login.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pb_login.setVisibility(View.INVISIBLE);
    }

    @Override
    public void logigSuccess(UserInfo.UserBean userBean) {
        Toast.makeText(this,"Loing Success",Toast.LENGTH_SHORT).show();
        Config.saveUserId(this,userBean.getId()+"");
        Config.savePhone(this,userBean.getPhone());
        Config.saveUserName(this,userBean.getName());
        hideProgress();
        finish();
    }

    @Override
    public void loginFail() {
        Toast.makeText(this,"Loing Fail",Toast.LENGTH_SHORT).show();
        hideProgress();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                showProgress();
                String phone = et_phone.getText().toString();
                String password = et_passwrod.getText().toString();
                getPresenter().login(phone, password);
                break;
        }
    }
}
