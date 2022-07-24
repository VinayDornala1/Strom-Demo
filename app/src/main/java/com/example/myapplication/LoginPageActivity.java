package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginPageActivity extends AppCompatActivity {
TextView textView;
Button button,button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        button=findViewById(R.id.btn);
        button1=findViewById(R.id.btn1);
        textView=findViewById(R.id.text6);
        if (getLoginState()) {
            int UserType = getUserType();
            if (UserType == 1) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_right,
//                        R.anim.slide_out_left);
                finish();
            }
        }
        textView.setPaintFlags(textView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), RegistrationPageActivity.class);
                startActivity(intent);
            }
        });


    }

    private int getUserType() {
        SharedPreferences sp = getSharedPreferences("app", MODE_PRIVATE);
        return sp.getInt("userType", 0);
    }

    private boolean getLoginState() {
        SharedPreferences sp = getSharedPreferences("app", MODE_PRIVATE);
        return sp.getBoolean("isLoggedIn", false);
    }

}