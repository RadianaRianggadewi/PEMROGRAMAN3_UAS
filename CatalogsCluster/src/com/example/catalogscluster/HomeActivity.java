package com.example.catalogscluster;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class HomeActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        
        /*Button panel = (Button) findViewById(R.id.btnPanel);
        TextView list = (TextView) findViewById(R.id.btnList);
        
        panel.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(HomeActivity.this, TableActivity.class);
            	startActivity(i);
			}

		});
        
        list.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(HomeActivity.this, MyListActivity.class);
				Intent in = getIntent();
				String user = in.getStringExtra("user");
				i.putExtra("user", user);
            	startActivity(i);
			}

		});*/
    }
    
    @Override
	public void onStart() {
		super.onStart();
		Intent in = getIntent();
		String user = in.getStringExtra("user");
		String welcome = "Selamat datang, ";
		TextView tv = (TextView) findViewById(R.id.user);
		tv.setText(user);
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	Intent in = getIntent();
		String user = in.getStringExtra("user");
		if(user.equals("admin")) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.menu.menu, menu);
		} else {
			MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.menu.user_menu, menu);
		}
        return true;
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent in = getIntent();
		String user = in.getStringExtra("user");
    	switch (item.getItemId()) {
    		case R.id.action_listrumah:
    			Intent i1 = new Intent(HomeActivity.this, MyListActivity.class);
    			i1.putExtra("user", user);
            	startActivity(i1);
    			//Toast.makeText(HomeActivity.this, "List Rumah Selected", Toast.LENGTH_SHORT).show();
                return true;
                
    		case R.id.action_pengguna:
    			Intent i2 = new Intent(HomeActivity.this, TableActivity.class);
    			i2.putExtra("user", user);
            	startActivity(i2);
    			//Toast.makeText(HomeActivity.this, "Pengguna Selected", Toast.LENGTH_SHORT).show();
                return true;
                
    		case R.id.action_cek:
    			Intent i3 = new Intent(HomeActivity.this, InputStokActivity.class);
    			i3.putExtra("user", user);
            	startActivity(i3);
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
	
