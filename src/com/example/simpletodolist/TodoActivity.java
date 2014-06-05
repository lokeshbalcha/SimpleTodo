package com.example.simpletodolist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TodoActivity extends Activity {
	private static final int REQUEST_CODE = 0;
	ArrayList<String> items;
	ArrayAdapter<String> itemsAdapter;
	ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        lvItems.setAdapter(itemsAdapter);
        //items.add("Item one");
        //items.add("Item two");
        setupListViewListener();
    }

	
	private void setupListViewListener() {
		lvItems.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long rowId) {
				items.remove(position);
				itemsAdapter.notifyDataSetChanged();
				saveItems();
				return true;
			}
		});
	
		lvItems.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent intent = new Intent(TodoActivity.this, EditActivity.class);
				intent.putExtra("pos", position);
				Log.d("asdw", position+"");
				intent.putExtra("toedit", items.get(position).toString());
		        TodoActivity.this.startActivityForResult(intent, 0);
				}
		});

			}

	public void addTodoItem(View v){
		EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
		itemsAdapter.add(etNewItem.getText().toString());
		etNewItem.setText("");
		saveItems();
		
	}
	
	private void saveItems() {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			FileUtils.writeLines(todoFile, items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void readItems(){
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			items = new ArrayList<String>(FileUtils.readLines(todoFile));
		} catch (IOException e) {
			items = new ArrayList<String>();
			e.printStackTrace();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  // REQUEST_CODE is defined above
		//Log.d("asdw", "here");
	  if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
		  
	     String editItemText = data.getExtras().getString("edited");
	     int position = data.getExtras().getInt("position");
	     Log.d("asdw", position+"");
	     items.set(position, editItemText);
		 itemsAdapter.notifyDataSetChanged();
		 saveItems();
	  }
	} 
	
	
}