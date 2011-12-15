package mileline.tasks;

import java.util.ArrayList;
import java.util.Date;

import mileline.model.MileStone;
import mileline.model.TimeStone;
import mileline.views.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SeznamMileStonuAdapter extends BaseAdapter {
	private static ArrayList<MileStone> searchArrayList;

	private LayoutInflater mInflater;

	public static ArrayList<MileStone> sortByDate(ArrayList<MileStone> list) {
		ArrayList<MileStone> vysledek = new ArrayList<MileStone>();
		MileStone nejmensi = null;

		do {
			for (MileStone stone : list) {
				if (nejmensi == null
						|| nejmensi.getDatum().after(stone.getDatum())) {
					nejmensi = stone;
				}
			}
			vysledek.add(nejmensi);
			list.remove(nejmensi);
			nejmensi = null;
		} while (!list.isEmpty());
		return vysledek;
	}

	public SeznamMileStonuAdapter(Context context, ArrayList<TimeStone> results) {
		ArrayList<MileStone> mileStony = new ArrayList<MileStone>();
		for (TimeStone time : results) {
			for (MileStone mile : time.getMileStony()) {
				mileStony.add(mile);
			}
		}
		searchArrayList = sortByDate(mileStony);

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
			convertView = mInflater.inflate(R.layout.milestone_list, null);
			holder = new ViewHolder();
			holder.nazev = (TextView) convertView.findViewById(R.id.nazev);
			holder.datum = (TextView) convertView.findViewById(R.id.datum);
			holder.predmet = (TextView) convertView.findViewById(R.id.predmet);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.nazev.setText(searchArrayList.get(position).getNazev());
		Date date = searchArrayList.get(position).getDatum();
		holder.datum.setText(date.getDay() + ". " + date.getMonth() + ". "
				+ date.getYear() + " " + date.getHours() + ":"
				+ date.getMinutes());
		if (searchArrayList.get(position).getTimeStone() != null)
			holder.predmet.setText(searchArrayList.get(position).getTimeStone()
					.getKod());
		return convertView;
	}

	static class ViewHolder {
		TextView nazev;
		TextView datum;
		TextView predmet;
	}
}
