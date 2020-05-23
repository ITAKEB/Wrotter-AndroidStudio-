package com.example.wrotter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.wrotter.clases.ConexionSQliteHelper;
import com.example.wrotter.clases.Utilidades;

public class Personalizar extends AppCompatActivity {

    Button bt1,bt2,bt3,bt4,bt5,bt6,pre;
    ImageButton atras;
    int id= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar);

        atras= findViewById(R.id.btAtrasPersonalizar);
        pre= findViewById(R.id.btPredeterminado);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);

        id=Home.obtenerId();


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Personalizar.this,CerrarSesion.class);
                startActivity(i);
            }
        });

        ConexionSQliteHelper conn = new ConexionSQliteHelper(this,"bd_usuarios",null,1);
        final SQLiteDatabase db=conn.getWritableDatabase();


        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                String[] parametros={Integer.toString(id)};
                ContentValues values = new ContentValues();
                values.put(Utilidades.CAMPO_TEMA,0);

                db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);
                Intent i = new Intent(Personalizar.this,CerrarSesion.class);
                Toast.makeText(Personalizar.this,"Cambio exitoso!",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                String[] parametros={Integer.toString(id)};
                ContentValues values = new ContentValues();
                values.put(Utilidades.CAMPO_TEMA,1);

                db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);
                Intent i = new Intent(Personalizar.this,CerrarSesion.class);
                Toast.makeText(Personalizar.this,"Cambio exitoso!",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                String[] parametros={Integer.toString(id)};
                ContentValues values = new ContentValues();
                values.put(Utilidades.CAMPO_TEMA,2);

                db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);
                Intent i = new Intent(Personalizar.this,CerrarSesion.class);
                Toast.makeText(Personalizar.this,"Cambio exitoso!",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                String[] parametros={Integer.toString(id)};
                ContentValues values = new ContentValues();
                values.put(Utilidades.CAMPO_TEMA,3);

                db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);
                Intent i = new Intent(Personalizar.this,CerrarSesion.class);
                Toast.makeText(Personalizar.this,"Cambio exitoso!",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                String[] parametros={Integer.toString(id)};
                ContentValues values = new ContentValues();
                values.put(Utilidades.CAMPO_TEMA,4);

                db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);
                Intent i = new Intent(Personalizar.this,CerrarSesion.class);
                Toast.makeText(Personalizar.this,"Cambio exitoso!",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                String[] parametros={Integer.toString(id)};
                ContentValues values = new ContentValues();
                values.put(Utilidades.CAMPO_TEMA,5);

                db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);
                Intent i = new Intent(Personalizar.this,CerrarSesion.class);
                Toast.makeText(Personalizar.this,"Cambio exitoso!",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                String[] parametros={Integer.toString(id)};
                ContentValues values = new ContentValues();
                values.put(Utilidades.CAMPO_TEMA,6);

                db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);
                Intent i = new Intent(Personalizar.this,CerrarSesion.class);
                Toast.makeText(Personalizar.this,"Cambio exitoso!",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(Personalizar.this,CerrarSesion.class);
        startActivity(i);
    }
}
