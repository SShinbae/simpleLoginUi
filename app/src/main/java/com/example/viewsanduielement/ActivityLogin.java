package com.example.viewsanduielement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.viewsanduielement.databinding.ActivityLoginBinding;
import com.google.android.material.navigation.NavigationView;

public class ActivityLogin extends AppCompatActivity {

    ActivityLoginBinding binding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = binding.main; //nama id dalam [.xml] file
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.nav_open, R.string.nav_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = binding.navigation; //nama id dalam [.xml] file

        binding.button.setOnClickListener(this::fnLogin);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                int id = item.getItemId();

                if (id == R.id.nav_login_activity) {
                    intent = new Intent(ActivityLogin.this, ActivityLogin.class);
                } else if (id == R.id.nav_register_activity) {
                    intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                } else if (id == R.id.nav_expenses_activity) {
                    intent = new Intent(ActivityLogin.this, MainActivity.class);
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