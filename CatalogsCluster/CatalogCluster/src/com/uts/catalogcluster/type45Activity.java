package com.uts.catalogcluster;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class type45Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type45_layout);
        
        Button logout = (Button) findViewById(R.id.btnBack);
        Button detail = (Button) findViewById(R.id.button2);
        Button back = (Button) findViewById(R.id.button3);
        Button contact = (Button) findViewById(R.id.button4);

        logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(type45Activity.this, MainActivity.class);
            	startActivity(i);
            	finish();
			}
		});
        
        detail.setOnClickListener(new View.OnClickListener() {
        	TextView txt1 = (TextView) findViewById(R.id.user);
        	TextView txt2 = (TextView) findViewById(R.id.textView3);
        	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				txt1.setVisibility(View.VISIBLE);
				txt2.setVisibility(View.VISIBLE);

			}
		});
        
        back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(type45Activity.this, MyListActivity.class);
				Intent in = getIntent();
	            String user = in.getStringExtra("user");
	            i.putExtra("user", user);
            	startActivity(i);
            	finish();
			}
		});
        
        contact.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new
	        			Intent(Intent.ACTION_DIAL, 
	        				Uri.parse("tel:+651234567"));
	        		startActivity(i);
			}
		});
    }

    @Override
	public void onStart() {
		super.onStart();
		Intent i = getIntent();
		String user = i.getStringExtra("user");
		String welcome = "Selamat datang, ";
		TextView tv = (TextView) findViewById(R.id.TextView01);
		tv.setText(welcome+""+user);
	}

}
