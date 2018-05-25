package com.example.aucun.camera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Intent getIntent;

    private BackPressCloseHandler backPressCloseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backPressCloseHandler = new BackPressCloseHandler(this);


        getIntent = getIntent();
        setContentView(R.layout.activity_main);


    }







    public void onLinkToComputerClicked(View v) {                                                   //컴퓨터 연동 버튼 누를때
        Intent intent = new Intent(getApplicationContext(),activity_login.class);
        if (getIntent.getStringExtra("userNum") != null)
        {
            intent.putExtra("userNum", getIntent.getStringExtra("userNum"));
        }
        startActivity(intent);
    }


    public void onGraphClicked(View v) {
        Intent intent = new Intent(getApplicationContext(),GraphActivity.class);
        if (getIntent.getStringExtra("userNum") != null)
        {
            intent.putExtra("userNum", getIntent.getStringExtra("userNum"));
        }
        startActivity(intent);
    }

    public void onSettingClicked(View v) {
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        if (getIntent.getStringExtra("userNum") != null)
        {
            intent.putExtra("userNum", getIntent.getStringExtra("userNum"));
        }
        startActivity(intent);
    }







    @Override public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();


    }


}
