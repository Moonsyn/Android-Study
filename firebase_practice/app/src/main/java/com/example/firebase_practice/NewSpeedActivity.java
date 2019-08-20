package com.example.firebase_practice;

import android.app.AppComponentFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class NewSpeedActivity extends AppCompatActivity {

    private RecyclerView newSpeedRecyclerView;
    private NewSpeedRecyclerViewAdapter newSpeedRecyclerViewAdapter;
    private ArrayList<NewSpeedRecyclerViewItem> mList;

    private LinearLayoutManager mLinearLayoutManager;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private CollectionReference newSpeedColRef;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_newspeed);

        newSpeedRecyclerView = findViewById(R.id.rvNewspeed);
        mLinearLayoutManager = new LinearLayoutManager(this);
        newSpeedRecyclerView.setLayoutManager(mLinearLayoutManager);
        mList = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        newSpeedColRef = db.collection("users")
                .document(user.getDisplayName()).collection("NewSpeed");

        for(int i=0;i<10;i++){
            newSpeedColRef.document("num" + String.valueOf(i+1)).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException fe) {
                    try{
                        String content = documentSnapshot.get("content").toString();
                        String comment = documentSnapshot.get("comment").toString();
                        mList.add(new NewSpeedRecyclerViewItem(content, comment));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        newSpeedRecyclerViewAdapter = new NewSpeedRecyclerViewAdapter(NewSpeedActivity.this, mList);
        newSpeedRecyclerView.setAdapter(newSpeedRecyclerViewAdapter);

    }
}
