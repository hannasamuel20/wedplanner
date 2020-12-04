package com.example.weddingplanner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesAdapterClass extends RecyclerView.Adapter<NotesAdapterClass.ViewHolderClass>  {
public interface OnItemClickListener{
    void onChecked(int position);


}
private OnItemClickListener listener;

public void setOnItemClickListener(OnItemClickListener listener){
    this.listener=listener;
}


    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass viewHolderClass, int i) {
        Note h=listHolder.get(i);
        viewHolderClass.note.setText(h.getMemo());
        viewHolderClass.tvdate.setText(h.getDate());
        viewHolderClass.checkb.setChecked(h.isChecked());
    }

    private ArrayList<Note> listHolder;




    public static class ViewHolderClass extends RecyclerView.ViewHolder{


        public TextView note;
        public TextView tvdate;
        public CheckBox checkb;


        public ViewHolderClass(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            note=itemView.findViewById(R.id.memo);
            tvdate=itemView.findViewById(R.id.date);
            checkb=itemView.findViewById(R.id.checkBox);


            checkb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onChecked(position);
                        }

                    }
                }
            });







        }
    }

    public NotesAdapterClass(ArrayList<Note> list) {
        listHolder=list;

    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cards_item_memos,viewGroup,false);


        ViewHolderClass vh= new ViewHolderClass(v,listener);
        return vh;

    }


    @Override
    public int getItemCount() {

        return listHolder.size();
    }





}
