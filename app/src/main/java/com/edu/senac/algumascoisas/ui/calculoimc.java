package com.edu.senac.algumascoisas.ui;

import android.os.Bundle;

import com.edu.senac.algumascoisas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class calculoimc extends AppCompatActivity {

    EditText editAltura,editPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculoimc);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        editPeso = findViewById(R.id.editPeso);
        editAltura=findViewById(R.id.editAltura);
    }

    public void sair(View v){
        finish();
    }

    public void calcular(View v){
        String peso=editPeso.getText().toString(),altura=editAltura.getText().toString();
        Float pes = Float.parseFloat(peso), alt= Float.parseFloat(altura);
        Float res = pes/(alt * alt);

        String result = Float.toString(res);

        String r = Float.toString(res);

        if(res < 18.5){
            r = "Abaixo do peso";
        }else if(res >= 18.5 && res < 25){
            r = "Peso normal";
        }else if(res >= 25 && res < 30){
            r = "Sobrepeso";
        }else if(res >= 30 && res < 35){
            r = "Grau 1 de obesidade";
        }else if(res >= 35 && res < 40){
            r = "Grau 2 de obesidade";
        }else{
            r = "Grau 3 de obesidade";
        }

        Toast.makeText(this,result + " -> " + r,Toast.LENGTH_SHORT).show();

    }

}
