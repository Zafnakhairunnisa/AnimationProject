package com.example.animationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ObjectAnimator mAnimator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgView = findViewById(R.id.image);
        Button btn = findViewById(R.id.btn_animate);

//        mAnimator = ObjectAnimator.ofFloat(imgView, "x", 300);

        mAnimator = ObjectAnimator.ofFloat(imgView, "y", 1200);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimator.setDuration(1000);
                mAnimator.start();
            }
        });
    }
}