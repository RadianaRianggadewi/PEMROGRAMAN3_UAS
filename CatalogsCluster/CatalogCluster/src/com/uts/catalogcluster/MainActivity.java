package com.uts.catalogcluster;

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
		    
		    @Override
			public void onClick(View v) {
		    	String userName=txtUsername.getText().toString();
				String password=txtPassword.getText().toString();
				String admin = "";
				
				String storedPassword=userdata.getSinlgeEntry(userName);
				if(userName.equals(admin) && password.equals(admin)) {
					Intent i = new Intent(MainActivity.this, HomeActivity.class);
					i.putExtra("user", "admin");
	            	startActivity(i);
				} else if(password.equals(storedPassword)) {
					Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
					Intent i = new Intent(MainActivity.this, MyListActivity.class);
					i.putExtra("user", userName);
	            	startActivity(i);
				} else {
					Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
				}
		    	
		    }
        });
        
        btn2.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            	startActivity(i);
            	finish();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent in = getIntent();
		String user = in.getStringExtra("user");
    	switch (item.getItemId()) {
    		case R.id.action_listrumah:
    			Intent i1 = new Intent(MainActivity.this, MyListActivity.class);
    			i1.putExtra("user", user);
            	startActivity(i1);
    			//Toast.makeText(HomeActivity.this, "List Rumah Selected", Toast.LENGTH_SHORT).show();
                return true;
                
    		case R.id.action_pengguna:
    			Intent i2 = new Intent(MainActivity.this, TableActivity.class);
    			i2.putExtra("user", user);
            	startActivity(i2);
    			//Toast.makeText(HomeActivity.this, "Pengguna Selected", Toast.LENGTH_SHORT).show();
                return true;
                
    		/*case R.id.action_transaksi:
    			//Intent i3 = new Intent(HomeActivity.this, TableActivity.class);
    			//i3.putExtra("user", user);
            	//startActivity(i3);
    			//Toast.makeText(HomeActivity.this, "Transaksi Selected", Toast.LENGTH_SHORT).show();
                return true;*/
                
    		default:
                return super.onOptionsItemSelected(item);
            
    	}
    }
}
