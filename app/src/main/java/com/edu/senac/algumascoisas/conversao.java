package com.edu.senac.algumascoisas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class conversao extends AppCompatActivity {

    EditText editBytes;
    TextView textKB,textMB,textGB,textTB,error;
    Spinner spinnerConvert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversao);

        editBytes = findViewById(R.id.editBytes);
        textKB = findViewById(R.id.textKB);
        textMB = findViewById(R.id.textMB);
        textGB = findViewById(R.id.textGB);
        textTB = findViewById(R.id.textTB);
        error = findViewById(R.id.errorText);
        spinnerConvert = findViewById(R.id.spinnerConvert);

        spinnerConvert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calcularConversao(null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    public void calcularConversao(View v){
        String spinner=spinnerConvert.getSelectedItem().toString();
        NumberFormat formatter = new DecimalFormat("0.00");


        if(editBytes.getText().toString().trim().length() > 0){
            Double by = Double.parseDouble(editBytes.getText().toString());
            error.setText("");
            if(spinner.equals("KB")){
                textKB.setText(formatter.format(by));
                textMB.setText(formatter.format(by/1024));
                textGB.setText(formatter.format(by/1048576));
                textTB.setText(formatter.format(by/1073741824));
            }else if(spinner.equals("MB")){
                textKB.setText(formatter.format(by*1024));
                textMB.setText(formatter.format(by));
                textGB.setText(formatter.format(by/1024));
                textTB.setText(formatter.format(by/1048576));
            }else if(spinner.equals("GB")){
                textKB.setText(formatter.format((by*1048576)));
                textMB.setText(formatter.format(by*1024));
                textGB.setText(formatter.format(by));
                textTB.setText(formatter.format(by/1024));
            }else{
                textKB.setText(formatter.format(by*1073741824));
                textMB.setText(formatter.format(by*1048576));
                textGB.setText(formatter.format(by*1024));
                textTB.setText(formatter.format(by));
            }
        }else{
            textKB.setText("0");
            textMB.setText("0");
            textGB.setText("0");
            textTB.setText("0");
            error.setText("Número informado inválido ou nulo");
        }
        }
    }

