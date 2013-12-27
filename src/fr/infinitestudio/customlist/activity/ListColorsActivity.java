package fr.infinitestudio.customlist.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import fr.infinitestudio.customlist.R;
import fr.infinitestudio.customlist.adapter.ListColorsAdapter;
import fr.infinitestudio.customlist.model.Color;

public class ListColorsActivity extends ListActivity implements OnItemClickListener{

	private static String EXTRA_COLORS_SELECTED = "EXTRA_COLORS_SELECTED";
	
	private List<Color> colors;
	private ArrayList<Color> colorsSelected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		colorsSelected = new ArrayList<Color>();
		colorsSelected = getIntent().getParcelableArrayListExtra(EXTRA_COLORS_SELECTED);
		
		colors = createColors(colorsSelected);
		
		setListAdapter(new ListColorsAdapter(this, R.layout.item_list, colors));
		getListView().setOnItemClickListener(this);
	}

	/**
	 * Fonction appelée au clic d'une des checkbox
	 * @param v
	 */
	public void checkboxHandler(View v) {
		CheckBox cb = (CheckBox) v;
		// On récupère la position à l'aide du tag défini dans l'adapter ListColorsAdapter
		int position = Integer.parseInt(cb.getTag().toString());
		
		// On récupère l'élément Color sur lequel on se trouve
		Color color = (Color)getListView().getItemAtPosition(position);
		color.setChecked(cb.isChecked());
		
		addColorSelected(color);
	}
	
	/**
	 * Fonction appelée lors de la sélection d'un item de la liste
	 * @param arg0
	 * @param view
	 * @param pos
	 * @param id
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int pos, long id) {
		// On coche la checkbox associée à l'élément cliqué
		CheckBox cb = (CheckBox)getListView().getChildAt(pos - getListView().getFirstVisiblePosition()).findViewById(R.id.checkColor);
		cb.toggle();
		
		// On récupère l'élément Color sur lequel on se trouve
		Color color = (Color)getListView().getItemAtPosition(pos);
		color.setChecked(cb.isChecked());
		
		addColorSelected(color);
	}
	
	@Override
	public void onBackPressed() {
		// On revient sur l'activité MainActivity en lui envoyant la liste des couleurs sélectionnées
		Intent intent = new Intent();
		intent.putParcelableArrayListExtra(EXTRA_COLORS_SELECTED, colorsSelected);
		setResult(Activity.RESULT_OK, intent);
		finish();
	}

	/**
	 * Fonction qui ajoute ou retire une couleur de la liste des couleurs sélectionnées
	 * @param color
	 */
	private void addColorSelected(Color color){
		if(colorsSelected.contains(color) && !color.isChecked()){
			colorsSelected.remove(color);
		}else if(!colorsSelected.contains(color) && color.isChecked()){
			colorsSelected.add(color);
		}
	}
	
	/**
	 * Fonction permettant de construire la liste des différentes couleurs
	 * @param colorsSelected
	 * @return
	 */
	private List<Color> createColors(List<Color> colorsSelected){
		List<Color> colors = new ArrayList<Color>();
		colors.add(createColor(1, "#1abc9c", "Turquoise"));
		colors.add(createColor(2, "#2ecc71", "Emerald"));
		colors.add(createColor(3, "#3498db", "Peter River"));
		colors.add(createColor(4, "#9b59b6", "Amethyst"));
		colors.add(createColor(5, "#34495e", "Wet Asphal"));
		colors.add(createColor(6, "#16a085", "Green Sea"));
		colors.add(createColor(7, "#27ae60", "Nephritis"));
		colors.add(createColor(8, "#2980b9", "Belize Hole"));
		colors.add(createColor(9, "#8e44ad", "Wisteria"));
		colors.add(createColor(10, "#2c3e50", "Midnight Blue"));
		colors.add(createColor(11, "#f1c40f", "Sun Flower"));
		colors.add(createColor(12, "#e67e22", "Carrot"));
		colors.add(createColor(13, "#e74c3c", "Alizarin"));
		colors.add(createColor(14, "#ecf0f1", "Clouds"));
		colors.add(createColor(15, "#95a5a6", "Concrete"));
		colors.add(createColor(16, "#f39c12", "Orange"));
		colors.add(createColor(17, "#d35400", "Pumpkin"));
		colors.add(createColor(18, "#c0392b", "Pomegranate"));
		colors.add(createColor(19, "#bdc3c7", "Silver"));
		colors.add(createColor(20, "#7f8c8d", "Asbestos"));
		
		for(Color colorSelected : colorsSelected){
			for(Color color : colors){
				if(colorSelected.equals(color)){
					color.setChecked(true);
					break;
				}
			}
		}
		return colors;
	}
	
	/**
	 * Fonction permettant de créer un objet Color
	 * @param id
	 * @param color
	 * @param label
	 * @return
	 */
	private Color createColor(Integer id, String color, String label){
		Color c = new Color();
		c.setChecked(false);
		c.setColor(color);
		c.setId(id);
		c.setLabel(label);
		return c;
	}
}
