package com.cjt.shopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjt.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopInfoFragment extends Fragment {

    public static ShopInfoFragment newInstance() {
        ShopInfoFragment fragment = new ShopInfoFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", "text");
        fragment.setArguments(args);
        return fragment;
    }

    public ShopInfoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluate, container, false);
        return view;
    }

    private void initDatas() {
    }


}
