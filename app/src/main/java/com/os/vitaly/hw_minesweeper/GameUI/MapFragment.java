package com.os.vitaly.hw_minesweeper.GameUI;


import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.os.vitaly.hw_minesweeper.R;
import com.os.vitaly.hw_minesweeper.Services.GpsLocation;

/**
 * Created by ilya on 09/09/2017.
 */




public class MapFragment extends Fragment implements OnMapReadyCallback {


    private Intent intent;
    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    private double xLocation,yLocation;



    public MapFragment(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.gps, container, false);
        return mView;
    }
    public void  onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        mMapView= (MapView) mView.findViewById(R.id.map);
        if (mMapView!=null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);


        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap =googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        Cursor res = MainActivity.currentDB.getAllData();
        if (res.getCount() == 0) // no data for us
        {

        }

        else
        {
            int counter = 0;

            while(res.moveToNext())
            {
                String name = res.getString(0);
                double longitude = Double.parseDouble( res.getString(1));
                double latitude = Double.parseDouble( res.getString(2));
                int time = res.getInt(3);



                googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(name).snippet("Time: " + time + " Seconds"));

                if (counter == 0)
                {
                    CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
                    CameraPosition scorePos = CameraPosition.builder().target(new LatLng(latitude, longitude)).zoom(15).bearing(0).tilt(45).build();
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(scorePos));
                    googleMap.animateCamera(zoom);
                }
                counter++;
            }




        }


    }


}
