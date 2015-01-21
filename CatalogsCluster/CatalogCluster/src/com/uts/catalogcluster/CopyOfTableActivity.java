package com.uts.catalogcluster;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class CopyOfTableActivity extends Activity {

	TableLayout table_layout;
	EditText firstname_et, lastname_et;
	Button addmem_btn, viewmem_btn;

	TextView tv;

	UserDataSource sqlcon;

	ProgressDialog PD;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_layout2);

		sqlcon = new UserDataSource(this);

		firstname_et = (EditText) findViewById(R.id.fistname_et_id);
		viewmem_btn = (Button) findViewById(R.id.viewmem_btn_id);
		addmem_btn = (Button) findViewById(R.id.addmem_btn_id);
		table_layout = (TableLayout) findViewById(R.id.tableLayout1);
		//firstname_et.setVisibility(View.GONE);

		sqlcon.open();
		Cursor c = sqlcon.readEntry();

		int rows = c.getCount();
		int cols = c.getColumnCount();

		c.moveToFirst();

		BuildTable();

		addmem_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(firstname_et.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),"You must select row..",Toast.LENGTH_LONG).show();
				} else {
					new MyAsync().execute();
				}
			}


		});
		
		viewmem_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(firstname_et.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),"You must select row..",Toast.LENGTH_LONG).show();
				} else {
					String firstname = firstname_et.getText().toString();
					Intent inte = new Intent(CopyOfTableActivity.this, TampilPenggunaActivity.class);
					inte.putExtra("user", firstname);
	            	startActivity(inte);
	            	finish();
				}
			}
			
		});

	}

	private void BuildTable() {

		sqlcon.open();
		Cursor c = sqlcon.readEntry();

		int rows = c.getCount();
		int cols = c.getColumnCount();

		c.moveToFirst();

		// outer for loop
		for (int i = 0; i < rows; i++) {

			TableRow row = new TableRow(this);
			row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
			LayoutParams.WRAP_CONTENT));

			// inner for loop
			for (int j = 0; j < cols; j++) {

				tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
				//tv.setBackgroundResource(R.drawable.cell_shape);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setPadding(0, 5, 0, 5);

				tv.setText(c.getString(j));

				row.addView(tv);

				row.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						TableRow tr1=(TableRow)v;
						TextView tv1= (TextView)tr1.getChildAt(0);
						TextView tv2= (TextView)tr1.getChildAt(1);
						String a = tv2.getText().toString();
						//tr1.setBackgroundColor(Color.BLACK);
						firstname_et.setText(a);
						Toast.makeText(getApplicationContext(),tv1.getText().toString()+" Selected",Toast.LENGTH_LONG).show();

					}
				});

			}

			c.moveToNext();

			table_layout.addView(row);

		}
		sqlcon.close();

	}


	private class MyAsync extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();

			table_layout.removeAllViews();

			PD = new ProgressDialog(CopyOfTableActivity.this);
			PD.setTitle("Please Wait..");
			PD.setMessage("Loading...");
			PD.setCancelable(false);
			PD.show();
		}

		@Override
		protected Void doInBackground(Void... params) {

			String firstname = firstname_et.getText().toString();
			String lastname = lastname_et.getText().toString();

			// inserting data
			sqlcon.open();
			sqlcon.deleteData(firstname);
			// BuildTable();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			BuildTable();
			PD.dismiss();
		}
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
    			Intent i1 = new Intent(CopyOfTableActivity.this, MyListActivity.class);
    			i1.putExtra("user", user);
            	startActivity(i1);
    			//Toast.makeText(HomeActivity.this, "List Rumah Selected", Toast.LENGTH_SHORT).show();
                return true;
                
    		case R.id.action_pengguna:
    			Intent i2 = new Intent(CopyOfTableActivity.this, CopyOfTableActivity.class);
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
