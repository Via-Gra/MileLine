package mileline.restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

public class MileLineClient extends AsyncTask<URL, String, String>{

	private TextView text;
	private Button butt;

	
	public MileLineClient(TextView text, Button butt) {
		super();
		this.text = text;
		this.butt = butt;
	}

	@Override
	protected void onPostExecute(String result){
		super.onPostExecute(result);
		text.setText(result);
		butt.setEnabled(true);
	}

	@Override
	protected String doInBackground(URL... params) {
		try{
			HttpURLConnection conn = (HttpURLConnection) params[0].openConnection();
			conn.connect();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			StringBuilder responseText = new StringBuilder();
			String line = "";
			while((line=br.readLine()) != null){
				responseText.append(line);
			}
			
			return responseText.toString();
		}catch(IOException e){
			return e.getMessage();
		}
	}

}
