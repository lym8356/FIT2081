package com.example.week2lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {

    EditText nameText;
    EditText qtyText;
    EditText costText;
    EditText descText;
    ToggleButton frozenBtn;
    Button clearBtn;
    Button addBtn;
    private static final String TAG = "SMSReceiver";

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

        /* Request permissions to access SMS */
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);
        /* Create and instantiate the local broadcast receiver
           This class listens to messages come from class SMSReceiver
         */
        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();

        /*
         * Register the broadcast handler with the intent filter that is declared in
         * class SMSReceiver @line 11
         * */
        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));

    }

    class MyBroadCastReceiver extends BroadcastReceiver {

        /*
         * This method 'onReceive' will get executed every time class SMSReceive sends a broadcast
         * */
        @Override
        public void onReceive(Context context, Intent intent) {
            /*
             * Retrieve the message from the intent
             * */
            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);

            Log.i(TAG, "message is  " + msg.length());
            /*
             * String Tokenizer is used to parse the incoming message
             * The protocol is to have the account holder name and account number separate by a semicolon
             * */

            StringTokenizer sT = new StringTokenizer(msg, ";");
            String itemName = sT.nextToken();
            String itemQty = sT.nextToken();
            String itemCost = sT.nextToken();
            String itemDesc = sT.nextToken();
            Boolean itemCond = Boolean.parseBoolean(sT.nextToken());
            /*
             * Now, its time to update the UI
             * */
            nameText.setText(itemName);
            qtyText.setText(itemQty);
            costText.setText(itemCost);
            descText.setText(itemDesc);
            frozenBtn.setChecked(itemCond);
        }

    }


    public void onclickaddBtn(View view){
        String displayText = nameText.getText().toString();
        Toast.makeText(this,"New item (" + displayText +") has been added", Toast.LENGTH_LONG).show();
//        nameText.setText("");
    }

    public void onclickclearBtn(View view){
        nameText.setText("");
        qtyText.setText("");
        costText.setText("");
        descText.setText("");
        frozenBtn.setChecked(false);
    }
}
