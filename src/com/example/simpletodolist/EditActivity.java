package com.example.simpletodolist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class EditActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		EditText etEditItem = (EditText)findViewById(R.id.etEditItem);
		etEditItem.setText(getIntent().getStringExtra("toedit"));	
		
	}
	
	
	
}
