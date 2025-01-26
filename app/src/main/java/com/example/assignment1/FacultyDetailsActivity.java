package com.example.assignment1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FacultyDetailsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_details);

        TextView tvDetails = findViewById(R.id.tvDetails);

        Faculty faculty = (Faculty) getIntent().getSerializableExtra("faculty");
        if (faculty != null) {
            String experience = calculateExperience(faculty.getDoj());
            tvDetails.setText("Name : " + faculty.getName() +
                    "\nDesignation : " + faculty.getDesignation() +
                    "\nGender : " + faculty.getGender() +
                    "\nDate of Joining : " + faculty.getDoj() +
                    "\nExperience : " + experience);
        }
    }

    private String calculateExperience(String doj) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date joiningDate = dateFormat.parse(doj);
            Calendar joinCal = Calendar.getInstance();
            joinCal.setTime(joiningDate);

            Calendar currentCal = Calendar.getInstance();

            int years = currentCal.get(Calendar.YEAR) - joinCal.get(Calendar.YEAR);
            int months = currentCal.get(Calendar.MONTH) - joinCal.get(Calendar.MONTH);
            int days = currentCal.get(Calendar.DAY_OF_MONTH) - joinCal.get(Calendar.DAY_OF_MONTH);

            if (months < 0) {
                years--;
                months += 12;
                days --;
            }

            return years + " years, " + months + " months &" + days + "days";
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid DOJ format";
        }
    }
}