package com.example.aucun.camera;

import android.content.ContentValues;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class
Main2Activity extends AppCompatActivity {
    protected String mRecordingFile;
    SoundPool sound1, sound2, sound3, sound4;
    int soundId1, soundId2, soundId3, soundId4;
    int selectSound;
    ContentValues contentValues;
    Main2Activity.NetworkTask networkTask;
    String url = "http://124.153.179.11/set_sound.php";
    Intent getIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getIntent = getIntent();

        //1번 소리
        sound1 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId1 = sound1.load(this, R.raw.a,1);
        Button btn1 = (Button) findViewById(R.id.radioButton5);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSound = 1;
                sound1.play(soundId1, 1f, 1f, 0, 0, 1f);
                contentValues = new ContentValues();
                contentValues.put("Unum", getIntent.getStringExtra("userNum"));
                contentValues.put("Sound", selectSound);
                networkTask = new NetworkTask(url, contentValues);
                networkTask.execute();
            }
        });
        //2번 소리
        sound2 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId2 = sound2.load(this, R.raw.b,1);
        Button btn2 = (Button) findViewById(R.id.radioButton4);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSound = 2;
                sound2.play(soundId2, 1f, 1f, 0, 0, 1f);
                contentValues = new ContentValues();
                contentValues.put("Unum", getIntent.getStringExtra("userNum"));
                contentValues.put("Sound", selectSound);
                networkTask = new NetworkTask(url, contentValues);
                networkTask.execute();
            }
        });
        //3번 소리
        sound3 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId3 = sound3.load(this, R.raw.c,1);
        Button btn3 = (Button) findViewById(R.id.radioButton3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSound = 3;
                sound3.play(soundId3, 1f, 1f, 0, 0, 1f);
                contentValues = new ContentValues();
                contentValues.put("Unum", getIntent.getStringExtra("userNum"));
                contentValues.put("Sound", selectSound);
                networkTask = new NetworkTask(url, contentValues);
                networkTask.execute();
            }
        });
        //4번 소리
        sound4 = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId4 = sound4.load(this, R.raw.d,1);
        Button btn4 = (Button) findViewById(R.id.radioButton2);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSound = 4;
                sound4.play(soundId4, 1f, 1f, 0, 0, 1f);
                contentValues = new ContentValues();
                contentValues.put("Unum", getIntent.getStringExtra("userNum"));
                contentValues.put("Sound", selectSound);
                networkTask = new NetworkTask(url, contentValues);
                networkTask.execute();
            }
        });

    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpConnection requestHttpURLConnection = new RequestHttpConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.

        }

    }
}
