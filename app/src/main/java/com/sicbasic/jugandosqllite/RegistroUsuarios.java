package com.sicbasic.jugandosqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sicbasic.jugandosqllite.utilidades.Utilidades;

public class RegistroUsuarios extends AppCompatActivity {

    EditText campoID,campoNombre,campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        campoID = (EditText) findViewById(R.id.etIdRegistro);
        campoNombre = (EditText) findViewById(R.id.etNombreRegistro);
        campoTelefono = (EditText) findViewById(R.id.etTelefonoRegistro);

    }

    public void onClick(View view){
        registrarUsuarios();
        limpiar();
        //registrarUsuariosSQL();
    }

    private void limpiar() {
        campoID.setText("");
        campoNombre.setText("");
        campoTelefono.setText("");
    }

    private void registrarUsuariosSQL() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        String insert = "INSERT INTO "+Utilidades.TABLA_USUARIO+" ("+Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+") values ( "+
                campoID.getText().toString()+",'"+campoNombre.getText().toString()+"','"+campoID.getText().toString()+"')";

        Log.i("Pruebas",insert);

        db.execSQL(insert);
        db.close();
    }

    private void registrarUsuarios() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID,campoID.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(),"Id Registro: ( "+idResultante+" )",Toast.LENGTH_LONG).show();

        db.close();
    }
}
