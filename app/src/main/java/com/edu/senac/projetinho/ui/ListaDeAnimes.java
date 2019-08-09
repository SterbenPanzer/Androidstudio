package com.edu.senac.projetinho.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.edu.senac.projetinho.R;
import com.edu.senac.projetinho.helper.AdapterListAnime;
import com.edu.senac.projetinho.helper.DatabaseHelper;
import com.edu.senac.projetinho.model.Anime;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListaDeAnimes extends AppCompatActivity {

    ListView listaAnime;
    List<Anime> animes;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_animes);

        final Intent i = new Intent(this, cadastroAnime.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.removeExtra("anime");
                startActivity(i);
            }
        });

        animes = new ArrayList<>();
        AdapterListAnime adapter = new AdapterListAnime(animes, this);
        listaAnime = findViewById(R.id.listaAnime);
        listaAnime.setAdapter(adapter);

        databaseHelper = new DatabaseHelper(this);

        final Intent intent = new Intent(this, visualizar_anime.class);

        listaAnime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("anime", animes.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        animes = null;
        animes = databaseHelper.buscarTodosAnime();
        AdapterListAnime adapterList = (AdapterListAnime) listaAnime.getAdapter();
        adapterList.atualizarAnimes(animes);
    }
}
