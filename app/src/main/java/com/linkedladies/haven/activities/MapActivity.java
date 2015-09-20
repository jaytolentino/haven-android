package com.linkedladies.haven.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.linkedladies.haven.R;
import com.linkedladies.haven.fragments.DisasterInfoFragment;
import com.linkedladies.haven.fragments.MapFragment;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MapActivity extends BaseActivity implements MapFragment.DisasterClickListener {

    @Bind(R.id.slidingLayout) SlidingUpPanelLayout slidingLayout;
    @Bind(R.id.fabCheckin) FloatingActionButton fabCheckin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContent, new MapFragment())
                .commit();

        slidingLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View view, float v) {
                /* NOP */
            }

            @Override
            public void onPanelCollapsed(View view) {
                fabCheckin.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
                fabCheckin.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPanelExpanded(View view) {
                fabCheckin.setVisibility(View.VISIBLE);
                fabCheckin.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
            }

            @Override
            public void onPanelAnchored(View view) {
                /* NOP */
            }

            @Override
            public void onPanelHidden(View view) {
                /* NOP */
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDisasterClicked(DisasterInfoFragment disasterInfoFragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flDisasterInfo, disasterInfoFragment)
                .commit();
        slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

        fabCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkin = new Intent(MapActivity.this, CheckinActivity.class);
                startActivity(checkin);
            }
        });
    }
}
