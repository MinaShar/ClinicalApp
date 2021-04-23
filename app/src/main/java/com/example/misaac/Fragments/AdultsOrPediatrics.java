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
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.misaac.Interfaces.SelfCreation;
import com.example.misaac.test1.R;

public class AdultsOrPediatrics extends Fragment implements SelfCreation {

    Fragment _currentFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _currentFragment = this;
        View v = inflater.inflate(R.layout.fragment_adults_pediatrics, container, false);

        RenameTitleBar();

        CardView AdultsCard = (CardView)v.findViewById(R.id.adults);
        AdultsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// 1 => adults
                SharedPreferences pref = _currentFragment.getActivity().getSharedPreferences("private",Context.MODE_PRIVATE);
                pref.edit().putString("DiseaseOptions","1").commit();/// Adults => 1

                FrameLayout FL = (FrameLayout) ((AppCompatActivity)v.getContext()).findViewById(R.id.DiseaseID2Content);
                FL.removeAllViews();

                Fragment fragment = new EnterWeightOnly();
                FragmentManager fm = ((AppCompatActivity)v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction = transaction.add(R.id.DiseaseID2Content, fragment).addToBackStack("my_stack");
                transaction.commit();
            }
        });

        CardView PediatricsCard = (CardView)v.findViewById(R.id.pediatrics);
        PediatricsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// 2 => pediatrics
                SharedPreferences pref = _currentFragment.getActivity().getSharedPreferences("private",Context.MODE_PRIVATE);
                pref.edit().putString("DiseaseOptions","2").commit();/// Pediatrics => 2

                FrameLayout FL = (FrameLayout) ((AppCompatActivity)v.getContext()).findViewById(R.id.DiseaseID2Content);
                FL.removeAllViews();

                Fragment fragment = new EnterWeightOnly();
                FragmentManager fm = ((AppCompatActivity)v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction = transaction.add(R.id.DiseaseID2Content, fragment).addToBackStack("my_stack");
                transaction.commit();
            }
        });

        return v;
    }

    public void RenameTitleBar(){

        SharedPreferences pref = _currentFragment.getActivity().getSharedPreferences("private",Context.MODE_PRIVATE);
        int DiseaseID = Integer.parseInt( pref.getString("DiseaseID","-1") );

        switch (DiseaseID){
            case 1:
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Meningitis");
                break;
            default:
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Select type");
                break;
        }

    }

    @Override
    public AdultsOrPediatrics CreateMeAgain() {
        return new AdultsOrPediatrics();
    }
}
