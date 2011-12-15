package mileline.tasks;

import java.util.ArrayList;

import mileline.model.TimeStone;
import mileline.views.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SeznamTimeStonuAdapter extends BaseAdapter {
	private static ArrayList<TimeStone> searchArrayList;

	private LayoutInflater mInflater;

	
	public SeznamTimeStonuAdapter(Context context, ArrayList<TimeStone> results) {
		searchArrayList = results;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return searchArrayList.size();
	}

	public Object getItem(int position) {
		return searchArrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.timestone_list, null);
			holder = new ViewHolder();
			holder.nazev = (TextView) convertView.findViewById(R.id.nazev);
			holder.kod = (TextView) convertView.findViewById(R.id.kod);
			holder.typ = (TextView) convertView.findViewById(R.id.typ);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.nazev.setText(searchArrayList.get(position).getNazev());
		holder.kod.setText(searchArrayList.get(position).getKod());
		holder.typ.setText(searchArrayList.get(position).getTyp()
				.toString());

		return convertView;
	}

	static class ViewHolder {
		TextView nazev;
		TextView kod;
		TextView typ;
	}
}
