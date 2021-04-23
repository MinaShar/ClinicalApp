package com.example.misaac.test1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.misaac.Fragments.AdultsOrPediatrics;
import com.example.misaac.Fragments.AgeAndWeight;
import com.example.misaac.Fragments.DiseaseID2FirstPage;
import com.example.misaac.Fragments.DiseaseID2SecondPage;
import com.example.misaac.Fragments.DiseaseID4Options;
import com.example.misaac.Interfaces.SelfCreation;

import java.util.List;

public class DiseaseID2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_id2);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        SharedPreferences pref = getSharedPreferences("private",Context.MODE_PRIVATE);
        int DiseaseID = Integer.parseInt(pref.getString("DiseaseID" , "-1"));

        Fragment fragment;
        FragmentManager fm;
        FragmentTransaction transaction;

        switch (DiseaseID){
            case 2:
                ///////////////////////////////////////////////////////////////////////////////
                fragment = new DiseaseID2FirstPage();
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction = transaction.add(R.id.DiseaseID2Content, fragment).addToBackStack("my_stack");
                transaction.commit();
                break;
                ///////////////////////////////////////////////////////////////////////////////
            case 1:
                fragment = new AdultsOrPediatrics();
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction = transaction.add(R.id.DiseaseID2Content, fragment).addToBackStack("my_stack");
                transaction.commit();
                break;
            case 3:
                fragment = new AdultsOrPediatrics();
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction = transaction.add(R.id.DiseaseID2Content, fragment).addToBackStack("my_stack");
                transaction.commit();
                break;
            case 4:
                fragment = new AgeAndWeight();
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction = transaction.add(R.id.DiseaseID2Content, fragment).addToBackStack("my_stack");
                transaction.commit();
                break;
            case 9:
                fragment = new DiseaseID4Options();
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction = transaction.add(R.id.DiseaseID2Content, fragment).addToBackStack("my_stack");
                transaction.commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            int index = getSupportFragmentManager().getBackStackEntryCount() - 1;

            if(index >= 0){
                FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(index);
                String tag = backEntry.getName();
                List<Fragment> all_fragment = getSupportFragmentManager().getFragments();

                all_fragment.remove(all_fragment.size()-1);
                getSupportFragmentManager().popBackStack();

                if(all_fragment.size() > 0){
                    Fragment fragment = all_fragment.get(all_fragment.size()-1);

                    fragment = ((SelfCreation)fragment).CreateMeAgain();

                    FrameLayout FL = (FrameLayout) findViewById(R.id.DiseaseID2Content);
                    FL.removeAllViews();

//                    FrameLayout Fl = (FrameLayout)findViewById(R.id.DiseaseID2Content)
                    getSupportFragmentManager().popBackStack();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction = transaction.add(R.id.DiseaseID2Content , fragment).addToBackStack(tag);
                    transaction.commit();
                }else {
                    super.onBackPressed();
                }
            }else{
                super.onBackPressed();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.disease_id2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
