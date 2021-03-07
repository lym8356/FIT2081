package com.example.week2lab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
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

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FloatingActionButton fabBtn;
    ArrayList<Item> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_drawer_layout);
        String username = getIntent().getExtras().getString("loginUser");
        setTitle("Welcome " + username);

        nameText = findViewById(R.id.nameText);
        qtyText = findViewById(R.id.qtyText);
        costText = findViewById(R.id.costText);
        descText = findViewById(R.id.descText);
        frozenBtn = findViewById(R.id.frozenBtn);
        clearBtn = findViewById(R.id.clearBtn);
        addBtn = findViewById(R.id.addBtn);

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        fabBtn = findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new MyNavListener());

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add_item_opt:
                addItem();
                break;
            case R.id.clear_all_opt:
                clearAll();
                break;
        }
        return true;
    }


    public void onclickaddBtn(View view) {
        String displayText = nameText.getText().toString();
        Toast.makeText(this, "New item (" + displayText + ") has been added", Toast.LENGTH_LONG).show();

        boolean freezeCond;
        if(frozenBtn.getText().toString().equals("YES")){
            freezeCond = true;
        }else{
            freezeCond = false;
        }

        Item item = new Item(nameText.getText().toString(), Double.parseDouble(costText.getText().toString()),
                Integer.parseInt(qtyText.getText().toString()), freezeCond, descText.getText().toString());
        //Log.i("warehouse app",frozenBtn.getText().toString());
        Log.i("warehouse app", Boolean.toString(item.isFreeze()));


        data.add(item);
    }

    void addItem() {
        String displayText = nameText.getText().toString();
        Toast.makeText(getApplicationContext(), "New item (" + displayText + ") has been added", Toast.LENGTH_LONG).show();
        //Log.i("warehouse app",frozenBtn.getText().toString());
        boolean freezeCond;
        if(frozenBtn.getText().toString().equals("YES")){
            freezeCond = true;
        }else{
            freezeCond = false;
        }

        Item item = new Item(nameText.getText().toString(), Double.parseDouble(costText.getText().toString()),
                Integer.parseInt(qtyText.getText().toString()), freezeCond , descText.getText().toString());
        Log.i("warehouse app", Boolean.toString(item.isFreeze()));
        data.add(item);
    }

    void clearAll() {
        nameText.setText("");
        qtyText.setText("");
        costText.setText("");
        descText.setText("");
        frozenBtn.setChecked(false);
    }

    void listAll() {
        saveAndCall();
    }


    public void onclickclearBtn(View view) {
        nameText.setText("");
        qtyText.setText("");
        costText.setText("");
        descText.setText("");
        frozenBtn.setChecked(false);
    }

    private void saveAndCall() {
        Intent intent = new Intent(this, backend.class);
        Gson gson = new Gson();
        String st = gson.toJson(data);
        intent.putExtra("key", st);

        startActivity(intent);
    }

    class MyNavListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.add_item) {
                addItem();
            } else if (id == R.id.clear_all) {
                clearAll();
            } else if (id == R.id.list_all) {
                listAll();
            }
            drawerLayout.closeDrawers();
            return true;
        }
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
}
