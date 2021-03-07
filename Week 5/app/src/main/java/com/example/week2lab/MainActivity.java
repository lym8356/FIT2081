package com.example.week2lab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    EditText nameText;
    EditText qtyText;
    EditText costText;
    EditText descText;
    ToggleButton frozenBtn;
    Button clearBtn;
    Button addBtn;

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FloatingActionButton fabBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_drawer_layout);

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
        fabBtn=findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new MyNavListener());

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.add_item_opt:
                addItem();
                break;
            case R.id.clear_all_opt:
                clearAll();
                break;
        }
        return true;
    }

    public void onclickaddBtn(View view){
        String displayText = nameText.getText().toString();
        Toast.makeText(this,"New item (" + displayText +") has been added", Toast.LENGTH_LONG).show();
    }

    void addItem(){
        String displayText = nameText.getText().toString();
        Toast.makeText(getApplicationContext(),"New item (" + displayText +") has been added", Toast.LENGTH_LONG).show();
    }

    void clearAll(){
        nameText.setText("");
        qtyText.setText("");
        costText.setText("");
        descText.setText("");
        frozenBtn.setChecked(false);
    }

    public void onclickclearBtn(View view){
        nameText.setText("");
        qtyText.setText("");
        costText.setText("");
        descText.setText("");
        frozenBtn.setChecked(false);
    }

    class MyNavListener implements NavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            if(id==R.id.add_item){
                addItem();
            }else if(id==R.id.clear_all){
                clearAll();
            }
            drawerLayout.closeDrawers();
            return true;
        }
    }
}
