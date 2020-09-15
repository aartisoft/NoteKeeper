package com.example.notekeeper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notekeeper.R;
import com.example.notekeeper.model.NoteModel;
import com.example.notekeeper.utils.SharedPrefs;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    ArrayList<NoteModel> mNoteModels;
    Context context;
    OnNoteListener mOnNoteListener;
    SharedPrefs prefs;





    public NoteAdapter(Context context, OnNoteListener onNoteListener) {

        this.context = context;
        this.mOnNoteListener = onNoteListener;
        this.prefs = new SharedPrefs(context);
        //sets Data
        refresh();




    }

    /*public NoteAdapter(Context context){
        this.context = context;
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,null);

        ViewHolder obj = new ViewHolder(view, mOnNoteListener);

        return  obj;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int position) {

       final  NoteModel currentNoteModel = mNoteModels.get(position);


        holder.mTitle.setText(currentNoteModel.getTitle());

        holder.mDes.setText(mNoteModels.get(position).getDescription());
        holder.mTime.setText(mNoteModels.get(position).getTime());

      final  boolean isFav  = currentNoteModel.getIsfavorited();

      if (isFav == true){
          holder.favBtn.setColorFilter(context.getResources().getColor(R.color.colorPrimary));
      }
      else{
          holder.favBtn.setColorFilter(Color.GRAY);
      }


        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFav == true){
                    //remove from favs

                    currentNoteModel.setIsfavorited(false);
                    holder.favBtn.setColorFilter(Color.GRAY);
                    Toast.makeText(context,"Removed from Favorites",Toast.LENGTH_SHORT).show();

                }else{
                    //favourite note
                    currentNoteModel.setIsfavorited(true);
                    holder.favBtn.setColorFilter(Color.RED);
                    Toast.makeText(context,"Added to Favorites",Toast.LENGTH_SHORT).show();

                }

                prefs.updateNote(currentNoteModel);
                refresh();

            }
        });
        //holder.mImageView.setImageResource(NoteModels.get(position).getImg());


    }

    @Override
    public int getItemCount() {
        return mNoteModels.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       // ImageView mImageView;
        TextView mTitle, mDes;
        TextView mTime;
        OnNoteListener onNoteListener;
        ImageButton favBtn;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            //this.mImageView = itemView.findViewById(R.id.imagelv);
            this.mDes = itemView.findViewById(R.id.descriptionlv);
            this.mTitle = itemView.findViewById(R.id.titlelv);
            this.mTime = itemView.findViewById(R.id.dateTime);

            this.onNoteListener = onNoteListener;
            this.favBtn = itemView.findViewById(R.id.fav_button);



            itemView.setOnClickListener(this);//Attatches ViewHolder to OnClickListener

        }


        @Override
        public void onClick(View v) {
            onNoteListener.OnNoteClick(mNoteModels.get(getAbsoluteAdapterPosition()));

        }
    }

    public void refresh(){
        ArrayList<NoteModel> list = new ArrayList<>();

        ArrayList<NoteModel> allNotes = prefs.getAllNotes();
        for (int i = 0; i < allNotes.size(); i++){
            NoteModel item = allNotes.get(i);

            if (item.getDeleted() == false){
                list.add(item);
            }
        }

        this.mNoteModels = list;
        notifyDataSetChanged();
    }

//interface to detect item click in recycler
    public interface OnNoteListener{
        void OnNoteClick(NoteModel noteModel);//sends position of clicked item
    }

}
