package com.linkedladies.haven.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linkedladies.haven.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DisasterInfoFragment extends Fragment {

    private static final String TITLE = "title";
    private static final String CHECKIN_COUNT = "checkinCount";

    @Bind(R.id.tvTitle) TextView tvTitle;
    @Bind(R.id.tvCount) TextView tvCount;

    private String title;
    private int checkinCount;

    public static DisasterInfoFragment newInstance(String title, int checkinCount) {
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putInt(CHECKIN_COUNT, checkinCount);

        DisasterInfoFragment fragment = new DisasterInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DisasterInfoFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            Bundle args = getArguments();
            title = args.getString(TITLE);
            checkinCount = args.getInt(CHECKIN_COUNT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_disaster_info, container, false);
        ButterKnife.bind(this, rootView);
        tvTitle.setText(title);
        tvCount.setText(checkinCount + " check-ins");
        return rootView;
    }
}
