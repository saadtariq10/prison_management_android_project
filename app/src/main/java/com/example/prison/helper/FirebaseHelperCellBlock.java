package com.example.prison.helper;

import com.example.prison.model.Block;
import com.example.prison.model.Inmate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FirebaseHelperCellBlock {

    private DatabaseReference blocksRef = FirebaseDatabase.getInstance().getReference("blocks");

    public void addBlock(Block block) {
        String key = blocksRef.push().getKey();
        if (key != null) {
            blocksRef.child(key).setValue(block);
        }
    }

    public CompletableFuture<List<Block>> getAllBlocks() {
        CompletableFuture<List<Block>> future = new CompletableFuture<>();
        blocksRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Block> blocks = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Block block = snapshot.getValue(Block.class);
                    blocks.add(block);
                }
                future.complete(blocks);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });
        return future;
    }
}