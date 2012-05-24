
package jp.nayutaya.ncommons.android.map;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class ColorOverlay extends Overlay
{
    private final Paint paint;
    private boolean visible;

    public ColorOverlay()
    {
        this.paint = new Paint();
        this.setVisible(false);
    }

    public ColorOverlay(final Paint paint)
    {
        this.paint = paint;
        this.setVisible(false);
    }

    public Paint getPaint()
    {
        return this.paint;
    }

    public boolean isVisible()
    {
        return this.visible;
    }

    public void setVisible(final boolean visible)
    {
        this.visible = visible;
    }

    @Override
    public void draw(final Canvas canvas, final MapView mapView, final boolean shadow)
    {
        if ( this.isVisible() && shadow )
        {
            canvas.drawRect(0.0f, 0.0f, mapView.getWidth(), mapView.getHeight(), this.getPaint());
        }
    }
}
