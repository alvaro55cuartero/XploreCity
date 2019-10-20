package com.example.xplorecity.eventCarpa;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplorecity.R;
import com.example.xplorecity.tools.Event;

import java.util.Calendar;
import java.util.Date;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    Event event;

    private int currentLayout = 0;
    private ScrollView scrollView;
    private Button b;
    private String seleccion;
    private Spinner spinner;
    private ImageView img;
    private TextView text;
    private TextView hint;
    private EditText answer;
    ArrayAdapter<String> adapter;
    private int [] imagenes;
    private String [] texOption;
    private String [] texResp;
    private int [] texOption2;
    private String [] texResp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_carpa_template);



        texOption = new String[]{getResources().getString(R.string.Esquina),getResources().getString(R.string.Silla),getResources().getString(R.string.Impresora),
                getResources().getString(R.string.Mesa),getResources().getString(R.string.Mesilla),getResources().getString(R.string.Estanteria)};
        texResp= new String[]{"Esquina","Silla","Impresora",
                "Mesa","Mesilla","Estanteria"};
        int tet = (int)Math.floor(Math.random()*texResp.length);

        texOption2 = new int[]{R.drawable.img41,R.drawable.img42,R.drawable.img43,R.drawable.img44,R.drawable.img45,R.drawable.img46,R.drawable.img47,
                R.drawable.img48};
        texResp2= new String[]{"Gafas", "Papelera", "Puerta", "Libro", "Maletín" ,"Calendario" ,"Calculadora" ,"Carta"};
        int tet2 = (int)Math.floor(Math.random()*texResp2.length);


        event = new Event(new Layout[]{
                new Layout(new int[]{R.drawable.img11},null, null, null, getResources().getString(R.string.botext1), null),
                new Layout(new int[]{0}, getResources().getString(R.string.msn1), null, null, getResources().getString(R.string.botext2), null),
                new Layout(new int[]{0}, getResources().getString(R.string.msn2), null, null, getResources().getString(R.string.botext3), null),
                new Layout(new int[]{R.drawable.img21}, null, getResources().getString(R.string.hint1), new String[]{"Los Madroños"}, getResources().getString(R.string.botext4), new String[]{"Los Tejos", "Los Acebos", "Los Magnolios", "Las Encinas", "Los Olivos", "Los Madroños", "Los pinos", "Aulario", "El Sario", "Administración", "Rectorado"}),
                new Layout(new int[]{R.drawable.imgma1}, null, null, null, getResources().getString(R.string.botext5), null),
                new Layout(new int[]{0}, getResources().getString(R.string.msn3)+texOption[tet], null, null, getResources().getString(R.string.botext6), null),
                new Layout(new int[]{R.drawable.img3}, null, getResources().getString(R.string.hint2), new String[] {texResp[tet]}, getResources().getString(R.string.botext7), new String[]{"Silla","Libro","Folios","Cuaderno","Estanteria","Mesa","Portatil","Armario","Proyector","Esquina","Estanteria Pared","Mesilla","Techo","Impresora","Gafas","Agenda","Reloj","Archivador"}),
                new Layout(new int[]{R.drawable.img31,R.drawable.img32,R.drawable.img33,R.drawable.img34,R.drawable.img35,
                        R.drawable.img36,}, null, getResources().getString(R.string.hint3), new String[] {"683", "527","423","363","345","868"}, getResources().getString(R.string.botext8), new String[]{"Selecciona una respuesta...","868","232","363","210","527","361","683","567","423","047","321","393","345"}),
                new Layout(new int[]{texOption2[tet2]}, null, getResources().getString(R.string.hint4), null, getResources().getString(R.string.botext9), null),
                new Layout(new int[]{R.drawable.img51}, null, getResources().getString(R.string.hint5), new String[]{texResp2[tet2]}, getResources().getString(R.string.botext10), new String[]{"Selecciona una respuesta...","Gafas", "Papelera", "Puerta", "Libro", "Maletín" ,"Calendario" ,"Calculadora" ,"Carta"}),
                new Layout(new int[]{R.drawable.img61,R.drawable.img62,R.drawable.img63,R.drawable.img64,
                        R.drawable.img65,R.drawable.img66}, null, getResources().getString(R.string.hint6), new String[]{"201", "203", "204", "205", "207", "217"}, getResources().getString(R.string.botext11), new String[]{"Selecciona una respuesta...","203","214","210","204","206","809","211","201","212","205","808","110","103","217"}),
                new Layout(new int[]{R.drawable.imgma1}, null, null, null, getResources().getString(R.string.botext12), null),
                new Layout(new int[]{0}, getResources().getString(R.string.msn5), null, null, getResources().getString(R.string.botext13), null),
                new Layout(new int[]{R.drawable.img71,R.drawable.img72,R.drawable.img73}, null, getResources().getString(R.string.hint7), new String[] {"25/10/19"}, getResources().getString(R.string.botext14), new String[]{"Selecciona una respuesta...","24/10/19","26/10/19","28/10/19","27/10/19","25/10/19","29/10/19","21/10/19","25/10/19","23/10/19","22/10/19","30/10/19"}),
                new Layout(new int[]{0},getResources().getString(R.string.msn6),null,null,getResources().getString(R.string.botext15),null)
        });

        checkDate("25/10/2019");


        b = findViewById(R.id.button);
        b.setOnClickListener(this);

        scrollView = (ScrollView) findViewById(R.id.mar);

        makeLayout(event.layouts[currentLayout]);

    };

    public boolean checkDate(String date) {
        String[] datevalue = date.split("/");
        Date currentTime = Calendar.getInstance().getTime();
        return currentTime.after(new Date(Integer.parseInt(datevalue[2]),Integer.parseInt(datevalue[1]), Integer.parseInt(datevalue[0])));
    }



    public void makeLayout(Layout layout) {
        img = findViewById(R.id.image);
        text = findViewById(R.id.text);
        hint = findViewById(R.id.hint);
        spinner = (Spinner)findViewById(R.id.spinner);

        b =(Button)findViewById(R.id.button);

        b.setText(layout.getBotontext());

        if(layout.getImg() != 0) {
            img.setImageResource(layout.getImg());
            img.setVisibility(View.VISIBLE);

        }else {
            img.setVisibility(View.GONE);
        }
        if(layout.getTexto() != null) {
            text.setText(layout.getTexto());
            text.setVisibility(View.VISIBLE);


        } else {
            text.setVisibility(View.GONE);
        }

        if(layout.getHint() != null) {
            hint.setText(layout.getHint());
            hint.setVisibility(View.VISIBLE);
        } else {
            hint.setVisibility(View.GONE);
        }


        if (layout.getOpciones()!=null) {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,layout.getOpciones());
            spinner.setAdapter(adapter);
            spinner.setVisibility(View.VISIBLE);

        }else {
            spinner.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        comprobaciones();
    }

    public void comprobaciones(){

        if(currentLayout==3 || currentLayout==6 ||currentLayout==7 ||currentLayout==9 ||currentLayout==10 ||currentLayout==13){
            seleccion = spinner.getSelectedItem().toString();
            //Toast.makeText(this,seleccion, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this,layouts[currentLayout].getRespuesta(), Toast.LENGTH_SHORT).show();
            if(seleccion.equals(event.layouts[currentLayout].getRespuesta())){
                Toast.makeText(this,R.string.correcto, Toast.LENGTH_SHORT).show();
                todoBien();

            }else{
                Toast.makeText(this,R.string.fallo, Toast.LENGTH_LONG).show();
            }
        }else {
            todoBien();
        }


    }

    public void todoBien(){
        scrollView.scrollTo(0,0);
        if(currentLayout < event.layouts.length ) {
            if(true) {
                currentLayout++;
                makeLayout(event.layouts[currentLayout]);
            }
        }
    }
}
