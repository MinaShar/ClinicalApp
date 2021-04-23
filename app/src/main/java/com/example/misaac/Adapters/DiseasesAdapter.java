package com.example.misaac.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misaac.Dialogues.DiseaseID2Result;
import com.example.misaac.test1.R;

import java.util.List;

import com.example.misaac.Models.Disease;

public class DiseasesAdapter extends BaseAdapter {

    Context _context;
    List<Disease> _diseases;
    Activity _currentActivity;

    public DiseasesAdapter(Activity _currentActivity, Context _context, List<Disease> _diseases) {
        this._context = _context;
        this._diseases = _diseases;
        this._currentActivity = _currentActivity;
    }

    @Override
    public int getCount() {
        return _diseases.size();
    }

    @Override
    public Object getItem(int position) {
        return _diseases.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = View.inflate(_context, R.layout.item_disease,null);
        TextView _diseaseNameTXT = (TextView)v.findViewById(R.id.disease_name);
        _diseaseNameTXT.setText(_diseases.get(position).Name);

        v.setTag(_diseases.get(position).ID);

        if(_diseases.get(position).ID == 1){
            int _width = parent.getWidth();
            final Activity _c = (Activity)v.getContext();
            Button b = new Button(v.getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,3);
            params.setMargins(5,5,5,5);
            b.setLayoutParams(params);
            b.setWidth(200);
            b.setText("CrCl");
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences pref = _c.getSharedPreferences("private",Context.MODE_PRIVATE);
                    pref.edit().putString("DiseaseID1Info","1").commit();
                    new DiseaseID2Result(_c).show();
                }
            });
            b.setBackgroundColor(_c.getResources().getColor( R.color.colorPrimary ) );
            ((LinearLayout)((CardView)v).findViewById(R.id.MainContrinerBtn)).addView(b);
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Fire click","ID : "+_diseases.get(position).ID);
                Toast.makeText(_context, "ID : "+v.getTag(), Toast.LENGTH_LONG).show();

                Disease.DecideTheSelectedDisease(_currentActivity, (Integer) v.getTag() );
            }
        });

        return v;
    }
}
