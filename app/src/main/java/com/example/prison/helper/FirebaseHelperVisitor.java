package com.example.prison.helper;

import com.example.prison.model.Visitor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FirebaseHelperVisitor {

    private DatabaseReference visitorsRef = FirebaseDatabase.getInstance().getReference("visitors");

    public void addVisitor(Visitor visitor) {
        String key = visitorsRef.push().getKey();
        if (key != null) {
            visitorsRef.child(key).setValue(visitor);
        }
    }

    public CompletableFuture<List<Visitor>> getAllVisitors() {
        CompletableFuture<List<Visitor>> future = new CompletableFuture<>();
        visitorsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Visitor> visitors = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Visitor visitor = snapshot.getValue(Visitor.class);
                    visitors.add(visitor);
                }
                future.complete(visitors);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });
        return future;
    }
}