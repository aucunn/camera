package com.example.aucun.camera;

        import android.content.ContentValues;
        import android.os.AsyncTask;
        import android.os.DropBoxManager;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Spinner;
        import android.widget.TextView;
        import com.github.mikephil.charting.charts.LineChart;
        import com.github.mikephil.charting.components.XAxis;
        import com.github.mikephil.charting.components.YAxis;
        import com.github.mikephil.charting.data.Entry;
        import com.github.mikephil.charting.data.LineData;
        import com.github.mikephil.charting.data.LineDataSet;
        import com.github.mikephil.charting.formatter.IValueFormatter;
        import com.github.mikephil.charting.formatter.IAxisValueFormatter;
        import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
        import com.github.mikephil.charting.utils.ViewPortHandler;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.lang.Object;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Collection;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.Map;
        import java.text.DecimalFormat;
        import java.util.ArrayList;
        import java.util.Set;

public class GraphActivity extends AppCompatActivity {

    String DB_Date ="aa!";
    DateDic date;
    int dateType = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Spinner s = (Spinner)findViewById(R.id.dateSetting);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                dateType = position + 1;
                ContentValues values1=new ContentValues();
                values1.put("Unum", 1);

                // URL 설정.
                String url = "http://124.153.179.11/get_time.php";

                // AsyncTask를 통해 HttpURLConnection 수행.
                GraphActivity.NetworkTask networkTask = new GraphActivity.NetworkTask(url, values1);
                networkTask.execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ContentValues values1=new ContentValues();
        values1.put("Unum", 1);

        // URL 설정.
        String url = "http://124.153.179.11/get_time.php";

        // AsyncTask를 통해 HttpURLConnection 수행.
        GraphActivity.NetworkTask networkTask = new GraphActivity.NetworkTask(url, values1);
        networkTask.execute();


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
            DB_Date = s;
            analyzeDB();
            nextExec();
        }
    }

    void nextExec()
    {
        LineChart chart = (LineChart) findViewById(R.id.chart);                                     //차트를 만듭니다~~  차트의 기본 설정을 하는곳이에요
        chart.setTouchEnabled(false);

        YAxis noPight = chart.getAxisRight();                                                       //오른쪽 y축 무력화
        noPight.setEnabled(false);

        YAxis Y_Axis = chart.getAxisLeft();                                                         //Y축 담당

        Y_Axis.setDrawZeroLine(true);
        Y_Axis.setGranularityEnabled(true);
        Y_Axis.setAxisMinimum(0f);
        Y_Axis.setDrawGridLines(true);
        Y_Axis.setDrawAxisLine(true);
        Y_Axis.setLabelCount(10);



        XAxis X_Axis = chart.getXAxis();                                                            //X축 담당
        X_Axis . setDrawAxisLine ( true );
        X_Axis . setDrawGridLines ( false );
        X_Axis.setGranularityEnabled(true);
        X_Axis.setGranularity(1);
        X_Axis.setPosition(XAxis.XAxisPosition.BOTTOM);

        String [] inputData = new String[date.getSize()];
        for(int i = 0; i < date.getSize(); i++){
            inputData[i] = date.getDate(i);
        }

        X_Axis.setValueFormatter(new MyXAxisValueFormatter (inputData));



        ArrayList<Entry> wristGraph = new ArrayList<Entry>();                                           //차트에 값을 집어넣는곳, 디비에서 뭔갈 받아와서 넣어야겟죠??


        int putGraph=0;
        for(int i =0; i < date.getSize(); i++){
            putGraph = (int)date.getCount(i);
            wristGraph.add(new Entry(i, putGraph));

        }





        LineDataSet setComp1 = new LineDataSet(wristGraph,"KBS");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("1.Q");
        xVals.add("2.Q");
        xVals.add("3.Q");
        xVals.add("4.Q");

        LineData data = new LineData(setComp1);

        chart.setData(data);
        chart.invalidate();
    }

    void analyzeDB(){                                                                               //DB에서 받아온 데이터를 잘라 넣기


        JSONObject jObj;

        date = new DateDic(dateType);;
        //Map<String, Integer> dic = new HashMap<String, Integer>();

        try {
            JSONArray jarray = new JSONObject(DB_Date).getJSONArray("webnautes"); // JSON에서 webnautes배열 가져오기
            int len = jarray.length();//배열길이..


            for (int i = 0; i < len; i++)
            {
                jObj = jarray.getJSONObject(i);//JSON배열에서 객채 가져오기
                try {
                    date.add(jObj.getString("DateTime"));//객채(DateTime)에서 " "앞부분만 추출


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}





