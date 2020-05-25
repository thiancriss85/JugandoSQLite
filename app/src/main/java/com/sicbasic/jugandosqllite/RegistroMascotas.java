package com.sicbasic.jugandosqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sicbasic.jugandosqllite.entidades.Usuario;
import com.sicbasic.jugandosqllite.utilidades.Utilidades;

import java.util.ArrayList;

public class RegistroMascotas extends AppCompatActivity {

    EditText campoNombreMascota, campoRazaMascota;
    Spinner campoIdDueno;

    ArrayList<String> listaUsuarios;
    ArrayList<Usuario> usuarios;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascotas);

        campoNombreMascota = (EditText) findViewById(R.id.etNombreRegistroMascotas);
        campoRazaMascota= (EditText) findViewById(R.id.etRazaRegistroMascotas);
        campoIdDueno = (Spinner)findViewById(R.id.spDuenoRegistroMascotas);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        consultarListaPersonas();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaUsuarios);
        campoIdDueno.setAdapter(adapter);

        campoIdDueno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void consultarListaPersonas() {

    }

    private void registroMascota() {

        SQLiteDatabase db =  conn.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_MASCOTA,campoNombreMascota.getText().toString());
        values.put(Utilidades.CAMPO_RAZA_MASCOTA,campoRazaMascota.getText().toString());

        int idSpinner= (int) campoIdDueno.getSelectedItem();

        if (idSpinner!=0){
            int idDueno = usuarios.get(idSpinner-1).getId();
            values.put(Utilidades.CAMPO_ID_DUENO,idDueno);

            Long idResultante = db.insert(Utilidades.TABLA_MASCOTA,Utilidades.CAMPO_ID_MASCOTA,values);

            Toast.makeText(getApplicationContext(),"Mascota registrada con Exito!!! ("+idResultante.toString()+")",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(),"Debe seleccinar un dueno",Toast.LENGTH_SHORT).show();
        }
    }
}
