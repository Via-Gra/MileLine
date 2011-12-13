package mileline.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import mileline.restclient.RequestMethod;
import mileline.restclient.RestClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class RefreshTimeStonesTask extends AsyncTask<URL, String, String> {
	public Context appContext;
	private ProgressDialog dialog;

	private TextView text;
	private Button butt;

	public RefreshTimeStonesTask(TextView text, Button butt) {
		super();
		this.text = text;
		this.butt = butt;
	}

	@Override
	protected void onPreExecute() {
		this.dialog = ProgressDialog.show(appContext, "",
				"Refreshing TimeStones...");
	}

	@Override
	protected void onPostExecute(String result) {
		this.dialog.cancel();
		// MileLineCzActivity.this.getRefreshButt().setEnabled(true);

		text.setText(result);
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

		// return connect(params[0]);
		// try{
		// HttpURLConnection conn = (HttpURLConnection)
		// params[0].openConnection();
		// conn.connect();
		//
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(conn.getInputStream()));
		//
		// StringBuilder responseText = new StringBuilder();
		// String line = "";
		// while((line=br.readLine()) != null){
		// responseText.append(line);
		// }
		// return connect(params[0]);
		// return responseText.toString();
		// }catch(IOException e){
		// return e.getMessage();
		// }
	}

	/*
	 * This is a test function which will connects to a given rest service and
	 * prints it's response to Android Log with labels "MileLine".
	 */
	public static String connect(URL url) {
		HttpClient httpclient = new DefaultHttpClient();

		// Prepare a request object
		HttpGet httpget = new HttpGet(url.toString());

		// Execute the request
		HttpResponse response;
		try {
			// HttpURLConnection conn = (HttpURLConnection)
			// url.openConnection();
			// conn.connect();

			response = httpclient.execute(httpget);
			// Examine the response status
			Log.i("MileLine", response.getStatusLine().toString());

			// Get hold of the response entity
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			// to worry about connection release

			if (entity != null) {

				// A Simple JSON Response Read
				InputStream instream = entity.getContent();
				String result = convertStreamToString(instream);
				Log.i("MileLine", result);

				// A Simple JSONObject Creation
				JSONObject json = new JSONObject(result);
				Log.i("MileLine", "<jsonobject>\n" + json.toString()
						+ "\n</jsonobject>");

				// // A Simple JSONObject Parsing
				// JSONArray nameArray=json.names();
				// JSONArray valArray=json.toJSONArray(nameArray);
				// for(int i=0;i<valArray.length();i++)
				// {
				// Log.i("MileLine","<jsonname"+i+">\n"+nameArray.getString(i)+"\n</jsonname"+i+">\n"
				// +"<jsonvalue"+i+">\n"+valArray.getString(i)+"\n</jsonvalue"+i+">");
				// }
				//
				// // A Simple JSONObject Value Pushing
				// json.put("sample key", "sample value");
				// Log.i("MileLine","<jsonobject>\n"+json.toString()+"\n</jsonobject>");

				// Closing the input stream will trigger connection release
				instream.close();
				return json.toString();
			}
			return "Empty response";

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}

	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
