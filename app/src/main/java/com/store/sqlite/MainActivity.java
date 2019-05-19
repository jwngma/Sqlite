package com.store.sqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.store.sqlite.ProgrammingKnowledge.SqliteeActivity;
import com.store.sqlite.Utils.DatabaseOperations;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText id_edt, username_edt, email_edt, pass_edt;
    private Button create, update, delete, show;
    private String id, user, emai, pass;

    DatabaseOperations mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb= new DatabaseOperations(this);
        initAll();
    }

    private void initAll() {
        id_edt=findViewById(R.id.idd);
        username_edt=findViewById(R.id.usernamee);
        email_edt=findViewById(R.id.email);
        pass_edt=findViewById(R.id.passs);

        id=id_edt.getText().toString();
        user=username_edt.getText().toString();
        emai=email_edt.getText().toString();
        pass=pass_edt.getText().toString();


        create=findViewById(R.id.createuser);
        update=findViewById(R.id.updateUser);
        delete=findViewById(R.id.deleteUser);
        show=findViewById(R.id.showall);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted= mydb.Createuser(user, emai, pass);

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(MainActivity.this, "username is Empty", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(emai)) {
                    Toast.makeText(MainActivity.this, "pass is Empty", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(MainActivity.this, "Cpass is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (inserted==true){
                        Toast.makeText(MainActivity.this, "New User is inserted", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Not inserted", Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= mydb.getAlldata();
               if (res.getCount()==0){
                   ShowMessage("Error","not gpfpek");
                   return;

               }

               StringBuffer buffer= new StringBuffer();
               while (res.moveToNext()){
                   buffer.append("Id :"+res.getString(0)+"\n");
                   buffer.append("Name :"+res.getString(1)+"\n");
                   buffer.append("Email :"+res.getString(2)+"\n");
                   buffer.append("Pass :"+res.getString(3)+"\n\n");

               }

               ShowMessage("data", buffer.toString());

            }
        });


    }

    public  void ShowMessage(String title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
}
