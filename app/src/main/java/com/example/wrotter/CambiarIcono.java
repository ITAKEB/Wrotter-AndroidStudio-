package com.example.wrotter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.wrotter.adapters.AdaptadorAvatar;
import com.example.wrotter.clases.ConexionSQliteHelper;
import com.example.wrotter.clases.Utilidades;
import com.example.wrotter.fragments.InicioFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CambiarIcono extends AppCompatActivity implements com.example.wrotter.fragments.CambiarIcono.OnFragmentInteractionListener{

    Fragment fragmentCambiarIcono;
    ImageButton atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_icono);

        fragmentCambiarIcono = new com.example.wrotter.fragments.CambiarIcono();

        atras = findViewById(R.id.btAtrasCambiarIcono);


        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentsCambiarIcono,fragmentCambiarIcono).commit();


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(CambiarIcono.this,CerrarSesion.class);

                startActivity(i);
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onBackPressed() {
        finish();
        Intent i = new Intent(CambiarIcono.this,CerrarSesion.class);
        startActivity(i);
    }
}
