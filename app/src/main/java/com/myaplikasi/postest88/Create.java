package com.myaplikasi.postest88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Create extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText no, foto, alamat, tlp, sms, fasilitas, harga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        dbHelper = new DataHelper(this);
        no = (EditText) findViewById(R.id.editText1);
        foto = (EditText) findViewById(R.id.editText2);
        alamat = (EditText) findViewById(R.id.editText3);
        tlp = (EditText) findViewById(R.id.editText4);
        sms = (EditText) findViewById(R.id.editText5);
        fasilitas = (EditText) findViewById(R.id.editText6);
        harga = (EditText) findViewById(R.id.editText7);
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into biodata (no, foto, alamat, tlp, sms, fasilitas, harga ) values('" +
                                no.getText().toString()+"','"+
                                foto.getText().toString()+"','"+
                                alamat.getText().toString()+"','"+
                                tlp.getText().toString()+"','"+
                                sms.getText().toString()+"','"+
                                fasilitas.getText().toString()+"','"+
                                harga.getText().toString()+ "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            startActivity(new Intent(this, Login.class));
        }
        return true;
    }
}

