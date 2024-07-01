package com.example.prison;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainDashboard extends AppCompatActivity implements View.OnClickListener {

    private ImageView inmateListImageView, visitorListImageView, prisonListImageView, cellBlockListImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        // Initialize views
        inmateListImageView = findViewById(R.id.inmateimg);
        visitorListImageView = findViewById(R.id.visitorimg);
        prisonListImageView = findViewById(R.id.prisonimg);
        cellBlockListImageView = findViewById(R.id.cellblockimg);

        // Set click listeners
        inmateListImageView.setOnClickListener(this);
        visitorListImageView.setOnClickListener(this);
        prisonListImageView.setOnClickListener(this);
        cellBlockListImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Handle click events
        Intent intent;
        if (v.getId() == R.id.inmateimg) {
            intent = new Intent(this, InmateMain.class);
        } else if (v.getId() == R.id.visitorimg) {
            intent = new Intent(this, VisitorMain.class);
        } else if (v.getId() == R.id.prisonimg) {
            intent = new Intent(this, PrisonMain.class);
        } else if (v.getId() == R.id.cellblockimg) {
            intent = new Intent(this, CellBlockMain.class);
        } else {
            return;
        }
        startActivity(intent);
    }
}
