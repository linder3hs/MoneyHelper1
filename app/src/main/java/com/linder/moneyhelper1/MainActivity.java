package com.linder.moneyhelper1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.linder.moneyhelper1.model.Operation;
import com.linder.moneyhelper1.repositories.OperationRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static MainActivity _INSTANCE = null;

    private static final int REGISTER_FORM_REQUEST = 100;
    private TextView ahorro,credito,actual;
    private String tipoEn;
    private String tiposSpi;
    private double montoTarjeta, montoEfectivo, montoAhorro;
    private double montop;
    private ProgressBar progressBar;
    private double totalProgressI;
    private Button piechart;
    private PieChart mChart;
    private double totalProgressE;
    private int proI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ahorro = (TextView) findViewById(R.id.montoAhorro);
        credito = (TextView) findViewById(R.id.credito);
        actual = (TextView) findViewById(R.id.actual);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mChart = (PieChart) findViewById(R.id.chart1);


    }
        public void graphic(){
            List<PieEntry> entries = new ArrayList<>();

            entries.add(new PieEntry((float) totalProgressI, "Ingresos"));
            entries.add(new PieEntry((float) totalProgressE, "Egresos"));

            PieDataSet set = new PieDataSet(entries, "Money Helper");
            PieData data = new PieData(set);

            mChart.setData(data);
            mChart.invalidate();
            mChart.setHoleColor(Color.BLACK);
            mChart.setCenterTextColor(Color.BLACK);// refresh

            ArrayList<Integer> color = new ArrayList<>();
            color.add(Color.rgb(202, 63, 56));
            color.add(Color.rgb(22, 213, 126));
            set.setColors(color);
        }
        public void addItem(View view){
            startActivityForResult(new Intent(this, NewOperationActivity.class), 100);
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        OperationRepository operationRepository = OperationRepository.getInstance();

        List<Operation> operations = operationRepository.getOperations();
        for (Operation operation : operations) {
            //comentario
            tipoEn = operation.getTipoE();
            tiposSpi = operation.getTarjett();
            montop = operation.getMonto();


        }

        switch (tipoEn){
            case "Ingreso":
                totalProgressI = totalProgressI + montop;
                switch (tiposSpi){
                    case "Tarjeta de credito":
                        montoTarjeta=montoTarjeta+montop;
                        break;
                    case "Ahorro":
                        montoAhorro=montoAhorro+montop;
                        break;
                    case "Efectivo":
                        montoEfectivo=montoEfectivo+montop;
                        break;
                }
                break;
            case "Egreso":
                totalProgressE = totalProgressE + montop;
                switch (tiposSpi){
                    case "Tarjeta de credito":
                        if (montop > montoTarjeta){
                            Toast.makeText(this, "Dato menor", Toast.LENGTH_SHORT).show();
                        }else{
                            montoTarjeta=montoTarjeta-montop;
                        }
                        break;
                    case "Ahorro":
                        if (montop > montoAhorro){
                            Toast.makeText(this, "Dato menor", Toast.LENGTH_SHORT).show();
                        }else{
                            montoAhorro=montoAhorro-montop;
                        }
                        break;
                    case "Efectivo":
                        if (montop > montoEfectivo){
                            Toast.makeText(this, "Dato menor", Toast.LENGTH_SHORT).show();
                        }else{
                            montoEfectivo=montoEfectivo-montop;
                        }
                        break;
                }
                break;
        }

        credito.setText(String.valueOf(montoTarjeta));
        ahorro.setText(String.valueOf(montoAhorro));
        actual.setText(String.valueOf(montoEfectivo));
        double totalPro = totalProgressE + totalProgressI;
        totalProgressI = Math.round(totalProgressI*100/totalPro);
        proI = (int) totalProgressI;
        progressBar.setProgress(proI);


        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);
        graphic();





    }

}