package com.example.aucun.camera;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class activity_enroll extends AppCompatActivity {


    EditText textSendNickname;
    TextView yourNumberis;
    String sendNickname;
    boolean flag = true;
    Button enrollBtn;
    Button checkBtn;
    Button goToLoginBtn;
    private boolean commitOrCheck = false;

    Intent intent;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);


        intent = new Intent(this, activity_login.class);

        textSendNickname = (EditText) findViewById(R.id.editText2);
        yourNumberis = (TextView) findViewById(R.id.textView3);

        enrollBtn = (Button) findViewById(R.id.button5);
        checkBtn = (Button) findViewById(R.id.button8);
        goToLoginBtn = (Button) findViewById(R.id.button9);

    }


    public void onCheckredundancyClicked(View v){
        String url = "http://124.153.179.11/check_user.php";
        ContentValues sending = new ContentValues();

        sendNickname = textSendNickname.getText().toString();
        sending.put("Uname", sendNickname);

        activity_enroll.NetworkTask networkTask = new NetworkTask(url, sending);
        networkTask.execute();



    }

    public void onEnrollClicked(View v){
        String url = "http://124.153.179.11/commit_user.php";
        ContentValues sending = new ContentValues();

        sendNickname = textSendNickname.getText().toString();
        sending.put("Uname", sendNickname);

        activity_enroll.NetworkTask networkTask = new NetworkTask(url, sending);
        networkTask.execute();

    }

    public void onGotoLoginClicked(View v){
        //Intent intent = new Intent(getApplicationContext(),activity_login.class);
        startActivity(intent);

    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

                /*
        checkUser;
        checkUser + networkTask;
        commitUser;
*/
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

            if(commitOrCheck == false) {
                try {
                    JSONArray jarray = new JSONObject(s).getJSONArray("webnautes"); // JSON에서 webnautes배열 가져오기
                    int len = jarray.length();//배열길이..

                    if (len == 0) {
                        flag = false;
                        AlertDialog.Builder alert = new AlertDialog.Builder(activity_enroll.this);
                        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();     //닫기
                            }
                        });
                        alert.setMessage("사용가능한 닉네임 입니다.");
                        alert.show();
                        enrollBtn.setEnabled(true);
                        checkBtn.setEnabled(false);
                        textSendNickname.setEnabled(false);
                        commitOrCheck = true;



                    } else {
                        flag = true;
                        AlertDialog.Builder alert = new AlertDialog.Builder(activity_enroll.this);
                        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();     //닫기
                            }
                        });
                        alert.setMessage("이미 등록된 아이디 입니다.");
                        alert.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else{
                enrollBtn.setEnabled(false);
                yourNumberis.setVisibility(View.VISIBLE);
                goToLoginBtn.setVisibility(View.VISIBLE);
                goToLoginBtn.setEnabled(true);
                String userNum = analyzeDB(s);
                yourNumberis.setText("당신의 번호는 " + userNum + "입니다.");

                intent.putExtra("userNum", userNum);


            }


        }
    }

    String analyzeDB(String data){                                                                               //DB에서 받아온 데이터를 잘라 넣기


        JSONObject jObj;

        try {
            JSONArray jarray = new JSONObject(data).getJSONArray("webnautes"); // JSON에서 webnautes배열 가져오기
            jObj = jarray.getJSONObject(0);//JSON배열에서 객채 가져오기
            return jObj.getString("Unum");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "err";
    }

}
