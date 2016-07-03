package com.cjt.shopping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cjt.shopping.ui.acitivity.AddressManageActivity;
import com.cjt.shopping.R;
import com.cjt.shopping.ui.acitivity.UserInfoActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者: 陈嘉桐 on 2016/6/12
 * 邮箱: 445263848@qq.com.
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout myAddress;

    private CircleImageView mCircleImageView;

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", "text");
        fragment.setArguments(args);
        return fragment;
    }

    public MyFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        Bundle bundle = getArguments();
        mCircleImageView = (CircleImageView) view.findViewById(R.id.iv_usercover);
        myAddress = (RelativeLayout) view.findViewById(R.id.layout_myaddress);
        myAddress.setOnClickListener(this);
        mCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_myaddress:
                startActivity(new Intent(getActivity(), AddressManageActivity.class));
        }
    }
}
