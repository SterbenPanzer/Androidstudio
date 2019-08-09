package com.edu.senac.projetinho.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.edu.senac.projetinho.R;
import com.edu.senac.projetinho.helper.DatabaseHelper;
import com.edu.senac.projetinho.model.Anime;
import com.edu.senac.projetinho.model.Produto;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class cadastroAnime extends AppCompatActivity {

    ImageView imagemAnime;
    static EditText nomeAnime, sinopseAnime, temporadasAnime, episodiosAnime, dataLAnime, dataEAnime, diretorAnime;
    boolean imageSlct = false;
    Button btnSalvarAnime,btnDeletarAnime;
    Anime ani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_anime);

        imagemAnime = findViewById(R.id.editImagemAnimeView);
        nomeAnime = findViewById(R.id.editNomeText);
        sinopseAnime = findViewById(R.id.editSinopseAnime);
        temporadasAnime = findViewById(R.id.editTemporadasText);
        episodiosAnime = findViewById(R.id.editEpisodiosText);
        dataLAnime = findViewById(R.id.editDataLText);
        dataEAnime = findViewById(R.id.editDataEText);
        diretorAnime = findViewById(R.id.editDiretorText);
        btnDeletarAnime = findViewById(R.id.buttonDeletarAnime);
        btnSalvarAnime = findViewById(R.id.buttonSalvarAnime);

        Intent i = getIntent();
        ani = (Anime) i.getSerializableExtra("anime");

        DateFormat date2 = new SimpleDateFormat("dd-MM-yyyy");

        if (ani != null) {
            btnDeletarAnime.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Editando " + ani.getNome(), Toast.LENGTH_SHORT).show();
            byte[] decodedString = Base64.decode(ani.getFoto(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imagemAnime.setImageBitmap(decodedByte);
            imageSlct = true;
            nomeAnime.setText(ani.getNome());
            temporadasAnime.setText(Integer.toString(ani.getTemporada()));
            episodiosAnime.setText(Integer.toString(ani.getEpisodios()));
            sinopseAnime.setText(ani.getDescricao());
            dataLAnime.setText(date2.format(ani.getDataL()));
            dataEAnime.setText(date2.format(ani.getDataE()));
            diretorAnime.setText(ani.getDiretor());
        } else {
            btnDeletarAnime.setVisibility(View.GONE);
        }
    }


    public void tirarFotoAnime(View v) {
        if (checkAndRequestPermissions()) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, 100);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagemAnime.setImageBitmap(imageBitmap);
            imageSlct = true;
        }
    }

    public boolean checkAndRequestPermissions() {
        int camera = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
            return false;
        }
        return true;
    }

    public String  validarCamposAnime() {
        if (!imageSlct) {
            Log.d("salvarA", "Imagem não informada");
            return "O campo Imagem não foi informado!";
        }
        if (nomeAnime.getText().toString().trim().length() == 0) {
            Log.d("salvarA", "Nome do anime não informado");
            return "O nome do anime não foi informado!";
        }
        if (sinopseAnime.getText().toString().trim().length() == 0) {
            Log.d("salvarA", "A sinopse não foi informada");
            return "A sinopse do anime não foi informado!";
        }
        if (temporadasAnime.getText().toString().trim().length() == 0 || Integer.parseInt(temporadasAnime.getText().toString()) <= 0) {
            Log.d("salvarA", "A temporada do anime não informada");
            return "As temporadas do anime não foram informadas ou possuem valor inválido!";
        }
        if (episodiosAnime.getText().toString().trim().length() == 0 || Integer.parseInt(episodiosAnime.getText().toString()) <= 0) {
            Log.d("salvarA", "Os episodios do anime não foram informados");
            return "Os episodios do anime não foram informados ou possuem valor inválido!";
        }
        if (diretorAnime.getText().toString().trim().length() == 0) {
            Log.d("salvarA", "O diretor não foi informado");
            return "O diretor do anime não foi informado!";
        }else{

        Log.d("salvarA", "Campos informados");
        return "";
        }
    }

    public void salvarAnime(View view) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String mensagem = validarCamposAnime();

        if (ani == null) {

            Anime anime = new Anime();
            anime.setFoto(getImagem());
            anime.setNome(nomeAnime.getText().toString());
            anime.setDescricao(sinopseAnime.getText().toString());
            anime.setTemporada(Integer.parseInt(temporadasAnime.getText().toString()));
            anime.setEpisodios(Integer.parseInt(episodiosAnime.getText().toString()));
            anime.setDiretor(diretorAnime.getText().toString());
            anime.setDataL(formatter.parse(dataLAnime.getText().toString()));
            anime.setDataE(formatter.parse(dataEAnime.getText().toString()));

            databaseHelper.salvarAnime(anime);
        }else{
            ani.setFoto(getImagem());
            ani.setNome(nomeAnime.getText().toString());
            ani.setDescricao(sinopseAnime.getText().toString());
            ani.setTemporada(Integer.parseInt(temporadasAnime.getText().toString()));
            ani.setEpisodios(Integer.parseInt(episodiosAnime.getText().toString()));
            ani.setDiretor(diretorAnime.getText().toString());
            ani.setDataL(formatter.parse(dataLAnime.getText().toString()));
            ani.setDataE(formatter.parse(dataEAnime.getText().toString()));

            databaseHelper.updateAnime(ani);
        }

        finish();

    }

    public String getImagem(){
        Bitmap bitmap = ((BitmapDrawable) imagemAnime.getDrawable()).getBitmap();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

        byte[] byteArray = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        EditText editText;
        DatePickerFragment( EditText editText){
            this.editText=editText;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            final Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR,year);
            c.set(Calendar.MONTH,month);
            c.set(Calendar.DAY_OF_MONTH,day);


            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            editText.setText(dateFormat.format(c.getTime()));

        }
    }
    public void openCalendar(View view) {

        DialogFragment newFragment = new DatePickerFragment(dataLAnime);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void openCalendar2(View view) {

        DialogFragment newFragment = new DatePickerFragment(dataEAnime);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


}

