package com.cjt.shopping.ui.acitivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.cjt.shopping.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetAddressByMapActivity extends AppCompatActivity implements LocationSource,AMapLocationListener {
    private TextView tv_address;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private OnLocationChangedListener mListener=null;
    private AMapLocationClient mlocationClient=null;

    private MapView mMapView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_address_by_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMap().setLocationSource(this);
        mMapView.getMap().getUiSettings().setMyLocationButtonEnabled(true);
        mMapView.getMap().setMyLocationEnabled(true);
        mMapView.getMap().setMapType(AMap.MAP_TYPE_NORMAL);
        mMapView.getMap().moveCamera(CameraUpdateFactory.zoomTo(16));

        Log.i("CJT","放大倍数="+ mMapView.getMap().getMaxZoomLevel());
        tv_address= (TextView) findViewById(R.id.tv_address);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }


    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
        Log.i("CJT","activate");
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
        Log.i("CJT","deactivate");
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                tv_address.setText(amapLocation.getCity()+amapLocation.getDistrict()+amapLocation.getStreet()+amapLocation.getStreetNum());
                mlocationClient.stopLocation();
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr",errText);
            }
        }
        Log.i("CJT","onLocationChanged");
    }
}
