package com.codingblocks.sih19;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class BMIGraphActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bmi_curve);
       // GraphView graphView=findViewById(R.id.graphView);

        LineChartView lineChartView = findViewById(R.id.chart);
        int[] xAxisData = {0,1,2,3,4,5,6,7,8,9,10};
        double[] yAxisData = {14.4,14.5,14.5, 14,13.8,13.5,13.4,13.5,13.7,14.1,14.5};
        double[] yAxisData2 = {18,18,18,17,16.8,16.8,16.9,17,17.5,18.2,19};
        double[] yAxisData3 = {19,19,19,18,18,18.5,19,19.7,20.8,21,22};
        float wt= (float) 7.8;
        float ht= (float) 0.713;
        double bmi=calcBMI(wt,ht);
        Log.e("TAG","BMI"+bmi);
        double[] y={bmi};
        double []age={2};
        List yAxisValues = new ArrayList();
        List yAxisValues2 = new ArrayList();
        List yAxisValues3 = new ArrayList();
        List yPoint=new ArrayList();
        List xPoint=new ArrayList();
        List axisValues = new ArrayList();
        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));;
        Line line2 = new Line(yAxisValues2).setColor(Color.parseColor("#9C27B0"));;
        Line line3 = new Line(yAxisValues3).setColor(Color.parseColor("#9C27B0"));;
        Line ypoint=new Line(yPoint).setColor(Color.parseColor("#052672"));
        Line xpoint=new Line(xPoint).setColor(Color.parseColor("#052672"));
        for(int i = 0; i < xAxisData.length; i++){
            axisValues.add(i, new AxisValue(i).equals(xAxisData[i]));
        }
        for (int i = 0; i < yAxisData.length; i++){
            yAxisValues.add(new PointValue(i, (float) yAxisData[i]));
            yAxisValues2.add(new PointValue(i, (float) yAxisData2[i]));
            yAxisValues3.add(new PointValue(i, (float) yAxisData3[i]));
        }
        for(int i=0;i<1;i++)
        {
            yPoint.add(new PointValue((float) age[i], (float) y[i]));

        }
        List lines = new ArrayList();
        lines.add(line);
        lines.add(line2);
        lines.add(line3);
        lines.add(ypoint);
        lines.add(xpoint);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top =110;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);

/*        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(15, 22),

        });*/
       //  graphView.addSeries(series);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        lineChartView.setLineChartData(data);
        Axis axis = new Axis();
        data.setAxisXBottom(axis);
        Axis yAxis = new Axis();
        data.setAxisYLeft(yAxis);

    }
    private double calcBMI(float wt, float ht) {
        return (wt / (Math.pow(ht,2)));
    }
}

