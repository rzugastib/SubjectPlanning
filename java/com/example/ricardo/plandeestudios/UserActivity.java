package com.example.ricardo.plandeestudios;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    EditText nombre, usuario, contra, carrera, inicio;
    TextView prueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        nombre = (EditText) findViewById(R.id.etNombre);
        usuario = (EditText) findViewById(R.id.etUser);
        contra = (EditText) findViewById(R.id.etContra);
        carrera = (EditText) findViewById(R.id.etCarrera);
        inicio = (EditText) findViewById(R.id.etInicio);
        prueba = (TextView) findViewById(R.id.tvPrueba);
    }

    public void registro(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,6);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nombre",nombre.getText().toString());
        registro.put("idUser",usuario.getText().toString());
        registro.put("pwd",contra.getText().toString());
        registro.put("carrera",carrera.getText().toString());
        registro.put("inicio",inicio.getText().toString());
        if(db.insert("usuario",null,registro)>0){
            Intent i = new Intent(this,LoginActivity.class);
            Bundle b = new Bundle();
            b.putString("Usuario",usuario.getText().toString());
            i.putExtras(b);
            startActivity(i);
        }else
            prueba.setText("Error al registrar el usuario. Intenta otro usuario");
        db.close();
    }
}
