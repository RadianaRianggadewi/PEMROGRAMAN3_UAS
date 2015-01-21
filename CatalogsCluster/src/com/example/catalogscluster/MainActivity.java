package com.example.catalogscluster;

import com.example.catalogscluster.models.UserDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	UserDataSource userdata;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        
        userdata = new UserDataSource(this);
        userdata.open();
        
        Button btn1 = (Button) findViewById(R.id.btnSubmit);
        Button btn2 = (Button) findViewById(R.id.btnRegister);
        
        btn1.setOnClickListener(new View.OnClickListener() {
	        final  EditText txtUsername = (EditText) findViewById(R.id.formUsername);
		    final  EditText txtPassword = (EditText) findViewById(R.id.formPassword);
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String userName=txtUsername.getText().toString();
				String password=txtPassword.getText().toString();
				String admin = "";
				
				String storedPassword=userdata.getSinlgeEntry(userName);
				if(userName.equals(admin) && password.equals(admin)) {
					Intent i = new Intent(MainActivity.this, HomeActivity.class);
					i.putExtra("user", "admin");
	            	startActivity(i);
	            	finish();
				} else if(password.equals(storedPassword)) {
					Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
					Intent i = new Intent(MainActivity.this, MyListActivity.class);
					i.putExtra("user", userName);
	            	startActivity(i);
	            	finish();
				} else {
					Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
				}
			}

        });
        /*btn1.setOnClickListener(new View.OnClickListener() {
        	EditText txtUsername = (EditText) findViewById(R.id.formUsername);
        	EditText txtPassword = (EditText) findViewById(R.id.formPassword);
        	TextView err = (TextView) findViewById(R.id.errorMsg);
        	
			@Override
			public void onClick(View v) {
				String admin = "admin";
				String user1 = "imam";
				String user2 = "dewi";
				
				if(txtUsername.getText().toString().equals("") && txtPassword.getText().toString().equals("")) {
					Intent i = new Intent(MainActivity.this, HomeActivity.class);
					i.putExtra("user", admin);
	            	startActivity(i);
				} else if(txtUsername.getText().toString().equals(user1) && txtPassword.getText().toString().equals("123")) {
					Intent i = new Intent(MainActivity.this, MyListActivity.class);
					i.putExtra("user", user1);
	            	startActivity(i);
				} else if(txtUsername.getText().toString().equals(user2) && txtPassword.getText().toString().equals("123")) {
					Intent i = new Intent(MainActivity.this, MyListActivity.class);
					i.putExtra("user", user2);
	            	startActivity(i);
				} else {
					Context context = getApplicationContext();
					CharSequence text = "Username/Password is incorrect";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
			}
		});*/
        
        btn2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            	startActivity(i);
            	finish();
			}
        	
		});
    }
    
}
