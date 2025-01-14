package com.example.viewsanduielement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.viewsanduielement.databinding.ActivityRegisterBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class ActivityRegister extends AppCompatActivity {

    ActivityRegisterBinding binding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = binding.main; //nama id dalam [.xml] file
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.nav_open, R.string.nav_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = binding.navigation; //nama id dalam [.xml] file

        binding.button.setOnClickListener(this::expanse);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                int id = item.getItemId();

                if (id == R.id.nav_login_activity) {
                    intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                } else if (id == R.id.nav_register_activity) {
                    intent = new Intent(ActivityRegister.this, ActivityRegister.class);
                } else if (id == R.id.nav_expenses_activity) {
                    intent = new Intent(ActivityRegister.this, ActivityExpense.class);
                } else {
                    return false;
                }

                startActivity(intent);
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void expanse(View view) {
        Intent intent = new Intent(this, ActivityExpense.class);
        startActivity(intent);
    }

   private void calculateAge() {
    String yearOfBirthString = binding.editTextDOB.getText().toString();

    if (yearOfBirthString.isEmpty()) {
        Toast.makeText(this, "Please enter your year of birth", Toast.LENGTH_SHORT).show();
        return;
    }

    try {
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
    } catch (NumberFormatException e) {
        Toast.makeText(this, "Invalid year of birth format", Toast.LENGTH_SHORT).show();
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
