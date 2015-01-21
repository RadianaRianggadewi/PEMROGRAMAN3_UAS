package com.uts.catalogcluster;

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
    protected void onCreate(Bundle sis) {
        super.onCreate(sis);
        setContentView(R.layout.home_layout);
        
        Button panel = (Button) findViewById(R.id.btnPanel);
        TextView list = (TextView) findViewById(R.id.btnList);
        
        panel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, TableActivity.class);
            	startActivity(i);
            	
			}
		});
        
        list.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(HomeActivity.this, MyListActivity.class);
				Intent in = getIntent();
				String user = in.getStringExtra("user");
				i.putExtra("user", user);
            	startActivity(i);
			}
		});	
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
}
	
