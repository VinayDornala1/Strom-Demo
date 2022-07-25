package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.myapplication.fragment.FirstFragment;
import com.example.myapplication.fragment.FourFragment;
import com.example.myapplication.fragment.SecondFragment;
import com.example.myapplication.fragment.ThirdFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout click,click1,click2,click3,click4,click5,click6;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    SharedPreferences prefs;
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    FourFragment fourFragment=new FourFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click=findViewById(R.id.click);
        click1=findViewById(R.id.click1);
        click2=findViewById(R.id.click2);
        click3=findViewById(R.id.click3);
        click4=findViewById(R.id.click4);
        click5=findViewById(R.id.click5);
        click6=findViewById(R.id.click6);
//        toolbar = findViewById(R.id.toolbar);
//        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        click6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent();
//                intent.setAction(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_TEXT,"Hello World");
//                intent.setType("text/plain");
//                if (intent.resolveActivity(getPackageManager())!=null){
//                    startActivity(intent);
//                }
//            }
//        });
//        click5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,thirdFragment).commit();
//            }
//        });
//        prefs=getSharedPreferences("app",MODE_PRIVATE);
//        Password=prefs.getString("number","number");
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("My Application");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new FirstFragment()).commit();
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, com.google.android.material.R.string.appbar_scrolling_view_behavior, com.google.android.material.R.string.appbar_scrolling_view_behavior);
//        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.person:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, secondFragment).commit();
                return true;
            case R.id.homes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, firstFragment).commit();
                return true;
            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, thirdFragment).commit();
                return true;
            case R.id.search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fourFragment).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ClickItemSelected(){
        bottomNavigationView.setSelectedItemId(R.id.settings);
    }
    public void ClickItem(){
        bottomNavigationView.setSelectedItemId(R.id.person);
    }
    public void ClickSelected(){
        bottomNavigationView.setSelectedItemId(R.id.search);
    }
}