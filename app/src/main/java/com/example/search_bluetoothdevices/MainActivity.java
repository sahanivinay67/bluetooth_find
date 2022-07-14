package com.example.search_bluetoothdevices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText et_name,et_number;
    String name,number;
    boolean j;

    DataBase dataBase;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitData();
        CheckUserLogin();


    }

    private void CheckUserLogin() {
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);


        j=dataBase.is_ps_DataInserted();
        if (j==false)
        {
            progressDialog.dismiss();

            Intent intent=new Intent(getApplicationContext(),User_Profile.class);
            startActivity(intent);


        }else
        progressDialog.dismiss();

    }

    private void InitData() {
        et_name=findViewById(R.id.et_name);
        et_number=findViewById(R.id.et_number);

        //Init DataBase and ProgressDailog
        dataBase=new DataBase(this);
        progressDialog= new ProgressDialog(this);


    }


    public void On_Login_Btn(View view) {
        name=et_name.getText().toString();
        number=et_number.getText().toString();
        if (name.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
            et_name.setError("Name");
        }else if(number.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please Enter Number", Toast.LENGTH_SHORT).show();
            et_number.setError("Number");
        }
        else if (number.length()!=10)
        {
            Toast.makeText(getApplicationContext(), "Please Enter Correct Number", Toast.LENGTH_SHORT).show();
            et_number.setError("Number");
        }
        else {
            boolean i=dataBase.insert_User_Data(new User_Model(1,name,number,1));
            if (i==true)
            {
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),User_Profile.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Login Fail", Toast.LENGTH_SHORT).show();
            }




        }

    }


}