package fr.infinitestudio.customlist.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import fr.infinitestudio.customlist.R;
import fr.infinitestudio.customlist.model.Color;

public class MainActivity extends Activity implements OnClickListener{

	private static int REQUEST_CODE = 1;
	private static String EXTRA_COLORS_SELECTED = "EXTRA_COLORS_SELECTED";
	
	private ArrayList<Color> colorsSelected;
	
	private TextView infosColorsSelected;
	private Button button;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		
		colorsSelected = new ArrayList<Color>();
		
		infosColorsSelected = (TextView)findViewById(R.id.textInfos);
		button = (Button)findViewById(R.id.btnChooseColors);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View paramView) {
		// On appelle l'activité ListColorsActivity en lui envoyant la liste des couleurs sélectionnées précédemment
		Intent intent = new Intent(context, ListColorsActivity.class);
		intent.putParcelableArrayListExtra(EXTRA_COLORS_SELECTED, colorsSelected);
		startActivityForResult(intent, REQUEST_CODE);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// On teste le retour de l'activité ListColorsActivity
		if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
			// On construit le message à afficher suivant les couleurs sélectionnées
			colorsSelected = data.getParcelableArrayListExtra(EXTRA_COLORS_SELECTED);
			if(colorsSelected.isEmpty()){
				infosColorsSelected.setText(R.string.emptyColors);
			}else{
				StringBuffer buffer = new StringBuffer();
				for(int i = 0; i < colorsSelected.size(); i++){
					if(i != 0){
						buffer.append(", ");
					}
					buffer.append(colorsSelected.get(i).getLabel());
				}
				infosColorsSelected.setText(String.format(getResources().getString(R.string.colorsSelected), buffer));
			}
		}
	}
}
