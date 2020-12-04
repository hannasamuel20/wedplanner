package com.example.weddingplanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    public static int hotelPrice;
    public static String hotelName;

    private TextView mTextMessage;
    private TextView mBudgetNumber;
    private TextView mTotalBudget;
    private TextView mCompletedTasks;
    private TextView mTotalTasks;
    private TextView mTotalGuests;
    private TextView mHotelName;
    private TextView holdert;
    private TextView holderd;
    private CardView hotelCard;
    private CardView memoCard;
    private CardView guestsCard;

    public static int returnBudget(){
        return hotelPrice*GuestsList.contactArrayList.size();
    }
    public static int budgetNum=returnBudget();


    private static  long originalDiff;
    private long diff;
    boolean check=true;

    private CountDownTimer countDownTimer;
    private ColorfulRingProgressView crpv;








    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_notifications:
//                    Fragment frag= new DiscoverFragment();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,frag).commit();

                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_discover:
//                    mTextMessage.setText(R.string.title_discover);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mTotalBudget.setText("/"+Main2Activity.budgetVal+" ETB");
        Log.d("bbbbbbbbbb",String.valueOf(budgetNum)+"    "   +String.valueOf(budgetNum)+"  "  +GuestsList.contactArrayList.size()+"   "+hotelPrice);
        mBudgetNumber.setText(String.valueOf(returnBudget()));
        if(hotelName!=null) {
            mHotelName.setText(hotelName);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tool=findViewById(R.id.tool);
        setSupportActionBar(tool);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        holdert=findViewById(R.id.displayt);
        holderd=findViewById(R.id.day);
        crpv =  findViewById(R.id.crpv);
        hotelCard=findViewById(R.id.hotel_card);
        memoCard=findViewById(R.id.tasks_card);
        guestsCard=findViewById(R.id.guests_card);
        mHotelName=findViewById(R.id.hotel_name);


        showContacts();



        crpv.setPercent(0);
        crpv.setStartAngle(0);
        calculate();


        mBudgetNumber=findViewById(R.id.budget_number);
        mTotalBudget=findViewById(R.id.total_budget);

        mTotalBudget.setText("/"+Main2Activity.budgetVal+" ETB");
        Log.d("bbbbbbbbbb",String.valueOf(budgetNum)+"    "   +String.valueOf(budgetNum)+"  "  +GuestsList.contactArrayList.size()+"   "+hotelPrice);
        mBudgetNumber.setText(String.valueOf(returnBudget()));





        mCompletedTasks=findViewById(R.id.completed_tasks);
        mTotalTasks=findViewById(R.id.total_tasks);

        mCompletedTasks.setText(String.valueOf(ToDoLists.completedTasks()));
        mTotalTasks.setText("/"+String.valueOf(ToDoLists.holderList.size())+" Tasks");



        mTotalGuests=findViewById(R.id.total_guests);
        mTotalGuests.setText(String.valueOf(GuestsList.contactArrayList.size()));


        if(hotelName!=null) {
            mHotelName.setText(hotelName);
        }








        countDownTimer=new CountDownTimer(diff,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                diff=millisUntilFinished;
                int res= 100-(int) (100 * diff /originalDiff);

                int animationDuration = 2500; // 2500ms = 2,5s
                crpv.setPercent(res);
                //circularProgressBar.setProgressWithAnimation(res, animationDuration);
                calculate();

            }

            @Override
            public void onFinish() {
                holdert.setText(getString(R.string.time_holder,0,0,0));


            }
        }.start();

        hotelCard.setOnClickListener(this);
        memoCard.setOnClickListener(this);
        guestsCard.setOnClickListener(this);




    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == GuestsList.PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, GuestsList.PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.hotel_card:
                 intent=new Intent(this, HotelsListView.class);
                startActivity(intent);
                break;
            case R.id.tasks_card:

                 intent=new Intent(this, ToDoLists.class);
                startActivity(intent);
                break;
            case R.id.guests_card:

                intent=new Intent(this, GuestsList.class);
                startActivity(intent);
                break;




        }
    }

    public void calculate(){
        Calendar calendar= Calendar.getInstance();
        String todays = new String(calendar.get(Calendar.MONTH )+ "/"+ calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR) +
                " "+ calendar.get(Calendar.HOUR_OF_DAY )+ ":"+ calendar.get(Calendar.MINUTE) + ":"+ calendar.get(Calendar.SECOND));


        //String dateStart = "04/20/2019 11:29:58";
        //String setDate = date+" "+time;

        String setDate =  DateAndTimeHolder.dateandtime;

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(todays);
            d2 = format.parse(setDate);
            if (check){
                originalDiff= d2.getTime() - d1.getTime();
                check=false;

            }



            //in milliseconds
            diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            Log.d("janna",Long.toString(diff));
            Log.d("janna",Long.toString(originalDiff));



            holdert.setText(getString(R.string.time_holder,diffHours,diffMinutes,diffSeconds));
            holderd.setText(String.valueOf(diffDays));







        } catch (Exception e) {
            e.printStackTrace();
        }


    }





}
