package com.edu.senac.algumascoisas.ui;

import android.content.Intent;
import android.os.Bundle;

import com.edu.senac.algumascoisas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_aplicativos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sobre:
                Intent i = new Intent(this, Sobre.class);
                startActivity(i);
                //abrir a tela de sobre
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sair(View v) {
        finish();
    }

    public void calcularIMC(View v) {
        Intent i = new Intent(this, calculoimc.class);
        startActivity(i);
    }

    public void converter(View v) {
        Intent i = new Intent(this, conversao.class);
        startActivity(i);
    }


    public void listar(View v) {
        Intent i = new Intent(this, ListaDeProdutos.class);
        startActivity(i);
    }

}
