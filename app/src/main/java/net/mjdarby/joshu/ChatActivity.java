package net.mjdarby.joshu;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ChatActivity extends ListActivity implements AsyncAware {
    private ChatMessageAdapter adapter;

    public void sendMessage(View v) {
        Button myButton = findViewById(R.id.chatSend);
        myButton.setEnabled(false);
        EditText myEditText = findViewById(R.id.chatText);
        myEditText.setEnabled(false);
        CallServerTask myCallServerTask = new CallServerTask();
        myCallServerTask.delegate = this;
        adapter.add(new ChatMessage(myEditText.getText().toString(), false));
        myCallServerTask.execute("chat " + myEditText.getText().toString());
        myEditText.setText("");
    }

    @Override
    public void processFinish(String output){
        // Get only the text response
        String myResponseText;
        try {
            ObjectMapper myMapper = new ObjectMapper();
            ServerResponse myResponse = myMapper.readValue(output, ServerResponse.class);
            myResponseText = myResponse.getText();
        }
        catch (IOException e) {
            myResponseText = "I'm not feeling great";
        }

        // Print output
        adapter.add(new ChatMessage(myResponseText, true));

        // Unlock send button
        EditText myEditText = findViewById(R.id.chatText);
        myEditText.setEnabled(true);
        Button myButton = findViewById(R.id.chatSend);
        myButton.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        adapter = new ChatMessageAdapter(
                this,
                R.layout.chat_row
        );
        setListAdapter(adapter);
    }
}
