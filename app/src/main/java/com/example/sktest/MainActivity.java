package com.example.sktest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText editText;
    public Button button;
    public Button searchBtn;
    private SQLiteDatabase db;
    String carNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        searchBtn = (Button) findViewById(R.id.searchBtn);
        editText = (EditText) findViewById(R.id.editTextText);
        DBHelper dbHelper = new DBHelper(MainActivity.this, 1);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carNum = editText.getText().toString();
                dbHelper.insert(carNum);
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carNum = editText.getText().toString();
                if(dbHelper.getResult(carNum)) {
                    Toast.makeText(MainActivity.this, "exist", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "no exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}