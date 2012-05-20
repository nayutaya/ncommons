
package jp.nayutaya.ncommons.android.map;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.maps.MapView;

public class MapViewFactory
{
    public static MapView createMapView(final Context context, final Map<String, String> apiKeyMap)
    {
        final String apiKey = getApiKey(context, apiKeyMap);
        final MapView mapView = new MapView(context, apiKey);
        return mapView;
    }

    private static String getApiKey(final Context context, final Map<String, String> apiKeyMap)
    {
        final String fingerprint = getFingerPrintOfPackageSignature(context);
        if ( apiKeyMap.containsKey(fingerprint) )
        {
            return apiKeyMap.get(fingerprint);
        }
        else
        {
            throw new RuntimeException("unknown fingerprint: " + fingerprint);
        }
    }

    private static String getFingerPrintOfPackageSignature(final Context context)
    {
        final byte[] signature = getPackageSignature(context);
        final byte[] digest = digestBytesByMD5(signature);
        return formatBytesByHex(digest);
    }

    private static byte[] getPackageSignature(final Context context)
    {
        try
        {
            final PackageManager manager = context.getPackageManager();
            final PackageInfo info = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            final Signature signature = info.signatures[0];
            return signature.toByteArray();
        }
        catch ( NameNotFoundException e )
        {
            return null;
        }
    }

    private static byte[] digestBytesByMD5(final byte[] message)
    {
        try
        {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            return digest.digest(message);
        }
        catch ( NoSuchAlgorithmException e )
        {
            return null;
        }
    }

    private static String formatBytesByHex(final byte[] values)
    {
        final StringBuilder builder = new StringBuilder();
        for ( final byte value : values )
        {
            builder.append(String.format("%02X", value));
        }
        return builder.toString();

    }
}
