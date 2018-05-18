package com.example.aucun.camera;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.github.mikephil.charting.charts.LineChart;
        import com.github.mikephil.charting.components.XAxis;
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

        LineChart chart = (LineChart) findViewById(R.id.chart);                                     //차트를 만듭니다~~  차트의 기본 설정을 하는곳이에요
        chart.setTouchEnabled(false);

        YAxis noPight = chart.getAxisRight();                                                       //오른쪽 y축 무력화
        noPight.setEnabled(false);

        YAxis Y_Axis = chart.getAxisLeft();                                                         //Y축 담당

        Y_Axis.setDrawZeroLine(false);
        Y_Axis.setGranularity(1f);
        Y_Axis.setAxisMinimum(0f);


        XAxis X_Axis = chart.getXAxis();                                                            //X축 담당
        X_Axis.setGranularity(1f);
        X_Axis . setDrawAxisLine ( true );
        X_Axis . setDrawGridLines ( false );
        X_Axis.setPosition(XAxis.XAxisPosition.BOTTOM);



        ArrayList<Entry> wristGraph = new ArrayList<Entry>();                                           //차트에 값을 집어넣는곳, 디비에서 뭔갈 받아와서 넣어야겟죠??

        wristGraph.add(new Entry(1,1));
        wristGraph.add(new Entry(2,1));
        wristGraph.add(new Entry(3,2)); //영성이 바보3
        wristGraph.add(new Entry(4,3));
        wristGraph.add(new Entry(5,3));
        wristGraph.add(new Entry(6,3));
        wristGraph.add(new Entry(7,5));
        wristGraph.add(new Entry(8,7));
        wristGraph.add(new Entry(9,13));
        wristGraph.add(new Entry(10,3));
        wristGraph.add(new Entry(11,22));
        wristGraph.add(new Entry(12,5));
        wristGraph.add(new Entry(13,22));
        wristGraph.add(new Entry(14,13));
        wristGraph.add(new Entry(15,3));
        wristGraph.add(new Entry(16,22));
        wristGraph.add(new Entry(17,5));
        wristGraph.add(new Entry(18,22));
        wristGraph.add(new Entry(19,13));
        wristGraph.add(new Entry(20,3));
        wristGraph.add(new Entry(21,22));
        wristGraph.add(new Entry(22,5));
        wristGraph.add(new Entry(23,22));


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
