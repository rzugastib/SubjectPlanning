package com.example.ricardo.plandeestudios;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView bienvenida;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bienvenida = (TextView) findViewById(R.id.tvBienvenida);
        b = this.getIntent().getExtras();
        bienvenida.setText("Hola, "+(String)b.get("Nombre") + ". ¿Qué quieres hacer?");
    }

    public void verLista(View v){
        //startActivity(new Intent(this, ListActivity.class));
    }

    public void verResumen(View v){
        Intent i = new Intent(this,SummaryActivity.class);
        i.putExtras(b);
        startActivity(i);
    }

    public void verMateria(View v){
        Intent i = new Intent(this,MateriaActivity.class);
        i.putExtras(b);
        startActivity(i);
    }
}
