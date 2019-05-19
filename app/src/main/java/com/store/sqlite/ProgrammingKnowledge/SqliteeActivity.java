package com.store.sqlite.ProgrammingKnowledge;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.store.sqlite.R;

public class SqliteeActivity extends AppCompatActivity {

    private EditText username_edt, surname_edt, marks_edt, id_edt;
    private Button regBtn, updateBtn, viewBtn, deleteBtn;
    private String username, surname, marks, id;

    private DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlitee);
        initAll();
    }

    private void initAll() {
        username_edt = findViewById(R.id.username);
        surname_edt = findViewById(R.id.surname);
        marks_edt = findViewById(R.id.marks);
        regBtn = findViewById(R.id.createBtn);
        updateBtn = findViewById(R.id.updateBtn);
        viewBtn = findViewById(R.id.viewBtn);
        id_edt = findViewById(R.id.id);
        deleteBtn = findViewById(R.id.deleteBtn);
        mydb = new DatabaseHelper(this);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                username = username_edt.getText().toString();
                surname = surname_edt.getText().toString();
                marks = marks_edt.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(SqliteeActivity.this, "username is Empty", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(surname)) {
                    Toast.makeText(SqliteeActivity.this, "pass is Empty", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(marks)) {
                    Toast.makeText(SqliteeActivity.this, "Cpass is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    boolean inserted = mydb.insertdata(username, surname, marks);
                    if (inserted == true) {
                        Toast.makeText(SqliteeActivity.this, "Data is inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SqliteeActivity.this, "Data is not inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = username_edt.getText().toString();
                surname = surname_edt.getText().toString();
                marks = marks_edt.getText().toString();
                id = id_edt.getText().toString();
                if (TextUtils.isEmpty(id)) {
                    Toast.makeText(SqliteeActivity.this, "id is empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(username)) {
                    Toast.makeText(SqliteeActivity.this, "username is Empty", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(surname)) {
                    Toast.makeText(SqliteeActivity.this, "pass is Empty", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(marks)) {
                    Toast.makeText(SqliteeActivity.this, "Cpass is Empty", Toast.LENGTH_SHORT).show();
                } else {

                    boolean updated = mydb.Updatedata(id, username, surname, marks);
                    if (updated == true) {
                        Toast.makeText(SqliteeActivity.this, "The data has been updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SqliteeActivity.this, "The data has failed to update", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = id_edt.getText().toString();
                if (TextUtils.isEmpty(id)) {
                    Toast.makeText(SqliteeActivity.this, "id is empty", Toast.LENGTH_SHORT).show();
                } else {
                    Integer deletedRows = mydb.deletedata(id);
                    if (deletedRows > 0) {
                        Toast.makeText(SqliteeActivity.this, "Data is Deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SqliteeActivity.this, "Data is not Deleted", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = mydb.getAlldata();
                if (res.getCount() == 0) {

                    showAlert("Error", "No Data is found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id :" + res.getString(0) + "\n");
                    buffer.append("Name: " + res.getString(1) + "\n");
                    buffer.append("Surname : " + res.getString(2) + "\n");
                    buffer.append("Marks :" + res.getString(3) + "\n\n");

                }

                showAlert("data", buffer.toString());
            }
        });


    }

    public void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }


}
