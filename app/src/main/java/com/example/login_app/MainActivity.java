package com.example.login_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText et;
    EditText et2;
    String st;
    String pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = findViewById(R.id.button);
        et = findViewById(R.id.username);
        et2 = findViewById(R.id.password);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Display.class);
                st=et.getText().toString();
                pd = et2.getText().toString();
                i.putExtra("value",st);
                i.putExtra("value2",pd);
                startActivity(i);
                finish();
            }
        });
    }
}
