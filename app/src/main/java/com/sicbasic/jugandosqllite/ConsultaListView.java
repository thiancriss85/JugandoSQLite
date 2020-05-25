package com.sicbasic.jugandosqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sicbasic.jugandosqllite.entidades.Usuario;
import com.sicbasic.jugandosqllite.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultaListView extends AppCompatActivity {

    ListView listViewUsuarios;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_list_view);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        listViewUsuarios = (ListView)findViewById(R.id.lvConsultaUsuariosListView);

        consultaListaUsuarios();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewUsuarios.setAdapter(adaptador);

        listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = "id: :"+listaUsuarios.get(position).getId()+"\n";
                informacion +="Nombre: "+listaUsuarios.get(position).getNombre()+"\n";
                informacion+="Telefono: "+listaUsuarios.get(position).getTelefono();

                //Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_SHORT).show();

                Usuario usuario = listaUsuarios.get(position);

                Intent intent = new Intent(ConsultaListView.this,DetalleUsuario.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario",usuario);

                intent.putExtras(bundle);

                startActivity(intent);

            }
        });

    }

    private void consultaListaUsuarios() {

        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario usuario =null;
        listaUsuarios = new ArrayList<Usuario>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            listaUsuarios.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {

        listaInformacion = new ArrayList<String>();

        for (int i=0;i<listaUsuarios.size();i++) {
            listaInformacion.add(listaUsuarios.get(i).getId() + " - " + listaUsuarios.get(i).getNombre());
        }

    }
}
