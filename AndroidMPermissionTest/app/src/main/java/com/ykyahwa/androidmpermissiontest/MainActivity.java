package com.ykyahwa.androidmpermissiontest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button mBtCallPhone;
    private Button mBtCheckWifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtCallPhone = (Button) findViewById(R.id.BT_CALL_PHONE);
        mBtCallPhone.setOnClickListener(buttonListener);

        mBtCheckWifi = (Button) findViewById(R.id.BT_CHECK_WIFI);
        mBtCheckWifi.setOnClickListener(buttonListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private final int  MY_PERMISSIONS_REQUEST_CALL_PHONE = 1000;

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.BT_CALL_PHONE :

                    if (checkSelfPermission(Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                        return;
                    } else {
                        callPhone();
                    }
                    break;

                case R.id.BT_CHECK_WIFI :
                    checkWifi();
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! do the
                    // calendar task you need to do.
                    callPhone();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "NOT GRANTED", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'switch' lines to check for other
            // permissions this app might request
        }
    }

    private void checkWifi(){
        WifiManager mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        String msg = "";
        switch (mWifiManager.getWifiState()) {
            case WifiManager.WIFI_STATE_DISABLING:
                msg = "WIFI_STATE_DISABLING";
                break;
            case WifiManager.WIFI_STATE_DISABLED:
                msg = "WIFI_STATE_DISABLED";
                break;
            case WifiManager.WIFI_STATE_ENABLING:
                msg = "WIFI_STATE_ENABLING";
                break;
            case WifiManager.WIFI_STATE_ENABLED:
                msg = "WIFI_STATE_ENABLED";
                break;
            case WifiManager.WIFI_STATE_UNKNOWN:
                msg = "WIFI_STATE_UNKNOWN";
                break;
            default:
                msg = "WIFI_STATE_UNKNOWN";
                break;
        }
        Toast.makeText(this, msg ,Toast.LENGTH_LONG).show();
    }

    private void callPhone() {
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-1234-5678"));
        startActivity(i);
    }
}
