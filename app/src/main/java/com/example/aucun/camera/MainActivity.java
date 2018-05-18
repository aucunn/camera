package com.example.aucun.camera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backPressCloseHandler = new BackPressCloseHandler(this);

        setContentView(R.layout.activity_main);
    }







    public void onLinkToComputerClicked(View v) {                                                   //컴퓨터 연동 버튼 누를때
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);
    }


    public void onGraphClicked(View v) {
        Intent intent = new Intent(getApplicationContext(),GraphActivity.class);
        startActivity(intent);
    }

    public void onSettingClicked(View v) {
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);
    }







    @Override public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();


    }


}
