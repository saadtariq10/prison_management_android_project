package com.example.prison;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prison.helper.FirebaseHelperInmate;
import com.example.prison.helper.SqliteHelperInmate;
import com.example.prison.model.Inmate;

import java.util.Calendar;

public class inmatelist extends AppCompatActivity {

    private FirebaseHelperInmate firebaseHelper;
    private SqliteHelperInmate sqliteHelper;
    private EditText fullNameInput, addressInput, complexionInput, eyeColorInput;

    private RadioGroup genderRadioGroup;
    private Spinner maritalStatusSpinner, crimeSpinner;
    private Button dobButton, sentenceStartDateButton, sentenceEndDateButton, submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inmatelist);

        firebaseHelper = new FirebaseHelperInmate();
        sqliteHelper = new SqliteHelperInmate(this);

        fullNameInput = findViewById(R.id.fullNameInput);
        dobButton = findViewById(R.id.dobButton);
        addressInput = findViewById(R.id.addressInput);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        maritalStatusSpinner = findViewById(R.id.maritalStatusSpinner);
        crimeSpinner = findViewById(R.id.crimeSpinner);
        complexionInput = findViewById(R.id.complexionInput);
        eyeColorInput = findViewById(R.id.eyeColorInput);
        sentenceStartDateButton = findViewById(R.id.sentenceStartDateButton);
        sentenceEndDateButton = findViewById(R.id.sentenceEndDateButton);
        submitButton = findViewById(R.id.submitButton);

        dobButton.setOnClickListener(v -> showDatePickerDialog(dobButton));

        sentenceStartDateButton.setOnClickListener(v -> showDatePickerDialog(sentenceStartDateButton));

        sentenceEndDateButton.setOnClickListener(v -> showDatePickerDialog(sentenceEndDateButton));

        submitButton.setOnClickListener(v -> {
            String fullName = fullNameInput.getText().toString();
            String dob = dobButton.getText().toString();
            String gender = ((RadioButton) findViewById(genderRadioGroup.getCheckedRadioButtonId())).getText().toString();
            String maritalStatus = maritalStatusSpinner.getSelectedItem().toString();
            String address = addressInput.getText().toString();
            String crime = crimeSpinner.getSelectedItem().toString();
            String complexion = complexionInput.getText().toString();
            String eyeColor = eyeColorInput.getText().toString();
            String sentenceStartDate = sentenceStartDateButton.getText().toString();
            String sentenceEndDate = sentenceEndDateButton.getText().toString();

            Inmate inmate = new Inmate(fullName, dob, gender, maritalStatus, address, crime, complexion, eyeColor, sentenceStartDate, sentenceEndDate);

            // Add data to SQLite synchronously
            sqliteHelper.addInmate(inmate);

            // Add data to Firebase
            firebaseHelper.addInmate(inmate);
            Intent intent = new Intent(inmatelist.this, InmateMain.class);
            startActivity(intent);
        });


    }

    private void showDatePickerDialog(final Button button) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(inmatelist.this, (view, year1, month1, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            button.setText(date);
        }, year, month, day);
        datePickerDialog.show();
    }
}
