package com.example.eventsheet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
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

public class CreateEvent extends AppCompatActivity {

    String Country, eventType, eventSubType, eventRange, eventSpec, eventSubSpec, eventFees, startDate, endDate,
            eventTitle, eventAuthor, eventContent, eventTime;
    SwitchCompat switchCompat;
    EditText from_date, to_date, eventTitleText, eventAuthorText, eventContentText, eventTimeText;
    DatabaseReference databaseReference;
    int eventsCount = 0;

    final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendar2 = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        TextView appbar_title = findViewById(R.id.appbar_title);
        appbar_title.setText("إضافة فعالية");

        switchCompat = findViewById(R.id.fees_switch);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        from_date = findViewById(R.id.from_date);
        to_date = findViewById(R.id.to_date);
        eventTitleText = findViewById(R.id.eventTitle);
        eventAuthorText = findViewById(R.id.eventAuthor);
        eventContentText = findViewById(R.id.eventContent);
        eventTimeText = findViewById(R.id.eventTime);

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
                new DatePickerDialog(getApplicationContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getApplicationContext(), date2, myCalendar2.get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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

        final Spinner countrySpinner = findViewById(R.id.country_spinner);
        final ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countriesArray) {
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
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!countrySpinner.getSelectedItem().toString().equals("الدولة")) {
                    Country = countrySpinner.getSelectedItem().toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        ***********************************************************************************************
        List<String> eventTypeArray = new ArrayList<>();
        eventTypeArray.add("ندوات");
        eventTypeArray.add("دورات");
        eventTypeArray.add("مؤتمرات");
        eventTypeArray.add("فعاليات");
        eventTypeArray.add("مبادرات");
        eventTypeArray.add(0, "نوع  الغعالية ");


        final ArrayAdapter<String> eventTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, eventTypeArray) {
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
        final Spinner eventTypeSpinner = (Spinner) findViewById(R.id.event_type_spinner);
        eventTypeSpinner.setAdapter(eventTypeAdapter);
        eventTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!eventTypeSpinner.getSelectedItem().toString().equals("نوع الفعالية")) {
                    eventType = eventTypeSpinner.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        **************************************************************************************
        List<String> eventSubTypeArray = new ArrayList<>();
        eventSubTypeArray.add("فعالية كبرى");
        eventSubTypeArray.add("فعالية صغرى");
        eventSubTypeArray.add("فعالية ترفيهية");
        eventSubTypeArray.add(0, "نوع الفعالية الفرعي");


        ArrayAdapter<String> eventSubTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, eventSubTypeArray) {
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
        final Spinner eventSubTypeSpinner = findViewById(R.id.event_type_sub_spinner);
        eventSubTypeSpinner.setAdapter(eventSubTypeAdapter);
        eventSubTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!eventSubTypeSpinner.getSelectedItem().toString().equals("نوع الفعالية الفرعي")) {
                    eventSubType = eventSubTypeSpinner.getSelectedItem().toString();
                }

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


        final ArrayAdapter<String> eventRangeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, eventRangeArray) {
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
        final Spinner eventRangeSpinner = findViewById(R.id.event_range_spinner);
        eventRangeSpinner.setAdapter(eventRangeAdapter);
        eventRangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (!eventRangeSpinner.getSelectedItem().toString().equals("نطاق الفعالية")) {
                    eventRange = eventRangeSpinner.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //*******************************************************************************
        final List<String> eventSpecArray = new ArrayList<>();
        eventSpecArray.add(0, "تخصص الفعالية");
        eventSpecArray.add("الاعلام و التصوير");
        final ArrayAdapter<String> eventSpecAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, eventSpecArray) {
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
        Spinner eventSpecSpinner = findViewById(R.id.event_spec_spinner);
        eventSpecSpinner.setAdapter(eventSpecAdapter);
        eventSpecSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (!eventRangeSpinner.getSelectedItem().toString().equals("تخصص الفعالية")) {
                    eventSpec = eventRangeSpinner.getSelectedItem().toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //*******************************************************************************
        List<String> eventSubSpecArray = new ArrayList<>();
        eventSubSpecArray.add("فعالية عامة");
        eventSubSpecArray.add("فعالية خاصة");
        eventSubSpecArray.add(0, "تخصص الفعالية الفرعي");


        final ArrayAdapter<String> eventSubSpecAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, eventSubSpecArray) {
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
        final Spinner eventSubSpecSpinner = findViewById(R.id.event_spec_sub_spinner);
        eventSubSpecSpinner.setAdapter(eventSubSpecAdapter);
        eventSubSpecSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (!eventSubSpecSpinner.getSelectedItem().toString().equals("تخصص الفعالية الفرعي")) {
                    eventSubSpec = eventSubSpecSpinner.getSelectedItem().toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void CreateEvent(View view) {
        if (switchCompat.isChecked()) {
            eventFees = "مدفوعة";
        } else {
            eventFees = "مجانية";
        }
        startDate = from_date.getText().toString();
        endDate = to_date.getText().toString();
        eventTitle = eventTitleText.getText().toString();
        eventAuthor = eventAuthorText.getText().toString();
        eventContent = eventContentText.getText().toString();
        eventTime = eventTimeText.getText().toString();

        databaseReference.child("events").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    eventsCount++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.child("events").child("event"+(eventsCount+1)).child("eventLocation").setValue(Country);
        databaseReference.child("events").child("event"+(eventsCount+1)).child("eventType").setValue(eventType);
        databaseReference.child("events").child("event"+(eventsCount+1)).child("eventSubType").setValue(eventSubType);
        databaseReference.child("events").child("event"+(eventsCount+1)).child("eventSpec").setValue(eventSpec);
        databaseReference.child("events").child("event"+(eventsCount+1)).child("eventSubSpec").setValue(eventSubSpec);
        databaseReference.child("events").child("event"+(eventsCount+1)).child("eventRange").setValue(eventRange);
        databaseReference.child("events").child("event"+(eventsCount+1)).child("eventFees").setValue(eventFees);
        databaseReference.child("events").child("event"+(eventsCount+1)).child("eventStartDate").setValue(startDate);
        databaseReference.child("events").child("event"+(eventsCount+1)).child("eventEndDate").setValue(endDate);
        databaseReference.child("events").child("event"+(eventsCount+1)).child("eventTitle").setValue(eventTitle);
        databaseReference.child("events").child("event"+(eventsCount+1)).child("eventAuthor").setValue(eventAuthor);
        databaseReference.child("events").child("event"+(eventsCount+1)).child("eventContent").setValue(eventContent);
        databaseReference.child("events").child("event"+(eventsCount+1)).child("eventTime").setValue(eventTime);

        databaseReference.child("createdEvents").child(FirebaseAuth.getInstance().getCurrentUser()
                .getUid()).child("event"+(eventsCount+1)).setValue("1");
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