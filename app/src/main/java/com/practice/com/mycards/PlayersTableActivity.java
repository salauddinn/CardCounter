package com.practice.com.mycards;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class PlayersTableActivity extends AppCompatActivity {
    private String[] players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_table);
        players = getIntent().getStringArrayExtra("players");
        TableRow headerTableRow = findViewById(R.id.headerRow);
        TableRow dataTableRow = findViewById(R.id.dataRow);
        TableRow footerColumns = findViewById(R.id.footerRow);

        EditText editText = findViewById(R.id.defaultEdit1);
        EditText editText2 = findViewById(R.id.defaultEdit2);
        sumUpdater(0, editText);
        sumUpdater(1, editText2);
        for (int i = 0; i < players.length; i++) {
            if (i < headerTableRow.getChildCount()) {
                TextView headerColumn = (TextView) headerTableRow.getChildAt(i);
                headerColumn.setText(players[i]);
            } else {
                setHeaderColumn(headerTableRow, players[i]);
                setHeaderColumn(footerColumns, "0");
                setMainColumn(dataTableRow, i);
            }
        }
    }

    private void setMainColumn(TableRow dataTableLayout, final int column) {
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.0f);
        EditText editText = new EditText(this);
        editText.setGravity(Gravity.CENTER_HORIZONTAL);
        editText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        editText.setBackground(null);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        params.setMargins(1, 1, 1, 1);
        editText.setLayoutParams(params);
        sumUpdater(column, editText);
        View v = new View(this);
        v.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
        v.setBackgroundColor(Color.BLACK);
        dataTableLayout.addView(v);
        dataTableLayout.addView(editText);
    }

    private void sumUpdater(final int column, EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            int beforeValue = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!s.toString().isEmpty()) {
                    beforeValue = Integer.parseInt(s.toString());
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    int number = Integer.parseInt(s.toString());
                    TableRow footerColumns = findViewById(R.id.footerRow);

                    for (int i = 0; i < players.length; i++) {
                        if (i < footerColumns.getChildCount()) {
                            TextView footerColumn = (TextView) footerColumns.getChildAt(i);
                            String value = footerColumn.toString();
                            if (!value.isEmpty() && i == column) {
                                if (beforeValue == 0) {
                                    int sum = Integer.parseInt(footerColumn.getText().toString()) + number;
                                    footerColumn.setText(String.valueOf(sum));
                                } else {
                                    int sum = (Integer.parseInt(footerColumn.getText().toString()) - beforeValue) + number;
                                    footerColumn.setText(String.valueOf(sum));
                                }
                            }
                        }

                    }

                }
            }
        });
    }

    private void setHeaderColumn(TableRow headerTableLayout, String player) {
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.0f);
        TextView textView = new TextView(this);
        textView.setText(player);
        textView.setBackgroundColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        params.setMargins(1, 1, 1, 1);
        textView.setTextColor(Color.BLACK);
        textView.setLayoutParams(params);
        headerTableLayout.addView(textView);
    }

    public void addRow(View view) {
        int noOfColumns = players.length;
        TableLayout tableLayout = findViewById(R.id.maintable);
        TableRow tableRow = new TableRow(this);
        int count = 0;
        while (count < noOfColumns) {
            setMainColumn(tableRow, count);
            count++;
        }
        tableRow.setBackgroundResource(R.drawable.horizontal);
        tableLayout.addView(tableRow);
    }
}
