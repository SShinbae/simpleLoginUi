package com.example.viewsanduielement;

import android.app.DatePickerDialog;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.DatePicker;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.viewsanduielement.databinding.ActivityStudentMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ActivityStudentMain extends AppCompatActivity {


    private ActivityStudentMainBinding binding;

    private Student student;
    private Vector<Student> students;
    private Student.StudentViewHolder.StudentAdapter adapter;
    private DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityStudentMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        super.onCreate(savedInstanceState);

        binding.fabAdd.setOnClickListener(this:: fnAddToRest);
        binding.strBirthdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                int mHour = cldr.get(Calendar.HOUR);
                int mMinute = cldr.get(Calendar.MINUTE);
                String strDay = "";

                // datePicker Dialog
                datePicker = new DatePickerDialog(ActivityStudentMain.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       //Format date as YYYY/MM/DD
                        String formattedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                        binding.strBirthdate.setText(formattedDate);
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        students = new Vector<>();
        adapter = new Student.StudentViewHolder.StudentAdapter(getLayoutInflater(),students);

        binding.rcvStud.setAdapter(adapter);
        binding.rcvStud.setLayoutManager(new LinearLayoutManager(this));

    }

//    private void fnAddToRest(View view) {
//        String strURL = "http://192.168.0.117/RESTAPI/rest_api.php";
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("fnAddToRest", "Response: " + response);
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.e("fnAddToRest", "JSON Exception: " + e.getMessage());
//                }
//            }
////            @Override
////            public void onResponse(String response) {
////                JSONObject jsonObject = null;
////                try {
////                    jsonObject = new JSONObject(response);
////                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
////
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////            }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("fnAddToRest", "Volley Error: " + error.getMessage());
//            }
//        })
//        {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                String fullname = binding.strFullname.getText().toString();
//                String studNo = binding.strStudNo.getText().toString();
//                String email = binding.strEmail.getText().toString();
//                String birth = binding.strBirthdate.getText().toString();
//                String gender = "";
//                String state = binding.spnState.getSelectedItem().toString();
//
//                if(binding.rbMale.isChecked()){
//                    gender = binding.rbMale.getText().toString();
//                }
//                else if (binding.rbFemale.isChecked()) {
//                    gender = binding.rbFemale.getText().toString();
//                }
//
//                Map<String,String> params = new HashMap<>();
//                params.put("selectFn", "fnSaveData");
//                params.put("fullname", fullname);
//                params.put("studNo", studNo);
//                params.put("email", email);
//                params.put("gender", gender);
//                params.put("birthdate", birth);
//                params.put("state", state);
//
//                Log.d("fnAddToRest", "Params:"+ params.toString());
//                return params;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }
private void fnAddToRest(View view) {
    String strURL = "http://192.168.0.117/RESTAPI/rest_api.php";
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d("fnAddToRest", "Response: " + response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("fnAddToRest", "JSON Exception: " + e.getMessage());
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("fnAddToRest", "Volley Error: " + error.getMessage());
        }
    }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            String fullname = binding.strFullname.getText().toString();
            String studNo = binding.strStudNo.getText().toString();
            String email = binding.strEmail.getText().toString();
            String birth = binding.strBirthdate.getText().toString();
            String gender = "";
            String state = binding.spnState.getSelectedItem().toString();

            if (binding.rbMale.isChecked()) {
                gender = binding.rbMale.getText().toString();
            } else if (binding.rbFemale.isChecked()) {
                gender = binding.rbFemale.getText().toString();
            }

            Map<String, String> params = new HashMap<>();
            params.put("selectFn", "fnSaveData");
            params.put("fullname", fullname);
            params.put("studNo", studNo);
            params.put("email", email);
            params.put("gender", gender);
            params.put("birthdate", birth);
            params.put("state", state);

            Log.d("fnAddToRest", "Params: " + params.toString());
            return params;
        }
    };
    requestQueue.add(stringRequest);
}

    private void fnAdd(View view) {
        String fullname = binding.strFullname.getText().toString();
        String studNo = binding.strStudNo.getText().toString();
        String email = binding.strEmail.getText().toString();
        String birth = binding.strBirthdate.getText().toString();
        String gender = "";
        String state = binding.spnState.getSelectedItem().toString();

        if(binding.rbMale.isChecked()){
            gender = binding.rbMale.getText().toString();
        }
        else if (binding.rbFemale.isChecked()) {
            gender = binding.rbFemale.getText().toString();
        }

        try {
            student = new Student(fullname, studNo, email, gender, birth, state);

            students.add(student);
            adapter.notifyItemInserted(students.size());
        }catch (Exception f){
            Log.e("ActivityStudentMain", "Error in fnAdd", f);
        }

    }

}
