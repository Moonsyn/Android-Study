package com.example.firebase_practice.Activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase_practice.Adapter.TimeLineRecyclerViewAdapter;
import com.example.firebase_practice.Entities.TimeLineRecyclerViewItem;
import com.example.firebase_practice.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class TimeLineActivity extends AppCompatActivity {

    private RecyclerView timeLineRecyclerView;
    private ArrayList<TimeLineRecyclerViewItem> mList;

    private LinearLayoutManager mLinearLayoutManager;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private CollectionReference timeLineColRef;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_timeline);

        timeLineRecyclerView = findViewById(R.id.rvTimeLine);
        mLinearLayoutManager = new LinearLayoutManager(this);
        timeLineRecyclerView.setLayoutManager(mLinearLayoutManager);
        mList = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        timeLineColRef = db.collection("users")
                .document("Seungyeon Moon").collection("Timeline");


        final TimeLineRecyclerViewAdapter timeLineRecyclerViewAdapter = new TimeLineRecyclerViewAdapter(TimeLineActivity.this, mList);

        for(int i=1;i<5;i++){
            timeLineColRef.document("num" + String.valueOf(i)).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException fe) {
                    if(documentSnapshot.exists()){
                        try{
                            String content = documentSnapshot.get("content").toString();
                            String comment = documentSnapshot.get("comment").toString();
                            mList.add(new TimeLineRecyclerViewItem(content, comment));
                            Log.d("mList", String.valueOf(mList.size()));
                            timeLineRecyclerViewAdapter.notifyDataSetChanged();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        fe.printStackTrace();
                    }

                }
            });
        }

        timeLineRecyclerView.setAdapter(timeLineRecyclerViewAdapter);


    }
}
