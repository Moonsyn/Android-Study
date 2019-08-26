package com.example.firebase_practice.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firebase_practice.Entities.NewSpeedRecyclerViewItem;
import com.example.firebase_practice.R;

public class NewSpeedRecyclerViewAdapter extends RecyclerView.Adapter<NewSpeedRecyclerViewAdapter.NewSpeedViewHolder> {

    private ArrayList<NewSpeedRecyclerViewItem> mList;
    private Context context;

    private static final int VIEW_TYPE_TEXT = 0;
    private static final int VIEW_TYPE_IMAGE = 1;

    static class NewSpeedViewHolder extends  RecyclerView.ViewHolder{

        private TextView tvContent;
        private TextView tvComment;

        private NewSpeedViewHolder(@NonNull View view) {
            super(view);
            this.tvContent = view.findViewById(R.id.tvContentNewspeed);
            this.tvComment = view.findViewById(R.id.tvCommentNewspeed);
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

        //viewHolder.tvContent.setText(mList.get(viewHolder.getAdapterPosition()).getContent());
        //viewHolder.tvComment.setText(mList.get(viewHolder.getAdapterPosition()).getComment());

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewSpeedRecyclerViewAdapter.NewSpeedViewHolder holder, int position){
        Log.d("List Length", String.valueOf(mList.size()));
        Log.d("viewHolder index", String.valueOf(position));

        holder.tvContent.setText(mList.get(position).getContent());
        holder.tvComment.setText(mList.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    @Override
    public int getItemViewType(int position){
        // 이 코드를 이용해서 아이템 목록의 뷰 타입에 따라 코드를 가독성 있게 짤 수 있다.
        return position % 2 == 0 ? VIEW_TYPE_TEXT : VIEW_TYPE_IMAGE;
    }
}
