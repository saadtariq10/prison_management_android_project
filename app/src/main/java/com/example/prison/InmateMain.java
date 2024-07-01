package com.example.prison;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prison.helper.FirebaseHelperInmate;
import com.example.prison.helper.SqliteHelperInmate;
import com.example.prison.model.Inmate;

import java.util.List;

public class InmateMain extends AppCompatActivity {

    private FirebaseHelperInmate firebaseHelper;
    private SqliteHelperInmate sqliteHelper;
    private ListView listView;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inmate_main);

        firebaseHelper = new FirebaseHelperInmate();
        sqliteHelper = new SqliteHelperInmate(this);
        listView = findViewById(R.id.listView);
        if (isNetworkAvailable()) {
            // Retrieve data from Firebase
            firebaseHelper.getAllInmates().thenApply(inmates -> {
                // Populate ListView with retrieved inmates
                adapter = new ArrayAdapter<>(this, R.layout.list_format, inmates);
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
        List<Inmate> inmates = sqliteHelper.getAllInmates();
        // Populate ListView with retrieved data
        ArrayAdapter<Inmate> adapter = new ArrayAdapter<>(this, R.layout.list_format, inmates);
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