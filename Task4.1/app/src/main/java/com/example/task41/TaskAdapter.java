package com.example.task41;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    List<Task> tasks;
    Context context;
    public TaskAdapter(List<Task> tasks, Context context){
        this.tasks = tasks;
        this.context = context;
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position){
        Task task = tasks.get(position);
        holder.tvTitle.setText(task.getTitle());
        holder.tvDueDate.setText(task.getDueDate());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddEditTaskActivity.class);
            intent.putExtra("task_id", task.getId());
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount(){
        return tasks.size();
    }
    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDueDate;
        public TaskViewHolder(@NonNull View itemView){
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDueDate = itemView.findViewById(R.id.tv_due_date);
        }
    }
}
