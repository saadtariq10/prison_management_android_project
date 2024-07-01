package com.example.prison.helper;

import com.example.prison.model.Inmate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class FirebaseHelperInmate {

    private DatabaseReference inmatesRef = FirebaseDatabase.getInstance().getReference("inmates");

    public void addInmate(Inmate inmate) {
        String key = inmatesRef.push().getKey();
        if (key != null) {
            inmatesRef.child(key).setValue(inmate);
        }
    }

    public CompletableFuture<List<Inmate>> getAllInmates() {
        CompletableFuture<List<Inmate>> future = new CompletableFuture<>();
        inmatesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Inmate> inmates = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Inmate inmate = snapshot.getValue(Inmate.class);
                    inmates.add(inmate);
                }
                future.complete(inmates);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });
        return future;
    }
}