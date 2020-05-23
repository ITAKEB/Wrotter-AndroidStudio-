package com.example.wrotter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wrotter.actividades.SplashActivity;
import com.example.wrotter.clases.ConexionSQliteHelper;
import com.example.wrotter.clases.Utilidades;

public class Test extends AppCompatActivity {

    private ProgressBar pbTiempo;
    private  int id = 0;
    private TextView tvPregunta,tvOpA,tvOpB,tvOpC,tvOpD;
    private ImageButton btAtras;
    private int i=10;
    private int puntaje = 0;
    private CountDownTimer cuenta;
    private CardView cardOpA,cardOpB,cardOpC,cardOpD,cardPregunta;
    private View Correcto,Incorrecto;
    private int num = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        btAtras = findViewById(R.id.btAtrasTest);
        pbTiempo = findViewById(R.id.progressbar);
        Bundle b = getIntent().getExtras();

        num = (int)(Math.random()*Utilidades.listaPreguntas.size()-1)+0;
        //num=0;
        id = b.getInt("Id");
        ConexionSQliteHelper conn = new ConexionSQliteHelper(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {Integer.toString(id)};
        String[] campos = {Utilidades.CAMPO_PUNTAJE};

        final Cursor cursor = db.query(Utilidades.TABLA_JUGADOR, campos, Utilidades.CAMPO_ID + "=?", parametros, null, null, null);
        cursor.moveToFirst();
        puntaje = cursor.getInt(0);


        pbTiempo.setProgress(i);

        LayoutInflater inflater = LayoutInflater.from(Test.this);

        final View vista = inflater.inflate(R.layout.imagen_alert, null);
        Correcto = inflater.inflate(R.layout.alert_correcto, null);
        Incorrecto = inflater.inflate(R.layout.alert_incorrecto, null);


        cuenta = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                //Log.v("Log_tag", "Tick of Progress"+ i+ l);
                pbTiempo.setProgress((int) i * 100 / (10000 / 1000));
                i--;
            }

