package com.example.prison;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prison.helper.FirebaseHelperCellBlock;
import com.example.prison.helper.SQLiteHelperCellBlock;
import com.example.prison.model.Block;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cellblocklist extends AppCompatActivity {
    private FirebaseHelperCellBlock firebaseHelper;
    private SQLiteHelperCellBlock sqliteHelper;

    private TextInputEditText blockNameInput, capacityInput, floorInput, buildingInput, descriptionInput;
    private Spinner securityLevelSpinner;
    private Button submitButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cellblocklist);
        firebaseHelper = new FirebaseHelperCellBlock();
        sqliteHelper = new SQLiteHelperCellBlock(this);

        // Initialize views
        blockNameInput = findViewById(R.id.blockNameInput);
        capacityInput = findViewById(R.id.capacityInput);
        floorInput = findViewById(R.id.floorInput);
        buildingInput = findViewById(R.id.buildingInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        securityLevelSpinner = findViewById(R.id.securityLevelSpinner);
        submitButton = findViewById(R.id.submitButton);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("blocks");

        // Handle submit button click
        submitButton.setOnClickListener(v -> {
            // Get input values
            String blockName = blockNameInput.getText().toString().trim();
            String capacity = capacityInput.getText().toString().trim();
            String floor = floorInput.getText().toString().trim();
            String building = buildingInput.getText().toString().trim();
            String description = descriptionInput.getText().toString().trim();
            String securityLevel = securityLevelSpinner.getSelectedItem().toString();

            // Validate inputs
            if (blockName.isEmpty() || capacity.isEmpty() || floor.isEmpty() || building.isEmpty() || description.isEmpty()) {
                Toast.makeText(cellblocklist.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Save data to Firebase
                Block block = new Block(blockName, capacity, securityLevel, floor, building, description);
                // Add data to SQLite synchronously
                sqliteHelper.addBlock(block);

                // Add data to Firebase
                firebaseHelper.addBlock(block);
                Intent intent = new Intent(cellblocklist.this, CellBlockMain.class);
                startActivity(intent);
            }
        });
    }

}