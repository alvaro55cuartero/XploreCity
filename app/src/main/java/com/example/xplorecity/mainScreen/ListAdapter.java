package com.example.xplorecity.mainScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xplorecity.R;
import com.example.xplorecity.eventManager.Event;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private List<Event> list;

    public ListAdapter(List<Event> list) {
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
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
        img.setImageResource(list.get(i).getIconImg());

        TextView name = viewout.findViewById(R.id.text);
        name.setText(list.get(i).getName());

        return viewout;
    }
}
