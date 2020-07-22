package com.example.notekeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notekeeper.R;
import com.example.notekeeper.model.NoteModel;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    ArrayList<NoteModel> mNoteModels;
    Context context;
    OnNoteListener mOnNoteListener;




    public NoteAdapter(Context context, ArrayList<NoteModel> myList, OnNoteListener onNoteListener) {
        this.mNoteModels = myList;
        this.context = context;
        this.mOnNoteListener = onNoteListener;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,null);

        ViewHolder obj = new ViewHolder(view, mOnNoteListener);

        return  obj;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTitle.setText(mNoteModels.get(position).name);
        holder.mTitle.setText(mNoteModels.get(position).getTitle());
        holder.mDes.setText(mNoteModels.get(position).getDescription());
        //holder.mImageView.setImageResource(NoteModels.get(position).getImg());


    }

    @Override
    public int getItemCount() {
        return mNoteModels.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       // ImageView mImageView;
        TextView mTitle, mDes;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            //this.mImageView = itemView.findViewById(R.id.imagelv);
            this.mDes = itemView.findViewById(R.id.descriptionlv);
            this.mTitle = itemView.findViewById(R.id.titlelv);

            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);//Attatches ViewHolder to OnClickListener

        }


        @Override
        public void onClick(View v) {
            onNoteListener.OnNoteClick(mNoteModels.get(getAbsoluteAdapterPosition()));

        }
    }

    public void refresh(ArrayList<NoteModel> list){
        mNoteModels = list;
        notifyDataSetChanged();
    }
//interface to detect item click in recycler
    public interface OnNoteListener{
        void OnNoteClick(NoteModel noteModel);//sends position of clicked item
    }

}
