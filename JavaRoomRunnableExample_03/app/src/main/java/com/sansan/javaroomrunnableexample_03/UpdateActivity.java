package com.sansan.javaroomrunnableexample_03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    private EditText edt_name;
    private EditText edt_age;
    private int uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edt_name = findViewById(R.id.up_name);
        edt_age = findViewById(R.id.up_age);

        String uName = getIntent().getStringExtra("userName");
        String uAge = getIntent().getStringExtra("userAge");
        uId = getIntent().getIntExtra("uId", 0);

        edt_name.setText(uName);
        edt_age.setText(uAge);

        Button btn_edit = findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = edt_name.getText().toString();
                String userAge = edt_age.getText().toString();

                updateUser(uId, userName, userAge);

                Intent intent_edit = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent_edit);

                finish();

            }
        });
    }

    private void updateUser(int uId, String name, String age) {

        Thread t = new Thread() {
            public void run() {
                User user = new User();
                user.userName = name;
                user.userAge = age;
                user.uId = uId;

                UserDatabase db = UserDatabase.getInstance(UpdateActivity.this);
                db.userDao().updateUser(user);

                setResult(Activity.RESULT_OK);

                db.close();
            }
        };
        t.start();
    }
}