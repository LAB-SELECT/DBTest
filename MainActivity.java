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

        public void writeFile(String str) {

            //외부 저장소(External Storage)가 마운트(인식) 되었을 때 동작
            //getExternalStorageState() 함수를 통해 외부저장장치가 Mount 되어 있는지를 확인
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //다운로드 폴더에 "tagging.txt" 이름으로 txt 파일 저장
                //Environment.DIRECTORY_DOWNLOADS - 기기의 기본 다운로드 폴더
                String fileName = "dbInfo.txt";
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), fileName);
                if(file.exists()) {
                    int i = 1;
                    while(true) {
                        String newFileName = fileName.replace(".", "(" + i + ").");
                        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), newFileName);
                        if(!file.exists()) {
                            break;
                        }
                        i++;
                    }
                }
                try {
                    FileWriter fw = new FileWriter(file, false);
                    fw.write(str);
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();

            }
        }

        //아래 코드 DB 정보 저장하기 버튼 만들어 줘야 함
        /////
        string dbInfo;
        dbHelper.readAll(dbInfo);
        writeFile(dbInfo);
        /////


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