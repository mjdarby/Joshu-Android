package net.mjdarby.joshu;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageAdapter extends ArrayAdapter<ChatMessage> {
    private TextView message;
    private List<ChatMessage> messages = new ArrayList<ChatMessage>();
    private LinearLayout wrapper;

    public ChatMessageAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public int getCount() {
        return this.messages.size();
    }

    @Override
    public void add(ChatMessage object) {
        messages.add(object);
        super.add(object);
    }

    public ChatMessage getItem(int index) {
        return this.messages.get(index);
    }

    public View getView(int position, View ConvertView, ViewGroup parent){
        View v = ConvertView;
        if(v==null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v =inflater.inflate(R.layout.chat_row, parent, false);
        }

        wrapper = v.findViewById(R.id.wrapper);
        ChatMessage myChatMessage = getItem(position);
        message =(TextView)v.findViewById(R.id.text);
        message.setText(myChatMessage.mString);
        message.setBackgroundResource(myChatMessage.mLeft ? R.drawable.bubble :R.drawable.bubble_r);
        wrapper.setGravity(myChatMessage.mLeft? Gravity.LEFT:Gravity.RIGHT);
        return v;
    }
}
