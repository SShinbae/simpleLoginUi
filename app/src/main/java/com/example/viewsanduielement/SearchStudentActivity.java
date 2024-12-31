package com.example.viewsanduielement;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.viewsanduielement.databinding.ActivitySearchStudentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
public class SearchStudentActivity extends AppCompatActivity {

    private ActivitySearchStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySearchStudentBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.btnSearch.setOnClickListener(this:: fnSearch );
    }
    
    private void fnSearch(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String strURL = "http://192.168.0.117/RESTAPI/rest_api.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("fnSearch", "Response: " + response);
                try {
                    Toast.makeText(SearchStudentActivity.this, "Getting some response here", Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        binding.txtVwStudName2.setText(jsonObject.getString("stud_name"));
                        binding.txtVwStudNo.setText(jsonObject.getString("stud_no"));
                        binding.txtVwStudGender.setText(jsonObject.getString("stud_gender"));
                        binding.txtVwStudState.setText(jsonObject.getString("stud_state"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
//            public void onResponse(String response) {
//                try {
//                    Toast.makeText(SearchStudentActivity.this, "Getting some respond here", Toast.LENGTH_SHORT).show();
//
//                    JSONObject jsonArray = new JSONObject(response);
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        binding.txtVwStudName2.setText(jsonObject.getString("stud_name"));
//                        binding.txtVwStudNo.setText(jsonObject.getString("stud)gender"));
//                        binding.txtVwStudGender.setText(jsonObject.getString("stud_no"));
//                        binding.txtVwStudState.setText(jsonObject.getString("stud_state"));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchStudentActivity.this, "Unable to fetch student info", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                String strStudNo = binding.edtStudID.getText().toString();
                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnSearchStudent");
                params.put("studID", binding.edtStudID.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    
}