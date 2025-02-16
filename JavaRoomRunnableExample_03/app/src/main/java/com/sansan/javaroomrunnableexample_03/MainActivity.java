package com.sansan.javaroomrunnableexample_03;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<User> userList;
    private Context context;
    UserAdapter adapter;
    UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton flb = findViewById(R.id.flb);

        context = getApplicationContext();
        adapter = new UserAdapter(this,userList);

        db = UserDatabase.getInstance(this);

        recyclerViewRun();

        flb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_insert = new Intent(getApplicationContext(), InsertUserActivity.class);
                startActivity(intent_insert);

                finish();
            }
        });

    }

    private void recyclerViewRun(){
        Thread thread = new Thread(){
            @SuppressLint("NotifyDataSetChanged")
            public void run(){
                userList = (ArrayList<User>) UserDatabase.getInstance(context)
                        .userDao()
                        .getAllUser();
                adapter = new UserAdapter(MainActivity.this,userList);
                adapter.notifyDataSetChanged();

                RecyclerView recyclerView = findViewById(R.id.rv_list);

                LinearLayoutManager layoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
            }
        };
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserDatabase.destroyInstance();
        db = null;
    }

    /*
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);

        loadUserList();
    }

    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){

                        loadUserList();
                    }
                }
            }
    );


    private void loadUserList() {
        UserDatabase db = UserDatabase.getInstance(this.getApplicationContext());

        List<User> userList = db.userDao().getAllUser();
        if (userList.size() > 0){
            int position = userList.size() - 1;

            Toast.makeText(this, "최근등록자" + userList.get(position).userName,
                    Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No Data",
                    Toast.LENGTH_SHORT).show();
        }

    }

     */
}