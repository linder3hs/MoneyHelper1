package com.linder.moneyhelper1;

import android.content.Intent;
import android.graphics.Path;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.linder.moneyhelper1.model.Operation;
import com.linder.moneyhelper1.repositories.OperationRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REGISTER_FORM_REQUEST = 100;
    private TextView ahorro,credito,actual;
    private String tipoEn;
    private String tiposSpi;
    private double montoTarjeta, montoEfectivo, montoAhorro;
    private double montop;
    private ProgressBar progressBar;
    private double totalProgressI;
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
                        montoTarjeta=montoTarjeta-montop;
                        break;
                    case "Ahorro":
                        montoAhorro=montoAhorro-montop;
                        break;
                    case "Efectivo":
                        montoEfectivo=montoEfectivo-montop;
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




    }

}