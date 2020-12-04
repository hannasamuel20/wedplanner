package com.example.weddingplanner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoLists extends AppCompatActivity implements DialogFragmentMemo.MemoListener {

    private RecyclerView mRecyclerView;
    private NotesAdapterClass mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static ArrayList<Note>   holderList=new ArrayList<>();



    public static int completedTasks(){
        int count=0;

        for (Note n:holderList) {

            if(n.isChecked()){
                count++;
            }

        }
        return count;


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_lists);



        mRecyclerView=findViewById(R.id.recyclerViewNotes);
       mRecyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        mAdapter=new NotesAdapterClass(holderList);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);



        mAdapter.setOnItemClickListener(new NotesAdapterClass.OnItemClickListener() {
            @Override
            public void onChecked(int position) {

                if(! holderList.get(position).isChecked()){
                holderList.get(position).setChecked(true);
                    Log.d("haaaa",String.valueOf(holderList.get(position).isChecked()));


                }
            else{
                    holderList.get(position).setChecked(false);
                    Log.d("haaaa",String.valueOf(holderList.get(position).isChecked()));

                }

            }
        });






        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openMemoDialog();


//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void openMemoDialog(){
        DialogFragmentMemo dfm=new DialogFragmentMemo();
        dfm.show(getSupportFragmentManager(),"example dialog");




    }

    @Override
    public void sendmemo(String memo) {

        holderList.add(0,new Note(memo, "Thursday,12/1920",false));
        mAdapter.notifyDataSetChanged();

    }
}
