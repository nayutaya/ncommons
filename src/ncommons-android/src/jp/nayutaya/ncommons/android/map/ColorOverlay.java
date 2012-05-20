
package jp.nayutaya.ncommons.android.map;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class ColorOverlay extends Overlay
{
    private final Paint paint;

    public ColorOverlay()
    {
        this.paint = new Paint();
    }

    public ColorOverlay(final Paint paint)
    {
        this.paint = paint;
    }

    public Paint getPaint()
    {
        return this.paint;
    }

    @Override
    public void draw(final Canvas canvas, final MapView mapView, final boolean shadow)
    {
        if ( shadow )
        {
            canvas.drawRect(0.0f, 0.0f, mapView.getWidth(), mapView.getHeight(), this.getPaint());
        }
    }
}
