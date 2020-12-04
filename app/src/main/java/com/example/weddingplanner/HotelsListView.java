package com.example.weddingplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HotelsListView extends AppCompatActivity {
//    private RecyclerView mRecyclerView;
//    private AdapterClass mAdapter;
//    private RecyclerView.LayoutManager layoutManager;
//    private RequestQueue mRequestQueue;
//    ArrayList<Holder>holderList;


    private final String JSON_URL="http://amexabiy.com/hanna/json/getHotels.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private ArrayList<Holder>holderList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_list_view);
        Log.d("Haaaaaa","hererereer");

        holderList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        jsonrequest();

//        mRecyclerView=findViewById(R.id.recyclerView);
//        mRecyclerView.setHasFixedSize(true);
//        layoutManager= new LinearLayoutManager(this);
//        holderList=new ArrayList<>();
//        mRequestQueue= Volley.newRequestQueue(this);
//        mRecyclerView.setLayoutManager(layoutManager);
      //  parseJSON();
    }


    private  void jsonrequest(){
        request=new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Haaaaa","345678");

                JSONObject jsonObject=null;
                for(int i=0;i<response.length();i++){
                    Log.d("Haaaaa",response.length()+"");


                    try {
                        jsonObject=response.getJSONObject(i);
                        String hotelName=jsonObject.getString("name");
                        Log.d("Haaaaaaaaaaa",hotelName);
                        String imageUrl=jsonObject.getString("image_src");

                        int review=jsonObject.getInt("review");
                        double rating=jsonObject.getDouble("rating");
                        String location=jsonObject.getString("location");
                        String description=jsonObject.getString("description");
                        int price=jsonObject.getInt("price_per_dish");
                        int pplLimit=jsonObject.getInt("People_limit");
                        Holder hotel=new Holder( hotelName, description, imageUrl,location,  rating,  review,price,pplLimit);
                            holderList.add(hotel);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                setUpRecyclerview(holderList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue=Volley.newRequestQueue(HotelsListView.this);
        requestQueue.add(request);
    }
    private void setUpRecyclerview(ArrayList<Holder>hh){

        AdapterClass mAdapter=new AdapterClass(hh,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);


    }


//    private void parseJSON(){
//        Log.d("Haaaaaa","insideparseJSOn");
//        String url="http://amexabiy.com/hanna/json/getHotels.php";
//        JsonObjectRequest request =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d("Haaaaaa","hererereer");
//                try {
//                    Log.d("Haaaaaa","hererereer");
//                    JSONArray jsonArray=response.getJSONArray(null);
//                    for(int i=0; i<jsonArray.length();i++){
//                        JSONObject hotel=jsonArray.getJSONObject(i);
//                        String hotelName=hotel.getString("name");
//                        Log.d("Haaaaaaaaaaa",hotelName);
//                        String imageUrl=hotel.getString("image_src");
//
//                        int review=hotel.getInt("review");
//                        double rating=hotel.getDouble("rating");
//                        String location=hotel.getString("location");
//                        String description=hotel.getString("description");
//                        int price=hotel.getInt("price_per_dish");
//                        int pplLimit=hotel.getInt("People_limit");
//                        holderList.add(new Holder( hotelName, description, imageUrl,location,  rating,  review,price,pplLimit));
//
//
//                    }
//                    mAdapter= new AdapterClass(holderList,HotelsListView.this);
//                    mRecyclerView.setAdapter(mAdapter);
//                } catch (JSONException e) {
//                    Log.d("notoo","herreeee");
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        mRequestQueue.add(request);
//
//    }

    public void start(){
        Intent i=new Intent(this,HotelDetails.class);
        startActivity(i);

    }
}
