package com.example.aucun.camera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }





    public void onButton1Clicked(View v){
        Toast.makeText(getApplicationContext(), "꺼지시겠습니까??", Toast.LENGTH_SHORT).show(); finish();

    }

    public void onButton2Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);
    }

    public void onButton3Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(),GraphActivity.class);
        startActivity(intent);
    }

    @Override public void onBackPressed() {  }


}
