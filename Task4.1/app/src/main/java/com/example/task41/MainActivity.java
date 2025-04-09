package com.example.task41;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.room.Room;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TaskAdapter adapter;
    List<Task> tasks = new ArrayList<>();
    AppDatabase db;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.fab);
        adapter = new TaskAdapter(tasks, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "task_db").build();
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);
            startActivity(intent);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        new Thread(() -> {
            tasks.clear();
            tasks.addAll(db.taskDao().getAllTasks());
            runOnUiThread(() -> adapter.notifyDataSetChanged());
        }).start();
    }
}
