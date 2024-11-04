package com.example.viewsanduielement;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.viewsanduielement.databinding.ActivityRegisterBinding;

public class ActivityRegister extends AppCompatActivity {

    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }

    @Override
    protected void onStart() {
        Intent intent = getIntent();
        binding.editTextTextPersonName.setText(intent.getStringExtra("username"));
        binding.editTexTextPassword.setText(intent.getStringExtra("password"));

        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        fnSaveState();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }



}