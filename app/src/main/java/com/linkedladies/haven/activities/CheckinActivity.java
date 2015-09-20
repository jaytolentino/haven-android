package com.linkedladies.haven.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.linkedladies.haven.R;
import com.linkedladies.haven.fragments.CheckinFormFragment;
import com.linkedladies.haven.fragments.DisasterInfoFragment;
import com.linkedladies.haven.helpers.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CheckinActivity extends AppCompatActivity {

    @Bind(R.id.tvTitle) TextView tvTitle;
    @Bind(R.id.tbCheckin) Toolbar tbCheckin;
    @Bind(R.id.tvType) TextView tvType;

    private CheckinFormFragment checkinFormFragment;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        ButterKnife.bind(this);

        initialize();
        setupToolbar();

        checkinFormFragment = new CheckinFormFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flCheckinContent, checkinFormFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_checkin_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (id == R.id.action_submit) {
            final int checkinCount = checkinFormFragment.getCheckinCount();

            // TODO fix POST request
//            int injuredCount = checkinFormFragment.getInjuredCount();
//
//            final ProgressDialog dialog = ProgressDialog.show(CheckinActivity.this, "",
//                    "Sending check-in...", true);
//            HavenClient.sendCheckin(
//                    checkinCount,
//                    injuredCount,
//                    new Callback<Results>() {
//                        @Override
//                        public void success(Results results, Response response) {
//                            dialog.dismiss();
//                            Log.i(CheckinActivity.class.getSimpleName(), "Successfully posted checkin");
//                            Intent openMap = new Intent(CheckinActivity.this, MapActivity.class);
//                            openMap.putExtra(MapActivity.CHECKED_IN, true);
//                            openMap.putExtra(MapActivity.COUNT, checkinCount);
//                            startActivity(openMap);
//                        }
//
//                        @Override
//                        public void failure(RetrofitError error) {
//                            dialog.dismiss();
//                            Log.e(CheckinActivity.class.getSimpleName(), "Failed to post checkin");
//                            Intent openMap = new Intent(CheckinActivity.this, MapActivity.class);
//                            openMap.putExtra(MapActivity.CHECKED_IN, true);
//                            openMap.putExtra(MapActivity.COUNT, checkinCount);
//                            startActivity(openMap);
//                        }
//                    }
//            );

            Intent openMap = new Intent(CheckinActivity.this, MapActivity.class);
            openMap.putExtra(MapActivity.CHECKED_IN, true);
            openMap.putExtra(MapActivity.COUNT, checkinCount);
            startActivity(openMap);
        }

        return super.onOptionsItemSelected(item);
    }

    private void initialize() {
        title = getIntent().getExtras() != null
                ? getIntent().getExtras().getString(DisasterInfoFragment.TITLE)
                : getString(R.string.app_name);
    }

    private void setupToolbar() {
        setSupportActionBar(tbCheckin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTitle.setText(title);
        tbCheckin.setPadding(0, Utils.getStatusBarHeight(getApplicationContext()), 0, 0);
        tvType.setText("Check In");
    }
}
