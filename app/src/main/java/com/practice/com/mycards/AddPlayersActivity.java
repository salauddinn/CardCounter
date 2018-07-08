package com.practice.com.mycards;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class AddPlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        int noOfPlayer = getIntent().getIntExtra("noOfPlayers", 2);
        LinearLayout linearLayout = findViewById(R.id.playersLayout);
        for (int i = 3; i <= noOfPlayer; i++) {
            EditText editText = new EditText(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            editText.setHint("Player" + i);
            editText.setLayoutParams(params);
            linearLayout.addView(editText);
        }
    }

    public void onSubmit(View view) {
        Intent intent =new Intent(this,PlayersTableActivity.class);
        LinearLayout linearLayout = findViewById(R.id.playersLayout);
        String[] players = getPlayerNames(linearLayout);
        if (players == null) return;
        intent.putExtra("players",players);
        startActivity(intent);
    }

    @Nullable
    private String[] getPlayerNames(LinearLayout linearLayout) {
        String[] players=new String[linearLayout.getChildCount()];
        for(int i=0;i<linearLayout.getChildCount();i++){
            EditText editText=(EditText) linearLayout.getChildAt(i);
            String playerName = editText.getText().toString();
            if(playerName.isEmpty()){
                editText.setError("Enter the valid name");
                return null;
            }
            players[i]=playerName;
        }
        return players;
    }
}
