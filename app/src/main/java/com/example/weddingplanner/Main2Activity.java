package com.example.weddingplanner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, DialogFragmentBudget.BudgetListener {
    public static int budgetVal;
    private String date;
    private String time="00:00:00";
    private static  long originalDiff;
    private long diff;
    boolean check=true;

    private CountDownTimer countDownTimer;
    private Button pickDate;

    private Button calculator;
    private Button chooseBuget;
    private TextView holder;
    private TextView budget;

    @Override
    public void sendmemo(int memo) {
        budgetVal=memo;

    }

    public void calculate(){
        Calendar calendar= Calendar.getInstance();
        String todays = new String(calendar.get(Calendar.MONTH )+ "/"+ calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR) +
                " "+ calendar.get(Calendar.HOUR_OF_DAY )+ ":"+ calendar.get(Calendar.MINUTE) + ":"+ calendar.get(Calendar.SECOND));


        //String dateStart = "04/20/2019 11:29:58";
        String setDate = date+" "+time;
        DateAndTimeHolder.dateandtime=setDate;

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

            budget.setText(diffDays+"  "+diffHours+"  "+diffMinutes+"  "+diffSeconds+"  ");





        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ConstraintLayout l=findViewById(R.id.linear);
        AnimationDrawable animationDrawable=(AnimationDrawable)l.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        pickDate=findViewById(R.id.pick);

        calculator=findViewById(R.id.calculate);
        holder=findViewById(R.id.display);
        chooseBuget =findViewById(R.id.choose_budget);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        chooseBuget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragmentBudget dfg=new DialogFragmentBudget();
                dfg.show(getSupportFragmentManager()," dialog");
            }
        });



        calculator.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                startIntent();
                calculate();
                countDownTimer=new CountDownTimer(diff,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        diff=millisUntilFinished;
                       int res= 100-(int) (100 * diff /originalDiff);



//                        waveLoadingView.setProgressValue(res);
                        calculate();

                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }
        });
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datep=new DatePickerFrag();
                datep.show(getSupportFragmentManager(),"date picker");


            }
        });



    }


    public void startIntent(){

        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);


    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c= Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        date= new String(month+ "/"+dayOfMonth+ "/" + year);
        String currDate= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
       // holder.setText(currDate);

    }


}
