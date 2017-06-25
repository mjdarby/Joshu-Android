package net.mjdarby.joshu;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class CallServerTask extends AsyncTask<String, Integer, String> {
    public AsyncAware delegate = null;

    protected String doInBackground(String... strings) {
        int count = strings.length;
        String hostName = "10.0.2.2";
        int port = 9999;
        for (int i = 0; i < count; i++) {
            try {
                Socket mySocket = new Socket(hostName, port);
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
                DataOutputStream outputStream = new DataOutputStream(mySocket.getOutputStream());
                outputStream.writeBytes(strings[i]);
                String responseLine;
                while ((responseLine = inputStream.readLine()) != null) {
                    Log.d("SERVER", "Server: " + responseLine);
                    return responseLine;
                }
                return "no reply";
            }
            catch (java.io.IOException e) {
                Log.d("SERVER", "Didn't work");
                Log.d("SERVER", e.getMessage());
                return "what";
            }
        }
        return "no string";
    }

    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
