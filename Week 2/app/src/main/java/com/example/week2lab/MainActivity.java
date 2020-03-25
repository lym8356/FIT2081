package com.example.week2lab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText nameText;
    EditText qtyText;
    EditText costText;
    EditText descText;
    ToggleButton frozenBtn;
    Button clearBtn;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.nameText);
        qtyText = findViewById(R.id.qtyText);
        costText = findViewById(R.id.costText);
        descText = findViewById(R.id.descText);
        frozenBtn = findViewById(R.id.frozenBtn);
        clearBtn = findViewById(R.id.clearBtn);
        addBtn = findViewById(R.id.addBtn);
    }

    public void onclickaddBtn(View view){
        String displayText = nameText.getText().toString();
        Toast.makeText(this,"New item (" + displayText +") has been added", Toast.LENGTH_LONG).show();
        nameText.setText("");
    }

    public void onclickclearBtn(View view){
        nameText.setText("");
        qtyText.setText("");
        costText.setText("");
        descText.setText("");
        frozenBtn.setChecked(false);
    }
}
