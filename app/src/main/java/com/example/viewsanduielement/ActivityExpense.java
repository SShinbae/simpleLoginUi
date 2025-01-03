package com.example.viewsanduielement;

import static com.example.viewsanduielement.sqlite.DatabaseExpenses.tblExpense;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.viewsanduielement.databinding.ActivityExpenseBinding;
import com.example.viewsanduielement.sqlite.DatabaseExpenses;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityExpense extends AppCompatActivity {
    ActivityExpenseBinding binding;

    private ExpenseAdapter expenseAdapter;
    private DatabaseExpenses databaseExpenses;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityExpenseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseExpenses = new DatabaseExpenses(this); // Initialize DatabaseExpenses

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
        navigationView = binding.navigation; //nama id dalam [.xml] file

        binding.editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnInvokeDatePicker();
            }
        });

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
                return true;
            }
        });

        // Populate RecyclerView with expenses from SQLite database
        List<Expense> expenses = databaseExpenses.fnGetAllExpenses();
        expenseAdapter = new ExpenseAdapter(expenses);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Corrected line
        binding.recyclerView.setAdapter(expenseAdapter);
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
        String expName = binding.editTextExpanses.getText().toString();
        String expDate = binding.editTextDate.getText().toString();
        float expValue = Float.parseFloat(binding.editTextTotal.getText().toString());
        int expQty = (int) binding.spinner.getSelectedItemId();
        float expTotal = expQty * expValue;

        // Save to local SQLite database
        Expense expense = new Expense(expName, expDate, expValue, expQty);
        databaseExpenses.fnInsertExpense(expense);

        // Save to remote database
        String strURL = "http://your-server-url/RESTAPI/rest_api.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error parsing response: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorMessage = "Error: ";
                if (error.networkResponse != null) {
                    errorMessage += "Status code: " + error.networkResponse.statusCode + ", ";
                }
                errorMessage += error.getMessage();
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnSaveData");
                params.put("exp_name", expName);
                params.put("exp_date", expDate);
                params.put("exp_value", String.valueOf(expValue));
                params.put("exp_qty", String.valueOf(expQty));
                params.put("exp_total", String.valueOf(expTotal));
                return params;
            }
        };
        requestQueue.add(stringRequest);

        // Update RecyclerView
        expenseAdapter.addExpense(expense);
    }

    private void fnTakePic(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    DatePickerDialog pickerDialog;

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