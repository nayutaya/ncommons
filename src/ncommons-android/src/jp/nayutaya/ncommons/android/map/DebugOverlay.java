
package jp.nayutaya.ncommons.android.map;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class DebugOverlay extends Overlay
{
    private final Paint paint;
    private boolean visible;

    public DebugOverlay()
    {
        this.paint = new Paint();
        this.paint.setColor(Color.RED);
        this.paint.setTextSize(10.0f);
        this.paint.setTypeface(Typeface.MONOSPACE);
        this.setVisible(false);
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
        if ( !this.isVisible() || shadow )
        {
            return;
        }

        final int w = mapView.getWidth();
        final int h = mapView.getHeight();
        final Projection projection = mapView.getProjection();
        final GeoPoint c = mapView.getMapCenter();
        final GeoPoint nw = projection.fromPixels(0, 0);
        final GeoPoint se = projection.fromPixels(w, h);

        final int x = 10;
        int y = 20;
        canvas.drawText("Center: " + c.getLatitudeE6() + "," + c.getLongitudeE6(), x, y, this.paint);
        y += 10;
        canvas.drawText("North: " + nw.getLatitudeE6(), x, y, this.paint);
        y += 10;
        canvas.drawText("South: " + se.getLatitudeE6(), x, y, this.paint);
        y += 10;
        canvas.drawText("West: " + nw.getLongitudeE6(), x, y, this.paint);
        y += 10;
        canvas.drawText("East: " + se.getLongitudeE6(), x, y, this.paint);
        y += 10;
        canvas.drawText("Zoom: " + mapView.getZoomLevel(), x, y, this.paint);
    }
}
