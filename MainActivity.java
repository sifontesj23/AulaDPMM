package com.example.aulasqlite;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            // Criando banco de dados SQLITE
            SQLiteDatabase bancodedados = this.openOrCreateDatabase("APP", Context.MODE_PRIVATE, null);

            // Corrigindo nome da coluna e tipo de dado
            bancodedados.execSQL("CREATE TABLE IF NOT EXISTS PESSOAS (nome VARCHAR, idade INTEGER)");

            //Insere um registro
            bancodedados.execSQL("INSERT INTO PESSOAS (nome, idade) VALUES ('Ricardo', 39)");

            //Recuperar os dados
                //Uso de cursor
                Cursor cursor = bancodedados.rawQuery("SELECT nome, idade FROM pessoas", null);
                //Indices de tabela
                int indiceNOME = cursor.getColumnIndex("nome");
                int indiceIDADE = cursor.getColumnIndex("idade");
             cursor.moveToFirst();
             while (cursor!= null){
                 String nome = cursor.getString(indiceNOME);
                 int idade = cursor.getInt(indiceIDADE);
                 cursor.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
