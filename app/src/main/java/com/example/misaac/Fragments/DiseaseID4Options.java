package com.example.misaac.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misaac.Dialogues.DiseaseID2Result;
import com.example.misaac.Interfaces.SelfCreation;
import com.example.misaac.test1.R;

public class DiseaseID4Options extends Fragment implements SelfCreation {

    Fragment _currentFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ChangeTilte();
        _currentFragment = this;
        View v = inflater.inflate(R.layout.fragment_disease_id4_options, container, false);

        CardView Card1 = (CardView) v.findViewById(R.id.Disease4Option1);
        CardView Card2 = (CardView) v.findViewById(R.id.Disease4Option2);
        CardView Card3 = (CardView) v.findViewById(R.id.Disease4Option3);

        Card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardsEventHandler(1);
            }
        });

        Card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardsEventHandler(2);
            }
        });

        Card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardsEventHandler(3);
            }
        });

        return v;
    }


    public void CardsEventHandler(int DiseaseID4Option){
        SharedPreferences pref =_currentFragment.getActivity().getSharedPreferences("private",Context.MODE_PRIVATE);
        pref.edit().putString("DiseaseID4Option",Integer.toString( DiseaseID4Option ) ).commit();

        new DiseaseID2Result(_currentFragment.getActivity()).show();
    }




    public void ChangeTilte(){

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Select Option");

    }

    @Override
    public Fragment CreateMeAgain() {
        return new DiseaseID4Options();
    }
}
