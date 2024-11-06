package com.example.viewsanduielement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.viewsanduielement.databinding.ActivityRegisterBinding;

import java.util.Calendar;

public class ActivityRegister extends AppCompatActivity {

    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }

    private void calculateAge() {
        String yearOfBirthString = binding.editTextDOB.getText().toString();


            int yearOfBirth = Integer.parseInt(yearOfBirthString);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int age = currentYear - yearOfBirth;

            if (age < 0) {
                Toast.makeText(this, "Please enter a valid year of birth", Toast.LENGTH_SHORT).show();
            } else {
                // Display the calculated age
                String ageMessage = "You are " + age + " years old";
                binding.editTextDOB.setText(ageMessage);
            }
        }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        binding.editTextTextPersonName.setText(intent.getStringExtra("username"));
        binding.editTextTextPassword.setText(intent.getStringExtra("password"));


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

    private void fnSaveState() {
        calculateAge();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }



}