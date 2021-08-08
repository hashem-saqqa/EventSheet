package com.example.eventsheet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    protected List<Event_model> mDataset_filtered;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    All_events_adapter all_event_adapter;


    View view;
//    List<String> eventSpecArray;

    EditText from_date;
    EditText to_date;
    ImageView closeDialog, applyFilter;

    String Country = "", eventType = "", eventSubType = "", eventRange = "", eventSpec = "", eventSubSpec = "", eventFees = "", startDate = "", endDate = "";

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
        applyFilter = view.findViewById(R.id.done_icon);

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

        applyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataset_filtered = new ArrayList<>();
                startDate = from_date.getText().toString();
                endDate = to_date.getText().toString();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data : snapshot.getChildren()) {

                            if (!Country.equals("") & !Country.equals("الدولة")) {
                                if (data.child("eventLocation").getValue(String.class).equals(Country)) {
                                } else {
                                    continue;
                                }
                            }
                            if (!eventType.equals("") & !eventType.equals("نوع الغعالية")) {
                                if (data.child("eventType").getValue(String.class).equals(eventType)) {
                                } else {
                                    continue;
                                }
                            }

                            if (!eventSubType.equals("") & !eventSubType.equals("نوع الفعالية الفرعي")) {
                                if (data.child("eventSubType").getValue(String.class).equals(eventSubType)) {
                                } else {
                                    continue;
                                }
                            }
                            if (!eventRange.equals("") & !eventRange.equals("نطاق الفعالية")) {
                                if (data.child("eventRange").getValue(String.class).equals(eventRange)) {
                                } else {
                                    continue;
                                }
                            }
                            if (!eventSpec.equals("") & !eventSpec.equals("تخصص الفعالية")) {
                                if (data.child("eventSpec").getValue(String.class).equals(eventSpec)) {
                                } else {
                                    continue;
                                }
                            }
                            if (!eventSubSpec.equals("") & !eventSubSpec.equals("تخصص الفعالية الفرعي")) {
                                if (data.child("eventSubSpec").getValue(String.class).equals(eventSubSpec)) {
                                } else {
                                    continue;
                                }
                            }
                            if (!eventFees.equals("") & !eventFees.equals("الرسوم")) {
                                if (data.child("eventFees").getValue(String.class).equals(eventFees)) {
                                    Log.d("searchFilterResult", "onDataChange: " + data);
                                } else {
                                    continue;
                                }
                            }

                            Log.d("searchFilterResult", "onDataChange: " + data);

                            mDataset_filtered.add(new Event_model(data.getKey(), R.drawable.nopath___copy__79_,
                                    data.child("eventTitle").getValue(String.class),
                                    data.child("eventLocation").getValue(String.class),
                                    data.child("eventStartDate").getValue(String.class),
                                    data.child("eventEndDate").getValue(String.class)));
//
                        }

                        mRecyclerView = getActivity().findViewById(R.id.recyclerView_all_event);
                        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        all_event_adapter = new All_events_adapter(mDataset_filtered);
                        mRecyclerView.setAdapter(all_event_adapter);

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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

        final Spinner countrySpinner = view.findViewById(R.id.country_spinner);
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
//        countrySpinner.setSelection(0);
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (!countrySpinner.getSelectedItem().toString().equals("الدولة")) {
                Country = countrySpinner.getSelectedItem().toString();
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//*******************************************************************************
        List<String> eventTypeArray = new ArrayList<>();
        eventTypeArray.add("ندوة");
        eventTypeArray.add("دورة");
        eventTypeArray.add("مؤتمر");
        eventTypeArray.add("فعالية");
        eventTypeArray.add("مبادرة");
        eventTypeArray.add(0, "نوع الغعالية");


        final ArrayAdapter<String> eventTypeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, eventTypeArray) {
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
        final Spinner eventTypeSpinner = (Spinner) view.findViewById(R.id.event_type_spinner);
        eventTypeSpinner.setAdapter(eventTypeAdapter);
        eventTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (!eventTypeSpinner.getSelectedItem().toString().equals("نوع الفعالية")) {
                eventType = eventTypeSpinner.getSelectedItem().toString();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        final Spinner eventSubTypeSpinner = view.findViewById(R.id.event_type_sub_spinner);
        eventSubTypeSpinner.setAdapter(eventSubTypeAdapter);
        eventSubTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (!eventSubTypeSpinner.getSelectedItem().toString().equals("نوع الفعالية الفرعي")) {
                eventSubType = eventSubTypeSpinner.getSelectedItem().toString();
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //*******************************************************************************
        List<String> eventRangeArray = new ArrayList<>();
        eventRangeArray.add("فعالية دولية");
        eventRangeArray.add("فعالية محلية");
        eventRangeArray.add(0, "نطاق الفعالية");


        final ArrayAdapter<String> eventRangeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, eventRangeArray) {
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
        final Spinner eventRangeSpinner = view.findViewById(R.id.event_range_spinner);
        eventRangeSpinner.setAdapter(eventRangeAdapter);
        eventRangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                if (!eventRangeSpinner.getSelectedItem().toString().equals("نطاق الفعالية")) {
                eventRange = eventRangeSpinner.getSelectedItem().toString();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //*******************************************************************************
        final List<String> eventSpecArray = new ArrayList<>();
        eventSpecArray.add(0, "تخصص الفعالية");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("the orderByKey", "onDataChange: " + snapshot);

                for (DataSnapshot data : snapshot.getChildren()) {
//                    if (!eventSpecArray.contains(data.child("eventSpec").getValue(String.class))) {
                    eventSpecArray.add(data.child("eventSpec").getValue(String.class));
//                    }
                }

                final ArrayAdapter<String> eventSpecAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, eventSpecArray) {
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
                final Spinner eventSpecSpinner = view.findViewById(R.id.event_spec_spinner);
                eventSpecSpinner.setAdapter(eventSpecAdapter);
                eventSpecSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                        if (!eventSpecSpinner.getSelectedItem().toString().equals("تخصص الفعالية")) {
                        eventSpec = eventSpecSpinner.getSelectedItem().toString();
//                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

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


        final ArrayAdapter<String> eventSubSpecAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, eventSubSpecArray) {
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
        final Spinner eventSubSpecSpinner = view.findViewById(R.id.event_spec_sub_spinner);
        eventSubSpecSpinner.setAdapter(eventSubSpecAdapter);
        eventSubSpecSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                if (!eventSubSpecSpinner.getSelectedItem().toString().equals("تخصص الفعالية الفرعي")) {
                eventSubSpec = eventSubSpecSpinner.getSelectedItem().toString();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
        final Spinner eventFeesSpinner = view.findViewById(R.id.fees_spinner);
        eventFeesSpinner.setAdapter(eventFeesAdapter);
        eventFeesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                if (!eventFeesSpinner.getSelectedItem().toString().equals("الرسوم")) {
                eventFees = eventFeesSpinner.getSelectedItem().toString();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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