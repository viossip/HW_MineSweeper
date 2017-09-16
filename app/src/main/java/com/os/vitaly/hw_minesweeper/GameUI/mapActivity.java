package com.os.vitaly.hw_minesweeper.GameUI;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.os.vitaly.hw_minesweeper.Controls.GameListener;
import com.os.vitaly.hw_minesweeper.Controls.ServiceListener;
import com.os.vitaly.hw_minesweeper.R;

/**
 * Created by ilya on 09/09/2017.
 */

public class mapActivity extends Fragment implements OnMapReadyCallback , ServiceListener{

    private Intent intent;
    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    private double xLocation,yLocation;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreate(LayoutInflater inflater, ViewGroup container ,Bundle savedInstanceState){
        mView=inflater.inflate(R.layout.gps,container,false);
        return mView;
    }
    public void  onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        mMapView= (MapView) mView.findViewById(R.id.map);
        if (mMapView!=null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);

        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap =googleMap;
        LatLng location = new LatLng(xLocation,yLocation);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        googleMap.addMarker(new MarkerOptions().position(location));
        googleMap.animateCamera(zoom);

    }




    @Override
    public void GpsUpdate(double xLocation, double yLocation) {
        this.xLocation=xLocation;
        this.yLocation=yLocation;
    }
}
