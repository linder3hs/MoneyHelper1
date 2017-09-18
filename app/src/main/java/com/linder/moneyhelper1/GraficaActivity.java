package com.linder.moneyhelper1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class GraficaActivity extends AppCompatActivity {
    private PieChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica);


        mChart = (PieChart) findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

     /*   MainActivity mainActivity = MainActivity.getInstance();
        int i = (int) mainActivity.getTotalProgressI();
        int e = (int) mainActivity.getTotalProgressE();

        Toast.makeText(GraficaActivity.this, String.valueOf(e), Toast.LENGTH_SHORT).show();

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry( i, "Ingresos"));
        entries.add(new PieEntry( e, "Egresos"));

        PieDataSet set = new PieDataSet(entries, "Election Results");
        PieData data = new PieData(set);
        mChart.setData(data);
        mChart.invalidate(); // refresh
*/

    }
}