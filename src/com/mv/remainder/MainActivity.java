package com.mv.remainder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	Intent intent;
	TextView Id;
	private Button btn_addremainder,btn_viewremainder;
	DBController controller = new DBController(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SmsManager smsManager = SmsManager.getDefault();
	
		ArrayList<HashMap<String, String>> animalList =  controller.gettodayremainder();
		if(animalList.size()!=0) {
		    ListView lv = getListView();
			lv.setOnItemClickListener(new OnItemClickListener() {
				  @Override 
				  public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					  Id = (TextView) view.findViewById(R.id.Id);
					  String valId = Id.getText().toString();
					/*  Intent  objIndent = new Intent(getApplicationContext(),EditAnimal.class);
					  objIndent.putExtra("animalId", valAnimalId); 
					  startActivity(objIndent); */ 
				  }
			}); 
			
			ListAdapter adapter = new SimpleAdapter( MainActivity.this,animalList, R.layout.viewremainder_entry, new String[] { "Id","Name","Phoneno"}, new int[] {R.id.Id, R.id.Name,R.id.Dob}); 
			setListAdapter(adapter);
			
			HashMap<String, String> animalList1 =  controller.getreminfo();
			
			Iterator iter = animalList1.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry mEntry = (Map.Entry) iter.next();
				//System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
				
				//String phoneno=animalList1.get("Phoneno"); 
				//Toast.makeText(getApplicationContext(),animalList1.get("Phoneno") ,Toast.LENGTH_LONG).show();
				try
				{
				smsManager.getDefault().sendTextMessage(animalList1.get("Phoneno"), null, "Many More Happy Returns of the day friend..!", null, null);
				Toast.makeText(getApplicationContext(),"Msg Send.!" ,Toast.LENGTH_LONG).show();
				}
				catch(Exception ex)
				{
					Toast.makeText(getApplicationContext(),"Msg Not Send.!" ,Toast.LENGTH_LONG).show();
				}
				//smsManager.sendTextMessage(animalList1.get("Phoneno"), null, "Many More Happy Returns of the day friend..!", null, null);	
			}
			
			
			
			/*Intent sendIntent = new Intent(Intent.ACTION_VIEW);
			//sendIntent.putExtra("sms_body", "default content"); 
			sendIntent.setType("vnd.android-dir/mms-sms");
			startActivity(sendIntent); */  
			
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void addremainder(View v)
	{
		Intent inte=new Intent(this,AddRemainder.class);
		this.startActivity(inte);		
		
	}
	
	public void viewremainder(View v)
	{
		Intent inte=new Intent(this,ViewRemainder.class);
		this.startActivity(inte);
		
		
	}
	

}
