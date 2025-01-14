package com.example.viewsanduielement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.viewsanduielement.databinding.ActivityExpenseBinding;
import com.example.viewsanduielement.sqlite.DatabaseExpenses;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class ActivityExpense extends AppCompatActivity {
    ActivityExpenseBinding binding;
    private ExpenseAdapter expenseAdapter;
    private DatabaseExpenses databaseExpenses;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    DatePickerDialog pickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityExpenseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseExpenses = new DatabaseExpenses(this);
        expenseAdapter = new ExpenseAdapter(databaseExpenses.fnGetAllExpenses());

        binding.recyclerViewExpenses.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewExpenses.setAdapter(expenseAdapter);

        Integer[] numbers = new Integer[15];
        for (int i = 0; i < 15; i++) {
            numbers[i] = i;
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, numbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        binding.buttonSave.setOnClickListener(this::fnSaveExp);
        binding.imgExp.setOnClickListener(this::fnTakePic);

        drawerLayout = binding.main; //nama id dalam [.xml] file
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnInvokeDatePicker();
            }
        });

        navigationView = binding.navigation; //nama id dalam [.xml] file
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                int id = item.getItemId();

                if (id == R.id.nav_login_activity) {
                    intent = new Intent(ActivityExpense.this, ActivityLogin.class);
                } else if (id == R.id.nav_register_activity) {
                    intent = new Intent(ActivityExpense.this, ActivityRegister.class);
                } else if (id == R.id.nav_expenses_activity) {
                    intent = new Intent(ActivityExpense.this, ActivityExpense.class);
                } else {
                    return false;
                }

                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        binding.imgExp.setImageBitmap(bitmap);
    }

    private void fnSaveExp(View view) {
        int qtyItem = (int) binding.spinner.getSelectedItemId();
        float expenseValue = Float.parseFloat(binding.editTextTotal.getText().toString());
        binding.edtExpValue.setText(String.valueOf(qtyItem * expenseValue));

        // Save the expense to the database
        Expense expense = new Expense(
                binding.editTextExpanses.getText().toString(),
                binding.editTextDate.getText().toString(),
                expenseValue,
                qtyItem
        );
        databaseExpenses.fnInsertExpense(expense);

        // Update the RecyclerView
        expenseAdapter.addExpense(expense);
    }

    private void fnTakePic(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    private void fnInvokeDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                binding.editTextDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);
        pickerDialog.show();
    }
}