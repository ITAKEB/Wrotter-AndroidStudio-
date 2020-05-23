package com.example.wrotter.fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wrotter.CerrarSesion;
import com.example.wrotter.Home;
import com.example.wrotter.Personalizar;
import com.example.wrotter.R;
import com.example.wrotter.adapters.AdaptadorAvatar;
import com.example.wrotter.clases.ConexionSQliteHelper;
import com.example.wrotter.clases.Utilidades;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CambiarIcono.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CambiarIcono#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CambiarIcono extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerAvatars;
    private FloatingActionButton btCambiar;
    private Activity actividad;
    private View vista;
    private int id;

    public CambiarIcono() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CambiarIcono.
     */
    // TODO: Rename and change types and number of parameters
    public static CambiarIcono newInstance(String param1, String param2) {
        CambiarIcono fragment = new CambiarIcono();
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
        vista =inflater.inflate(R.layout.fragment_cambiar_icono, container, false);
        recyclerAvatars=vista.findViewById(R.id.recyclerAvatars);
        recyclerAvatars.setLayoutManager(new GridLayoutManager(this.actividad,1));
        recyclerAvatars.setHasFixedSize(true);
        final AdaptadorAvatar miAdaptador = new AdaptadorAvatar(Utilidades.listaAvatars);
        recyclerAvatars.setAdapter(miAdaptador);

        btCambiar = vista.findViewById(R.id.btCambiar);

        btCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConexionSQliteHelper conn = new ConexionSQliteHelper(actividad,"bd_usuarios",null,1);
                SQLiteDatabase db=conn.getWritableDatabase();
                ContentValues values = new ContentValues();
                id= Home.obtenerId();

                String[] parametros={Integer.toString(id)};
                values.put(Utilidades.CAMPO_AVATAR,Utilidades.avatarIdSeleccion);

                db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);
                Intent i = new Intent(actividad, CerrarSesion.class);
                Toast.makeText(actividad,"Cambio exitoso!",Toast.LENGTH_SHORT).show();
                startActivity(i);


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
