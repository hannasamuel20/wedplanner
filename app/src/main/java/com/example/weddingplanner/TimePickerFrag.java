package com.example.weddingplanner;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import java.util.Calendar;

public class TimePickerFrag extends DialogFragment {
    Calendar calendar=Calendar.getInstance();
    int hour=calendar.get(Calendar.HOUR_OF_DAY);
    int minute=calendar.get(Calendar.MINUTE);
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new TimePickerDialog(getContext(),(TimePickerDialog.OnTimeSetListener) getContext(),hour,minute, DateFormat.is24HourFormat(getActivity()));
    }
}
