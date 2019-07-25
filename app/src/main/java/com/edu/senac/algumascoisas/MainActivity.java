package com.edu.senac.algumascoisas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editSenha,editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity","Passando pelo método onCreate ...");
        setContentView(R.layout.activity_main);

        editSenha = findViewById(R.id.editSenha);
        editEmail=findViewById(R.id.editEmail);
    }

    public void onStart(){
        super.onStart();
        Log.i("MainActivity","Passando pelo método onStart ...");
    }

    public void onRestart(){
        super.onRestart();
        Log.i("MainActivity","Passando pelo método onRestart ...");
    }

    public void onStop(){
        super.onStop();
        Log.i("MainActivity","Passando pelo método onStop ...");
    }

    public void onResume(){
        super.onResume();
        Log.i("MainActivity","Passando pelo método onResume ...");
    }

    public void onPause(){
        super.onPause();
        Log.i("MainActivity","Passando pelo método onPause ...");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.i("MainActivity","Passando pelo método onDestroy ...");
    }

    public void irParaprincipal(View v){
        String email=editEmail.getText().toString();
        String senha=editSenha.getText().toString();
        if(email.equals("FelipeEduardo") && senha.equals("123")){
            Intent i=new Intent(this,principal.class);
            startActivity(i);
        }else{
            Toast.makeText( this,  "Usuario incorreto" , Toast.LENGTH_SHORT).show();
        }
    }
}
