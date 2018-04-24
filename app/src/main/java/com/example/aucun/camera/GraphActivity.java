package com.example.aucun.camera;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.github.mikephil.charting.charts.LineChart;
        import com.github.mikephil.charting.components.YAxis;
        import com.github.mikephil.charting.data.Entry;
        import com.github.mikephil.charting.data.LineData;
        import com.github.mikephil.charting.data.LineDataSet;

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
        wristGraph.add(new Entry(7,2));
        wristGraph.add(new Entry(8,3));

        LineDataSet setComp1 = new LineDataSet(wristGraph,"KBS");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(setComp1);
    }
}
