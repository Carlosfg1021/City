package com.example.city;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Grafica extends AppCompatActivity {

    /*
    //Grafica
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_perfil);

        pieChart = findViewById(R.id.graficoPastel);

        crearGraficoPastel();

    }


    private void crearGraficoPastel() {

        Description description = new Description();
        description.setText("Grafica Resultados");

        pieChart.setDescription(description);

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        pieEntries.add(new PieEntry(2, 3));
        pieEntries.add(new PieEntry(3, 8));
        pieEntries.add(new PieEntry(6, 7));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Ganadas y Perdidas");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);

    }*/

}
