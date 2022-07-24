package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
public class OtpPageActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    PinView pinView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);
        textView = findViewById(R.id.text4);
        button = findViewById(R.id.btn);
        pinView=findViewById(R.id.firstPinView);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!pinView.getText().toString().isEmpty()&&pinView.getText().toString().trim().length()>=6) {
//                String name = pinView.getText().toString();
                    Intent intent = new Intent(OtpPageActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    setLoginState(true, 1);
                }else if (pinView.getText().toString().isEmpty()){
                    Toast.makeText(OtpPageActivity.this, "please enter 6 number", Toast.LENGTH_SHORT).show();
                }else if (!(pinView.getText().toString().trim().length() >=6)){
                    Toast.makeText(OtpPageActivity.this, "please enter pin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setLoginState(boolean status,Integer userType) {
        SharedPreferences sp = getSharedPreferences("app",
                MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
//        ed.putString("number",getIntent().getStringExtra("number"));
        ed.putBoolean("isLoggedIn", status);
        ed.putInt("userType",userType);
        ed.commit();
    }
}