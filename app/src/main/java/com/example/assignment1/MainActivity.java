package com.example.assignment1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText etFacultyName, etDoJ;
    private Spinner spinnerDesignation;
    private RadioGroup rgGender;
    private Button btnSubmit, btnPickDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFacultyName = findViewById(R.id.etFacultyName);
        etDoJ = findViewById(R.id.etDoJ);
        spinnerDesignation = findViewById(R.id.spinnerDesignation);
        rgGender = findViewById(R.id.rgGender);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnPickDate = findViewById(R.id.btnPickDate);

        btnPickDate.setOnClickListener(v -> showDatePicker());

        btnSubmit.setOnClickListener(v -> {
            String name = etFacultyName.getText().toString();
            String designation = spinnerDesignation.getSelectedItem().toString();
            String gender = ((RadioButton) findViewById(rgGender.getCheckedRadioButtonId())).getText().toString();
            String doj = etDoJ.getText().toString();

            Faculty faculty = new Faculty();
            faculty.setName(name);
            faculty.setDesignation(designation);
            faculty.setGender(gender);
            faculty.setDoj(doj);

            Intent intent = new Intent(MainActivity.this, FacultyDetailsActivity.class);
            intent.putExtra("faculty", faculty);
            startActivity(intent);
        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            etDoJ.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1);
        }, year, month, day);
        datePickerDialog.show();
    }
}