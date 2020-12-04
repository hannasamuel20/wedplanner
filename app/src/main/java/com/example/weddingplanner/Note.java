package com.example.weddingplanner;

public class Note {
    private String memo;
    private String date;
    private boolean checked=false;




    public Note(String memo, String date, boolean checked){
        this.memo=memo;
        this.date=date;
        this.checked=checked;



    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getMemo() {
        return memo;
    }

    public String getDate() {
        return date;
    }

    public boolean isChecked() {
        return checked;
    }
}
