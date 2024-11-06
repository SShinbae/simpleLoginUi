package com.example.viewsanduielement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.viewsanduielement.databinding.ActivityLoginBinding;

public class ActivityLogin extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(this::fnLogin);
    }

    public void fnLogin(View view) {

        String username = binding.editTextTextPersonName.getText().toString();
        String password = binding.editTextTextPassword.getText().toString();


        Intent intent = new Intent(this,ActivityRegister.class);
        intent.putExtra("username",username);
        //intent.putExtra("password", binding.editTextTextPassword.toString());
        intent.putExtra("password",password);
        startActivity(intent);
    }



    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }




}