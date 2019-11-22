package com.example.xplorecity.activities.mainScreen;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xplorecity.R;
import com.example.xplorecity.eventManager.EventInfo;
import com.example.xplorecity.tools.FileIO;

public class ListAdapter extends BaseAdapter {

    private EventInfo eventList[];

    public ListAdapter(EventInfo eventList[]) {
        this.eventList = eventList;
    }


    @Override
    public int getCount() {
        return eventList.length;
    }

    @Override
    public Object getItem(int i) {
        return eventList[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View viewout = inflater.inflate(R.layout.activity_cell, null);

        ImageView img = viewout.findViewById(R.id.img);
        Bitmap b = FileIO.loadIMG(viewGroup.getContext(), "", eventList[i].getImg());
        img.setImageBitmap(b);

        TextView name = viewout.findViewById(R.id.text);
        name.setText(eventList[i].getTitle());

        TextView fecha = viewout.findViewById(R.id.textFecha);
        fecha.setText("Fecha: " + eventList[i].getFecha());

        TextView premio = viewout.findViewById(R.id.textPremio);
        premio.setText("Premio: " + eventList[i].getPremio());

        TextView personas = viewout.findViewById(R.id.textPersonas);
        personas.setText("Personas: " + eventList[i].getPersonas());

        return viewout;
    }
}
