package com.hackathon.pow_wow;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Muazzam on 6/18/2017.
 */

public class chatActivity extends Activity {
    EditText edittext;
    ImageView chatmsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        edittext = (EditText)findViewById(R.id.msg_textbox);
        chatmsg = (ImageView)findViewById(R.id.chatmsg);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    edittext.setText("");
                    chatmsg.setVisibility(View.VISIBLE);

                    return true;
                }
                return false;
            }
        });
    }
}
