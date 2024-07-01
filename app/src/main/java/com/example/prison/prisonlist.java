package com.example.prison;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prison.helper.FirebaseHelperInmate;
import com.example.prison.helper.FirebaseHelperPrison;
import com.example.prison.helper.FirebaseHelperVisitor;
import com.example.prison.helper.SQLiteHelperPrison;
import com.example.prison.helper.SQLiteHelperVisitor;
import com.example.prison.helper.SqliteHelperInmate;
import com.example.prison.model.Inmate;
import com.example.prison.model.Prison;
import com.google.android.material.textfield.TextInputEditText;

public class prisonlist extends AppCompatActivity {
    private TextInputEditText prisonNameInput, prisonLocationInput, prisonCapacityInput;
    private Button submitButton;
    private Spinner prisonSecurityLevelSpinner;
    private FirebaseHelperPrison firebaseHelper;
    private SQLiteHelperPrison sqliteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prisonlist);

        firebaseHelper = new FirebaseHelperPrison();
        sqliteHelper = new SQLiteHelperPrison(this);

        prisonNameInput = findViewById(R.id.prisonNameInput);
      prisonLocationInput= findViewById(R.id.prisonLocationInput);
      prisonCapacityInput = findViewById(R.id.prisonCapacityInput);
        prisonSecurityLevelSpinner = findViewById(R.id.prisonSecurityLevelSpinner);

        submitButton = findViewById(R.id.submitButton);


        submitButton.setOnClickListener(v -> {
            String prisonName = prisonNameInput.getText().toString();
            String location = prisonLocationInput.getText().toString();
            int capacity = Integer.parseInt(prisonCapacityInput.getText().toString());
            String securityLevel = prisonSecurityLevelSpinner.getSelectedItem().toString();

            // Create a Prison object with the retrieved data
            Prison prison = new Prison(prisonName, location, capacity, securityLevel);

            // Add data to SQLite synchronously
            sqliteHelper.addPrison(prison);

            // Add data to Firebase
            firebaseHelper.addPrison(prison);
            Intent intent = new Intent(prisonlist.this, PrisonMain.class);
            startActivity(intent);
        });
    }
}