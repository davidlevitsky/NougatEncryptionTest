package com.example.davidlevitsky.nougatencryptiontest;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
       // DevicePolicyManager dm = new DevicePolicyManager()

        DevicePolicyManager localDPM = (DevicePolicyManager) getApplicationContext()
                .getSystemService(getApplicationContext().DEVICE_POLICY_SERVICE);
        int encryption_status = localDPM.getStorageEncryptionStatus();
        Toast toast = Toast.makeText(getApplicationContext(), Integer.toString(encryption_status), Toast.LENGTH_LONG);
        toast.show();
        Log.d("TEST", "TEST");
        setPreference();
        getPreference();



    }

    public void setup() {
        final ArrayList<String> itemsList = new ArrayList<String>();
        itemsList.add("firstItem");
        itemsList.add("secondItem");
        itemsList.add("thridItem");
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsList);
        ListView listView = (ListView) findViewById(R.id.lvItems);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = itemsList.get(position);
                Toast toast = Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void setPreference() {
        Context c = getApplicationContext();
        SharedPreferences settings = c.getSharedPreferences("PREFERENCES", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Test Key", "Test Value");
        editor.commit();
        Context deviceProtected = c.createDeviceProtectedStorageContext();
        SharedPreferences protectedSettings = deviceProtected.getSharedPreferences("PROTECTED", 0);
        SharedPreferences.Editor protectedEditor = protectedSettings.edit();
        protectedEditor.putString("Protected Key", "Protected Value");
        protectedEditor.commit();


    }

    public void getPreference() {
        Context c = getApplicationContext();
        SharedPreferences settings = c.getSharedPreferences("PREFERENCES", 0);
        String value = settings.getString("Test Key", "");
        Toast.makeText(c, "value: " + value, Toast.LENGTH_LONG).show();

        settings = c.getSharedPreferences("PROTECTED", 0);
        String unsure = settings.getString("Protected Key", "");
        Toast.makeText(c, "UNSURE: " + unsure, Toast.LENGTH_LONG).show();



        Context deviceProtected = c.createDeviceProtectedStorageContext();
        SharedPreferences pSettings = deviceProtected.getSharedPreferences("PROTECTED", 0);
        String pVal = pSettings.getString("Protected Key", "");
        Toast.makeText(c, "p value: " + pVal, Toast.LENGTH_LONG).show();

    }





}
