package com.example.ricardo.plandeestudios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MateriaActivity extends AppCompatActivity {
    private EditText clave, nombre, creditos, calificacion, semestre, cursada;
    private TextView prueba;

    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);
        clave = (EditText) findViewById(R.id.etClave);
        nombre = (EditText) findViewById(R.id.etNombre);
        creditos = (EditText) findViewById(R.id.etCred);
        calificacion = (EditText) findViewById(R.id.etCalif);
        semestre = (EditText) findViewById(R.id.etSemestre);
        cursada = (EditText) findViewById(R.id.etCursada);
        prueba = (TextView) findViewById(R.id.tvPrueba);
    }

    public void agrega(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,2);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        //Instanciamos el bundle que nos va a permitir obtener el String del usuario. Necesario como foreign key para la tupla de cada   materia.
        b = this.getIntent().getExtras();
        registro.put("clave",clave.getText().toString());
        registro.put("nombre",nombre.getText().toString());
        registro.put("creditos",creditos.getText().toString());
        registro.put("calificacion",calificacion.getText().toString());
        registro.put("semestre",semestre.getText().toString());
        registro.put("cursada",cursada.getText().toString());
        registro.put("idUser",(String)b.get("Usuario"));
        if(db.insert("materia",null,registro)>0){
            limpiar(v);
            prueba.setText("Ha quedado registrada la materia");
        }else
            prueba.setText("No se pudo dar de alta la materia");
        db.close();
    }
    public void baja(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,2);
        SQLiteDatabase db = admin.getWritableDatabase();
        if(db.delete("materia","clave = '" + clave.getText().toString() +"'",null)>0){
            prueba.setText("Se ha eliminado el registro de la materia con clave: " + clave.getText());
            limpiar(v);
        }else
            prueba.setText("No se pudo eliminar");
        db.close();
    }
    public void consulta(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,2);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "select nombre, creditos, calificacion, semestre, cursada from materia inner join usuario on usuario.idUser = materia.idUser where clave = '" + clave.getText().toString() + "' and idUser = '" + b.get("Usuario").toString() + "'";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToNext()){
            prueba.setText("Se realizó la búsqueda para la clave: " + clave.getText().toString());
            nombre.setText(cursor.getString(0));
            creditos.setText(Integer.toString(cursor.getInt(1)));
            calificacion.setText(Integer.toString(cursor.getInt(2)));
            semestre.setText(Integer.toString(cursor.getInt(3)));
            cursada.setText(Integer.toString(cursor.getInt(4)));
        }else
            prueba.setText("No se encuentra la materia");
        db.close();
    }

    public void modifica(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,2);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        if(!nombre.getText().toString().equals("")){registro.put("nombre",nombre.getText().toString());}
        if(!creditos.getText().toString().equals("")){registro.put("creditos",creditos.getText().toString());}
        if(!calificacion.getText().toString().equals("")){registro.put("calificacion",calificacion.getText().toString());}
        if(!semestre.getText().toString().equals("")){registro.put("semestre",semestre.getText().toString());}
        if(!cursada.getText().toString().equals("")){registro.put("cursada",cursada.getText().toString());}
        if(db.update("materia",registro,"clave = '" + clave.getText().toString()+ "'",null)>0){
            limpiar(v);
            prueba.setText("Se ha modificado la información de la materia");
        }else
            prueba.setText("No se ha modificado la materia");
        db.close();
    }

    public void limpiar(View v){
        clave.setText("");
        nombre.setText("");
        creditos.setText("");
        calificacion.setText("");
        semestre.setText("");
        cursada.setText("");
    }



}
