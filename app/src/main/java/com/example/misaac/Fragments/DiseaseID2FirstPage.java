package com.example.misaac.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.provider.SelfDestructiveThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.misaac.Interfaces.SelfCreation;
import com.example.misaac.test1.R;

public class DiseaseID2FirstPage extends Fragment implements SelfCreation {

    Fragment _currentFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_disease_id_2_first_page, container, false);

        _currentFragment = this;

        RenameTitleBar();

        CardView CV1=(CardView)v.findViewById(R.id.Disease2FirstPageOption1);
        CV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "First card!", Toast.LENGTH_LONG).show();

                SharedPreferences pref = getActivity().getSharedPreferences("private",Context.MODE_PRIVATE);
                pref.edit().putString("DiseaseID2Options","1").commit();

                FrameLayout FL = (FrameLayout) ((AppCompatActivity)v.getContext()).findViewById(R.id.DiseaseID2Content);
                FL.removeAllViews();

                ////////////////////////////////////////////////
                Fragment fragment = new AgeAndWeight();
                FragmentManager fm = ((AppCompatActivity)v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction = transaction.add(R.id.DiseaseID2Content, fragment).addToBackStack("my_stack");
                transaction.commit();
                ///////////////////////////////////////////////
            }
        });

        CardView CV2=(CardView)v.findViewById(R.id.Disease2FirstPageOption2);
        CV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Second card!", Toast.LENGTH_LONG).show();
            }
        });

        CardView CV3=(CardView)v.findViewById(R.id.Disease2FirstPageOption3);
        CV3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Third card!", Toast.LENGTH_LONG).show();
            }
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Please select");
//        v.findViewById()

        return v;
    }

    public void RenameTitleBar(){

        SharedPreferences pref = _currentFragment.getActivity().getSharedPreferences("private",Context.MODE_PRIVATE);
        int DiseaseID = Integer.parseInt( pref.getString("DiseaseID","-1") );

        switch (DiseaseID){
            case 2:
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Encephalitis");
                break;
        }

    }

    public DiseaseID2FirstPage CreateNew(){
        return new DiseaseID2FirstPage();
    }

    @Override
    public DiseaseID2FirstPage CreateMeAgain() {
        return new DiseaseID2FirstPage();
    }
}
