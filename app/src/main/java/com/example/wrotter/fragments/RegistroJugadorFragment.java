package com.example.wrotter.fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.wrotter.R;
import com.example.wrotter.adapters.AdaptadorAvatar;
import com.example.wrotter.clases.ConexionSQliteHelper;
import com.example.wrotter.clases.Utilidades;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistroJugadorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistroJugadorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroJugadorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    View vista;
    Activity actividad;
    RecyclerView recyclerAvatar;
    ImageButton btAtras;
    FloatingActionButton btRegistrar;
    EditText campoNick;
    EditText campoContraseña;
    EditText campoConfirmarContraseña;


    public RegistroJugadorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroJugadorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroJugadorFragment newInstance(String param1, String param2) {
        RegistroJugadorFragment fragment = new RegistroJugadorFragment();
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

        vista= inflater.inflate(R.layout.fragment_registro_jugador, container, false);
        recyclerAvatar=vista.findViewById(R.id.recyclerAvatarsId);
        recyclerAvatar.setLayoutManager(new GridLayoutManager(this.actividad,2));
        recyclerAvatar.setHasFixedSize(true);
        final AdaptadorAvatar miAdaptador = new AdaptadorAvatar(Utilidades.listaAvatars);
        recyclerAvatar.setAdapter(miAdaptador);


        btAtras = vista.findViewById(R.id.btAtrasRegistro);
        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction  fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.contenedorFragments,new InicioFragment());
                fr.commit();
            }
        });

        btRegistrar = vista.findViewById(R.id.btRegistrarse);
        campoNick = vista.findViewById(R.id.etUsuario);
        campoContraseña = vista.findViewById(R.id.etContraseña);
        campoConfirmarContraseña= vista.findViewById(R.id.etConfirmarContraseña);

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarJugador();
            }
        });

        return vista;
    }

    private void registrarJugador() {

        ConexionSQliteHelper conn = new ConexionSQliteHelper(actividad,"bd_usuarios",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (!campoNick.getText().toString().isEmpty() &&
                !campoContraseña.getText().toString().isEmpty() && !campoConfirmarContraseña.getText().toString().isEmpty()
            && !campoNick.getText().toString().trim().equals("") && !campoContraseña.getText().toString().trim().equals("")
            && !campoConfirmarContraseña.getText().toString().trim().equals("")) {

            if (campoContraseña.getText().toString().equals(campoConfirmarContraseña.getText().toString())){
                values.put(Utilidades.CAMPO_NOMBRE,campoNick.getText().toString());
                values.put(Utilidades.CAMPO_CONTRASEÑA,campoContraseña.getText().toString());
                values.put(Utilidades.CAMPO_AVATAR,Utilidades.avatarIdSeleccion);
                values.put(Utilidades.CAMPO_PUNTAJE,Math.random()*9+1);
                values.put(Utilidades.CAMPO_ULTIMA_PALRABRA,0);
                values.put(Utilidades.CAMPO_PUNTAJE,0);
                values.put(Utilidades.CAMPO_PUNTACION_MAXIMA,0);

                String[] parametros = {campoNick.getText().toString()};
                String prueNombre="";
                String[]campos = {Utilidades.CAMPO_NOMBRE};

                try {
                    Cursor cursor= db.query(Utilidades.TABLA_JUGADOR,campos,Utilidades.CAMPO_NOMBRE+"=?",parametros,null,null,null);

                    cursor.moveToFirst();

                    prueNombre = cursor.getString(0);
                    if (campoNick.getText().toString().equals(prueNombre)) {
                        Toast.makeText(actividad, "Usuario ya registrado", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Long id = db.insert(Utilidades.TABLA_JUGADOR,Utilidades.CAMPO_ID,values);
                    Toast.makeText(actividad,"Registro Exitoso!",Toast.LENGTH_SHORT).show();

                    FragmentTransaction  fr=getFragmentManager().beginTransaction();
                    fr.replace(R.id.contenedorFragments,new InicioFragment());
                    fr.commit();
                }



            }else{
                Toast.makeText(actividad,"Las contraseñas no coinciden",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(actividad,"Rellene todos los campos",Toast.LENGTH_LONG).show();
        }
    }

    public void limpiar(){
        campoNick.setText("");
        campoContraseña.setText("");
        campoConfirmarContraseña.setText("");
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
        if (context instanceof Activity) {
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
