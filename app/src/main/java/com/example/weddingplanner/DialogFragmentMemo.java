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

public class DialogFragmentMemo extends AppCompatDialogFragment {
private EditText etMemo;
private MemoListener memoListener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.memo_dialog,null);

        builder.setView(view).setTitle("Add Memo").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String txt=etMemo.getText().toString();
                        memoListener.sendmemo(txt);


                    }
                });

        etMemo=view.findViewById(R.id.memo);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        memoListener=(MemoListener)context;
    }

    public interface MemoListener{
        void sendmemo(String memo);
    }
}
