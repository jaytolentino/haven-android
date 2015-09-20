package com.linkedladies.haven.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.linkedladies.haven.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckinFormFragment extends Fragment {

    @Bind(R.id.llAddPersonForm) LinearLayout llAddPersonForm;
    @Bind(R.id.llPersonContainer) LinearLayout llPersonContainer;
    @Bind(R.id.etAddVPerson) EditText etAddPerson;
    @Bind(R.id.cbInjured) CheckBox cbInjured;
    @Bind(R.id.spAddedPersonType) Spinner spAddedPersonType;
    @Bind(R.id.tvAddPerson) TextView tvAddPerson;
    @Bind(R.id.ivAddPerson) ImageView ivAddPerson;

    private int checkinCount;
    private int injuredCount;

    public CheckinFormFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_checkin_form, container, false);
        ButterKnife.bind(this, rootView);
        checkinCount = 1;
        injuredCount = 0;

        return rootView;
    }

    @OnClick(R.id.tvAddPerson)
    public void onShowAddPersonForm(View view) {
        view.setVisibility(View.GONE);
        llAddPersonForm.setVisibility(View.VISIBLE);
        ivAddPerson.setVisibility(View.GONE);

    }

    @OnClick(R.id.btnAddPerson)
    public void  onAddPerson(View view) {
        View personItem = LayoutInflater.from(getActivity()).inflate(R.layout.item_person, null);

        PersonItemViewHolder personHolder = new PersonItemViewHolder(personItem);
        personHolder.tvName.setText(etAddPerson.getText().toString());

        String selectedType = spAddedPersonType.getItemAtPosition(spAddedPersonType.getSelectedItemPosition()).toString();
        personHolder.tvType.setText(selectedType);

        personHolder.tvStatusDescription.setVisibility(cbInjured.isChecked() ? View.VISIBLE : View.INVISIBLE);

        llPersonContainer.addView(personItem);

        etAddPerson.setText("");
        cbInjured.setChecked(false);
        spAddedPersonType.setSelection(0);
        llAddPersonForm.setVisibility(View.GONE);
        tvAddPerson.setVisibility(View.VISIBLE);
        ivAddPerson.setVisibility(View.VISIBLE);

        injuredCount++;
        checkinCount++;
    }

    public class PersonItemViewHolder {
        @Bind(R.id.tvName) TextView tvName;
        @Bind(R.id.tvType) TextView tvType;
        @Bind(R.id.tvStatusDescription) TextView tvStatusDescription;

        PersonItemViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

    public int getCheckinCount() {
        return checkinCount;
    }

    public int getInjuredCount() {
        return injuredCount;
    }
}
