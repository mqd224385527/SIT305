package com.example.task41;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
public class AddEditTaskActivity extends AppCompatActivity {
    EditText etTitle, etDescription, etDueDate;
    Button btnSave, btnDelete;
    AppDatabase db;
    int taskId = -1;
    Task currentTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        etDueDate = findViewById(R.id.et_due_date);
        btnSave = findViewById(R.id.btn_save);
        btnDelete = findViewById(R.id.btn_delete);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "task_db").build();
        if(getIntent().hasExtra("task_id")){
            taskId = getIntent().getIntExtra("task_id", -1);
            new Thread(() -> {
                currentTask = db.taskDao().getTaskById(taskId);
                runOnUiThread(() -> {
                    if(currentTask != null){
                        etTitle.setText(currentTask.getTitle());
                        etDescription.setText(currentTask.getDescription());
                        etDueDate.setText(currentTask.getDueDate());
                    }
                });
            }).start();
            btnDelete.setVisibility(android.view.View.VISIBLE);
        } else {
            btnDelete.setVisibility(android.view.View.GONE);
        }
        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            String dueDate = etDueDate.getText().toString().trim();
            if(title.isEmpty() || dueDate.isEmpty()){
                runOnUiThread(() -> Toast.makeText(AddEditTaskActivity.this, "Title and Due Date required", Toast.LENGTH_SHORT).show());
                return;
            }
            new Thread(() -> {
                if(taskId == -1){
                    Task task = new Task(title, description, dueDate);
                    db.taskDao().insertTask(task);
                } else {
                    currentTask.setTitle(title);
                    currentTask.setDescription(description);
                    currentTask.setDueDate(dueDate);
                    db.taskDao().updateTask(currentTask);
                }
                runOnUiThread(() -> finish());
            }).start();
        });
        btnDelete.setOnClickListener(v -> {
            new Thread(() -> {
                if(currentTask != null){
                    db.taskDao().deleteTask(currentTask);
                }
                runOnUiThread(() -> finish());
            }).start();
        });
    }
}
