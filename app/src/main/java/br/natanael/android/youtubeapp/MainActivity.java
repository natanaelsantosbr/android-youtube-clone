package br.natanael.android.youtubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String GOOGLE_API_KEY = "AIzaSyBf7K-Eqt1QYt1VXQCgaM0xUYST0dk_Qas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
