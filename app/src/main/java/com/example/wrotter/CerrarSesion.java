package com.example.wrotter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.wrotter.clases.ConexionSQliteHelper;
import com.example.wrotter.clases.Utilidades;

public class CerrarSesion extends AppCompatActivity {

    Fragment fragmentRanking ;
    int id;

    Button cerrar,btCambiarIcono,btCambiarNombre,btCambiarContraseña,btPersonalizar;
    ImageButton btAtras;
    TextView tvPuntajeM,tvNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_sesion);

        id= Home.obtenerId();
        tvPuntajeM = findViewById(R.id.tvPuntajeMaxima);
        tvNombre = findViewById(R.id.tvNombre2);
        btCambiarIcono = findViewById(R.id.btCambiarIcono);
        btPersonalizar = findViewById(R.id.btPersonalizar);
        btCambiarNombre = findViewById(R.id.btCambiarNombre);
        btCambiarContraseña = findViewById(R.id.btCambiarContraseña);

        btPersonalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(CerrarSesion.this,Personalizar.class);
                startActivity(i);
            }
        });

        btCambiarIcono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(CerrarSesion.this,CambiarIcono.class);
                startActivity(i);
            }
        });

        btCambiarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(CerrarSesion.this,CambiarNombre.class);
                startActivity(i);
            }
        });
        btCambiarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(CerrarSesion.this,CambiarContra.class);
                startActivity(i);
            }
        });

        ConexionSQliteHelper conn = new ConexionSQliteHelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        String[] parametros = {Integer.toString(id)};
        String nombre="";
        int puntaje=0;
        String[]campos = {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_PUNTACION_MAXIMA};

        Cursor cursor= db.query(Utilidades.TABLA_JUGADOR,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
        cursor.moveToFirst();
        nombre=cursor.getString(0);
        puntaje=cursor.getInt(1);

        tvNombre.setText(nombre);
        tvPuntajeM.setText(Integer.toString(puntaje));


        cerrar= (Button) findViewById(R.id.btCerrar);
        btAtras=(ImageButton) findViewById(R.id.btAtrasCerrarSesion);

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent( CerrarSesion.this,Home.class);
                i.putExtra("Id",id);
                startActivity(i);

            }
        });
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(CerrarSesion.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent( CerrarSesion.this,Home.class);
        i.putExtra("Id",id);
        startActivity(i);
    }
}
