package com.app.imsopopular;

import com.app.imsopopular.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnClickListener, OnItemSelectedListener{
	Intent service;
	Button startButton;
	Button stopButton;
	Spinner timeSpinner;
	protected static boolean stopped = false;
	protected static int time = 10;
	boolean alreadyStarted = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(this);
		stopButton = (Button) findViewById(R.id.stopButton);
		stopButton.setOnClickListener(this);
		timeSpinner = (Spinner) findViewById(R.id.time_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.time_arrays, R.layout.spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(R.layout.spinner_item);
		// Apply the adapter to the spinner
		timeSpinner.setAdapter(adapter);
		timeSpinner.setOnItemSelectedListener(this);
		timeSpinner.setSelection(1);
		
		
		
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case(R.id.startButton):
			start();
		

		/*
		PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);

		AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		// Start every 30 seconds
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 30*1000, pintent);*/  
		break;

		case(R.id.stopButton):
			//Log.d("Main", "Stopping Service");
		stop();
		break;
		default:
			break;


		}


	}
	
	private void start(){
		stopped =false;
		if(!alreadyStarted){
			alreadyStarted = true;
			service = new Intent(this, VibrateService.class);
			//Log.d("Main", "Starting Service");
			startService(service);
		}
	}
	
	private void stop(){
		stopped = true;
		alreadyStarted = false;

		if(service!=null){
			stopService(service);
		}

		
	}
		
	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		stop();
		switch(pos){
		case(0):
			time =5;
			break;
		case(1):
			time =10;
			break;
		case(2):
			time =30;
			break;
		case(3):
			time=60;
			break;
		case(4):
			time=300;
			break;
		case(5):
			time=600;
			break;
		case(6):
			time=1800;
			break;
		default:
			return;
		
		}
		start();
		
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
