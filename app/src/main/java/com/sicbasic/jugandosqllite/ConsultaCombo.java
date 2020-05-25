package com.sicbasic.jugandosqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.sicbasic.jugandosqllite.entidades.Usuario;
import com.sicbasic.jugandosqllite.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultaCombo extends AppCompatActivity {

    Spinner comboUsuario;
    TextView tvID,tvNombre,tvTelefono;
    ArrayList<String> listaUsuario;
    ArrayList<Usuario> usuariosList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_combo);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        comboUsuario = (Spinner)findViewById(R.id.spUsuariosConsultaSpinner);
        tvID = (TextView)findViewById(R.id.tvIDConsultaSpinner);
        tvNombre = (TextView)findViewById(R.id.tvNombreConsultaSpinner);
        tvTelefono = (TextView)findViewById(R.id.tvTelefonoConsultaSpinner);

        consultarListaPersonas();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaUsuario);
        comboUsuario.setAdapter(adaptador);

        comboUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position!=0) {
                    tvID.setText(usuariosList.get(position - 1).getId().toString());
                    tvNombre.setText(usuariosList.get(position - 1).getNombre().toString());
                    tvTelefono.setText(usuariosList.get(position - 1).getTelefono());
                }else{
                    tvID.setText("");
                    tvNombre.setText("");
                    tvTelefono.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario usuario = null;
        usuariosList = new ArrayList<Usuario>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            usuariosList.add(usuario);
        }

        obtenetLista();

    }

    private void obtenetLista() {
        listaUsuario = new ArrayList<String>();
        listaUsuario.add("Seleccione un usuario");

        for (int i=0;i<usuariosList.size();i++){
            listaUsuario.add(usuariosList.get(i).getId()+" - "+usuariosList.get(i).getNombre());
        }
    }

}
