package com.example.misaac.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.misaac.Dialogues.DiseaseID2Result;
import com.example.misaac.Interfaces.SelfCreation;
import com.example.misaac.test1.R;

public class EnterWeightOnly extends Fragment implements SelfCreation {

    Fragment _currentFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _currentFragment = this;
        View v = inflater.inflate(R.layout.fragment_enter_weight, container, false);
        final EditText input_weight = (EditText)v.findViewById(R.id.input_weight);

        ChangeTilte();

        AppCompatButton Btn = (AppCompatButton)v.findViewById(R.id.btn_calculate);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( input_weight.getText().toString().compareTo("")== 0  ){
                    ((TextView)_currentFragment.getActivity().findViewById(R.id.AllFieldsErrorMsg)).setVisibility(View.VISIBLE);
                    return;
                }else {

                    SharedPreferences pref = getContext().getSharedPreferences("private",Context.MODE_PRIVATE);
                    pref.edit().putString("weight", input_weight.getText().toString() ).commit();

                    ((TextView)_currentFragment.getActivity().findViewById(R.id.AllFieldsErrorMsg)).setVisibility(View.INVISIBLE);
                }

                new DiseaseID2Result(_currentFragment.getActivity()).show();

            }
        });

        return v;
    }

    public void ChangeTilte(){

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Enter weight");

    }

    @Override
    public Fragment CreateMeAgain() {
        return new EnterWeightOnly();
    }
}
