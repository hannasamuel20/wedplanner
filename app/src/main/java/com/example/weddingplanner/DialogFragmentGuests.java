package com.example.weddingplanner;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class DialogFragmentGuests extends AppCompatDialogFragment {

    private EditText etName;
    private GuestsListener memoListener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.guests_dialog,null);

        builder.setView(view).setTitle("Add Guest").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String txt=etName.getText().toString();
                        memoListener.sendmemo(txt);


                    }
                });

        etName=view.findViewById(R.id.guest_name);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        memoListener=(GuestsListener) context;
    }

    public interface GuestsListener{
        void sendmemo(String memo);
    }
}
