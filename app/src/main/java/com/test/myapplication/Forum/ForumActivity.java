package com.test.myapplication.Forum;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.github.library.bubbleview.BubbleTextView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.myapplication.Forum.Message;
import com.test.myapplication.R;

import android.text.format.DateFormat;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;

public class ForumActivity extends AppCompatActivity {
private static int  SIGN_IN_CODE = 1;
private RelativeLayout activity_forum;
private FirebaseListAdapter<Message> adapter;
private EmojiconEditText emojiconEditText;
private ImageView emojiButton,submitButton;
private EmojIconActions emojIconActions;





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_CODE){
            if(resultCode== RESULT_OK){
                Snackbar.make(activity_forum,"Вы авторизованы",Snackbar.LENGTH_LONG).show();
                displayAllMessages();
            }else {
                Snackbar.make(activity_forum,"Вы не авторизованы",Snackbar.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        activity_forum = findViewById(R.id.activity_forum);
        submitButton =findViewById(R.id.submit_button);
        emojiButton = findViewById(R.id.emoji_button);
        emojiconEditText = findViewById(R.id.textField);
        emojIconActions = new EmojIconActions(getApplicationContext(),activity_forum,emojiconEditText,emojiButton);
        emojIconActions.ShowEmojIcon();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("message").push().setValue(new Message(
                   FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                        emojiconEditText.getText().toString()
                ));
                emojiconEditText.setText("");
            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser() == null)
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_CODE);
        else {
            Snackbar.make(activity_forum,"Вы авторизованы",Snackbar.LENGTH_LONG).show();
            displayAllMessages();
        }


    }

    private void displayAllMessages() {
        ListView listOfMessages = findViewById(R.id.list_of_messages);
        adapter = new FirebaseListAdapter<Message>(this,Message.class,R.layout.message_item, FirebaseDatabase.getInstance()
                .getReference().child("message")) {
            @Override
            protected void populateView(View v, Message model, int position) {
                TextView mess_user,mess_time;
                BubbleTextView mess_text;
                mess_user = v.findViewById(R.id.message_user);
                mess_time = v.findViewById(R.id.message_time);
                mess_text = v.findViewById(R.id.message_text);

                mess_user.setText(model.getUserName());
                mess_text.setText(model.getTextMessage());
                mess_time.setText(DateFormat.format("dd-MM-yyyy HH:mm:ss ",model.getMessageTime()));

            }
        };
        listOfMessages.setAdapter(adapter);
    }

}