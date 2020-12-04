package com.example.weddingplanner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class GuestsAdapterClass extends RecyclerView.Adapter<GuestsAdapterClass.ViewHolderClass> {

    public static int index= 0;

    private ArrayList<GuestsList.MyContact> listHolder;

    private int [] colorResources={
            R.color.red,

            R.color.yellow,
            R.color.green,
            R.color.purple,
            R.color.orange,
            R.color.blue






    };

    private Context mcontext;




    public static class ViewHolderClass extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {


        public TextView name;
        public View vview;
        public ImageView imageView;
        public CardView cardView;

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(),121,0,"Remove guest");
          //  menu.add(this.getAdapterPosition(),122,1,"add to wish item");
           // menu.setHeaderTitle("Chppse");

        }

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.contactname);
            vview=itemView.findViewById(R.id.view);
            imageView=itemView.findViewById(R.id.icon);
            cardView= itemView.findViewById(R.id.card_guests);

            cardView.setOnCreateContextMenuListener(this);


        }
    }



    public GuestsAdapterClass(ArrayList<GuestsList.MyContact> list,Context context) {
        listHolder=list;
        mcontext=context;

    }


    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cards_item_guests,viewGroup,false);

        ViewHolderClass vh= new ViewHolderClass(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderClass viewHolderClass, int i) {
        GuestsList.MyContact h=listHolder.get(i);
        viewHolderClass.name.setText(h.contactname);
        index=index%6;

       // viewHolderClass.imageView.setColorFilter(colorResources[index]);
        viewHolderClass.vview.setBackgroundResource(colorResources[index++]);



    }

    @Override
    public int getItemCount() {
        return listHolder.size();
    }
}
