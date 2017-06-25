package net.mjdarby.joshu;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ChatActivity extends ListActivity implements AsyncAware {
    private ChatMessageAdapter adapter;
    private ListView myListView;

    public void sendMessage(View v) {
        // Disable buttons
        Button myButton = findViewById(R.id.chatSend);
        myButton.setEnabled(false);
        EditText myEditText = findViewById(R.id.chatText);
        myEditText.setEnabled(false);

        // Setup async task
        CallServerTask myCallServerTask = new CallServerTask();
        myCallServerTask.delegate = this;
        adapter.add(new ChatMessage(myEditText.getText().toString(), false));

        // Scroll to bottom
        scrollListToBottom();

        // Send command async
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

        // Scroll to bottom
        scrollListToBottom();

        // Unlock send button
        EditText myEditText = findViewById(R.id.chatText);
        myEditText.setEnabled(true);
        Button myButton = findViewById(R.id.chatSend);
        myButton.setEnabled(true);
    }

    private void scrollListToBottom() {
        myListView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                myListView.setSelection(adapter.getCount() - 1);
            }
        });
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

        myListView = findViewById(android.R.id.list);
        scrollListToBottom();
    }
}
