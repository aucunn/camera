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







    public void onLinkToComputerClicked(View v) {
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);
    }

    public void onCheckDateClicked(View v) {
        Intent intent = new Intent(getApplicationContext(),TESTgraph.class);
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

    public void onGOOUTClicked(View v){
        Toast.makeText(getApplicationContext(), "꺼지시겠습니까??", Toast.LENGTH_SHORT).show(); finish();

    }

    @Override public void onBackPressed() {  }


}
