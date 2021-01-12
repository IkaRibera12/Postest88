package com.myaplikasi.postest88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Readus extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton2, back, btnsms, btnmap;
    TextView no, foto, alamat, tlp, sms, fasilitas, harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readus);

        dbHelper = new DataHelper(this);
        no = (TextView) findViewById(R.id.textViewliat1);
        foto = (TextView) findViewById(R.id.textViewliat2);
        alamat = (TextView) findViewById(R.id.textViewliat3);
        tlp = (TextView) findViewById(R.id.textViewliat4);
        sms = (TextView) findViewById(R.id.textViewliat5);
        fasilitas = (TextView) findViewById(R.id.textViewliat6);
        harga = (TextView) findViewById(R.id.textViewliat7);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE foto = '" +
                getIntent().getStringExtra("foto") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            no.setText(cursor.getString(0).toString());
            foto.setText(cursor.getString(1).toString());
            alamat.setText(cursor.getString(2).toString());
            tlp.setText(cursor.getString(3).toString());
            sms.setText(cursor.getString(4).toString());
            fasilitas.setText(cursor.getString(5).toString());
            harga.setText(cursor.getString(6).toString());
        }
        ton2 = (Button) findViewById(R.id.button1);
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        back = (Button) findViewById(R.id.button2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telephon = tlp.getText().toString();
                if (!TextUtils.isEmpty(telephon)){
                    String tl = "tel:"+telephon;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(tl)));
                }else{
                    Toast.makeText(Readus.this, "Masukan NO Telephon", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnmap = (Button) findViewById(R.id.button4);
        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maps = alamat.getText().toString();
                if (!TextUtils.isEmpty(maps)){
                    String mp = "https://www.google.co.id/maps/search/"+maps;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mp)));
                }else{
                    Toast.makeText(Readus.this, "Masukan Kata Kunci", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnsms = (Button)findViewById(R.id.button3);

        btnsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendSms();
            }
        });
    }

    private void SendSms() {
        Uri uri = Uri.parse("smsto:" + tlp.getText().toString());

        Intent smsSIntent = new Intent(Intent.ACTION_SENDTO, uri);
        // add the message at the sms_body extra field
        smsSIntent.putExtra("sms_body", btnsms.getText().toString());

        startActivity(smsSIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar ifit is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            startActivity(new Intent(this, Home.class));
        }
        return true;
    }
}
