package com.mv.remainder;


import java.util.ArrayList;
import java.util.HashMap;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;

public class ViewRemainder extends ListActivity {
	
	TextView Id;
	
	DBController controller = new DBController(this);
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewremainder);
		ArrayList<HashMap<String, String>> remainderList =  controller.getAllremainders();
		if(remainderList.size()!=0) {
			ListView lv = getListView();
			lv.setOnItemClickListener(new OnItemClickListener() {
				  @Override 
				  public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					  Id = (TextView) view.findViewById(R.id.Id);
					  String valAnimalId = Id.getText().toString();					  
					  //Intent  objIndent = new Intent(getApplicationContext(),EditAnimal.class);
					  //objIndent.putExtra("Id", valAnimalId); 
					  //startActivity(objIndent); 
				  }
			}); 
			ListAdapter adapter = new SimpleAdapter( ViewRemainder.this,remainderList, R.layout.viewremainder_entry, new String[] { "Id","Name","Events","Phoneno"}, new int[] {R.id.Id, R.id.Name,R.id.Events,R.id.Dob}); 
			setListAdapter(adapter);
		}
}
}
