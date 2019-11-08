package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

<<<<<<< HEAD
import com.example.dom.Dz1_7_7;
=======
>>>>>>> develop

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        setContentView(new Dz1_7_7(this));
=======
        setContentView(new Dz1_8_7_Simple_animation(this));
>>>>>>> develop
    }
}