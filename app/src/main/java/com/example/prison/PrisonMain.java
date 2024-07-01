package com.example.prison;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prison.helper.FirebaseHelperPrison;
import com.example.prison.helper.SQLiteHelperPrison;
import com.example.prison.model.Prison;

import java.util.List;

public class PrisonMain extends AppCompatActivity {

    private FirebaseHelperPrison firebaseHelper;
    private SQLiteHelperPrison sqliteHelper;
    private ListView listView;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prison_main);

        firebaseHelper = new FirebaseHelperPrison();
        sqliteHelper = new SQLiteHelperPrison(this);
        listView = findViewById(R.id.listView);
        if (isNetworkAvailable()) {
            // Retrieve data from Firebase
            firebaseHelper.getAllPrisons().thenApply(prisons -> {
                // Populate ListView with retrieved prisons
                adapter = new ArrayAdapter<>(this, R.layout.list_format, prisons);
                listView.setAdapter(adapter);
                return null;
            }).handle((result, exception) -> {
                if (exception != null) {
                    // Handle failure
                    Toast.makeText(this, "Failed to retrieve data from Firebase: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    // Retrieve data from SQLite
                    retrieveDataFromSQLite();
                }
                return null;
            });
        } else {
            // Retrieve data from SQLite
            retrieveDataFromSQLite();
        }
    }

    private void retrieveDataFromSQLite() {
        // Retrieve data from SQLite
        List<Prison> prisons = sqliteHelper.getAllPrisons();
        // Populate ListView with retrieved data
        ArrayAdapter<Prison> adapter = new ArrayAdapter<>(this, R.layout.list_format, prisons);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
}