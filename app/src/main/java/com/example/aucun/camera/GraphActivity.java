package com.example.aucun.camera;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.github.mikephil.charting.charts.LineChart;
        import com.github.mikephil.charting.data.Entry;

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
        
    }
}
