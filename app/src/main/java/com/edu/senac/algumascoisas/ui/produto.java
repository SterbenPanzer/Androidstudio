package com.edu.senac.algumascoisas.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.senac.algumascoisas.R;

import java.util.ArrayList;
import java.util.List;

public class produto extends AppCompatActivity {

    ImageView imagem;
    Spinner spinnerConvert;
    TextView textNome, textQuant;
    Button btnSave;
    boolean imagemBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        spinnerConvert = findViewById(R.id.spinnerConvert);
        imagem = findViewById(R.id.imagem);
        textNome = findViewById(R.id.textNome);
        textQuant = findViewById(R.id.textQuant);
        btnSave = findViewById(R.id.btnSave);


    }

    public void validar(View v) {

        //verifica os campos
        String mensagem = validarCampos();
        //caso seja verdadeiro salvar os campos
        if (mensagem.length() == 0) {
            //chama a função para salvar os campos
            salvarFormulario();
        } else {
            //chama função para exibir as mensagem
            mensagemErro(mensagem);
        }
    }

    public void salvarFormulario() {
        Toast.makeText(produto.this, "Salvado com sucesso!!!", Toast.LENGTH_SHORT).show();
    }

    public String validarCampos() {
        if (!imagemBoolean) {
            Log.d("save", "Imagem não selecionada.");
            return "Imagem não selecionada.";
        }
        if (textNome.getText().toString().length() == 0) {
            Log.d("save", "O campo nome está vazio.");
            return "O campo nome está vazio.";
        }
        if (textQuant.getText().toString().length() == 0 || Integer.parseInt(textQuant.getText().toString()) <=0 ) {
            Log.d("save", "O campo quantidade está vazio.");
            return "O campo quantidade está vazio ou não atende aos requisitos.";
        } else {
            return "";
        }
    }

    public void mensagemErro(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção");
        builder.setMessage(mensagem);
        //define um botão como positivo.
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(produto.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(produto.this, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        builder.create().show();
    }

    public void tirarFoto(View v) {

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
            imagem.setImageBitmap(imageBitmap);
            imagemBoolean = true;
        }
    }

    public boolean checkAndRequestPermissions() {

        int camera = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        List<String> ListPermissionsNeeded = new ArrayList<>();

        if (camera != PackageManager.PERMISSION_GRANTED) {
            ListPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (!ListPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, ListPermissionsNeeded.toArray(new String[ListPermissionsNeeded.size()]), 1);
            return false;
        }
        return true;
    }
}
