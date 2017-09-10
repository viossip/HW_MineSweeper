package com.os.vitaly.hw_minesweeper.GameUI;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.os.vitaly.hw_minesweeper.Controls.GameListener;
import com.os.vitaly.hw_minesweeper.Controls.ServiceListener;
import com.os.vitaly.hw_minesweeper.R;

/**
 * Created by ilya on 09/09/2017.
 */

public class mapActivity extends Fragment implements OnMapReadyCallback , ServiceListener{

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

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
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


    }


    @Override
    public void GpsUpdate(int xLocation, int yLocation) {

    }
}
