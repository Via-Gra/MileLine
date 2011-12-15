package mileline.tasks;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;

import mileline.diskmanager.HardFile;
import mileline.model.TimeStone;
import mileline.restclient.RequestMethod;
import mileline.restclient.RestClient;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RefreshTimeStonesTask extends AsyncTask<URL, String, String> {
	public Context appContext;
	private ProgressDialog dialog;
	private ListView seznamStonu;

	private TextView text;
	private Button butt;

	public RefreshTimeStonesTask(TextView text, Button butt, ListView seznamStonu) {
		super();
		this.text = text;
		this.butt = butt;
		this.seznamStonu = seznamStonu;
	}

	@Override
	protected void onPreExecute() {
		this.dialog = ProgressDialog.show(appContext, "",
				"Refreshing TimeStones...");
	}

	@Override
	protected void onPostExecute(String result) {
		this.dialog.cancel();
		HardFile hf = new HardFile ("all");
		hf.saveData(result);
		
		Gson gson = new Gson();
		
//		TimeStone stone = gson.fromJson(result, TimeStone.class);
		
		Type collectionType = new TypeToken<ArrayList<TimeStone>>(){}.getType();
		ArrayList<TimeStone> timeStones = (ArrayList<TimeStone>) gson.fromJson(result, collectionType);
		
		seznamStonu.setAdapter(new SeznamTimeStonuAdapter(appContext, timeStones));
		
		StringBuilder sb = new StringBuilder();
//		sb.append(stone.getNazev()).append("\n");
//		sb.append("Mile: \n");
		
//		for (MileStone ms : stone.getMileStony()) {
//			sb.append(ms.getNazev()+" "+ms.getPoznamka()+"\n");
//		}
		
		for (TimeStone timeStone : timeStones) {
			sb.append(timeStone.getNazev()).append(",");
		}
	/*	
		JSONTokener a = new JSONTokener(result);
		try {
			text.setText(((TimeStone) a.nextValue()).getNazev());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		text.setText(sb.toString());
		butt.setEnabled(true);
	}

	@Override
	protected String doInBackground(URL... params) {
		String responseString = null;

		String baseurlString = params[0].toString();

		RestClient client = new RestClient(baseurlString);
//		client.AddParam("appid", "YahooDemo");
//		client.AddParam("output", "json");

		try {
			client.Execute(RequestMethod.GET);
		} catch (Exception e) {
			e.printStackTrace();
		}

		responseString = client.getResponse();

		return responseString;
	}

}
