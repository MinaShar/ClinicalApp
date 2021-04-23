package com.example.misaac.Models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.misaac.Dialogues.DiseaseID2Result;
import com.example.misaac.Fragments.DiseaseID2FirstPage;
import com.example.misaac.Fragments.DiseaseID4Options;
import com.example.misaac.test1.DiseaseID2;
import com.example.misaac.test1.R;

import java.util.ArrayList;
import java.util.List;

public class Disease {

    public int ID;
    public String Name;

    public Disease(int ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public static List<Disease> getTheRequiredDiseases(){

        List<Disease> list = new ArrayList<Disease>();

        list.add(new Disease(1,"Meningitis"));
        list.add(new Disease(2,"Encephalitis"));
        list.add(new Disease(3,"TB meningitis"));
        list.add(new Disease(4,"Miscellaneous"));
        list.add(new Disease(5,"Ascites"));
        list.add(new Disease(6,"Spontaneous Bactirial Pertonts"));
        list.add(new Disease(7,"Liver support"));
        list.add(new Disease(8,"Hepatic Encephalitis"));
//        list.add(new Disease(9,"TEST"));

        return list;
    }

    public static void DecideTheSelectedDisease(Activity main_activity , int selectedID){

        SharedPreferences pref;
        boolean go_further = false;
        switch (selectedID){
            case 2:
                go_further = true;
                pref = main_activity.getSharedPreferences("private",Context.MODE_PRIVATE);
                pref.edit().putString("DiseaseID","2").commit();
                break;
            case 1:
                go_further = true;
                pref = main_activity.getSharedPreferences("private",Context.MODE_PRIVATE);
                pref.edit().putString("DiseaseID","1").commit();
                break;
            case 3:
                go_further = true;
                pref = main_activity.getSharedPreferences("private",Context.MODE_PRIVATE);
                pref.edit().putString("DiseaseID","3").commit();
                break;
            case 4:
                go_further = true;
                pref = main_activity.getSharedPreferences("private",Context.MODE_PRIVATE);
                pref.edit().putString("DiseaseID","4").commit();
                break;
            case 7:
                go_further = false;
                pref = main_activity.getSharedPreferences("private",Context.MODE_PRIVATE);
                pref.edit().putString("DiseaseID","7").commit();
                new DiseaseID2Result(main_activity).show();
                break;
            case 5:
                go_further = false;
                pref = main_activity.getSharedPreferences("private",Context.MODE_PRIVATE);
                pref.edit().putString("DiseaseID","5").commit();
                new DiseaseID2Result(main_activity).show();
                break;

                ////////////////to be removed
            case 9:
                go_further = true;
                pref = main_activity.getSharedPreferences("private",Context.MODE_PRIVATE);
                pref.edit().putString("DiseaseID","9").commit();
                new DiseaseID2Result(main_activity).show();
                break;
                /////////////////////////////
        }
        if (go_further == true) {
            Intent I = new Intent(main_activity.getApplicationContext(), DiseaseID2.class);
            main_activity.startActivity(I);
        }
    }
}
