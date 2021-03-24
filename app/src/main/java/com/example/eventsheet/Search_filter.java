package com.example.eventsheet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class Search_filter extends BottomSheetDialogFragment {


    public Search_filter() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view = View.inflate(getContext(),R.layout.activity_search_filter,null);
        dialog.setContentView(view);

//        String[] years = {"1996","1997","1998","1998"};

//        Spinner country_spinner = (Spinner) view.findViewById(R.id.country_spinner);
//        ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(getActivity(),
//                R.layout.spinner_item, years );
//        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        country_spinner.setAdapter(langAdapter);


    }
}