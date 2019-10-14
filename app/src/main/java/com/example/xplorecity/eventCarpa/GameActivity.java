package com.example.xplorecity.eventCarpa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplorecity.R;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    Layout[] layouts = new Layout[15];

    private int currentLayout = 0;
    private ScrollView scrollView;
    private Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_carpa_template);

        layouts[0] = new Layout(R.drawable.img11,null, null,null,getResources().getString(R.string.botext1));
        layouts[1] = new Layout(0, getResources().getString(R.string.msn1), null, null, getResources().getString(R.string.botext2));
        layouts[2] = new Layout(0, getResources().getString(R.string.msn2), null, null, getResources().getString(R.string.botext3));
        layouts[3] = new Layout(R.drawable.img21, null,  getResources().getString(R.string.hint1), "a", getResources().getString(R.string.botext4));
        layouts[4] = new Layout(R.drawable.imgmap, null,  null, null, getResources().getString(R.string.botext5));
        layouts[5] = new Layout(0, getResources().getString(R.string.msn3), null, null, getResources().getString(R.string.botext6));
        layouts[6] = new Layout(R.drawable.img31, null,  getResources().getString(R.string.hint2), "a", getResources().getString(R.string.botext7));
        layouts[7] = new Layout(R.drawable.img41, null,  getResources().getString(R.string.hint3), "a", getResources().getString(R.string.botext8));
        layouts[8] = new Layout(0, getResources().getString(R.string.msn4), null, "a", getResources().getString(R.string.botext9));
        layouts[9] = new Layout(R.drawable.img51, null,  getResources().getString(R.string.hint4), "a", getResources().getString(R.string.botext10));
        layouts[10] = new Layout(R.drawable.img61, null,  getResources().getString(R.string.hint5), "a", getResources().getString(R.string.botext11));
        layouts[11] = new Layout(R.drawable.imgmap, null,  null, "a", getResources().getString(R.string.botext12));
        layouts[12] = new Layout(0, getResources().getString(R.string.msn5), null,"a", getResources().getString(R.string.botext13));
        layouts[13] = new Layout(R.drawable.img71, null,  getResources().getString(R.string.hint6), "a", getResources().getString(R.string.botext14));
        layouts[14] = new Layout(0, getResources().getString(R.string.msn6),null, "a", getResources().getString(R.string.botext15));



        makeLayout(layouts[currentLayout]);

        b = findViewById(R.id.button);
        b.setOnClickListener(this);

        scrollView = (ScrollView) findViewById(R.id.mar);

    }

    public void makeLayout(Layout layout) {
        ImageView img = findViewById(R.id.image);
        TextView text = findViewById(R.id.text);
        TextView hint = findViewById(R.id.hint);
        EditText answer = findViewById(R.id.answer);
        b =(Button)findViewById(R.id.button);

        b.setText(layout.getBotontext());

        if(layout.getImg() != 0) {
            img.setImageResource(layout.getImg());
            img.setVisibility(View.VISIBLE);
            img.requestFocus();

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

        scrollView.scrollTo(0,0);
        if(currentLayout < layouts.length -1 ) {
            currentLayout++;
            makeLayout(layouts[currentLayout]);

        }
    }
}
