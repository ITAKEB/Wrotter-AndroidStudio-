package com.example.wrotter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wrotter.clases.ConexionSQliteHelper;
import com.example.wrotter.clases.Utilidades;
import com.example.wrotter.clases.vo.AprendidasVo;
import com.example.wrotter.fragments.InicioFragment;
import com.example.wrotter.fragments.RegistroJugadorFragment;

public class MainActivity extends AppCompatActivity implements InicioFragment.OnFragmentInteractionListener,RegistroJugadorFragment.OnFragmentInteractionListener{


    Fragment fragmentInicio,registroJugadorFragment;
    Button btRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ConexionSQliteHelper conn = new ConexionSQliteHelper(this,Utilidades.NOMBRE_BD,null,1)


        Utilidades.consultarListaJugadores(this);
        Utilidades.consultarListaPalabras(this);

        for (int i = 0;i<Utilidades.listaAprendidas.size();i++){
            System.out.println(Utilidades.listaAprendidas.get(i).getId_jugador()+" Palabra "+Utilidades.listaAprendidas.get(i).getId_palabra());
        }

        //System.out.println(Utilidades.listaJugad ores.get(0).getNombre());
        //ArrayList<Usuario> list = Utilidades.getListaUsuarios();
        Utilidades.obtenerListaAvatars();
        //System.out.println(Utilidades.listaUsuarios.get(1).getNombre());
        Utilidades.avatarIdSeleccion=1;
        registroJugadorFragment = new RegistroJugadorFragment();
        fragmentInicio = new InicioFragment();
        btRegistrar = (Button) findViewById(R.id.btIngresar);

        /*btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onBackPressed() {

        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);

        alerta.setMessage("¿Desea salir de la aplicación?").setCancelable(false)
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
}
