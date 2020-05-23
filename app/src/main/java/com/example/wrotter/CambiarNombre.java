package com.example.wrotter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.wrotter.clases.ConexionSQliteHelper;
import com.example.wrotter.clases.Utilidades;

public class CambiarNombre extends AppCompatActivity {

    private int id=0;
    ImageButton atras;
    EditText etNuevoNombre,etConfirmar;
    Button btConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_nombre);
        id= Home.obtenerId();
        etNuevoNombre = findViewById(R.id.etNuevoNombre);
        etConfirmar = findViewById(R.id.etConfirmar);
        btConfirmar = findViewById(R.id.btCambiar);
        atras = findViewById(R.id.btAtrasCambiarNombre);

        ConexionSQliteHelper conn = new ConexionSQliteHelper(this,"bd_usuarios",null,1);
        final SQLiteDatabase db=conn.getWritableDatabase();

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etNuevoNombre.getText().toString().isEmpty() && !etConfirmar.getText().toString().isEmpty()
                        && !etConfirmar.getText().toString().trim().equals("") && !etNuevoNombre.getText().toString().trim().equals("")){
                    if (etNuevoNombre.getText().toString().equals(etConfirmar.getText().toString())){
                        try {
                            String[]campos = {Utilidades.CAMPO_NOMBRE};
                            String[]parametros ={etNuevoNombre.getText().toString()};
                            String prueNombre ="";

                            Cursor cursor= db.query(Utilidades.TABLA_JUGADOR,campos,Utilidades.CAMPO_NOMBRE+"=?",parametros,null,null,null);

                            cursor.moveToFirst();

                            prueNombre = cursor.getString(0);
                            if (etNuevoNombre.getText().toString().equals(prueNombre)) {
                                Toast.makeText(CambiarNombre.this, "Usuario ya registrado \n Intenta uno nuevo", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            finish();
                            String[] parametros={Integer.toString(id)};
                            ContentValues values = new ContentValues();
                            values.put(Utilidades.CAMPO_NOMBRE,etNuevoNombre.getText().toString());


                            db.update(Utilidades.TABLA_JUGADOR,values,Utilidades.CAMPO_ID+"=?",parametros);
                            Intent i = new Intent(CambiarNombre.this,CerrarSesion.class);
                            Toast.makeText(CambiarNombre.this,"Cambio exitoso!",Toast.LENGTH_SHORT).show();
                            etNuevoNombre.setText("");
                            etConfirmar.setText("");
                            startActivity(i);
                        }
                    }else{
                        Toast.makeText(CambiarNombre.this,"Los campos no coinciden",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CambiarNombre.this,"Rellena todos los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(CambiarNombre.this,CerrarSesion.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(CambiarNombre.this,CerrarSesion.class);
        startActivity(i);
    }
}
