package fr.infinitestudio.customlist.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import fr.infinitestudio.customlist.R;
import fr.infinitestudio.customlist.model.Color;
import fr.infinitestudio.customlist.view.RectangleView;

public class ListColorsAdapter extends ArrayAdapter<Color> {

	private Context context;
	private int resource;

	public ListColorsAdapter(Context context, int textViewResourceId, List<Color> colors) {
		super(context, textViewResourceId, colors);
		resource = textViewResourceId;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ColorsViewHolder colorsViewHolder;
		// Gestion des performances : on "inflate" et on recherche les composants si nécessaire
		if (convertView == null) {
			convertView = View.inflate(context, resource, null);
 
			colorsViewHolder = new ColorsViewHolder();
			colorsViewHolder.text = (TextView)convertView.findViewById(R.id.textColor);
			colorsViewHolder.check = (CheckBox)convertView.findViewById(R.id.checkColor);
			colorsViewHolder.rectangle = (RectangleView)convertView.findViewById(R.id.rectangleColor);

			convertView.setTag(colorsViewHolder);
        } else {
        	colorsViewHolder = (ColorsViewHolder) convertView.getTag();
        }
 
		// On alimente notre objet Color
		Color color = getItem(position);
        if (color != null) {
        	colorsViewHolder.text.setText(color.getLabel());
        	colorsViewHolder.check.setChecked(color.isChecked());
        	colorsViewHolder.check.setTag(position);
        	colorsViewHolder.rectangle.setHexaColor(color.getColor());
        }
		return convertView;
	}
	
	/**
	 * Classe statique permettant d'améliorer les performances lors du parcours de la liste
	 *
	 */
	static class ColorsViewHolder {
		TextView text;
		CheckBox check;
		RectangleView rectangle;
    }
}
