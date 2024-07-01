package com.example.prison;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prison.helper.FirebaseHelperVisitor;
import com.example.prison.helper.SQLiteHelperVisitor;
import com.example.prison.model.Visitor;

import java.util.List;

public class VisitorMain extends AppCompatActivity {

    private FirebaseHelperVisitor firebaseHelper;
    private SQLiteHelperVisitor sqliteHelper;
    private ListView listView;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_main);

        firebaseHelper = new FirebaseHelperVisitor();
        sqliteHelper = new SQLiteHelperVisitor(this);
        listView = findViewById(R.id.listView);
        if (isNetworkAvailable()) {
            // Retrieve data from Firebase
            firebaseHelper.getAllVisitors().thenApply(visitors -> {
                // Populate ListView with retrieved visitors
                adapter = new ArrayAdapter<>(this, R.layout.list_format, visitors);
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
        List<Visitor> visitors = sqliteHelper.getAllVisitors();
        // Populate ListView with retrieved data
        ArrayAdapter<Visitor> adapter = new ArrayAdapter<>(this, R.layout.list_format, visitors);
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
