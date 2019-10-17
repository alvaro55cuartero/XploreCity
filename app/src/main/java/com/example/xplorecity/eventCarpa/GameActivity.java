package com.example.xplorecity.eventCarpa;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplorecity.R;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    Layout[] layouts = new Layout[15];

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_carpa_template);

        imagenes = new int[]{R.drawable.img11,R.drawable.img21,R.drawable.img3,R.drawable.img31,R.drawable.img32,R.drawable.img33,R.drawable.img34,R.drawable.img35,
                R.drawable.img36,R.drawable.img41,R.drawable.img42,R.drawable.img43,R.drawable.img44,R.drawable.img45,R.drawable.img46,R.drawable.img47,
                R.drawable.img48,R.drawable.img51,R.drawable.img61,R.drawable.img62,R.drawable.img63,R.drawable.img64,
                R.drawable.img65,R.drawable.img66,R.drawable.img71,R.drawable.img72,R.drawable.img73};

        texOption = new String[]{getResources().getString(R.string.Esquina),getResources().getString(R.string.Silla),getResources().getString(R.string.Impresora),
                getResources().getString(R.string.Mesa),getResources().getString(R.string.Mesilla),getResources().getString(R.string.Estanteria)};

        texResp= new String[]{"Esquina","Silla","Impresora",
                "Mesa","Mesilla","Estantería"};

        layouts[0] = new Layout(
                imagenes[0],
                null,
                null,
                null,
                getResources().getString(R.string.botext1),
                null);
        layouts[1] = new Layout(
                0,
                getResources().getString(R.string.msn1),
                null,
                null,
                getResources().getString(R.string.botext2),
                null);
        layouts[2] = new Layout(
                0,
                getResources().getString(R.string.msn2),
                null,
                null,
                getResources().getString(R.string.botext3),
                null);
        layouts[3] = new Layout(
                imagenes[1],
                null,
                getResources().getString(R.string.hint1),
                "Los Madroños",
                getResources().getString(R.string.botext4),
                new String[]{"Selecciona una respuesta...", "Los Tejos", "Los Acebos", "Los Magnolios", "Las Encinas", "Los Olivos", "Los Madroños", "Los pinos", "Aulario", "El Sario", "Administración", "Rectorado"});
        layouts[4] = new Layout(
                R.drawable.imgma1,
                null,
                null,
                null,
                getResources().getString(R.string.botext5),
                null);

        int tet = (int)Math.floor(Math.random()*(5-0+1)+0);
        String tot=texOption[tet];
        layouts[5] = new Layout(
                0,
                getResources().getString(R.string.msn3)+tot,
                null,
                null,
                getResources().getString(R.string.botext6),
                null);
        layouts[6] = new Layout(
                imagenes[2],
                null,
                getResources().getString(R.string.hint2),
                texResp[tet],
                getResources().getString(R.string.botext7),
                new String[]{"Selecciona una respuesta...","Silla","Folios","Cuaderno","Estanteria","Mesa","Portatil","Armario","Proyector","Esquina","Estanteria Pared","Mesilla","Techo","Impresora","Gafas","Agenda","Reloj","Archivador"});


        int imag = imagenes[(int)Math.floor(Math.random()*(8-3+1)+3)];
        layouts[7] = new Layout(
                imag,
                null,
                getResources().getString(R.string.hint3),
                Integer.toString(respNum(imag)),
                getResources().getString(R.string.botext8),
                new String[]{"Selecciona una respuesta...","868","232","363","210","527","361","683","567","423","047","321","393","345"});

        imag = imagenes[(int)Math.floor(Math.random()*(16-9+1)+9)];
        layouts[8] = new Layout(
                imag,
                null,
                getResources().getString(R.string.hint4),
                null,
                getResources().getString(R.string.botext9),
                null);

        layouts[9] = new Layout(
                imagenes[17],
                null,
                getResources().getString(R.string.hint5),
                respText(imag),
                getResources().getString(R.string.botext10),
                new String[]{"Selecciona una respuesta...","Lampara","Tijeras","Impresora","Gafas","Calculadora","Carta","Folios","Mesilla","Pizarra","Puerta","Bodega","Archivador","Papelera","Mesa","Silla","Reloj","Ordenador"});

        imag = imagenes[(int)Math.floor(Math.random()*(23-18+1)+18)];
        layouts[10] = new Layout(
                imag,
                null,
                getResources().getString(R.string.hint6),
                Integer.toString(respNum(imag)),
                getResources().getString(R.string.botext11),
                new String[]{"Selecciona una respuesta...","203","214","210","204","206","809","211","201","212","205","808","110","103","217"});
        layouts[11] = new Layout(
                R.drawable.imgma1,
                null,
                null,
                null,
                getResources().getString(R.string.botext12),
                null);
        layouts[12] = new Layout(
                0,
                getResources().getString(R.string.msn5),
                null,
                null,
                getResources().getString(R.string.botext13),
                null);

        imag = imagenes[(int)Math.floor(Math.random()*(26-24+1)+24)];
        layouts[13] = new Layout(
                imag,
                null,
                getResources().getString(R.string.hint7),
                respText(imag),
                getResources().getString(R.string.botext14),
                new String[]{"Selecciona una respuesta...","24/10/19","26/10/19","28/10/19","27/10/19","25/10/19","29/10/19","21/10/19","25/10/19","23/10/19","22/10/19","30/10/19"});
        layouts[14] = new Layout(
                0,
                getResources().getString(R.string.msn6),
                null,
                null,
                getResources().getString(R.string.botext15),
                null);





        b = findViewById(R.id.button);
        b.setOnClickListener(this);

        scrollView = (ScrollView) findViewById(R.id.mar);

        makeLayout(layouts[currentLayout]);

    }

    private static int respNum(int lay) {

        switch (lay) {
            case R.drawable.img31:
                return 683;
            case R.drawable.img32:
                return 527;
            case R.drawable.img33:
                return 423;
            case R.drawable.img34:
                return 363;
            case R.drawable.img35:
                return 345;
            case R.drawable.img36:
                return 868;
            case R.drawable.img61:
                return 201;
            case R.drawable.img62:
                return 203;
            case R.drawable.img63:
                return 204;
            case R.drawable.img64:
                return 205;
            case R.drawable.img65:
                return 207;
            case R.drawable.img66:
                return 217;
            default:
                return 0;

        }

    }

    private static String respText(int lay) {

        switch (lay) {
            case R.drawable.img41:
                return "Gafas";
            case R.drawable.img42:
                return "Papelera";
            case R.drawable.img43:
                return "Puerta";
            case R.drawable.img44:
                return "Libro";
            case R.drawable.img45:
                return "Maletín";
            case R.drawable.img46:
                return "Calendario";
            case R.drawable.img47:
                return "Calculadora";
            case R.drawable.img48:
                return "Carta";

            default:
                return null;

        }

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
            if(seleccion.equals(layouts[currentLayout].getRespuesta())){
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
        if(currentLayout < layouts.length -1 ) {
            currentLayout++;
            makeLayout(layouts[currentLayout]);
        }
    }
}
