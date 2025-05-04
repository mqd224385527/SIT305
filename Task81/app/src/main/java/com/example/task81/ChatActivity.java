package com.example.task81;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView chatRecyclerView;
    private EditText messageEditText;
    private ImageButton sendButton;
    private ChatAdapter chatAdapter;
    private List<Message> messageList;
    private List<Map<String, String>> chatHistory;
    private String username;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);

        username = getIntent().getStringExtra("username");
        
        messageList = new ArrayList<>();
        chatHistory = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList);
        
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5001/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);

        sendWelcomeMessage();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageContent = messageEditText.getText().toString().trim();
                if (!messageContent.isEmpty()) {
                    sendMessage(messageContent);
                    messageEditText.setText("");
                }
            }
        });
    }

    private void sendWelcomeMessage() {
        Message welcomeMessage = new Message("Welcome " + username + "!", false);
        chatAdapter.addMessage(welcomeMessage);
        
        Map<String, String> welcomeHistoryEntry = new HashMap<>();
        welcomeHistoryEntry.put("User", "Hello");
        welcomeHistoryEntry.put("Llama", "Welcome " + username + "!");
        chatHistory.add(welcomeHistoryEntry);
    }

    private void sendMessage(String messageContent) {
        Message userMessage = new Message(messageContent, true);
        chatAdapter.addMessage(userMessage);
        chatRecyclerView.scrollToPosition(messageList.size() - 1);

        Map<String, String> historyEntry = new HashMap<>();
        historyEntry.put("User", messageContent);

        ChatRequest chatRequest = new ChatRequest(messageContent, chatHistory);
        Call<ChatResponse> call = apiService.sendMessage(chatRequest);

        call.enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String botResponse = response.body().getMessage();
                    Message botMessage = new Message(botResponse, false);
                    chatAdapter.addMessage(botMessage);
                    chatRecyclerView.scrollToPosition(messageList.size() - 1);

                    historyEntry.put("Llama", botResponse);
                    chatHistory.add(historyEntry);
                } else {
                    Toast.makeText(ChatActivity.this, "Error getting response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
} 