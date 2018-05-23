package com.example.aucun.camera;

        import android.content.ContentValues;
        import android.os.AsyncTask;
        import android.os.DropBoxManager;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
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
    private TextView tv_outPut;
    private TextView testView;
    String DB_Date ="aa!";
    String Sub_DB_Date= "";
    String Sub_DB_Date2= "";
    StringBuffer Sub_DB_DateBuffer = new StringBuffer(Sub_DB_Date);
    Calendar cal = Calendar.getInstance();
    dateCounter dataForGraph = new dateCounter();
    int dateIndex;
    int indexForCount=0;
    CharSequence dateChar = "DateTime";

    ArrayList<String> dates = new ArrayList<String>();
    ArrayList<Integer> datei = new ArrayList<Integer>();


    int iii = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        ContentValues values1=new ContentValues();
        values1.put("Unum", 1);
        // 위젯에 대한 참조.
        tv_outPut = (TextView) findViewById(R.id.tv_outPut);



        // URL 설정.
        String url = "http://124.153.179.11/get_time.php";

        // AsyncTask를 통해 HttpURLConnection 수행.
        GraphActivity.NetworkTask networkTask = new GraphActivity.NetworkTask(url, values1);
        networkTask.execute();





        //------------------------------------------------------------------------------------------









        //------------------------------------------------------------------------------------------
/*
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
        X_Axis.setGranularity(1f);
        X_Axis . setDrawAxisLine ( true );
        X_Axis . setDrawGridLines ( false );
        X_Axis.setGranularityEnabled(true);
        X_Axis.setGranularity(1);
        X_Axis.setLabelCount(30);
        X_Axis.setPosition(XAxis.XAxisPosition.BOTTOM);



        ArrayList<Entry> wristGraph = new ArrayList<Entry>();                                           //차트에 값을 집어넣는곳, 디비에서 뭔갈 받아와서 넣어야겟죠??

        wristGraph.add(new Entry((int)1,(int)1));
        wristGraph.add(new Entry(2,1));
        wristGraph.add(new Entry(3,2)); //영성이 바보3
        wristGraph.add(new Entry(4,3));
        wristGraph.add(new Entry(5,3));
        wristGraph.add(new Entry(6,3));
        wristGraph.add(new Entry(7,5));


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
*/

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
            tv_outPut.setText(s);
            DB_Date = s;
            testView = (TextView) findViewById(R.id.testNum);
            testView.setText(DB_Date);
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
        X_Axis.setGranularity(1f);
        X_Axis . setDrawAxisLine ( true );
        X_Axis . setDrawGridLines ( false );
        X_Axis.setGranularityEnabled(true);
        X_Axis.setGranularity(1);
        X_Axis.setLabelCount(30);
        X_Axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        X_Axis.setValueFormatter(new DayAxisValueFormatter (chart));



        ArrayList<Entry> wristGraph = new ArrayList<Entry>();                                           //차트에 값을 집어넣는곳, 디비에서 뭔갈 받아와서 넣어야겟죠??
/*
        Entry
        for (int i = 0; i<iii; i++)
        {
            wristGraph.add(new Entry(dates.get(i), (int)1));
        }
        */
        int putGraph=0;
        for(int i =0; i < iii; i++){
            putGraph = (int)datei.get(i);
            wristGraph.add(new Entry(i+1, putGraph));

        }
        /*
        wristGraph.add(new Entry((int)1,(int)1));
        wristGraph.add(new Entry(2,1));
        wristGraph.add(new Entry(3,2)); //영성이 바보3
        wristGraph.add(new Entry(4,3));
        wristGraph.add(new Entry(5,3));
        wristGraph.add(new Entry(6,3));
        wristGraph.add(new Entry(7,5));
        */


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


        //Map<String, Integer> dic = new HashMap<String, Integer>();


        try {
            JSONArray jarray = new JSONObject(DB_Date).getJSONArray("webnautes"); // JSON에서 webnautes배열 가져오기
            int len = jarray.length();//배열길이


            for (int i = 0; i < len; i++)
            {
                jObj = jarray.getJSONObject(i);//JSON배열에서 객채 가져오기
                try {
                    String tmp = jObj.getString("DateTime").split(" ")[0];//객채(DateTime)에서 " "앞부분만 추출
                    if (dates.contains(tmp))//리스트에 있는 날짜인가?
                    {
                        int tmpi = dates.indexOf(tmp);//날짜 인덱스
                        datei.set(tmpi, datei.get(tmpi) + 1);//날짜의 값 1증가
                    }
                    else
                    {
                        dates.add(tmp);//날짜 추가
                        datei.add(1);
                        iii++;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



        /*
        dateIndex = 0;

        if(DB_Date.contains(dateChar)) {
            while (true) {
                indexForCount = DB_Date.length();
                dateIndex = DB_Date.indexOf("DateTime", dateIndex);
                if(dateIndex == -1) break;
                Sub_DB_Date = DB_Date.substring(dateIndex+12, dateIndex+22);
                dataForGraph.putDate(Sub_DB_Date, dataForGraph);

                dateIndex = dateIndex+8;
                if(dateIndex >= DB_Date.length()) break ;

            }
        }
        */
    }

}





