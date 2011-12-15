package mileline.views;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mileline.diskmanager.HardFile;
import mileline.model.MileStone;
import mileline.model.TimeStone;
import mileline.model.Typ;
import mileline.restclient.MileLineURLCreator;
import mileline.tasks.RefreshTimeStonesTask;
import mileline.tasks.SeznamMileStonuAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MileLineCzActivity extends Activity {
	private ListView seznamStonu;
	private TextView timeStoneText;
	private Button refreshButt;
	private static MileLineCzActivity self;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		self = this;
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		timeStoneText = (TextView) findViewById(R.id.deadLineText);
		HardFile hf = new HardFile ("all");
		timeStoneText.setText("loaded" + (String)hf.loadData());
		refreshButt = (Button) findViewById(R.id.refreshButton);
		
seznamStonu = (ListView) findViewById(R.id.listStone);
		
		//				data	zacatek
		ArrayList<TimeStone> stony = new ArrayList<TimeStone>();
		stony.add(new TimeStone ("Algoritmy","PAL",Typ.PREDMET));
		stony.add(new TimeStone ("asdfAlgoritmy","AL",Typ.PREDMET));
		stony.add(new TimeStone ("Algorisdfgtmy","L",Typ.PREDMET));
		
		Date date1 = new Date();
		Date date2 = new Date();
		Date date3 = new Date();
		try {		
			DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
			date1 = dfm.parse("2011-12-24 20:15:00 +0200");
			date2 = dfm.parse("2011-12-14 10:15:00 +0200");
			date3 = dfm.parse("2011-12-14 10:25:00 +0200");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		stony.get(0).getMileStony().add(new MileStone ("Vanoce", date1, "Jupii"));
		stony.get(1).getMileStony().add(new MileStone ("Snidane", date2, "Pochoutka"));
		stony.get(1).getMileStony().add(new MileStone ("Vecere", date3, "Pochoutka"));
		//			data	konec
		
		seznamStonu.setAdapter(new SeznamMileStonuAdapter(this, stony));
		
		seznamStonu.setTextFilterEnabled(true);

		  seznamStonu.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		      // When clicked, show a toast with the TextView text
		      Toast.makeText(getApplicationContext(), ((TextView) view.findViewById(R.id.nazev)).getText(),//((TextView) view).getTag().toString()
		          Toast.LENGTH_SHORT).show();
		    }
		  });
		
		refreshButt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String url = "";
				url = MileLineURLCreator.getAllTimeStonesURL();
//				url = MileLineURLCreator.getTimeStoneByIdURL("1002");
				
				refreshButt.setEnabled(false);
				timeStoneText.setText("Updating");
				
				RefreshTimeStonesTask task = new RefreshTimeStonesTask(timeStoneText, refreshButt, seznamStonu);
				task.appContext = MileLineCzActivity.this;
				try {
					task.execute(new URL(url));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	public static MileLineCzActivity getSelf  () {
		return self;
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
			onCreate(null);
		}
	}
	
	public static String refreshTimeStones(){
		return "";
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