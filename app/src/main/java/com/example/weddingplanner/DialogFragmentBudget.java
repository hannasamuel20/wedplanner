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

public class DialogFragmentBudget extends AppCompatDialogFragment {


    private EditText etBudget;
    private BudgetListener budgetListener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.budget_dialog,null);

        builder.setView(view).setTitle("Pick Budget").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int txt= Integer.parseInt(etBudget.getText().toString());
                        budgetListener.sendmemo(txt);


                    }
                });

        etBudget =view.findViewById(R.id.et_budget);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        budgetListener=(BudgetListener) context;
    }

    public interface BudgetListener{
        void sendmemo(int memo);
    }
}
