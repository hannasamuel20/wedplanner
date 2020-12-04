package com.example.weddingplanner;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class GuestsList extends AppCompatActivity implements DialogFragmentGuests.GuestsListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    //private ArrayList<String> holderList;

    public static ArrayList<MyContact>    contactArrayList=new ArrayList<MyContact>();
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    public static int totalGuests=contactArrayList.size();

    private static boolean done=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guests_list);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        if(done){
            showContacts();
            getContacts();
            done=false;


        }

        mRecyclerView=findViewById(R.id.recyclerViewGuests);
        mRecyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        mAdapter=new GuestsAdapterClass(contactArrayList,this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);












        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGuestDialog();


//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void openGuestDialog(){
        DialogFragmentGuests dfg=new DialogFragmentGuests();
        dfg.show(getSupportFragmentManager()," dialog");

    }

    @Override
    public void sendmemo(String memo) {
        MyContact myContact=new MyContact();
        myContact.contactname=memo;

        contactArrayList.add(0,myContact);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 121:
                contactArrayList.remove(item.getGroupId());
                mAdapter.notifyDataSetChanged();
                break;

            case 122:
                break;

        }
        return super.onContextItemSelected(item);
    }

    public class MyContact{

        public String contactname="";




    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
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
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        }
    }




    public void getContacts(){


        Cursor cursor=null;
        ContentResolver contentResolver=getContentResolver();

        try {
            cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            Log.d("heereeee","not null");
        } catch (Exception ex) {
            Log.e("Error on contact", ex.getMessage());
        }

        if(cursor.getCount()>0){

            while (cursor.moveToNext()){
                MyContact mycontact=new MyContact();
                String id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String contactName=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                mycontact.contactname=contactName;
                Log.d("hannaaaaa",contactName);

                contactArrayList.add(mycontact);


            }







        }



    }

}
