
package jp.nayutaya.ncommons.android.map;

import android.view.GestureDetector;
import android.view.MotionEvent;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class DoubleTapZoomOverlay extends Overlay implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener
{
    private final GestureDetector gestureDetector;
    private MapView mapView;
    private boolean enable;

    public DoubleTapZoomOverlay()
    {
        this.gestureDetector = new GestureDetector(this);
        this.mapView = null;
        this.setEnable(false);
    }

    public boolean isEnable()
    {
        return this.enable;
    }

    public void setEnable(final boolean enable)
    {
        this.enable = enable;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent e, final MapView mapView)
    {
        this.mapView = mapView;
        if ( this.isEnable() && this.gestureDetector.onTouchEvent(e) )
        {
            return true;
        }
        else
        {
            return super.onTouchEvent(e, mapView);
        }
    }

    public boolean onDown(final MotionEvent e)
    {
        return false;
    }

    public boolean onFling(final MotionEvent e1, final MotionEvent e2, final float velocityX, final float velocityY)
    {
        return false;
    }

    public void onLongPress(final MotionEvent e)
    {
    }

    public boolean onScroll(final MotionEvent e1, final MotionEvent e2, final float distanceX, final float distanceY)
    {
        return false;
    }

    public void onShowPress(final MotionEvent e)
    {
    }

    public boolean onSingleTapUp(final MotionEvent e)
    {
        return false;
    }

    public boolean onDoubleTap(final MotionEvent e)
    {
        return false;
    }

    public boolean onDoubleTapEvent(final MotionEvent e)
    {
        if ( this.isEnable() && this.mapView != null )
        {
            final int x = (int)e.getX();
            final int y = (int)e.getY();
            final MapController mapController = this.mapView.getController();
            mapController.zoomInFixing(x, y);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean onSingleTapConfirmed(final MotionEvent e)
    {
        return false;
    }
}
