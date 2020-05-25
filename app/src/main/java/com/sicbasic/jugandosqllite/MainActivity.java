package com.sicbasic.jugandosqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

    }

    public void onClick(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.btnRegistrarUsuario:
                //Toast.makeText(getApplicationContext(),"Clic en el boton",Toast.LENGTH_LONG).show();
                intent = new Intent(MainActivity.this,RegistroUsuarios.class);
                break;
            case R.id.btnConsultarUsuario:
                intent = new Intent(MainActivity.this,ConsultarUsuario.class);
                break;
            case R.id.btnConsultarUsuarioSpinner:
                intent = new Intent(MainActivity.this,ConsultaCombo.class);
                break;
            case R.id.btnConsultarUsuarioListView:
                intent = new Intent(MainActivity.this,ConsultaListView.class);
                break;
            case  R.id.btnRegistrarMascota:
                intent = new Intent(MainActivity.this,RegistroMascotas.class);
                break;
            case R.id.btnConsultarMascotas:
                intent= new Intent(MainActivity.this,ConsultaMascotas.class);
                break;
        }
        if(intent!=null){
            startActivity(intent);
        }
    }
}
