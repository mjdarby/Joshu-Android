package net.mjdarby.joshu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements AsyncAware {

    public void startChat(View v) {
        Button myButton = (Button) findViewById(R.id.startButton);
        myButton.setEnabled(false);

        CallServerTask myCallServerTask = new CallServerTask();
        myCallServerTask.delegate = this;
        myCallServerTask.execute("connect phone");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("SERVER", "LET'S GO!");
    }

    @Override
    public void processFinish(String output){
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}
