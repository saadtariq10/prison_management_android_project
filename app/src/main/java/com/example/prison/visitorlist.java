package com.example.prison;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prison.helper.FirebaseHelperInmate;
import com.example.prison.helper.FirebaseHelperVisitor;
import com.example.prison.helper.SQLiteHelperVisitor;
import com.example.prison.helper.SqliteHelperInmate;
import com.example.prison.model.Inmate;
import com.example.prison.model.Visitor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Locale;

public class visitorlist extends AppCompatActivity {

    private TextInputEditText visitorNameInput, contactInput, inmateNameInput, relationshipInput;
    private Button visitDateButton, visitTimeButton, submitButton;
    private FirebaseHelperVisitor firebaseHelper;
    private SQLiteHelperVisitor sqliteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitorlist);

        firebaseHelper = new FirebaseHelperVisitor();
        sqliteHelper = new SQLiteHelperVisitor(this);

        visitorNameInput = findViewById(R.id.visitorNameInput);
        contactInput = findViewById(R.id.contactInput);
        inmateNameInput = findViewById(R.id.inmateNameInput);
        relationshipInput = findViewById(R.id.relationshipInput);
        visitDateButton = findViewById(R.id.visitDateButton);
        visitTimeButton = findViewById(R.id.visitTimeButton);
        submitButton = findViewById(R.id.submitButton);

        visitDateButton.setOnClickListener(v -> showDatePickerDialog(visitDateButton));

        visitTimeButton.setOnClickListener(v -> showTimePickerDialog(visitTimeButton));


        submitButton.setOnClickListener(v -> {
            String visitorName = visitorNameInput.getText().toString();
            String contact = contactInput.getText().toString();
            String inmateName = inmateNameInput.getText().toString();
            String relationship = relationshipInput.getText().toString();
            String visitDate = visitDateButton.getText().toString();
            String visitTime = visitTimeButton.getText().toString();

            Visitor visitor = new Visitor(visitorName, contact, inmateName, relationship, visitDate, visitTime);
            // Add data to SQLite synchronously
            sqliteHelper.addVisitor(visitor);

            // Add data to Firebase
            firebaseHelper.addVisitor(visitor);
            Intent intent = new Intent(visitorlist.this, VisitorMain.class);
            startActivity(intent);
        });


    }

    private void showDatePickerDialog(final Button button) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(visitorlist.this, (view, year1, month1, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            button.setText(date);
        }, year, month, day);
        datePickerDialog.show();
    }
    private void showTimePickerDialog(final Button button) {
        // Get the current time
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(visitorlist.this,
                (view, hourOfDay, minute1) -> {
                    // Format the selected time (optional)
                    String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute1);

                    // Update the text of the button with the selected time
                    button.setText(selectedTime);
                }, hour, minute, true);

        // Show the TimePickerDialog
        timePickerDialog.show();
    }

}


