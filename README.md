# Joshu-Android
The Android client for Joshu. I can't write Java.

Hard-coded to connect to localhost 9999 for the Joshu server.

# How it works
## Start Chat
The 'start chat' button on load is responsible for sending a 'connect phone' message to Joshu. 
This adds the device to the list of authorised devices in Joshu server. Afterwards, the chat page is loaded.

## Chat page
The texting interface issues direct calls to Joshu, currently prefacing each command sent with 'chat' and giving a random response in return.

# Future TODOs
## Chat history
The last twenty messages should be pulled from Joshu server at the same time as authentication.

## NLP
In the future, the 'chat' command can be removed and the entered text will just be NLP'd like a console or GUI issued command.

## Respond to Query
Joshu will send periodic questions at key times throughout the day. For instance, if it is aware that the user is not 'in' (due to the 'leave' command being issued),
it will send a query via FCM to alert the user they have a waiting query. The message will appear marked in a special colour allowing the user to press it
and bring up a number of pre-defined responses. In our example, it may be a query asking if the user will be home on time. 
If so, it will stick to the regular schedule of turning on the lights five minutes before they are expected to return (double TODO, Joshu will automatically learn this)
If not, Joshu will respond accordingly.
