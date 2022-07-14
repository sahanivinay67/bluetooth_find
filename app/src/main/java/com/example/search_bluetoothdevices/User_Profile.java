package com.example.search_bluetoothdevices;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class User_Profile extends AppCompatActivity {


    TextView tv_number, tv_name;
    String Name, Number;
    DataBase dataBase;

    TextView tvName, tvMac;
    private BluetoothAdapter mBluetoothAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        InitData();
        SetData();




    }



    private void SetData() {
        List<User_Model> list = dataBase.get_User_Data();
        for (User_Model cn : list) {
            Name = cn.getName();
            Number = cn.getNumber();

            tv_name.setText(Name);
            tv_number.setText(Number);
        }


    }

    private void InitData() {

        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_name = (TextView) findViewById(R.id.tv_name);
        dataBase = new DataBase(this);

        // Declaring the textView for name from the layout file
        tvName = (TextView) findViewById(R.id.nameTv);

        // Declaring the textView for MAC ID from the layout file
        tvMac = (TextView) findViewById(R.id.macAddressTv);


        // Initializing the Bluetooth Adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }



    public void btnDiscover(View view) {
        // Checks if Bluetooth Adapter is present
        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth Not Supported", Toast.LENGTH_SHORT).show();
        } else {
            // List all the bonded devices(paired)
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {

                    // get the device name
                    String deviceName = device.getName();

                    // get the mac address
                    String macAddress = device.getAddress();

                    // append in the two separate views
                    tvName.append(deviceName + "\n");
                    tvMac.append(macAddress + "\n");
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}