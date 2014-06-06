package com.mv.remainder;

import java.util.Calendar;
import java.util.HashMap;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


public class AddRemainder extends Activity {
	
	private Spinner spinner1;
	private Button btnSubmit,getdate;
	private DatePicker dpResult;
	private TextView tvDisplayDate,udate;
	EditText Name,Phone,Dobs,Email;
	private int year;
	private int month;
	private int day;
	static final int DATE_DIALOG_ID = 999;
	static final int DATE_PICKER_ID = 1111; 
	DBController controller = new DBController(this);
	String item="";
	String gg="Common";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addremainder);
		
	      Name = (EditText) findViewById(R.id.editText_name);
	     Phone = (EditText) findViewById(R.id.editText_phoneno);      
	     Email = (EditText) findViewById(R.id.editText_email);
	      udate = (TextView) findViewById(R.id.textView_date);
	        getdate = (Button) findViewById(R.id.btn_getdate);
	        
	        final Calendar c = Calendar.getInstance();
	        year  = c.get(Calendar.YEAR);
	        month = c.get(Calendar.MONTH);
	        day   = c.get(Calendar.DAY_OF_MONTH);
	        
	        udate.setText(new StringBuilder()
            // Month is 0 based, just add 1
            .append(month + 1).append("-").append(day).append("-")
            .append(year).append(" "));
	        
	        getdate.setOnClickListener(new OnClickListener() {
	        	 
	            public void onClick(View v) {
	                 
	                // On button click show datepicker dialog 
	            	showDialog(DATE_PICKER_ID);
	 
	            }
	 
	        });        
	        
	       spinner1=(Spinner) findViewById(R.id.spinner1);
		  spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
		  
		@Override
			public void onItemSelected(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				 // On selecting a spinner item
	              item = adapter.getItemAtPosition(position).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}});
		 
	}


protected Dialog onCreateDialog(int id) {
    switch (id) {
    case DATE_PICKER_ID:
         
        // open datepicker dialog. 
        // set date picker for current date 
        // add pickerListener listner to date picker
        return new DatePickerDialog(this, pickerListener, year, month,day);
    }
    return null;
}
private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
	 
    // when dialog box is closed, below method will be called.
    @Override
    public void onDateSet(DatePicker view, int selectedYear,
            int selectedMonth, int selectedDay) {
         
        year  = selectedYear;
        month = selectedMonth;
        day   = selectedDay;

        // Show selected date 
        udate.setText(new StringBuilder().append(month + 1)
                .append("-").append(day).append("-").append(year)
                .append(" "));
 
       }
    }; 

	public void addremainder(View view) {
		
		
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		queryValues.put("Name", Name.getText().toString());
		queryValues.put("Phoneno", Phone.getText().toString());
		queryValues.put("Days", String.valueOf(day));
		queryValues.put("Months", String.valueOf(month));
		queryValues.put("Years", String.valueOf(year));
		queryValues.put("Events", item.toString());
		queryValues.put("Dobs", udate.getText().toString());
		queryValues.put("Groups", gg);
		queryValues.put("Emails", Email.getText().toString());	
		controller.insertAnimal(queryValues);
		this.callHomeActivity(view);
	}
	
	public void callHomeActivity(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(objIntent);
	}	
	}
