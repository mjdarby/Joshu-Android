package net.mjdarby.joshu;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

class CallServerTask extends AsyncTask<String, Integer, String> {
    AsyncAware delegate = null;

    protected String doInBackground(String... strings) {
        int count = strings.length;
        String hostName = "10.0.2.2";
        String stringToSend = strings[0];
        int port = 9999;
        try {
            Socket mySocket = new Socket(hostName, port);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            DataOutputStream outputStream = new DataOutputStream(mySocket.getOutputStream());
            outputStream.writeBytes(stringToSend);
            String responseLine;

            // Only expecting one line of response
            String aFullResponse = "";
            while ((responseLine = inputStream.readLine()) != null) {
                aFullResponse = aFullResponse + responseLine;
                Log.d("SERVER", "Server: " + responseLine);
            }
            return aFullResponse;
        }
        catch (java.io.IOException e) {
            Log.d("SERVER", "Didn't work");
            Log.d("SERVER", e.getMessage());
            return "what";
        }
    }

    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
