package com.example.translator;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    /*Инициализация коллекции HashMap с именем Dictionary
    данные в которой хранятся по принципу ключ-значение*/
    Map<String, String> dictionary = new HashMap<String, String>();
    /*Обьявление элементов интефейса как private,
    а их инициализация произвождится внутри method onCreate,
     чтобы избежать error NullPointerException:*/
   private  EditText searchField;
   private TextView displayField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Инициализация элементов интерфейса
        searchField = (EditText) findViewById(R.id.searchField);
        displayField = (TextView) findViewById(R.id.displayField);
        Button searchButton = (Button)findViewById(R.id.lookupButton);
        Button hsk = (Button)findViewById(R.id.hskInfoButton);
        Button hskWordsButton = (Button)findViewById(R.id.dictionaryBaseButton);
        //Вызов метода который ищет перевод слова в коллекции
        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                /*Вызов статического метода,который заполняет коллекцию dictionary */
                Dictionary.getDictionary(dictionary);
                /*Инициализация строковой переменной word и запись в нее значения,
                 введенного в элемент интерфейса searchField,а также приведение значения из этого
                 поля в строку и к нижнему регистру*/
                String word = searchField.getText().toString().toLowerCase();
                /*Отлов ситуации когда слово не введеное поле или не найдено в коллекции HashMap
                 через If-else*/
                if(dictionary.get(word)== null){
                    displayField.setText("Слово не найдено в словаре");
                }else {
                    displayField.setText(dictionary.get(word));
                }
            }
        });

        hsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Hsk.class);
                startActivity(intent);
            }
        });

        hskWordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DictionaryBase.class);
                startActivity(intent);
            }
        });
    }



}