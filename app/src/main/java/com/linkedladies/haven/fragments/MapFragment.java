package com.linkedladies.haven.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esri.android.map.MapView;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.android.toolkit.map.MapViewHelper;
import com.esri.android.toolkit.map.OnGraphicClickListener;
import com.esri.core.map.Graphic;
import com.linkedladies.haven.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MapFragment extends Fragment {

    private DisasterClickListener disasterClickListener;

    public interface DisasterClickListener {
        void onDisasterClicked(DisasterInfoFragment disasterInfoFragment);
    }

    @Bind(R.id.map) MapView mapView;

    MapViewHelper mapViewHelper;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // TODO add cast check
        disasterClickListener = (DisasterClickListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        ButterKnife.bind(this, rootView);
        mapViewHelper = new MapViewHelper(mapView);

        mapView.setOnStatusChangedListener(new OnStatusChangedListener() {
            @Override
            public void onStatusChanged(Object source, STATUS status) {

                if ((status == STATUS.INITIALIZED) && (source instanceof MapView)) {
                    addShelters();
                    addDisasters();
                    setupGraphicClicks();
                }

            }
        });

        return rootView;

    }

    @Override
    public void onPause() {
        super.onPause();

        if (mapView != null) {
            mapView.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mapView != null) {
            mapView.unpause();
        }
    }

    private void addShelters() {
        Drawable shelterIcon = getResources().getDrawable(R.drawable.ic_map_marker_shelter);
        mapViewHelper.addMarkerGraphic(37.761, -122.394, null, null, null, shelterIcon, false, 0);
    }

    private void addDisasters() {
        Drawable disasterIcon = getResources().getDrawable(R.drawable.ic_map_marker_disaster);
        mapViewHelper.addMarkerGraphic(37.7598579, -122.3835949, null, null, null, disasterIcon, false, 0);
    }

    private void setupGraphicClicks() {
        mapViewHelper.setShowGraphicCallout(false);

        mapViewHelper.setOnGraphicClickListener(new OnGraphicClickListener() {
            @Override
            public void onGraphicClick(Graphic graphic) {
                disasterClickListener.onDisasterClicked(DisasterInfoFragment.newInstance("SF TechCrunch Disrupt Hackathon Fire", 138));
            }
        });
    }
}
