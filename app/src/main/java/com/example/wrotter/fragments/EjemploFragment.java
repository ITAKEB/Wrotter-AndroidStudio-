package com.example.wrotter.fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
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
import android.widget.TextView;

import com.example.wrotter.Home;
import com.example.wrotter.R;
import com.example.wrotter.clases.ConexionSQliteHelper;
import com.example.wrotter.clases.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EjemploFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EjemploFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EjemploFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View vista;
    private CardView btRegresar;

    private TextView tvEjemplo1,tvEjemplo2;

    private Activity actividad;

    private int id =0;
    public EjemploFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EjemploFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EjemploFragment newInstance(String param1, String param2) {
        EjemploFragment fragment = new EjemploFragment();
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
        vista =inflater.inflate(R.layout.fragment_ejemplo, container, false);


        id= Home.obtenerId();
        tvEjemplo1= vista.findViewById(R.id.tvEjemplo1);
        tvEjemplo2= vista.findViewById(R.id.tvEjemplo2);


        ConexionSQliteHelper conn = new ConexionSQliteHelper(actividad,"bd_usuarios",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        id= Home.obtenerId();
        String[] parametros={Integer.toString(id)};
        String[] campos={Utilidades.CAMPO_ULTIMA_PALRABRA};

        int num= 0;
        Cursor cursor =db.query(Utilidades.TABLA_JUGADOR,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
        cursor.moveToFirst();
        num= cursor.getInt(0);

        tvEjemplo1.setText(Utilidades.listaEjemplos.get(num).getEjemplo1());
        tvEjemplo2.setText(Utilidades.listaEjemplos.get(num).getEjemplo2());




        btRegresar = vista.findViewById(R.id.btRegresar);
        btRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.contenedorFragmentsHome,new PalabrasFragment());
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
        if (context instanceof PalabrasFragment.OnFragmentInteractionListener) {
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
