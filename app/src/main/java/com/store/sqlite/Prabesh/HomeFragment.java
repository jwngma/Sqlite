package com.store.sqlite.Prabesh;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.store.sqlite.R;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private Button addBtn, viewBtn, updateBtn, deleteBtn;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        addBtn=view.findViewById(R.id.addbtn);
        viewBtn=view.findViewById(R.id.viewbtnn);
        updateBtn=view.findViewById(R.id.updatebtn);
        deleteBtn=view.findViewById(R.id.deletebtn);
        addBtn.setOnClickListener(this);
        viewBtn.setOnClickListener(this);

        return view;
    }

    public  void AddFragment(Fragment fragment){
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addbtn:
                AddFragment(new AddContactFragment());
                break;
            case R.id.viewbtnn:
                AddFragment(new viewFragment());
                break;

        }
    }
}
