package com.example.wrotter.fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wrotter.CerrarSesion;
import com.example.wrotter.Home;
import com.example.wrotter.R;
import com.example.wrotter.clases.ConexionSQliteHelper;
import com.example.wrotter.clases.Utilidades;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PalabrasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PalabrasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PalabrasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View vista;
    private CardView btEjemplo;
    private Button btAprendida;
    private Button btConfusa;
    private TextView tvPalabra,tv,Definicion;
    Activity actividad;
    int anterior =0;
    int nPalabra = 0;
    int palabrasAprendidas =0;
    int id = 0;
    int anterior2 = 0;
    ArrayList anteriores= new ArrayList();
    boolean canti = true;
    public PalabrasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PalabrasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PalabrasFragment newInstance(String param1, String param2) {
        PalabrasFragment fragment = new PalabrasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista= inflater.inflate(R.layout.fragment_palabras, container, false);


        final ConexionSQliteHelper conn = new ConexionSQliteHelper(actividad,"bd_usuarios",null,1);
        final SQLiteDatabase db=conn.getWritableDatabase();
        final String[] parametros = {Integer.toString(id)};

        int tema=Home.tema;

        tvPalabra= vista.findViewById(R.id.tvPalara);
        Definicion = vista.findViewById(R.id.tvDefinicion);
        btEjemplo= (CardView) vista.findViewById(R.id.btEjemplo);
        btAprendida= vista.findViewById(R.id.btAprendida);
        btConfusa= vista.findViewById(R.id.btConfusa);

        id=Home.obtenerId();
        anteriores.clear();



        if (tema==1){
            btAprendida.setBackgroundColor(getResources().getColor(R.color.colorOp1));
            btConfusa.setBackgroundColor(getResources().getColor(R.color.colorOp1));
        }else if (tema==2) {
            btAprendida.setBackgroundColor(getResources().getColor(R.color.colorOp2));
            btConfusa.setBackgroundColor(getResources().getColor(R.color.colorOp2));
        }else if (tema==3) {
            btAprendida.setBackgroundColor(getResources().getColor(R.color.colorOp3));
            btConfusa.setBackgroundColor(getResources().getColor(R.color.colorOp3));
        }else if (tema==4) {
            btAprendida.setBackgroundColor(getResources().getColor(R.color.colorOp4));
            btConfusa.setBackgroundColor(getResources().getColor(R.color.colorOp4));
        }else if (tema==5) {
            btAprendida.setBackgroundColor(getResources().getColor(R.color.colorOp5));
            btConfusa.setBackgroundColor(getResources().getColor(R.color.colorOp5));
        }else if (tema==6) {
            btAprendida.setBackgroundColor(getResources().getColor(R.color.colorOp6));
            btConfusa.setBackgroundColor(getResources().getColor(R.color.colorOp6));
        }else{
            btAprendida.setBackgroundColor(getResources().getColor(R.color.colorPrueba));
            btConfusa.setBackgroundColor(getResources().getColor(R.color.colorPrueba));
        }

        final String[] parametros2 = {Integer.toString(id)};
        final String[] campos2={Utilidades.CAMPO_ULTIMA_PALRABRA};
        ContentValues values = new ContentValues();
        /*values.put(Utilidades.CAMPO_ULTIMA_PALRABRA,2);
        db.update(Utilidades.TABLA_JUGADOR, values, Utilidades.CAMPO_ID + "=?", parametros2);*/


        Cursor cursor =db.query(Utilidades.TABLA_JUGADOR,campos2,Utilidades.CAMPO_ID+"=?",parametros2,null,null,null);
        cursor.moveToFirst();
        anterior=cursor.getInt(0);

        tvPalabra.setText(Utilidades.listaPalabras.get(anterior).getPalabra());
        Definicion.setText(Utilidades.listaPalabras.get(anterior).getDefinicion());



        //System.out.println("1 "+anterior);


        btAprendida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (anterior == Utilidades.listaPalabras.size()-1){
                    anterior=0;
                    tvPalabra.setText(Utilidades.listaPalabras.get(anterior).getPalabra());
                    Definicion.setText(Utilidades.listaPalabras.get(anterior).getDefinicion());
                }else{
                    anterior=anterior+1;
                    tvPalabra.setText(Utilidades.listaPalabras.get(anterior).getPalabra());
                    Definicion.setText(Utilidades.listaPalabras.get(anterior).getDefinicion());
                }
                ConexionSQliteHelper conn = new ConexionSQliteHelper(actividad,"bd_usuarios",null,1);
                SQLiteDatabase db=conn.getWritableDatabase();
                ContentValues values = new ContentValues();
                id= Home.obtenerId();

                String[] parametros={Integer.toString(id)};
                values.put(Utilidades.CAMPO_ULTIMA_PALRABRA,anterior);

                db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);
            }
        });

        btConfusa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Cursor cursor = db.query(Utilidades.TABLA_JUGADOR,campos2,Utilidades.CAMPO_ID+"=?",parametros2,null,null,null);
                cursor.moveToFirst();
                System.out.println(cursor.getString(0));
                anterior=cursor.getInt(0);*/
                System.out.println(anterior);
                if (anterior==0){
                    anterior=Utilidades.listaPalabras.size()-1;
                    tvPalabra.setText(Utilidades.listaPalabras.get(anterior).getPalabra());
                    Definicion.setText(Utilidades.listaPalabras.get(anterior).getDefinicion());
                }else{
                    anterior=anterior-1;
                    tvPalabra.setText(Utilidades.listaPalabras.get(anterior).getPalabra());
                    Definicion.setText(Utilidades.listaPalabras.get(anterior).getDefinicion());
                }
                ConexionSQliteHelper conn = new ConexionSQliteHelper(actividad,"bd_usuarios",null,1);
                SQLiteDatabase db=conn.getWritableDatabase();
                ContentValues values = new ContentValues();
                id= Home.obtenerId();

                String[] parametros={Integer.toString(id)};
                values.put(Utilidades.CAMPO_ULTIMA_PALRABRA,anterior);

                db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);


            }
        });





        btEjemplo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.contenedorFragmentsHome,new EjemploFragment());
                fr.commit();
            }
        });



        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            this.actividad = (Activity) context;
        }
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
