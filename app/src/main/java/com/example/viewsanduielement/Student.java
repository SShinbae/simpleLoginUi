package com.example.viewsanduielement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class Student {
    private String strFullname, strStudNo, strEmail, strGender, strBirthdate, strState;

    public Student(String strFullname, String strStudNo, String strEmail, String strGender, String strBirthdate, String strState) {
        this.strFullname = strFullname;
        this.strStudNo = strStudNo;
        this.strEmail = strEmail;
        this.strGender = strGender;
        this.strBirthdate = strBirthdate;
        this.strState = strState;
    }

    public String getStrFullname() {
        return strFullname;
    }

    public void setStrFullname(String strFullname) {
        this.strFullname = strFullname;
    }

    public String getStrStudNo() {
        return strStudNo;
    }

    public void setStrStudNo(String strStudNo) {
        this.strStudNo = strStudNo;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getStrGender() {
        return strGender;
    }

    public void setStrGender(String strGender) {
        this.strGender = strGender;
    }

    public String getStrBirthdate() {
        return strBirthdate;
    }

    public void setStrBirthdate(String strBirthdate) {
        this.strBirthdate = strBirthdate;
    }

    public String getStrState() {
        return strState;
    }

    public void setStrState(String strState) {
        this.strState = strState;
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {

        private final TextView lblFullname, lblStudNo, lblEmail, lblGender, lblBirthdate, lblState;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.lblFullname = itemView.findViewById(R.id.lblFullname);
            this.lblStudNo = itemView.findViewById(R.id.lblStudNo);
            this.lblEmail = itemView.findViewById(R.id.lblEmail);
            this.lblGender = itemView.findViewById(R.id.lblGender);
            this.lblBirthdate = itemView.findViewById(R.id.lblBirthdate);
            this.lblState = itemView.findViewById(R.id.lblState);
        }

        public void setStudent(Student student) {
            lblFullname.setText(student.getStrFullname());
            lblStudNo.setText(student.getStrStudNo());
            lblEmail.setText(student.getStrEmail());
            lblGender.setText(student.getStrGender());
            lblBirthdate.setText(student.getStrBirthdate());
            lblState.setText(student.getStrState());
        }

        public static class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder>{

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

}
