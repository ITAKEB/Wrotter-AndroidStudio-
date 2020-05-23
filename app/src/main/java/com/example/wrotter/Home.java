package com.example.wrotter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wrotter.clases.ConexionSQliteHelper;
import com.example.wrotter.clases.Utilidades;
import com.example.wrotter.clases.vo.AvatarVo;
import com.example.wrotter.fragments.EjemploFragment;
import com.example.wrotter.fragments.PalabrasFragment;
import com.example.wrotter.fragments.RankingFragment;

import java.util.List;

public class Home extends AppCompatActivity implements RankingFragment.OnFragmentInteractionListener ,PalabrasFragment.OnFragmentInteractionListener, EjemploFragment.OnFragmentInteractionListener {

    ImageView icono;
    TextView nombre,puntajeuwu;

    Fragment fragmentRanking ,fragmentPalabras;
    Button btTest;
    CardView perfil;
    Button btRanking;
    public  static int palabra = 0;
    public static int tema=0;
    int puntaje=0;

    public  static int id = 0;
    List<AvatarVo> listaAvatars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Utilidades.obtenerListaAvatars();
        Utilidades.obtenerListaPalabras();
        Utilidades.obtenerListaPreguntas();
        Utilidades.consultarListaEjemplos();

        icono = findViewById(R.id.ivIcono);
        nombre = findViewById(R.id.tvNombre);
        btTest= (Button) findViewById(R.id.btTest);
        perfil = findViewById(R.id.Perfil);
        Utilidades.avatarIdSeleccion=1;

        fragmentPalabras = new PalabrasFragment();


        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentsHome,fragmentPalabras).commit();

        Utilidades.consultarListaJugadores(this);
        btRanking =(Button) findViewById(R.id.btranking);

        perfil = (CardView) findViewById(R.id.Perfil);

        fragmentRanking = new RankingFragment();

        Utilidades.obtenerListaAvatars();

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Home.this,CerrarSesion.class);
                i.putExtra("Id",id);
                startActivity(i);
            }
        });
        btRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilidades.ordenarListaJugadores();
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentsHome,fragmentRanking).commit();
            }
        });

        //puntajeuwu = findViewById(R.id.tvPuntaje);
        Bundle b = getIntent().getExtras();
        id=b.getInt("Id");
        ConexionSQliteHelper conn = new ConexionSQliteHelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        try {
            String[] parametros = {Integer.toString(id)};
            int icon=0;

            int maxima=0;
            String[]campos = {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_AVATAR, Utilidades.CAMPO_PUNTAJE, Utilidades.CAMPO_ULTIMA_PALRABRA,Utilidades.CAMPO_TEMA,Utilidades.CAMPO_PUNTACION_MAXIMA};

            Cursor cursor= db.query(Utilidades.TABLA_JUGADOR,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();

            nombre.setText( cursor.getString(0));
            icon=cursor.getInt(1);
            puntaje=cursor.getInt(2);
            palabra = cursor.getInt(3);
            tema = cursor.getInt(4);
            maxima= cursor.getInt(5);
            //puntajeuwu.setText(Integer.toString(puntaje));

            icono.setImageResource(Utilidades.listaAvatars.get(icon-1).getAvatarId());


            if (tema==1){
                perfil.setCardBackgroundColor(getResources().getColor(R.color.colorOp1));
            }else if (tema==2) {
                perfil.setCardBackgroundColor(getResources().getColor(R.color.colorOp2));
            }else if (tema==3) {
                perfil.setCardBackgroundColor(getResources().getColor(R.color.colorOp3));
            }else if (tema==4) {
                perfil.setCardBackgroundColor(getResources().getColor(R.color.colorOp4));
            }else if (tema==5) {
                perfil.setCardBackgroundColor(getResources().getColor(R.color.colorOp5));
            }else if (tema==6) {
                perfil.setCardBackgroundColor(getResources().getColor(R.color.colorOp6));
            }else{
                perfil.setCardBackgroundColor(getResources().getColor(R.color.colorPrueba));
            }

            if (puntaje>maxima){
                String[] parametros2={Integer.toString(id)};
                ContentValues values = new ContentValues();
                values.put(Utilidades.CAMPO_PUNTACION_MAXIMA,puntaje);

                db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);
            }

            if (puntaje < 0){
                String[] parametros2={Integer.toString(id)};
                ContentValues values = new ContentValues();
                values.put(Utilidades.CAMPO_PUNTAJE,0);

                db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);

            }


        }catch (Exception e){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }


        btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(Home.this,Test.class);
                i.putExtra("Id",id);
                startActivity(i);


            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alerta = new AlertDialog.Builder(Home.this);

        alerta.setMessage("¿Desea salir de la aplicacíón?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })

                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog titulo = alerta.create();
        titulo.setTitle("Salir de la aplicación");
        titulo.show();
    }

    public static int obtenerPalabra(){

        return palabra;
    }

    public static int obtenerTema(){
        return tema;
    }
    public static int obtenerId(){

        return id;
    }


}
