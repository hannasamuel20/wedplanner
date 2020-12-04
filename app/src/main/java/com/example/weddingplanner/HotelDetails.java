package com.example.weddingplanner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class HotelDetails extends AppCompatActivity {
    private TextView tvHotelName;
    private TextView tvHotelLocation;
    private TextView tvHotelPrice;
    private TextView tvHotelPrice2;
    private TextView tvHotelDescription;
    private TextView tvHotelMax;
    private TextView tvHotelReview;
    private TextView tvHotelRating;
    private ImageView imFavourite;
    private CollapsingToolbarLayout ctb;

    boolean isclicked=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        tvHotelName=findViewById(R.id.hotel_name);
        tvHotelLocation=findViewById(R.id.hotel_location);
        tvHotelPrice=findViewById(R.id.hotel_price);
        tvHotelPrice2=findViewById(R.id.priceperdish);
        tvHotelDescription=findViewById(R.id.hotel_description);
        tvHotelMax=findViewById(R.id.maxppl);
        tvHotelReview=findViewById(R.id.review);
        tvHotelRating=findViewById(R.id.rating);
        imFavourite=findViewById(R.id.favourite);
        ctb=findViewById(R.id.collapsingToolbar);



        imFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isclicked){

                    imFavourite.setImageResource(R.drawable.heart);

                    int cost=Integer.parseInt(tvHotelPrice.getText().toString());
                    Log.d("yellowwww",String.valueOf(cost));
                    MainActivity.hotelPrice=cost;
                    MainActivity.hotelName=tvHotelName.getText().toString();
                    isclicked=true;
                }
                else{
                    imFavourite.setImageResource(R.drawable.heart_outline);
                    MainActivity.hotelPrice=0;
                    MainActivity.hotelName="Hotel Name";
                    isclicked=false;

                }


            }
        });


        Intent i=getIntent();

        String title=i.getStringExtra("title");
        String description=i.getStringExtra("description");
        String image=i.getStringExtra("image");
        String location=i.getStringExtra("location");
        double rating=i.getDoubleExtra("rating",0);
        int review=i.getIntExtra("review",0);
        int price=i.getIntExtra("price",0);
        int pplLimit=i.getIntExtra("pplLimit",0);

        Picasso.with(HotelDetails.this).load(image).into(new Target(){

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ctb.setBackground(new BitmapDrawable(HotelDetails.this.getResources(), bitmap));
                }
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                Log.d("TAG", "FAILED");
            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {
                Log.d("TAG", "Prepare Load");
            }
        });

        tvHotelName.setText(title);
        tvHotelDescription.setText(description+description);
        tvHotelLocation.setText(location);
        tvHotelRating.setText(String.valueOf(rating)+" Ratings");
        tvHotelReview.setText(String.valueOf(review)+" Reviews");
        tvHotelPrice.setText(String.valueOf(price));
        tvHotelPrice2.setText(String.valueOf(price)+" ETB per Dish");
        tvHotelMax.setText("Max of"+String.valueOf(pplLimit)+" People");




    }
}
