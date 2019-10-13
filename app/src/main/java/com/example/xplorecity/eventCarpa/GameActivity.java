package com.example.xplorecity.eventCarpa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplorecity.R;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    Layout[] layouts = new Layout[15];

    private int currentLayout = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_carpa_template);

        layouts[0] = new Layout(R.drawable.img11,null, null,null);
        layouts[1] = new Layout(0, getResources().getString(R.string.msn1), null, null);
        layouts[2] = new Layout(0, getResources().getString(R.string.msn2), null, null);
        layouts[3] = new Layout(R.drawable.img21, null,  getResources().getString(R.string.hint1), "a");
        layouts[4] = new Layout(R.drawable.imgmap, null,  null, null);
        layouts[5] = new Layout(0, getResources().getString(R.string.msn3), null, null);
        layouts[6] = new Layout(R.drawable.img31, null,  getResources().getString(R.string.hint2), "a");
        layouts[7] = new Layout(R.drawable.img41, null,  getResources().getString(R.string.hint3), "a");
        layouts[8] = new Layout(0, getResources().getString(R.string.msn4), null, "a");
        layouts[9] = new Layout(R.drawable.img51, null,  getResources().getString(R.string.hint4), "a");
        layouts[10] = new Layout(R.drawable.img61, null,  getResources().getString(R.string.hint5), "a");
        layouts[11] = new Layout(R.drawable.imgmap, null,  null, "a");
        layouts[12] = new Layout(0, getResources().getString(R.string.msn5), null,"a");
        layouts[13] = new Layout(R.drawable.img81, null,  getResources().getString(R.string.hint6), "a");
        layouts[14] = new Layout(0, getResources().getString(R.string.msn6),null, "a");



        makeLayout(layouts[currentLayout]);

        Button b = findViewById(R.id.button);
        b.setOnClickListener(this);

    }

    public void makeLayout(Layout layout) {
        ImageView img = findViewById(R.id.image);
        TextView text = findViewById(R.id.text);
        TextView hint = findViewById(R.id.hint);
        EditText answer = findViewById(R.id.answer);


        if(layout.getImg() != 0) {
            img.setImageResource(layout.getImg());
            img.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    5.0f
            );
            img.setLayoutParams(param);
        }else {
            img.setVisibility(View.GONE);
        }
        if(layout.getTexto() != null) {
            text.setText(layout.getTexto());
            text.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    4.0f
            );
            text.setLayoutParams(param);
        } else {
            text.setVisibility(View.INVISIBLE);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    9.0f
            );
            text.setLayoutParams(param);
        }

        if(layout.getHint() != null) {
            hint.setText(layout.getHint());
            hint.setVisibility(View.VISIBLE);
        } else {
            hint.setVisibility(View.INVISIBLE);
        }

        if (layout.getRespuesta() != null) {
            answer.setVisibility(View.VISIBLE);
        } else {
            answer.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        if(currentLayout < layouts.length -1 ) {
            currentLayout++;
            makeLayout(layouts[currentLayout]);
        }
    }
}
