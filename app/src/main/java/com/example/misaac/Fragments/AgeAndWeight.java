package com.example.misaac.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.misaac.Dialogues.DiseaseID2Result;
import com.example.misaac.Interfaces.SelfCreation;
import com.example.misaac.test1.R;

public class AgeAndWeight extends Fragment implements SelfCreation {

    Fragment _currentFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _currentFragment = this;

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Fill in");

        View v = inflater.inflate(R.layout.fragment_age_and_weight, container, false);

        final EditText input_age = (EditText)v.findViewById(R.id.input_age);
        final EditText input_weight = (EditText)v.findViewById(R.id.input_weight);
        final RadioGroup RG = (RadioGroup)v.findViewById(R.id.MonthsYearsOptions);


        AppCompatButton btn = (AppCompatButton)v.findViewById(R.id.btn_calculate);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioButtonID = RG.getCheckedRadioButtonId();
                View radioButton = RG.findViewById(radioButtonID);
                int idx = RG.indexOfChild(radioButton);

                String x = input_age.getText().toString();

                if(input_age.getText().toString().compareTo("")==0 || input_weight.getText().toString().compareTo("")==0 || idx == -1 ){
                    ((TextView)_currentFragment.getActivity().findViewById(R.id.AllFieldsErrorMsg)).setVisibility(View.VISIBLE);
                    return;
                }else {
                    ((TextView)_currentFragment.getActivity().findViewById(R.id.AllFieldsErrorMsg)).setVisibility(View.INVISIBLE);
                }

                SharedPreferences pref =_currentFragment.getActivity().getSharedPreferences("private",Context.MODE_PRIVATE);
                pref.edit().putString("age",input_age.getText().toString()).commit();
                pref.edit().putString("weight",input_weight.getText().toString()).commit();

                if(idx == 1){
                    pref.edit().putString("is_in_months","1").commit();
                }else {
                    pref.edit().putString("is_in_months","0").commit();
                }


                int DiseaseID = Integer.parseInt( pref.getString("DiseaseID","-1") );
                int DiseaseID2Options = Integer.parseInt( pref.getString("DiseaseID2Options","-1") );

                if (DiseaseID==2 && DiseaseID2Options==1){
                    new DiseaseID2Result(_currentFragment.getActivity()).show();
                }else if (DiseaseID==4){
                    Fragment fragment = new DiseaseID4Options();
                    FragmentManager fm = ((AppCompatActivity)_currentFragment.getContext()).getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction = transaction.add(R.id.DiseaseID2Content, fragment).addToBackStack("my_stack");
                    transaction.commit();
                }

            }
        });

        return v;
    }

    public AgeAndWeight CreateMeAgain(){
        return new AgeAndWeight();
    }
}
