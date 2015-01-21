package com.example.catalogscluster;

import com.example.catalogscluster.models.RumahDataSource;
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

public class InputStokActivity extends Activity {

	TableLayout table_layout;
	EditText firstname_et, lastname_et, txtnama, txtstok, txtdesc;
	Button addmem_btn, tambah;

	TextView tv;

	RumahDataSource sqlcon;

	ProgressDialog PD;



	@Override
	protected void onCreate(Bundle au) {
		super.onCreate(au);
		setContentView(R.layout.input_stok);

		sqlcon = new RumahDataSource(this);
		
		txtnama = (EditText) findViewById(R.id.editText1);
		txtstok = (EditText) findViewById(R.id.editText2);
		txtdesc = (EditText) findViewById(R.id.editText3);

		tambah = (Button) findViewById(R.id.viewmem_btn_id);
		
		tambah.setOnClickListener(new OnClickListener() {
			String nama = txtnama.getText().toString();
			String stok = txtstok.getText().toString();
			String desc = txtdesc.getText().toString();
			
			public void onClick(View v) {
				String nama = txtnama.getText().toString();
				String stok = txtstok.getText().toString();
				String desc = txtdesc.getText().toString();
				
				sqlcon.insertData(nama,stok,desc);
                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
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
				tv.setBackgroundResource(R.drawable.cellborder);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setPadding(0, 5, 0, 5);

				tv.setText(c.getString(j));

				row.addView(tv);

				row.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						TableRow tr1=(TableRow)v;
						TextView tv1= (TextView)tr1.getChildAt(1);
						TextView tv2= (TextView)tr1.getChildAt(2);
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

			PD = new ProgressDialog(InputStokActivity.this);
			PD.setTitle("Please Wait..");
			PD.setMessage("Loading...");
			PD.setCancelable(false);
			PD.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			BuildTable();
			PD.dismiss();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			String firstname = firstname_et.getText().toString();
			sqlcon.open();
			//sqlcon.deleteData(firstname);
			return null;
		}
	}


}
