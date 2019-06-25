package com.example.part2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import java.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class Lab5_2Activity extends AppCompatActivity implements View.OnClickListener {

    Button alertBtn;
    Button listBtn;
    Button progressBtn;
    Button dateBtn;
    Button timeBtn;
    Button customDialogBtn;

    AlertDialog customDialog;
    AlertDialog listDialog;
    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab5_2);
        alertBtn = (Button) findViewById(R.id.btn_alter);
        listBtn = (Button) findViewById(R.id.btn_list);
        progressBtn = (Button) findViewById(R.id.btn_progress);
        dateBtn = (Button) findViewById(R.id.btn_date);
        timeBtn = (Button) findViewById(R.id.btn_time);
        customDialogBtn = (Button) findViewById(R.id.btn_custom);

        alertBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);
        progressBtn.setOnClickListener(this);
        dateBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);
        customDialogBtn.setOnClickListener(this);


    }

    private void showToast(String message) {

        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();

    }

    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {

            if (dialog == customDialog && which == DialogInterface.BUTTON_POSITIVE) {

                showToast("Check Custom dialog click");
            } else if (dialog == listDialog) {

                String[] datas = getResources().getStringArray(R.array.dialog_array);
                showToast(datas[which] + "Selected");
            } else if (dialog == alertDialog && which == DialogInterface.BUTTON_POSITIVE) {
                showToast("Alert dialog ok Click");

            }

        }
    };

    @Override
    public void onClick(View v) {

        if (v == alertBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("Alarm");
            builder.setMessage("Are you done");
            builder.setPositiveButton("OK", dialogListener);
            builder.setPositiveButton("No", null);

            alertDialog = builder.create();
            alertDialog.show();

        } else if (v == listBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alarm Sound");
            builder.setSingleChoiceItems(R.array.dialog_array, 0, dialogListener);

            builder.setPositiveButton("Accept", null);
            builder.setNegativeButton("Cancel", null);
            listDialog = builder.create();
            listDialog.show();
        } else if (v == progressBtn) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setIcon(android.R.drawable.ic_dialog_alert);
            progressDialog.setTitle("Wait..");
            progressDialog.setMessage("Connecting Server. Waitting");

            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(true);

            progressDialog.show();

        } else if (v == dateBtn) {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dateDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            showToast(year + ":" + (monthOfYear+1) + ":" + dayOfMonth);
                        }
                    }, year, month, day);
            dateDialog.show();

        } else if (v == timeBtn) {

            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timeDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            showToast(hourOfDay + ":" + minute);
                        }
                    }, hour, minute, false);
            timeDialog.show();


        } else if (v == customDialogBtn) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.dialog_layout, null);

            customDialog = builder.create();
            customDialog.show();

        }


    }
}