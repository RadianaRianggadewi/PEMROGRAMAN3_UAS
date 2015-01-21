package com.example.catalogscluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyListActivity extends Activity {
	 
    // Array of strings storing country names
    String[] countries = new String[] {
        "Type 21",
        "Type 36",
        "Type 45",
        "Type 54",
        "Type 60",
        "Type 70"
    };
 
    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags = new int[]{
        R.drawable.type21,
        R.drawable.type36,
        R.drawable.type45,
        R.drawable.type54,
        R.drawable.type60,
        R.drawable.type70
    };
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
 
        for(int i=0;i<6;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", countries[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }
 
        // Keys used in Hashmap
        String[] from = { "flag","txt"};
 
        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt};
 
        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.gridview_layout, from, to);
 
        // Getting a reference to gridview of MainActivity
        GridView gridView = (GridView) findViewById(R.id.gridview);
 
        // Setting an adapter containing images to the gridview
        gridView.setAdapter(adapter);
        
        gridView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(countries[arg2].equals("Type 21")){
		            Intent intent = new Intent(MyListActivity.this, type21Activity.class);
		            Intent in = getIntent();
		            String user = in.getStringExtra("user");
		            intent.putExtra("user", user);
	            	startActivity(intent);
	            	finish();
                } else if(countries[arg2].equals("Type 36")){
					Intent intent = new Intent(MyListActivity.this, type36Activity.class);
					Intent in = getIntent();
		            String user = in.getStringExtra("user");
		            intent.putExtra("user", user);
	            	startActivity(intent);
	            	finish();
                } else if(countries[arg2].equals("Type 45")){
					Intent intent = new Intent(MyListActivity.this, type45Activity.class);
					Intent in = getIntent();
		            String user = in.getStringExtra("user");
		            intent.putExtra("user", user);
	            	startActivity(intent);
	            	finish();
                } else if(countries[arg2].equals("Type 54")){
					Intent intent = new Intent(MyListActivity.this, type54Activity.class);
					Intent in = getIntent();
		            String user = in.getStringExtra("user");
		            intent.putExtra("user", user);
	            	startActivity(intent);
	            	finish();
                } else if(countries[arg2].equals("Type 60")){
					Intent intent = new Intent(MyListActivity.this, type60Activity.class);
					Intent in = getIntent();
		            String user = in.getStringExtra("user");
		            intent.putExtra("user", user);
	            	startActivity(intent);
	            	finish();
                } else if(countries[arg2].equals("Type 70")){
					Intent intent = new Intent(MyListActivity.this, type70Activity.class);
					Intent in = getIntent();
		            String user = in.getStringExtra("user");
		            intent.putExtra("user", user);
	            	startActivity(intent);
	            	finish();
                }
			}

        });
        
        Button logout = (Button) findViewById(R.id.btnBack);
        
        logout.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MyListActivity.this, MainActivity.class);
            	startActivity(i);
            	finish();
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
		tv.setText(welcome+""+user);
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

}
