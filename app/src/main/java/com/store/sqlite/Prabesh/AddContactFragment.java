package com.store.sqlite.Prabesh;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.store.sqlite.R;


public class AddContactFragment extends Fragment {
    public AddContactFragment() {

    }


    private Button saveBtn;
    private EditText id_edt, name_edt, email_edt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);

        saveBtn = view.findViewById(R.id.saveBtn);
        id_edt = view.findViewById(R.id.contact_id);
        name_edt = view.findViewById(R.id.contact_name);
        email_edt = view.findViewById(R.id.contact_email);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = id_edt.getText().toString();
                String name = name_edt.getText().toString();
                String email = email_edt.getText().toString();

                ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
                SQLiteDatabase db = contactDbHelper.getWritableDatabase();
                contactDbHelper.AddContact(Integer.parseInt(id),name,email,db);
                id_edt.setText("");
                name_edt.setText("");
                email_edt.setText("");

                Toast.makeText(getActivity(), "Contact has beeen Saqved Succesfully", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
