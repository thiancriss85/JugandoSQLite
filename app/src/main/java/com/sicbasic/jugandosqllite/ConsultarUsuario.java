package com.sicbasic.jugandosqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sicbasic.jugandosqllite.utilidades.Utilidades;

public class ConsultarUsuario extends AppCompatActivity {

    EditText campoID,campoNombre,campoTelefono;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        campoID = (EditText)findViewById(R.id.etIdConsultaUsuario);
        campoNombre=(EditText)findViewById(R.id.etNombreConsultaUsuario);
        campoTelefono=(EditText)findViewById(R.id.etTelefonoConsultaUsuario);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnBuscarConsultaUsuario:
                //consultar();
                consultaSQL();
                break;
            case R.id.btnActualizarConsultaUsuario:
                actualizar();
                limpiar();
                break;
            case R.id.btnEliminarConsultaUsuario:
                eliminar();
                limpiar();
                break;
        }

    }

    private void eliminar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String [] parametros = {campoID.getText().toString()};

        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Registro eliminado",Toast.LENGTH_LONG).show();

        db.close();
    }

    private void actualizar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String [] parametros = {campoID.getText().toString()};
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());

        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Registro actualizado",Toast.LENGTH_LONG).show();

        db.close();
    }

    private void consultaSQL() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String [] parametros = {campoID.getText().toString()};

        try {
            Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+" FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.CAMPO_ID+"=?",parametros);
            cursor.moveToFirst();

            campoNombre.setText(cursor.getString(0));
            campoTelefono.setText(cursor.getString(1));

            cursor.close();
        }catch (Exception e){
            Log.i("Pruebas",e.getMessage());
            Toast.makeText(getApplicationContext(),"El documento no existe en la BD",Toast.LENGTH_LONG).show();
            limpiar();
        }

    }

    private void consultar() {
        SQLiteDatabase db = conn.getWritableDatabase();

        String [] parametros = {campoID.getText().toString()};
        String [] campos = {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};

        try {
            Cursor cursor;
            cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_ID + "=?", parametros, null, null, null);
            cursor.moveToFirst();

            campoNombre.setText(cursor.getString(0));
            campoTelefono.setText(cursor.getString(1));

            cursor.close();
        }catch (Exception e){
            Log.i("Pruebas",e.getMessage());
            Toast.makeText(getApplicationContext(),"El documento no existe en la BD",Toast.LENGTH_LONG).show();
            limpiar();
        }

    }

    private void limpiar() {
        campoID.setText("");
        campoNombre.setText("");
        campoTelefono.setText("");
    }

}
