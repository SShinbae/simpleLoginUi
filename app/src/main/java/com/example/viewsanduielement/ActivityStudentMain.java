package com.example.viewsanduielement;

import android.app.DatePickerDialog;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.DatePicker;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.viewsanduielement.databinding.ActivityStudentMainBinding;

import java.util.Calendar;
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

        binding.fabAdd.setOnClickListener(this:: fnAdd);
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
                       int monthOfYear = month + 1;
                        binding.strBirthdate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
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
