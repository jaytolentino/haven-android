package com.linkedladies.haven.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.linkedladies.haven.R;
import com.linkedladies.haven.fragments.DisasterInfoFragment;
import com.linkedladies.haven.fragments.MapFragment;
import com.linkedladies.haven.helpers.Utils;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MapActivity extends BaseActivity implements MapFragment.DisasterClickListener {

    public static final String CHECKED_IN = "checkedIn";
    public static final String COUNT = "count";

    @Bind(R.id.dlNavMenu) DrawerLayout dlNavMenu;
    @Bind(R.id.tbMap) Toolbar tbMap;
    @Bind(R.id.slidingLayout) SlidingUpPanelLayout slidingLayout;
    @Bind(R.id.fabCheckin) FloatingActionButton fabCheckin;

    private boolean checkedIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);

        checkedIn = getIntent().getExtras() != null && getIntent().getExtras().getBoolean(CHECKED_IN);

        setSupportActionBar(tbMap);
        setTitle(getString(R.string.app_name));
        tbMap.setPadding(0, Utils.getStatusBarHeight(getApplicationContext()), 0, 0);
        tbMap.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlNavMenu.openDrawer(GravityCompat.START);
            }
        });
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,  dlNavMenu, tbMap, R.string.app_name, R.string.app_name
        );
        dlNavMenu.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle.syncState();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContent, new MapFragment())
                .commit();

        if (checkedIn) {
            View rootView = findViewById(R.id.rootView);
            int count = getIntent().getExtras().getInt(COUNT);
            String message = getResources().getQuantityString(R.plurals.checkin, count, count);
            Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show();
        }
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
    public void onDisasterClicked(final DisasterInfoFragment disasterInfoFragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flDisasterInfo, disasterInfoFragment)
                .commit();
        slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

        fabCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkin = new Intent(MapActivity.this, CheckinActivity.class);
                checkin.putExtra(DisasterInfoFragment.TITLE, disasterInfoFragment.getTitle());
                startActivity(checkin);
            }
        });
    }
}
