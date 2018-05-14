package com.example.ricardo.plandeestudios;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {
    TextView prom, porc, max,grad, bienvenida, prueba;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        bienvenida = (TextView) findViewById(R.id.tvBienvenida);
        b = this.getIntent().getExtras();
        bienvenida.setText("Hola, "+ b.get("Nombre") +". Aquí te dejamos unos datos sobre tu carrera ");
        prom = (TextView) findViewById(R.id.tvPromedio);
        porc = (TextView) findViewById(R.id.tvPorcentaje);
        max = (TextView) findViewById(R.id.tvMax);
        grad = (TextView) findViewById(R.id.tvGraduacion);
        prueba = (TextView) findViewById(R.id.prueba);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null,2);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor cursor = db.rawQuery("select avg(calificacion) from materia where cursada = 1",null);
        prom.setText(Double.toString(cursor.getDouble(0)));
        db.close();
    }
}
