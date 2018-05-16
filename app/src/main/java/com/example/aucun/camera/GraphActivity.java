package com.example.aucun.camera;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.github.mikephil.charting.charts.LineChart;
        import com.github.mikephil.charting.components.YAxis;
        import com.github.mikephil.charting.data.Entry;
        import com.github.mikephil.charting.data.LineData;
        import com.github.mikephil.charting.data.LineDataSet;
        import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

        import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        LineChart chart = (LineChart) findViewById(R.id.chart);

        ArrayList<Entry> wristGraph = new ArrayList<Entry>();

        wristGraph.add(new Entry(5,0));
        wristGraph.add(new Entry(6,1));
        wristGraph.add(new Entry(7,2)); //영성이 바보3
        wristGraph.add(new Entry(8,3));

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
}
