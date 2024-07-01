package com.example.prison.helper;

import com.example.prison.model.Prison;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseHelperPrison {

    private DatabaseReference prisonsRef = FirebaseDatabase.getInstance().getReference("prisons");

    public void addPrison(Prison prison) {
        String key = prisonsRef.push().getKey();
        if (key != null) {
            prisonsRef.child(key).setValue(prison);
        }
    }

    public CompletableFuture<List<Prison>> getAllPrisons() {
        CompletableFuture<List<Prison>> future = new CompletableFuture<>();
        prisonsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Prison> prisons = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Prison prison = snapshot.getValue(Prison.class);
                    prisons.add(prison);
                }
                future.complete(prisons);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });
        return future;
    }
}
