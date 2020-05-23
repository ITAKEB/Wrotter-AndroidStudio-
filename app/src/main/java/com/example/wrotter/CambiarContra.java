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

public class CambiarContra extends AppCompatActivity {
    private int id=0;
    ImageButton atras;
    Button btCambiar;

    EditText contraAntigua,contraNueva,confirmarContra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contra);

        id = Home.obtenerId();
        atras = findViewById(R.id.btAtrasCambiarContraseña);
        btCambiar = findViewById(R.id.btCambiarContra);
        contraAntigua = findViewById(R.id.etContraseñaAntigua);
        contraNueva = findViewById(R.id.etNuevaContraseña);
        confirmarContra = findViewById(R.id.etConfirmarNuevaContra);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(CambiarContra.this,CerrarSesion.class);
                startActivity(i);
            }
        });

        ConexionSQliteHelper conn = new ConexionSQliteHelper(this,"bd_usuarios",null,1);
        final SQLiteDatabase db=conn.getWritableDatabase();

        btCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!confirmarContra.getText().toString().trim().equals("")&& !contraNueva.getText().toString().trim().equals("")
                    && !contraAntigua.getText().toString().trim().equals("") && !contraAntigua.getText().toString().isEmpty()
                    && !contraNueva.getText().toString().isEmpty() && !confirmarContra.getText().toString().isEmpty()){

                    String[]parametros ={Integer.toString(id)};
                    String prueNombre ="";
                    String[]campos ={Utilidades.CAMPO_CONTRASEÑA};

                    Cursor cursor= db.query(Utilidades.TABLA_JUGADOR,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);

                    cursor.moveToFirst();
                    prueNombre = cursor.getString(0);
                    if (!contraAntigua.getText().toString().equals(prueNombre)) {
                        Toast.makeText(CambiarContra.this, "La contraseña antigua no coincide", Toast.LENGTH_SHORT).show();
                        contraAntigua.setText("");
                    }else{
                        if (confirmarContra.getText().toString().equals(contraNueva.getText().toString())){

                            if (!confirmarContra.equals(prueNombre)) {
                                finish();
                                ContentValues values = new ContentValues();
                                values.put(Utilidades.CAMPO_CONTRASEÑA, contraNueva.getText().toString());

                                db.update(Utilidades.TABLA_JUGADOR, values, Utilidades.CAMPO_ID + "=?", parametros);
                                Intent i = new Intent(CambiarContra.this, CerrarSesion.class);
                                Toast.makeText(CambiarContra.this, "Cambio exitoso!", Toast.LENGTH_SHORT).show();
                                contraNueva.setText("");
                                contraAntigua.setText("");
                                confirmarContra.setText("");
                                startActivity(i);
                            }else{
                                Toast.makeText(CambiarContra.this,"La contraseña nueva no puede ser igual a la anterior",Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(CambiarContra.this,"Confirme bien la nueva contraseña, debe coincidir con la deseada",Toast.LENGTH_LONG).show();
                        }
                    }
                }else{
                    Toast.makeText(CambiarContra.this,"Rellena todos los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(CambiarContra.this,CerrarSesion.class);
        startActivity(i);
    }
}
