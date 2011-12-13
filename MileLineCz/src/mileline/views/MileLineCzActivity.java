package mileline.views;

import java.net.MalformedURLException;
import java.net.URL;

import mileline.restclient.MileLineClient;
import mileline.restclient.MileLineURLCreator;
import mileline.tasks.RefreshTimeStonesTask;
import mileline.views.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MileLineCzActivity extends Activity {
	private TextView timeStoneText;
	private Button refreshButt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		timeStoneText = (TextView) findViewById(R.id.deadLineText);
		
		refreshButt = (Button) findViewById(R.id.refreshButton);
		
		refreshButt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String url = "";
				url = MileLineURLCreator.getTimeStoneByIdURL("P");
				
				refreshButt.setEnabled(false);
				timeStoneText.setText("Updating");
				
				RefreshTimeStonesTask task = new RefreshTimeStonesTask(timeStoneText, refreshButt);
				task.appContext = MileLineCzActivity.this;
				try {
					task.execute(new URL(url));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				
//				try{
//					(new MileLineClient(timeStoneText,refreshButt)).execute(new URL(url));
//				}catch(MalformedURLException e){
//					e.printStackTrace();
//				}
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.optionText: // Toast.makeText(this, "You pressed Options!",
								// Toast.LENGTH_LONG).show();
			setContentView(R.layout.options);
			break;
		case R.id.aboutText: // Toast.makeText(this, "You pressed About!",
								// Toast.LENGTH_LONG).show();
			setContentView(R.layout.about);
			break;
		case R.id.exitText: // Toast.makeText(this, "You pressed Exit!",
							// Toast.LENGTH_LONG).show();
			moveTaskToBack(true);
			break;
		}
		return true;
	}

	public void mainClickHandler(View v) {
		if (((Button) v).isPressed()) {
			setContentView(R.layout.main);
		}
	}
	
	public static String refreshTimeStones(){
		return "";
	}
	
	public void refreshButtonClickHandler(View v){
		String url = "";
		url = MileLineURLCreator.getTimeStoneByIdURL("P");
		
		refreshButt.setEnabled(false);
		timeStoneText.setText("Updating");
//		try{
//			(new MileLineClient(timeStoneText,refreshButt)).execute(new URL(url));
//		}catch(MalformedURLException e){
//			e.printStackTrace();
//		}
	}


	public TextView getTimeStoneText() {
		return timeStoneText;
	}


	public void setTimeStoneText(TextView timeStoneText) {
		this.timeStoneText = timeStoneText;
	}


	public Button getRefreshButt() {
		return refreshButt;
	}


	public void setRefreshButt(Button refreshButt) {
		this.refreshButt = refreshButt;
	}
	
	
}