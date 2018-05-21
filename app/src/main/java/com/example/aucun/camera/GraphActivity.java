package com.example.aucun.camera;

        import android.content.ContentValues;
        import android.os.AsyncTask;
        import android.os.DropBoxManager;
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

        import java.text.DecimalFormat;
        import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {
    private TextView tv_outPut;
    private TextView testView;
    String DB_Date ="aa!";
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

        int indexOfDate;






        //------------------------------------------------------------------------------------------

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
        }
    }

    void fillChart(){

        
    }

}





