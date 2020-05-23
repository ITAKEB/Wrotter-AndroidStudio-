package com.example.wrotter.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wrotter.Home;
import com.example.wrotter.R;
import com.example.wrotter.clases.ConexionSQliteHelper;
import com.example.wrotter.clases.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InicioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Activity actividad;
    private  View vista2;

    private EditText Nick;
    private EditText Contraseña;

    Button btingresar;
    Button btIniciar;


    private OnFragmentInteractionListener mListener;

    public InicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
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

        vista2 =inflater.inflate(R.layout.fragment_inicio, container, false);
        btingresar = vista2.findViewById(R.id.btIngresar);
        btIniciar = vista2.findViewById(R.id.btIniciar);






        Nick = vista2.findViewById(R.id.etNick);
        Contraseña = vista2.findViewById(R.id.etContraseña);

        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                consultar();




            }
        });


        btingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction  fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.contenedorFragments,new RegistroJugadorFragment());
                fr.commit();

            }
        });


        return vista2;
    }

    private  void consultar(){
        ConexionSQliteHelper conn = new ConexionSQliteHelper(actividad,"bd_usuarios",null,1);
        SQLiteDatabase db= conn.getWritableDatabase();

        String nombre = Nick.getText().toString();
        String contras = Contraseña.getText().toString();



        String[] parametros = {Nick.getText().toString()};
        String[] campos = {Utilidades.CAMPO_ID,Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_CONTRASEÑA};

        if ( Nick.getText().toString().isEmpty() || Contraseña.getText().toString().isEmpty() ||
            Nick.getText().toString().trim().equals("") || Contraseña.getText().toString().trim().equals("")){

            Toast.makeText(actividad,"Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            try{
                int id ;
                String prueContraseña="";
                Cursor cursor= db.query(Utilidades.TABLA_JUGADOR,campos,Utilidades.CAMPO_NOMBRE+"=?",parametros,null,null,null);
                cursor.moveToFirst();
                id=cursor.getInt(0);
                prueContraseña=cursor.getString(2);
                if (prueContraseña.equals(contras)){
                    //  Toast.makeText(actividad,"Se encontró",Toast.LENGTH_SHORT).show();



                    Intent i = new Intent(actividad, Home.class);
                    i.putExtra("Id",id);
                    startActivity(i);
                    Toast.makeText(actividad,"Bienvenido(a) "+Nick.getText().toString()+"!",Toast.LENGTH_SHORT).show();
                    Nick.setText("");
                    Contraseña.setText("");
                }else {
                    Toast.makeText(actividad,"Contraseña incorrecta ",Toast.LENGTH_SHORT).show();
                    Contraseña.setText("");
                }


            }catch (Exception e){
                Toast.makeText(actividad,"Usuario Inexistente",Toast.LENGTH_SHORT).show();
                limpiar();
            }
        }

    }

    private void limpiar() {
        Nick.setText("");
        Contraseña.setText("");
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
