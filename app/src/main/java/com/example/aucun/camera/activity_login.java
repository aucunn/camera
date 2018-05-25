package com.example.aucun.camera;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class activity_login extends AppCompatActivity {

    EditText textSendNickname;
    String sendNickname;

    Intent getIntent;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getIntent = getIntent();
        textSendNickname = (EditText) findViewById(R.id.editText);

        if (getIntent.getStringExtra("userNum") != null){
            textSendNickname.setText(getIntent.getStringExtra("userNum"));
        }


    }
    public void onLoginClicked(View v){

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userNum", textSendNickname.getText().toString());

        //sendNickname = textSendNickname.getText().toString();
        startActivity(intent);


    }

    public void onEntrollClicked(View v) {                                                   //컴퓨터 연동 버튼 누를때
        Intent intent = new Intent(getApplicationContext(),activity_enroll.class);
        if (getIntent.getStringExtra("userNum") != null)
        {
            intent.putExtra("userNum", getIntent.getStringExtra("userNum"));
        }
        startActivity(intent);
    }

}
