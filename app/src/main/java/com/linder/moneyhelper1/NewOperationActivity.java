package com.linder.moneyhelper1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.linder.moneyhelper1.model.Operation;
import com.linder.moneyhelper1.repositories.OperationRepository;

public class NewOperationActivity extends AppCompatActivity {
    private Spinner tipo;
    private EditText newMonto;
    private RadioGroup tipoE;
    private String cuentaTip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_operation);

        newMonto = (EditText) findViewById(R.id.newMonto);
        tipoE = (RadioGroup) findViewById(R.id.tipoe);

        tipo = (Spinner) findViewById(R.id.tipodeentrada);

    }

    public void newRegister(View view) {

        String montos = newMonto.getText().toString();
        int monimput = Integer.parseInt(montos);
        String tipes = ((RadioButton) findViewById(tipoE.getCheckedRadioButtonId())).getText().toString();

        String tipos = tipo.getSelectedItem().toString();

        if (montos.isEmpty() || tipes.isEmpty() || tipos.isEmpty()) {
            Toast.makeText(this, "Rellene los datos", Toast.LENGTH_SHORT).show();
            return;
        }

        Operation operation = new Operation(monimput, tipes, tipos);
        /*Toast.makeText(this, monimput, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, tipes, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, tipos, Toast.LENGTH_SHORT).show();*/
        OperationRepository operationRepository = OperationRepository.getInstance();
        operationRepository.addOperation(operation);
        finish();


    }
}
