package akand.com.deviceidchecker;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.imeiId);
        TextView textView1 = (TextView) findViewById(R.id.uniqId);
        TextView textView2 = (TextView) findViewById(R.id.macId);
        TextView textView3 = (TextView) findViewById(R.id.complexId);
        TextView textView4 = (TextView) findViewById(R.id.buildId);

        String imei = getIMEI(getApplicationContext());
        String unique = getDeviceUniqueID(this);
        String mac = getMac(getApplicationContext());
        String build = getBuildId();

        String complexId = imei.concat(mac.substring(11,17));

        textView.setText(imei);
        textView1.setText(unique);
        textView2.setText(mac);
        textView3.setText(complexId);
        textView4.setText(build);
    }

    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
        if (deviceId == null) {
            deviceId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        }
        return deviceId;
    }

    public String getDeviceUniqueID(Activity activity){
        String device_unique_id = Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return device_unique_id;
    }

    public String getMac(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress = wInfo.getMacAddress();
        return macAddress;
    }
    public String getBuildId(){
        String id= Build.SERIAL;
        return  id;
    }
}
