package com.example.aucun.camera;


        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.github.mikephil.charting.charts.LineChart;
        import com.github.mikephil.charting.components.YAxis;
        import com.github.mikephil.charting.data.Entry;
        import com.github.mikephil.charting.data.LineData;
        import com.github.mikephil.charting.data.LineDataSet;
        import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
        import com.github.mikephil.charting.components.AxisBase;
        import com.github.mikephil.charting.components.LimitLine;
        import android.content.ContentValues;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.TextView;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;


public class GraphActivity extends AppCompatActivity {

    String json = null;

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
            json = result;
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);



        LineChart chart = (LineChart) findViewById(R.id.chart);      //전체적인 틀

        ArrayList<Entry> wristGraph = new ArrayList<Entry>();       //값들이 들어가는 어레이리스트

        // URL 설정.
        String url = "124.153.179.11/get_time.php";

        ContentValues val = new ContentValues();
        val.put("Unum", 1);
        // AsyncTask를 통해 HttpURLConnection 수행.
        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();

        String datee = null;
        String timee = null;
/*
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray jsonArray = jsonObj.getJSONArray("result");

            for (int i = 0; i< jsonArray.length(); i++)
            {
                JSONObject jObject = jsonArray.getJSONObject(i);

                datee = jObject.optString("Date");
                timee = jObject.optString("Time");

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
*/

        //요쯤에서 DB에서 값을 받아 변환 , x가 날짜가 되어야 하고, y가 횟수가 되어야함.

        wristGraph.add(new Entry(1,0f));
        wristGraph.add(new Entry(2,1f));
        wristGraph.add(new Entry(3,2f)); //영성이 바보3
        wristGraph.add(new Entry(4,3f));
        wristGraph.add(new Entry(5,5f));
        wristGraph.add(new Entry(6,6f));
        wristGraph.add(new Entry(7,7f));
        wristGraph.add(new Entry(8,8f));
        wristGraph.add(new Entry(9,9f));


        LineDataSet number_of_JASE = new LineDataSet(wristGraph,"KBS");     //눈에 보이는 데이터들의 선
        number_of_JASE.setAxisDependency(YAxis.AxisDependency.LEFT);




        LineData data = new LineData(number_of_JASE);
        chart.setData(data);
        chart.invalidate();

        YAxis leftAxis = chart.getAxisLeft();

        LimitLine ll = new LimitLine(140f, "Critical Blood Pressure");

        ll.setLineWidth(4f);

        ll.setTextSize(12f);
// .. and more styling options

        leftAxis.addLimitLine(ll);

}



}


