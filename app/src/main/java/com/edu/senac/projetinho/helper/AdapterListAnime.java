package com.edu.senac.projetinho.helper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.senac.projetinho.R;
import com.edu.senac.projetinho.model.Anime;


import java.util.List;

public class AdapterListAnime extends BaseAdapter {

    private final List<Anime> animes;
    private final Activity activity;


    public AdapterListAnime(List<Anime> animes, Activity activity) {
        this.animes = animes;
        this.activity = activity;

    }

    @Override
    public int getCount() {
        return animes.size();
    }

    @Override
    public Object getItem(int position) {
        return animes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return animes.get(position).getCodigo();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = activity.getLayoutInflater().inflate(R.layout.anime_item, parent, false);
        Anime anime = animes.get(position);
        TextView txNome = view.findViewById(R.id.nomeAnime);
        TextView txDiretor = view.findViewById(R.id.diretorAnime);
        TextView txEpisodios = view.findViewById(R.id.episodiosAnime);
        ImageView fotoAnime = view.findViewById(R.id.imgAnime);

        byte[] decodedString = Base64.decode(anime.getFoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        txNome.setText(anime.getNome());
        txDiretor.setText(anime.getDiretor());
        txEpisodios.setText(Integer.toString(anime.getEpisodios()));
        fotoAnime.setImageBitmap(decodedByte);

        return view;
    }

    public void atualizarAnimes(List<Anime> novosAnimes){
        this.animes.clear();
        this.animes.addAll(novosAnimes);
        notifyDataSetChanged();
    }
}

