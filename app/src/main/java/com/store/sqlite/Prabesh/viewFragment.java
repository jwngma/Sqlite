package com.store.sqlite.Prabesh;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.store.sqlite.R;


public class viewFragment extends Fragment {

    private TextView tv;


    public viewFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        tv = view.findViewById(R.id.viewText);
        ReadDatas();
        return view;
    }

    private void ReadDatas() {
        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        SQLiteDatabase database = contactDbHelper.getReadableDatabase();

        Cursor cursor = contactDbHelper.readContacts(database);
        String info = "";
        while (cursor.moveToNext()) {
            String id = Integer.toString(cursor.getColumnIndex(ContactContract.ContactEntry.CONTACT_ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.NAME));
            String email = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.EMAIL));

            info = info + "\n\n" + "Id :" + id + "\nName:" + name + "\nEmail :" + email;

        }
        tv.setText(info);
        contactDbHelper.close();
    }

}
