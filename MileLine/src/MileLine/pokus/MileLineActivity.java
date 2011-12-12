package MileLine.pokus;

import java.net.MalformedURLException;
import java.net.URL;

import mileline.restclient.MileLineClient;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MileLineActivity extends Activity {
	/** Called when the activity is first created. */
	private TextView timeStoneText;
	private Button refreshButt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		timeStoneText = (TextView) findViewById(R.id.deadLineText);
		refreshButt = (Button) findViewById(R.id.refreshButton);
		setContentView(R.layout.main);
		
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
	
	public void refreshButtonClickHandler(View v){
		String params = null;
		
		refreshButt.setEnabled(false);
		timeStoneText.setText("Updating");
		try{
			(new MileLineClient(timeStoneText,refreshButt)).execute(new URL(params));
		}catch(MalformedURLException e){
			e.printStackTrace();
		}
	}

}