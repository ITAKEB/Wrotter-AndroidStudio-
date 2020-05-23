package com.example.wrotter.clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQliteHelper extends SQLiteOpenHelper {
    public ConexionSQliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_JUGADOR);
        db.execSQL(Utilidades.CREAR_TABLA_PALABRA_APRENDIDAS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_JUGADOR);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PALABRAS_APRENDIDAS);
        onCreate(db);

    }
}
