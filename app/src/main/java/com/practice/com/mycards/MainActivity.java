package com.practice.com.mycards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onSubmit(View view) {
        EditText editText = findViewById(R.id.noOfPlayers);
        String text=editText.getText().toString();
        if(!text.isEmpty()){
            int noOfPlayers =Integer.parseInt(text);
        if (noOfPlayers > 1) {
            Intent intent = new Intent(this, AddPlayersActivity.class);
            intent.putExtra("noOfPlayers", noOfPlayers);
            startActivity(intent);
        }
        else{
            editText.setError("Please Enter greater than 1 ");
        }
        }else{
            editText.setError("Please Enter a Number ");
        }
    }
}
