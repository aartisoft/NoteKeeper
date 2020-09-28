package com.example.securenote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.securenote.R;
import com.example.securenote.model.NoteModel;
import com.example.securenote.utils.SharedPrefs;

import java.util.ArrayList;

public class RecycleBinAdapter extends RecyclerView.Adapter<RecycleBinAdapter.ViewHolder>{

    ArrayList<NoteModel> mNoteModels;
    Context context;
    SharedPrefs prefs;





    public RecycleBinAdapter(Context context) {
        this.context = context;
        this.prefs = new SharedPrefs(context);
        //sets the data
        refresh();





    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deletedlist,null);

        ViewHolder obj = new ViewHolder(view);

        return  obj;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

       final  NoteModel currentDelNote = mNoteModels.get(position);


        holder.mTitle.setText(currentDelNote.getTitle());

        holder.mDes.setText(mNoteModels.get(position).getDescription());
        holder.mTime.setText(mNoteModels.get(position).getTime());

        holder.restoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isDel  = currentDelNote.getDeleted();
                if (isDel){
                    //remove from RecycleBin

                    currentDelNote.setDeleted(false);


                    Toast.makeText(context,"Note Restored",Toast.LENGTH_SHORT).show();

                }



                prefs.updateNote(currentDelNote);
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

        ImageButton restoreBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //this.mImageView = itemView.findViewById(R.id.imagelv);
            this.mDes = itemView.findViewById(R.id.descriptionlv);
            this.mTitle = itemView.findViewById(R.id.titlelv);
            this.mTime = itemView.findViewById(R.id.dateTime);


            this.restoreBtn = itemView.findViewById(R.id.restoreButton);






        }


    }

    public void refresh(){
        ArrayList<NoteModel> delNotes = new ArrayList<>();

        ArrayList<NoteModel> allNotes = prefs.getAllNotes();
        for(int i = 0; i < allNotes.size(); i++){
            NoteModel item = allNotes.get(i);

            if(item.getDeleted()){
                delNotes.add(item);
            }
        }


        this.mNoteModels = delNotes;
        notifyDataSetChanged();
    }

}
