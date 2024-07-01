package com.example.prison;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.prison.helper.FirebaseHelperCellBlock;
import com.example.prison.helper.SQLiteHelperCellBlock;
import com.example.prison.model.Block;

import java.util.List;

public class CellBlockMain extends AppCompatActivity {

    private FirebaseHelperCellBlock blockFirebaseHelper;
    private SQLiteHelperCellBlock sqliteHelper;

    private ListView listView;
    private ArrayAdapter<Block> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_block_main);

        blockFirebaseHelper = new FirebaseHelperCellBlock();
        sqliteHelper = new SQLiteHelperCellBlock(this);

        listView = findViewById(R.id.listView);

        if (isNetworkAvailable()) {
            // Retrieve data from Firebase
            blockFirebaseHelper.getAllBlocks().thenApply(blocks -> {
                // Populate ListView with retrieved inmates
                adapter = new ArrayAdapter<>(this, R.layout.list_format, blocks);
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
        List<Block> blocks = sqliteHelper.getAllBlocks();
        // Populate ListView with retrieved data
        ArrayAdapter<Block> adapter = new ArrayAdapter<>(this, R.layout.list_format, blocks);
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