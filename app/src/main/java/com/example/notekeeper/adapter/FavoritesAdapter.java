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

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder>{

    ArrayList<NoteModel> mNoteModels;
    Context context;
    SharedPrefs prefs;





    public FavoritesAdapter(Context context) {

        this.context = context;
        this.prefs = new SharedPrefs(context);
        //sets the Data
        refresh();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favlist,null);

        ViewHolder obj = new ViewHolder(view);

        return  obj;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

       final  NoteModel currentNoteModel = mNoteModels.get(position);


        holder.mTitle.setText(currentNoteModel.getTitle());

        holder.mDes.setText(mNoteModels.get(position).getDescription());
        holder.mTime.setText(mNoteModels.get(position).getTime());

        holder.remove_favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isFav  = currentNoteModel.getIsfavorited();
                if (isFav == true){
                    //remove from favs
                    currentNoteModel.setIsfavorited(false);
                    Toast.makeText(context,"Removed from Favorites",Toast.LENGTH_SHORT).show();

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

    public  class ViewHolder extends RecyclerView.ViewHolder{
       // ImageView mImageView;
        TextView mTitle, mDes;
        TextView mTime;

        ImageButton remove_favButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //this.mImageView = itemView.findViewById(R.id.imagelv);
            this.mDes = itemView.findViewById(R.id.descriptionlv);
            this.mTitle = itemView.findViewById(R.id.titlelv);
            this.mTime = itemView.findViewById(R.id.dateTime);


            this.remove_favButton = itemView.findViewById(R.id.remove_favBtn);





        }



    }

    public void refresh(){
         ArrayList<NoteModel> favNotes = new ArrayList<>();

        ArrayList<NoteModel> allNotes = prefs.getAllNotes();
        for(int i = 0; i < allNotes.size(); i++){
            NoteModel item = allNotes.get(i);

            if(item.getIsfavorited()){
                favNotes.add(item);
            }
        }


        this.mNoteModels = favNotes;
        notifyDataSetChanged();
    }



}
