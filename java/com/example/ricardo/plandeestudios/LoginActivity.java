package com.example.ricardo.plandeestudios;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    EditText usuario,contra;
    TextView prueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = (EditText) findViewById(R.id.idUser);
        if(this.getIntent().hasExtra("Usuario")){
            usuario.setText((String)this.getIntent().getExtras().get("Usuario"));
        }
        contra = (EditText) findViewById(R.id.password);
        prueba = (TextView) findViewById(R.id.prueba);
    }

    public void ingresar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,6);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor cursor = db.rawQuery("select pwd, nombre, carrera from usuario where idUser = '"+ usuario.getText().toString() +"'",null);
        if(cursor.moveToNext()){
            if(contra.getText().toString().equals(cursor.getString(0))){
                Intent i = new Intent(this,MainActivity.class);
                Bundle b = new Bundle();
                b.putString("Nombre",cursor.getString(1));
                b.putString("Usuario",usuario.getText().toString());
                b.putString("Carrera",cursor.getString(2));
                i.putExtras(b);
                startActivity(i);
            }else
                prueba.setText("Contraseña incorrecta");
        }else
            prueba.setText("No se encontró el usuario");
    }

    public void registrar(View v){
        startActivity(new Intent(this,UserActivity.class));
    }

    public void auxiliar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null,5);
        admin.onUpgrade(admin.getWritableDatabase(),5,6);
    }
}
