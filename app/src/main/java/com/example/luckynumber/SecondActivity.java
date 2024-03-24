package com.example.luckynumber;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView welcome,randomnumber;
    Button btn2;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        welcome = findViewById(R.id.welcome);
        randomnumber = findViewById(R.id.randomnumber);
        btn2 = findViewById(R.id.btn2);

        Intent i = getIntent();
        String username = i.getStringExtra("name");
        int random_num = generateRandomNumber();
        randomnumber.setText(""+random_num);

        btn2.setOnClickListener(v -> sharedata(username,random_num));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public int generateRandomNumber(){
        Random random = new Random();
        int upper_limit = 1000;
        return random.nextInt(upper_limit);
    }

    public void sharedata(String username, int randumNum){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        i.putExtra(Intent.EXTRA_SUBJECT,username + "Random Number Generator");
        i.putExtra(Intent.EXTRA_TEXT,"Your Random Number Is : "+randumNum);

        startActivity(Intent.createChooser(i,"Choose a Platform"));
    }
}