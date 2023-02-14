package com.sansan.javaroomrunnableexample_03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton flb = findViewById(R.id.flb);
        flb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_insert = new Intent(getApplicationContext(), InsertUserActivity.class);
                activityResult.launch(intent_insert);

                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UserAdapter();

        recyclerView.setAdapter(adapter);

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

            Toast.makeText(this, "최근등록자" + userList.get(position).userName, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }

    }
}