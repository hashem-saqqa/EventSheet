package com.example.eventsheet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Search_filter extends BottomSheetDialogFragment {
    final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendar2 = Calendar.getInstance();

    View view;
    List<String> eventSpecArray;

    EditText from_date;
    EditText to_date;
    ImageView closeDialog;

    DatabaseReference databaseReference;

    public Search_filter() {
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        view = View.inflate(getContext(), R.layout.activity_search_filter, null);
        dialog.setContentView(view);

        from_date = view.findViewById(R.id.from_date);
        to_date = view.findViewById(R.id.to_date);
        closeDialog = view.findViewById(R.id.cancel_icon);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelFromDate();
            }
        };
        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, month);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelToDate();
            }
        };
        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date2, myCalendar2.get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("events");
        fillTheSpinner();
    }

    private void fillTheSpinner() {
        List<String> countriesArray = new ArrayList<>();
        countriesArray.add(0, "الدولة");
        for (Locale locale : Locale.getAvailableLocales()) {
            if (!TextUtils.isEmpty(locale.getDisplayCountry()) & !countriesArray.contains(locale.getDisplayCountry())) {
                countriesArray.add(locale.getDisplayCountry());
            }
        }

        Spinner countrySpinner = view.findViewById(R.id.country_spinner);
        final ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, countriesArray) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                }
                return view;
            }
        };
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryAdapter);
        countrySpinner.setSelection(0);

//*******************************************************************************
        List<String> eventTypeArray = new ArrayList<>();
        eventTypeArray.add("ندوات");
        eventTypeArray.add("دورات");
        eventTypeArray.add("مؤتمرات");
        eventTypeArray.add("فعاليات");
        eventTypeArray.add("مبادرات");
        eventTypeArray.add(0, "نوع  الغعالية ");


        ArrayAdapter<String> eventTypeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, eventTypeArray) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                }
                return view;
            }
        };

        eventTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner eventType = (Spinner) view.findViewById(R.id.event_type_spinner);
        eventType.setAdapter(eventTypeAdapter);
        eventType.setSelection(0);
//*******************************************************************************
        List<String> eventSubTypeArray = new ArrayList<>();
        eventSubTypeArray.add("فعالية كبرى");
        eventSubTypeArray.add("فعالية صغرى");
        eventSubTypeArray.add("فعالية ترفيهية");
        eventSubTypeArray.add(0, "نوع الفعالية الفرعي");


        ArrayAdapter<String> eventSubTypeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, eventSubTypeArray) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                }
                return view;
            }
        };

        eventSubTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner eventSubType = view.findViewById(R.id.event_type_sub_spinner);
        eventSubType.setAdapter(eventSubTypeAdapter);
        eventSubType.setSelection(0);

        //*******************************************************************************
        List<String> eventRangeArray = new ArrayList<>();
        eventRangeArray.add("فعالية دولية");
        eventRangeArray.add("فعالية محلية");
        eventRangeArray.add(0, "نطاق الفعالية");


        ArrayAdapter<String> eventRangeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, eventRangeArray) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                }
                return view;
            }
        };

        eventRangeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner eventRange = view.findViewById(R.id.event_range_spinner);
        eventRange.setAdapter(eventRangeAdapter);
        eventRange.setSelection(0);
        //*******************************************************************************
        eventSpecArray = new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("the orderByKey", "onDataChange: " + snapshot);
                eventSpecArray.add(0, "تخصص الفعالية");

                for (DataSnapshot data : snapshot.getChildren()) {
                    if (!eventSpecArray.contains(data.child("eventSpec").getValue(String.class))) {
                        eventSpecArray.add(data.child("eventSpec").getValue(String.class));
                    }
                }

                ArrayAdapter<String> eventSpecAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, eventSpecArray) {
                    @Override
                    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        if (position == 0) {
                            tv.setTextColor(Color.GRAY);
                        }
                        return view;
                    }
                };

                eventSpecAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner eventSpec = view.findViewById(R.id.event_spec_spinner);
                eventSpec.setAdapter(eventSpecAdapter);
                eventSpec.setSelection(0);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //*******************************************************************************
        List<String> eventSubSpecArray = new ArrayList<>();
        eventSubSpecArray.add("فعالية عامة");
        eventSubSpecArray.add("فعالية خاصة");
        eventSubSpecArray.add(0, "تخصص الفعالية الفرعي");


        ArrayAdapter<String> eventSubSpecAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, eventSubSpecArray) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                }
                return view;
            }
        };

        eventSubSpecAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner eventSubSpec = view.findViewById(R.id.event_spec_sub_spinner);
        eventSubSpec.setAdapter(eventSubSpecAdapter);
        eventSubSpec.setSelection(0);


        //*******************************************************************************
        List<String> eventFeesArray = new ArrayList<>();
        eventFeesArray.add("مجانية");
        eventFeesArray.add("مدفوعة");
        eventFeesArray.add(0, "الرسوم");


        ArrayAdapter<String> eventFeesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, eventFeesArray) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                }
                return view;
            }
        };

        eventFeesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner eventFees = view.findViewById(R.id.fees_spinner);
        eventFees.setAdapter(eventFeesAdapter);
        eventFees.setSelection(0);


    }

    private void updateLabelFromDate() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        from_date.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabelToDate() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        to_date.setText(sdf.format(myCalendar2.getTime()));
    }


}