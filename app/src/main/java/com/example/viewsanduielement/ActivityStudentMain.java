package com.example.viewsanduielement;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewsanduielement.databinding.ActivityStudentMainBinding;

public class ActivityStudentMain extends AppCompatActivity {

    private String strFullname, strStudNo, strEmail, strGender, strBirthdate, strState;
    private ActivityStudentMainBinding binding;

    private Student student;
    private Vector<Student> students;
    private StudentAdapter adapter;
    private DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityStudentMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_main);

    }
    public class StudentViewHolder extends RecyclerView.ViewHolder {

        private final TextView lblFullname, lblStudNo, lblEmail, lblGender, lblBirthdate, lblState;
        @SuppressLint("WrongViewCast")
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.lblFullname = itemView.findViewById(R.id.strFullname);
            this.lblStudNo = itemView.findViewById(R.id.strStudNo);
            this.lblEmail = itemView.findViewById(R.id.strEmail);
            this.lblGender = itemView.findViewById(R.id.strGender);
            this.lblBirthdate = itemView.findViewById(R.id.strBirthdate);
            this.lblState = itemView.findViewById(R.id.strState);
        }

        public void setStudent(Student student) {
            lblFullname.setText(student.getStrFullname());
            lblStudNo.setText(student.getStrStudNo());
            lblEmail.setText(student.getStrEmail());
            lblGender.setText(student.getStrGender());
            lblBirthdate.setText(student.getStrBirthdate());
            lblState.setText(student.getStrState());
        }
    }


    public

    public String getStrFullname() {
        return strFullname;
    }

    public void setStrFullname(String strFullname) {
        this.strFullname = strFullname;
    }
    public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder>{

        private final LayoutInflater layoutInflater;
        private final Vector<Student> students;

        public StudentAdapter(LayoutInflater layoutInflater, Vector<Student> students) {
            this.layoutInflater = layoutInflater;
            this.students = students;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new StudentViewHolder(layoutInflater.inflate(R.layout.item_student,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            holder.setStudent(students.get(position));
        }

        @Override
        public int getItemCount() {
            return students.size();
        }
    }
}
