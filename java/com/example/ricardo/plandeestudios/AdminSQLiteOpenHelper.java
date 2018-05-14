package com.example.ricardo.plandeestudios;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    String CREATE_USUARIO = "create table usuario (idUser text primary key, pwd text, nombre text, carrera text, inicio text)";
    String CREATE_MATERIA = "create table materia (clave text primary key, nombre text, creditos integer, calificacion integer, semestre integer, cursada integer, idUser text)";

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MATERIA);
        db.execSQL(CREATE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists materia");
        db.execSQL("drop table if exists usuario");
        db.execSQL(CREATE_MATERIA);
        db.execSQL(CREATE_USUARIO);
    }
}
