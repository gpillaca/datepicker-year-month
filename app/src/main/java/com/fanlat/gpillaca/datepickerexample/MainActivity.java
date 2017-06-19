package com.fanlat.gpillaca.datepickerexample;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.RelativeLayout.LayoutParams;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Button button_datepicker;
    private  TextView text_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button_datepicker = (Button) findViewById(R.id.main_button_datepicker);
        text_date = (TextView) findViewById(R.id.main_text_date);
        button_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new DatePickerFragment();
                fragment.show(getSupportFragmentManager(), "datepicker");

            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, dayOfMonth);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        String formattedDate = sdf.format(c.getTime());

        text_date.setText(formattedDate);
        Log.d("Main", formattedDate);

    }

    public  static  class DatePickerFragment extends DialogFragment {


        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.datePickerTheme, (DatePickerDialog.OnDateSetListener)getActivity(), year, month, day);

            TextView tv = new TextView(getActivity());
            LayoutParams lp = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, // Width of TextView
                    LayoutParams.WRAP_CONTENT); // Height of TextView
            tv.setLayoutParams(lp);
            tv.setPadding(10, 10, 10, 10);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
            tv.setText("Vencimiento");
            tv.setTextColor(Color.parseColor("#000000"));
            //v.setBackgroundColor(Color.parseColor("#FFD2DAA7"));

            datePickerDialog.setCustomTitle(tv);

            LinearLayout pickerParentLayout = (LinearLayout) datePickerDialog.getDatePicker().getChildAt(0);
            LinearLayout pickerSpinnersHolder = (LinearLayout) pickerParentLayout.getChildAt(0);
            pickerSpinnersHolder.getChildAt(0).setVisibility(View.GONE);

            return datePickerDialog;

        }


    }

}
