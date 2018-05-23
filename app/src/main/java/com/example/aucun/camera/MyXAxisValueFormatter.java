package com.example.aucun.camera;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class MyXAxisValueFormatter implements IAxisValueFormatter {

    private String[] mValues;
    int counter = 0;

    public MyXAxisValueFormatter(String[] values) {
        this.mValues= values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // "value" represents the position of the label on the axis (x or y)
        return mValues[(int) value % mValues.length];
    }

}