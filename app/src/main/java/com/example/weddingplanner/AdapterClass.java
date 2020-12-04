package com.example.weddingplanner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolderClass> {

    private ArrayList<Holder>listHolder;
    private OnItemClickListener mListener;
    private Context mcontext;


    public interface OnItemClickListener{
        void onItemClick(int position);


    }


    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }



    public static class ViewHolderClass extends RecyclerView.ViewHolder{

        public TextView tv;
        public TextView tvlocation;
        public TextView tvreview;
        public ImageView first;
        public ImageView second;
        public ImageView third;
        public ImageView fourth;
        public ImageView fifth;
        public ImageView hotelimage;
        public CardView cardView;


        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.textView);
            tvlocation=itemView.findViewById(R.id.location);
            tvreview=itemView.findViewById(R.id.review);
            first=itemView.findViewById(R.id.firststar);
            second=itemView.findViewById(R.id.secondstar);
            third=itemView.findViewById(R.id.thirdstar);
            fourth=itemView.findViewById(R.id.fourthstar);
            fifth=itemView.findViewById(R.id.fifthstar);
            hotelimage=itemView.findViewById(R.id.hotel_image_card);
            cardView=itemView.findViewById(R.id.card_view);



//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(listener !=null ){
//
//                        int position=getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION){
//                            listener.onItemClick(position);
//                        }
//
//
//                    }
//
//                }
//            });



        }
    }

    public AdapterClass( ArrayList<Holder> list, Context context) {
        listHolder=list;
        mcontext=context;

    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.alternative_hotel_card_view,viewGroup,false);
//
//
//        ViewHolderClass vh= new ViewHolderClass(v, mListener);
//        return vh;

        View v;
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        v=inflater.inflate(R.layout.alternative_hotel_card_view,viewGroup,false);
       final ViewHolderClass vh=new ViewHolderClass(v);

        vh.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mcontext, HotelDetails.class);
                i.putExtra("title",listHolder.get(vh.getAdapterPosition()).getTitle());
                i.putExtra("description",listHolder.get(vh.getAdapterPosition()).getDescription());
                i.putExtra("image",listHolder.get(vh.getAdapterPosition()).getImage_url());
                i.putExtra("location",listHolder.get(vh.getAdapterPosition()).getLocation());
                i.putExtra("rating",listHolder.get(vh.getAdapterPosition()).getRating());
                i.putExtra("review",listHolder.get(vh.getAdapterPosition()).getReview());
                i.putExtra("price",listHolder.get(vh.getAdapterPosition()).getPrice());
                i.putExtra("pplLimit",listHolder.get(vh.getAdapterPosition()).getPplLimit());


                mcontext.startActivity(i);

            }
        });




        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderClass viewHolderClass, int i) {

        Holder h=listHolder.get(i);
       viewHolderClass.tv.setText(h.getSm());
       viewHolderClass.tvlocation.setText(h.getLocation());
       viewHolderClass.tvreview.setText(h.getReview()+" Reviews");
       putStars(h.getRating(), viewHolderClass.first,viewHolderClass.second,viewHolderClass.third,viewHolderClass.fourth,viewHolderClass.fifth);


        Picasso.with(mcontext).load(h.getImage_url()).fit().centerInside().into(viewHolderClass.hotelimage);

    }


    public void putStars(double value, ImageView one,ImageView two, ImageView three, ImageView four, ImageView five){
        int newval= (int) Math.round(value/2);
        if (newval>=5){
            five.setImageResource(R.drawable.ic_star);

        }
        if (newval>=4){
            four.setImageResource(R.drawable.ic_star);

        }
        if (newval>=3){
            three.setImageResource(R.drawable.ic_star);

        }
        if (newval>=2){
            two.setImageResource(R.drawable.ic_star);

        }
        if (newval>=1){
            one.setImageResource(R.drawable.ic_star);

        }


    }

    @Override
    public int getItemCount() {

        return listHolder.size();
    }


}
