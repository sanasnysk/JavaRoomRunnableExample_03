package com.sansan.javaroomrunnableexample_03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertUserActivity extends AppCompatActivity {
    private EditText edt_name;
    private EditText edt_age;
    private Button btn_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_user);

        edt_name = findViewById(R.id.edt_name);
        edt_age = findViewById(R.id.edt_age);
        btn_insert = findViewById(R.id.btn_insert);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = edt_name.getText().toString();
                String uAge = edt_age.getText().toString();

                insertUser(uName, uAge);
            }
        });

    }

    private void insertUser(String name, String age) {

        Thread t = new Thread() {
            public void run() {
                User user = new User();
                user.userName = name;
                user.userAge = age;

                UserDatabase db = UserDatabase.getInstance(InsertUserActivity.this);
                db.userDao().insertUser(user);

                setResult(Activity.RESULT_OK);
            }
        };
        t.start();
    }
}