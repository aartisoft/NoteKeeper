package com.example.notekeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notekeeper.R;
import com.example.notekeeper.model.NoteModel;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    ArrayList<NoteModel> NoteModels;
    Context context;

    public NoteAdapter(Context context, ArrayList<NoteModel> myList) {
        this.NoteModels = myList;
        this.context = context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,null);

        ViewHolder obj = new ViewHolder(view);

        return  obj;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTitle.setText(NoteModels.get(position).name);
        holder.mTitle.setText(NoteModels.get(position).getTitle());
        holder.mDes.setText(NoteModels.get(position).getDescription());
        holder.mImageView.setImageResource(NoteModels.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return NoteModels.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTitle, mDes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.mImageView = itemView.findViewById(R.id.imagelv);
            this.mDes = itemView.findViewById(R.id.descriptionlv);
            this.mTitle = itemView.findViewById(R.id.titlelv);

        }
    }

}
