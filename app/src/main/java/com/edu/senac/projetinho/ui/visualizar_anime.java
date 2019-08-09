package com.edu.senac.projetinho.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.senac.projetinho.R;
import com.edu.senac.projetinho.model.Anime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class visualizar_anime extends AppCompatActivity {

    ImageView imagemAnime;
    static TextView nomeAnime, sinopseAnime, temporadasAnime, episodiosAnime, dataLAnime, dataEAnime, diretorAnime;
    Button btnMinhasNotas,btnUpdate;
    Anime ani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_anime);

        imagemAnime = findViewById(R.id.imagemAnimeView);
        nomeAnime = findViewById(R.id.nomeAnimeView);
        sinopseAnime = findViewById(R.id.descricaoView);
        temporadasAnime = findViewById(R.id.temporadasTextView);
        episodiosAnime = findViewById(R.id.episodiosTextView);
        dataLAnime = findViewById(R.id.dataLTextView);
        dataEAnime = findViewById(R.id.dataETextView);
        diretorAnime = findViewById(R.id.diretorTextView);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnMinhasNotas = findViewById(R.id.buttonMinhasNotas);

        Intent i = getIntent();
        ani = (Anime) i.getSerializableExtra("anime");

        DateFormat date2 = new SimpleDateFormat("dd/MM/yyyy");

        byte[] decodedString = Base64.decode(ani.getFoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imagemAnime.setImageBitmap(decodedByte);
        nomeAnime.setText(ani.getNome());
        temporadasAnime.setText(Integer.toString(ani.getTemporada()));
        episodiosAnime.setText(Integer.toString(ani.getEpisodios()));
        dataLAnime.setText(date2.format(ani.getDataL()));
        sinopseAnime.setText(ani.getDescricao());
        dataEAnime.setText(date2.format(ani.getDataE()));
        diretorAnime.setText(ani.getDiretor());

    }

    public void update(View v){
        final Intent intent = new Intent(this, cadastroAnime.class);

        intent.putExtra("anime", ani);
        startActivity(intent);
        finish();
    }
}
