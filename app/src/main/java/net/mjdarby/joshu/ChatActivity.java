package net.mjdarby.joshu;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChatActivity extends ListActivity implements AsyncAware {
    private ChatMessageAdapter adapter;

    public void sendMessage(View v) {
        Button myButton = (Button) findViewById(R.id.chatSend);
        myButton.setEnabled(false);
        EditText myEditText = (EditText) findViewById(R.id.chatText);
        myEditText.setEnabled(false);
        CallServerTask myCallServerTask = new CallServerTask();
        myCallServerTask.delegate = this;
        adapter.add(new ChatMessage(myEditText.getText().toString(), false));
        myCallServerTask.execute(myEditText.getText().toString());
        myEditText.setText("");
    }

    @Override
    public void processFinish(String output){
        // Print output
        adapter.add(new ChatMessage(output, true));

        // Unlock send button
        EditText myEditText = (EditText) findViewById(R.id.chatText);
        myEditText.setEnabled(true);
        Button myButton = (Button) findViewById(R.id.chatSend);
        myButton.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ChatMessage[] myList = new ChatMessage[] {new ChatMessage("hello", true),
                                                  new ChatMessage("world", false)};

        adapter = new ChatMessageAdapter(
                this,
                R.layout.chat_row
        );
        adapter.add(myList[0]);
        adapter.add(myList[1]);
        setListAdapter(adapter);
    }
}
