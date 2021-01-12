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


public class Update extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText no, foto, alamat, tlp, sms, fasilitas, harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new DataHelper(this);
        no = (EditText) findViewById(R.id.editTextup1);
        foto = (EditText) findViewById(R.id.editTextup2);
        alamat = (EditText) findViewById(R.id.editTextup3);
        tlp = (EditText) findViewById(R.id.editTextup4);
        sms = (EditText) findViewById(R.id.editTextup5);
        fasilitas = (EditText) findViewById(R.id.editTextup6);
        harga = (EditText) findViewById(R.id.editTextup7);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE foto = '" +
        getIntent().getStringExtra("foto") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            no.setText(cursor.getString(0).toString());
            foto.setText(cursor.getString(1).toString());
            alamat.setText(cursor.getString(2).toString());
            tlp.setText(cursor.getString(3).toString());
            sms.setText(cursor.getString(4).toString());
            fasilitas.setText(cursor.getString(5).toString());
            harga.setText(cursor.getString(6).toString());
        }
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);
        // daftarkan even onClick pada btnSimpan
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db =
                        dbHelper.getWritableDatabase();
                db.execSQL("update biodata set foto='"+
                                foto.getText().toString() +"', alamat='" +
                                alamat.getText().toString()+"', tlp='"+
                                tlp.getText().toString() +"', sms='" +
                                sms.getText().toString() +"', fasilitas='" +
                                fasilitas.getText().toString() +"', harga='" +
                                harga.getText().toString() + "' where no ='" +
                                no.getText().toString()+"'");
                Toast.makeText(getApplicationContext(),"Berhasil", Toast.LENGTH_LONG).show();
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
