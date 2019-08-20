package com.example.firebase_practice;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class NewSpeedRecyclerViewAdapter extends RecyclerView.Adapter<NewSpeedRecyclerViewAdapter.NewSpeedViewHolder> {

    private ArrayList<NewSpeedRecyclerViewItem> mList;
    private Context context;

    public class NewSpeedViewHolder extends  RecyclerView.ViewHolder{

        protected TextView tvContent;
        protected TextView tvComment;

        public NewSpeedViewHolder(@NonNull View view) {
            super(view);
            this.tvContent = view.findViewById(R.id.tvContentNewspeed);
            this.tvComment = view.findViewById(R.id.tvCommentNewspeed);

            final View mView = view;
        }
    }
    public NewSpeedRecyclerViewAdapter(Context context, ArrayList<NewSpeedRecyclerViewItem> list){
        this.context = context;
        this.mList = list;
    }
    @NonNull
    @Override
    public NewSpeedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recyclerview_newspeed, viewGroup, false);

        NewSpeedViewHolder viewHolder = new NewSpeedViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewSpeedRecyclerViewAdapter.NewSpeedViewHolder holder, int position){
        holder.tvContent.setText(mList.get(position).getContent());
        holder.tvComment.setText(mList.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
