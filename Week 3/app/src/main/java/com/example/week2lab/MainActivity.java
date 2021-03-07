package com.example.week2lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "WEEK3LAB";
    public static final String SP_FILE_NAME = "Testing01PreferencesFile";

    private EditText nameText;
    private EditText qtyText;
    private EditText costText;
    private EditText descText;

    ToggleButton frozenBtn;
    Button clearBtn;
    Button addBtn;

    private String nameTextState;
    private String qtyTextState;
    private String costTextState;
    private String descTextState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restoreSharedPreferences();
        setContentView(R.layout.activity_main);


        //Text fields
        nameText = findViewById(R.id.nameText);
        qtyText = findViewById(R.id.qtyText);
        costText = findViewById(R.id.costText);
        descText = findViewById(R.id.descText);
        //Buttons
        frozenBtn = findViewById(R.id.frozenBtn);
        clearBtn = findViewById(R.id.clearBtn);
        addBtn = findViewById(R.id.addBtn);
    }

    public void onclickaddBtn(View view){
        String displayText = nameText.getText().toString();
        Toast.makeText(this,"New item (" + displayText +") has been added", Toast.LENGTH_LONG).show();
        saveSharedPreferences();
    }

    public void onclickclearBtn(View view){
        nameText.setText("");
        qtyText.setText("");
        costText.setText("");
        descText.setText("");
        frozenBtn.setChecked(false);
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferenceEditor = getSharedPreferences("file1",0).edit();
        preferenceEditor.putString("nameDataText",nameText.getText().toString());
        preferenceEditor.putString("qtyDataText",qtyText.getText().toString());
        preferenceEditor.putString("costDataText",costText.getText().toString());
        preferenceEditor.putString("descDataText",descText.getText().toString());
        preferenceEditor.apply();

        Log.i(TAG, "onPause");

    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


    protected void onSaveInstanceState(Bundle outState) {
        //call the onSave method in the parent class
        super.onSaveInstanceState(outState);
        outState.putString("keyNameText",nameTextState);
        outState.putString("keyQtyText",qtyTextState);
        outState.putString("keyCostText",costTextState);
        outState.putString("keyDescText",descTextState);

        Log.i(TAG, "onSaveInstanceState");

    }

    //only gets executed if inState != null so no need to check for null Bundle
    protected void onRestoreInstanceState(Bundle inState) {
        //call the onRestore method in the parent class
        super.onRestoreInstanceState(inState);
        nameTextState = inState.getString("keyNameText");
        qtyTextState = inState.getString("keyQtyText");
        costTextState = inState.getString("keyCostText");
        descTextState = inState.getString("keyDescText");
        Log.i(TAG, "onRestoreInstanceState");

    }

    private void saveSharedPreferences(){

        SharedPreferences sp = getSharedPreferences(SP_FILE_NAME, 0);
        SharedPreferences.Editor editSP = sp.edit();
        editSP.putString("nameSP", nameTextState);
        editSP.putString("qtySP", qtyTextState);
        editSP.putString("costSP", costTextState);
        editSP.putString("descSP", descTextState);
        editSP.apply();
    }

    private void restoreSharedPreferences(){

        SharedPreferences sp = getSharedPreferences(SP_FILE_NAME, 0);
        nameTextState = sp.getString("nameSP", "default");
        qtyTextState = sp.getString("qtySP", "default");
        costTextState = sp.getString("costSP", "default");
        descTextState = sp.getString("descSP", "default");
    }


}
