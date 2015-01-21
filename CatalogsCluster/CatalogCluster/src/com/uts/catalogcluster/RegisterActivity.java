package com.uts.catalogcluster;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	EditText txtNama, txtKtp, txtAlamat, txtHp, txtEmail, txtUser, txtPass;
	Button Submit, Cancel;
	UserDataSource userdata;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        
        // get Instance  of Database Adapter
        userdata = new UserDataSource(this);
        userdata.open();
        
        txtNama = (EditText)findViewById(R.id.inputNama);
        txtKtp = (EditText)findViewById(R.id.inputKtp);
        txtAlamat = (EditText)findViewById(R.id.inputAlamat);
        txtHp = (EditText)findViewById(R.id.inputHp);
        txtEmail = (EditText)findViewById(R.id.inputEmail);
        txtUser = (EditText)findViewById(R.id.inputUser);
        txtPass = (EditText)findViewById(R.id.inputPass);
        
        Submit = (Button)findViewById(R.id.btnSubmit);
        Submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String nama   = txtNama.getText().toString();
				String ktp    = txtKtp.getText().toString();
				String alamat = txtAlamat.getText().toString();
				String hp     = txtHp.getText().toString();
				String email  = txtEmail.getText().toString();
				String user	  = txtUser.getText().toString();
				String pass   = txtPass.getText().toString();
				
				String username=userdata.getUsername(user);
				
				if(nama.equals("") || ktp.equals("") || alamat.equals("") || hp.equals("") || email.equals("") || pass.equals("") || user.equals("")) {
					Toast.makeText(getApplicationContext(), "Field Kosong", Toast.LENGTH_LONG).show();
                    return;
				} else {
					if(nama.equals(username)) {
						Toast.makeText(getApplicationContext(), "Account Already Registered ", Toast.LENGTH_LONG).show();
					} else {
						userdata.create(nama,ktp,alamat,hp,email,user,pass);
		                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
		                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
						startActivity(i);
						finish();
					}
					
				}
				
			}
        	
        });
        
        Cancel = (Button)findViewById(R.id.btnCancel);
        Cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(RegisterActivity.this, MainActivity.class);
				startActivity(i);
				finish();
			}
        	
        });
    }

}
