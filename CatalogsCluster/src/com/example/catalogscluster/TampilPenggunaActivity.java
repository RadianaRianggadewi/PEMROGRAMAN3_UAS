package com.example.catalogscluster;

import com.example.catalogscluster.models.UserDataSource;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

public class TampilPenggunaActivity extends Activity {
	
	TextView nama, ktp, alamat, hp, email, users, pass;
	UserDataSource sqlcon;
	
    @Override
    protected void onCreate(Bundle aa) {
        super.onCreate(aa);
        setContentView(R.layout.pengguna_layout);

        nama   = (TextView) findViewById(R.id.txtNama);
        ktp    = (TextView) findViewById(R.id.txtKtp);
        alamat = (TextView) findViewById(R.id.txtAlamat);
        hp     = (TextView) findViewById(R.id.txtHp);
        email  = (TextView) findViewById(R.id.txtEmail);
        users   = (TextView) findViewById(R.id.txtUser);
        pass   = (TextView) findViewById(R.id.txtPass);
        
        Intent in = getIntent();
		String user = in.getStringExtra("user");
		
		sqlcon = new UserDataSource(this);
		sqlcon.open();
        
		Cursor c = sqlcon.getData(user);
		
		nama.setText(c.getString(1));
		ktp.setText(c.getString(2));
		alamat.setText(c.getString(3));
		hp.setText(c.getString(4));
		email.setText(c.getString(5));
		users.setText(c.getString(6));
		pass.setText(c.getString(7));
		
		Button back = (Button) findViewById(R.id.btnBack);
		back.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(TampilPenggunaActivity.this, TableActivity.class);
				startActivity(i);
				finish();
			}

		});
		
    }
}
	
