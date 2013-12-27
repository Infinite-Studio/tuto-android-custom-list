package fr.infinitestudio.customlist.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class RectangleView extends View{

	private Paint paint = new Paint();
	private String hexaColor;

    public RectangleView(Context context) {
        super(context);
    }

    public RectangleView (Context context, AttributeSet attrs) {
    	super(context, attrs);
    }
    
    @SuppressLint("DrawAllocation")
	@Override
    public void onDraw(Canvas canvas) {
    	// On dessine un rectangle avec un fond de couleur uni défini par hexaColor
        paint.setColor(Color.parseColor(hexaColor));
 
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawRect(rect, paint);
    }

	public void setHexaColor(String hexaColor) {
		this.hexaColor = hexaColor;
	}
}
