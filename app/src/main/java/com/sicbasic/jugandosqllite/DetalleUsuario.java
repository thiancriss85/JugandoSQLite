package com.sicbasic.jugandosqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.sicbasic.jugandosqllite.entidades.Usuario;

public class DetalleUsuario extends AppCompatActivity {

    TextView campoID, campoNombre, campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        campoID = (TextView)findViewById(R.id.tvIDDetalleUsuario);
        campoNombre = (TextView)findViewById(R.id.tvNombreDetalleUsuario);
        campoTelefono = (TextView)findViewById(R.id.tvTelefonoDetalleUsuario);

        Bundle objetoEnviado = getIntent().getExtras();
        Usuario usuario = null;

        if(objetoEnviado!=null){
            usuario = (Usuario) objetoEnviado.getSerializable("usuario");

            campoID.setText(usuario.getId().toString());
            campoNombre.setText(usuario.getNombre());
            campoTelefono.setText(usuario.getTelefono());

        }
    }
}