            @Override
            public void onFinish() {
                i--;
                pbTiempo.setProgress(0);

                AlertDialog.Builder alerta = new AlertDialog.Builder(Test.this);
                alerta.setCancelable(false)
                        .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ConexionSQliteHelper conn = new ConexionSQliteHelper(Test.this, "bd_usuarios", null, 1);
                                SQLiteDatabase db = conn.getWritableDatabase();

                                String[] parametros = {Integer.toString(id)};
                                ContentValues values = new ContentValues();
                                values.put(Utilidades.CAMPO_PUNTAJE, puntaje - 4);

                                db.update(Utilidades.TABLA_JUGADOR, values, Utilidades.CAMPO_ID + "=?", parametros);

                                Intent i = new Intent(Test.this, Home.class);
                                i.putExtra("Id", id);
                                startActivity(i);
                                finish();
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setView(vista);
                titulo.show();
            }
        };
        cuenta.start();

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Test.this);

                alerta.setMessage("Al salir del test, se te restarán puntos de tu total de puntos acumulados \n ¿Desea salir del Test? " ).setCancelable(true)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ConexionSQliteHelper conn = new ConexionSQliteHelper(Test.this, "bd_usuarios", null, 1);
                                SQLiteDatabase db = conn.getWritableDatabase();

                                String[] parametros = {Integer.toString(id)};
                                ContentValues values = new ContentValues();
                                puntaje=puntaje-5;
                                if (puntaje<0){
                                    puntaje=0;
                                }
                                values.put(Utilidades.CAMPO_PUNTAJE, puntaje);

                                db.update(Utilidades.TABLA_JUGADOR, values, Utilidades.CAMPO_ID + "=?", parametros);

                                Intent i = new Intent(Test.this, Home.class);
                                i.putExtra("Id", id);
                                startActivity(i);
                                cuenta.cancel();
                                finish();

                            }
                        })

                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog titulo = alerta.create();
                titulo.setTitle("Salir del Test");
                titulo.show();
            }
        });

        cardPregunta = findViewById(R.id.cardPregunta);
        cardOpA = findViewById(R.id.carOpA);
        cardOpB = findViewById(R.id.carOpB);
        cardOpC = findViewById(R.id.carOpC);
        cardOpD = findViewById(R.id.carOpD);

        tvPregunta = findViewById(R.id.tvPregunta);
        tvOpA = findViewById(R.id.opA);
        tvOpB = findViewById(R.id.opB);
        tvOpC = findViewById(R.id.opC);
        tvOpD = findViewById(R.id.opD);

        tvPregunta.setText(Utilidades.listaPreguntas.get(num).getPregunta());
        tvOpA.setText(Utilidades.listaPreguntas.get(num).getOpA());
        tvOpB.setText(Utilidades.listaPreguntas.get(num).getOpB());
        tvOpC.setText(Utilidades.listaPreguntas.get(num).getOpC());
        tvOpD.setText(Utilidades.listaPreguntas.get(num).getOpD());

        cardOpA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvOpA.getText().equals(Utilidades.listaPreguntas.get(num).getOpCorrecta())) {
                    Correcta();


                } else {
                    Incorrecto();

                }
            }
        });

        cardOpB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvOpB.getText().equals(Utilidades.listaPreguntas.get(num).getOpCorrecta())) {
                    Correcta();

                } else {
                    Incorrecto();
                }
            }
        });

        cardOpC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvOpC.getText().equals(Utilidades.listaPreguntas.get(num).getOpCorrecta())) {
                    Correcta();
                } else {
                    Incorrecto();
                }

            }
        });

        cardOpD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvOpD.getText().equals(Utilidades.listaPreguntas.get(num).getOpCorrecta())) {
                    Correcta();

                } else {
                    Incorrecto();
                }

            }

        });
    }

            public void onBackPressed(){

                AlertDialog.Builder alerta = new AlertDialog.Builder(Test.this);

                alerta.setMessage("Al salir del test, se te restarán puntos de tu total de puntos acumulados \n \n ¿Desea salir del Test? ").setCancelable(true)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ConexionSQliteHelper conn = new ConexionSQliteHelper(Test.this, "bd_usuarios", null, 1);
                                SQLiteDatabase db = conn.getWritableDatabase();

                                String[] parametros = {Integer.toString(id)};
                                ContentValues values = new ContentValues();
                                puntaje=puntaje-4;
                                if (puntaje <=0){
                                    puntaje=0;
                                }
                                values.put(Utilidades.CAMPO_PUNTAJE, puntaje);

                                db.update(Utilidades.TABLA_JUGADOR, values, Utilidades.CAMPO_ID + "=?", parametros);

                                Intent i = new Intent(Test.this, Home.class);
                                i.putExtra("Id", id);
                                startActivity(i);
                                cuenta.cancel();
                                finish();
                            }
                        })

                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog titulo = alerta.create();
                titulo.setTitle("Salir del Test");
                titulo.show();
            };

            public void Correcta() {
                cuenta.cancel();

                AlertDialog.Builder alerta = new AlertDialog.Builder(Test.this);
                alerta.setCancelable(false)
                        .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                ConexionSQliteHelper conn = new ConexionSQliteHelper(Test.this, "bd_usuarios", null, 1);
                                SQLiteDatabase db = conn.getWritableDatabase();

                                String[] parametros = {Integer.toString(id)};
                                ContentValues values = new ContentValues();
                                values.put(Utilidades.CAMPO_PUNTAJE, puntaje + 4);

                                db.update(Utilidades.TABLA_JUGADOR, values, Utilidades.CAMPO_ID + "=?", parametros);
                                Intent i = new Intent(Test.this, Home.class);
                                i.putExtra("Id", id);
                                startActivity(i);
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setTitle("Felicidades!!");
                titulo.setView(Correcto);
                titulo.show();

            }


            public void Incorrecto(){
                cuenta.cancel();
                AlertDialog.Builder alerta = new AlertDialog.Builder(Test.this);
                alerta.setCancelable(false)
                        .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ConexionSQliteHelper conn = new ConexionSQliteHelper(Test.this,"bd_usuarios",null,1);
                                SQLiteDatabase db=conn.getWritableDatabase();

                                String[] parametros={Integer.toString(id)};
                                ContentValues values = new ContentValues();
                                puntaje=puntaje-4;
                                if (puntaje <=0){
                                    puntaje=0;
                                }
                                values.put(Utilidades.CAMPO_PUNTAJE,puntaje);

                                db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);

                                Intent i = new Intent(Test.this,Home.class);
                                i.putExtra("Id",id);
                                startActivity(i);
                                finish();
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setView(Incorrecto);
                titulo.show();
        }

}
