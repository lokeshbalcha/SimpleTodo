package com.example.simpletodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends Activity {
	
	EditText etEditItem;
	int pos;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		etEditItem = (EditText)findViewById(R.id.etEditItem);
		pos = getIntent().getIntExtra("pos", -1);
		etEditItem.setText(getIntent().getStringExtra("toedit"));
		etEditItem.setSelection(getIntent().getStringExtra("toedit").length());
		
		
	
	 final Button button = (Button) findViewById(R.id.save);
     button.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
             // Perform action on click   
        	 
        	 EditText etsave = (EditText) findViewById(R.id.etEditItem);
        	 
             Intent sendback = new Intent(EditActivity.this, TodoActivity.class);
             EditedItem(etsave);
             //EditActivity.this.startActivity(sendback);
         }
     });
	}



    public void EditedItem(View v) {
    	Intent i = new Intent();
    	String itemText = etEditItem.getText().toString();
    	i.putExtra("edited", itemText);
		i.putExtra("position", pos);
    	setResult(RESULT_OK, i);
    	finish();
    }

}

