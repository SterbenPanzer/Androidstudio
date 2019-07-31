package com.edu.senac.algumascoisas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.edu.senac.algumascoisas.R;

import java.util.ArrayList;
import java.util.List;

public class Sobre extends AppCompatActivity {
    ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        imagem=findViewById(R.id.imagem);
    }

    public void tirarFoto(View v){

        if (checkAndRequestPermissions()) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(takePictureIntent, 100);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagem.setImageBitmap(imageBitmap);
        }
    }

    public boolean checkAndRequestPermissions() {

        int camera = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA);
        List<String> ListPermissionsNeeded = new ArrayList<>();

        if (camera != PackageManager.PERMISSION_GRANTED){
            ListPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (!ListPermissionsNeeded.isEmpty()){
            ActivityCompat.requestPermissions( this,ListPermissionsNeeded.toArray(new String[ListPermissionsNeeded.size()]), 1);
            return false;
        }
        return true;
    }
}
