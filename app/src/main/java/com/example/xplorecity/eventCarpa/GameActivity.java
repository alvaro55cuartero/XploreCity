package com.example.xplorecity.eventCarpa;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.xplorecity.R;
import com.example.xplorecity.tools.Event;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.entity.StringEntity;

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

    private Gson gson;
    private String imei;
    private TelephonyManager telephonyManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
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

        Layout[] layouts = new Layout[28];


        layouts[0] = new Layout(new int[]{R.drawable.img11},null, null, null, getResources().getString(R.string.botext1), null);
        layouts[1] = new Layout(new int[]{0}, new String[]{getResources().getString(R.string.msn1)}, null, null, getResources().getString(R.string.botext2), null);
        layouts[2] = new Layout(new int[]{0}, new String[]{getResources().getString(R.string.msn2)}, null, null, getResources().getString(R.string.botext3), null);
        layouts[3] = new Layout(new int[]{R.drawable.img21}, null, getResources().getString(R.string.hint1), new String[]{"Los Madroños"}, getResources().getString(R.string.botext4), new String[]{"Los Tejos", "Los Acebos", "Los Magnolios", "Las Encinas", "Los Olivos", "Los Madroños", "Los pinos", "Aulario", "El Sario", "Administración", "Rectorado"});
        layouts[4] = new Layout(new int[]{R.drawable.imgma1}, null, null, null, getResources().getString(R.string.botext5), null);
        layouts[5] = new Layout(new int[]{0}, new String[]{getResources().getString(R.string.msn3)+texOption[tet]}, null, null, getResources().getString(R.string.botext6), null);
        layouts[6] = new Layout(new int[]{R.drawable.img3}, null, getResources().getString(R.string.hint2), new String[] {texResp[tet]}, getResources().getString(R.string.botext7), new String[]{"Silla","Libro","Folios","Cuaderno","Estanteria","Mesa","Portatil","Armario","Proyector","Esquina","Estanteria Pared","Mesilla","Techo","Impresora","Gafas","Agenda","Reloj","Archivador"});
        layouts[7] = new Layout(new int[]{R.drawable.img31,R.drawable.img32,R.drawable.img33,R.drawable.img34,R.drawable.img35, R.drawable.img36,}, null, getResources().getString(R.string.hint3), new String[] {"683", "527","423","363","345","868"}, getResources().getString(R.string.botext8), new String[]{"Selecciona una respuesta...","868","232","363","210","527","361","683","567","423","047","321","393","345"});
        layouts[8] = new Layout(new int[]{texOption2[tet2]}, null, getResources().getString(R.string.hint4), null, getResources().getString(R.string.botext9), null);
        layouts[9] = new Layout(new int[]{R.drawable.img51}, null, getResources().getString(R.string.hint5), new String[]{texResp2[tet2]}, getResources().getString(R.string.botext10), new String[]{"Selecciona una respuesta...","Gafas", "Papelera", "Puerta", "Libro", "Maletín" ,"Calendario" ,"Calculadora" ,"Carta"});
        layouts[10] = new Layout(new int[]{R.drawable.img61,R.drawable.img62,R.drawable.img63,R.drawable.img64, R.drawable.img65,R.drawable.img66}, null, getResources().getString(R.string.hint6), new String[]{"201", "203", "204", "205", "207", "217"}, getResources().getString(R.string.botext11), new String[]{"Selecciona una respuesta...","203","214","210","204","206","809","207","211","201","212","205","808","110","103","217"});
        layouts[11] = new Layout(new int[]{R.drawable.imgma1}, null, null, null, getResources().getString(R.string.botext12), null);
        layouts[12] = new Layout(new int[]{0}, new String[]{getResources().getString(R.string.msn5)}, null, null, getResources().getString(R.string.botext13), null);
        layouts[13] = new Layout(new int[]{R.drawable.img71,R.drawable.img72,R.drawable.img73}, null, getResources().getString(R.string.hint7), new String[] {"25/10/19","25/10/19","25/10/19"}, getResources().getString(R.string.botext14), new String[]{"Selecciona una respuesta...","24/10/19","26/10/19","28/10/19","27/10/19","25/10/19","29/10/19","21/10/19","25/10/19","23/10/19","22/10/19","30/10/19"});
        layouts[14] = new Layout(new int[]{0},new String[]{getResources().getString(R.string.msn6)},null,null,getResources().getString(R.string.botext15),null);
        layouts[15] = new Layout(new int[]{R.drawable.img81}, null, getResources().getString(R.string.hint8),null,getResources().getString(R.string.botxet16), null);
        layouts[16] = new Layout(new int[]{0},new String[]{getResources().getString(R.string.msn7)}, null,null,getResources().getString(R.string.botxet17),null);
        layouts[17] = new Layout(new int[]{R.drawable.carpa},null, getResources().getString(R.string.hint9),null,getResources().getString(R.string.botxet18),null);
        layouts[18] = new Layout(new int[]{0},new String[]{getResources().getString(R.string.msn81),getResources().getString(R.string.msn82),getResources().getString(R.string.msn83)},getResources().getString(R.string.hint10),new String[]{"Negro", "Negro", "Rojo"},getResources().getString(R.string.botxet19),new String[]{"Blanco", "Violeta", "Azul", "Rosa", "Rubio", "Avellana", "Café", "Castaño", "Negro", "Verde", "Rojo"});
        layouts[19] = new Layout(new int[]{0},new String[]{getResources().getString(R.string.msn91),getResources().getString(R.string.msn92),getResources().getString(R.string.msn93),getResources().getString(R.string.msn94),getResources().getString(R.string.msn95)},getResources().getString(R.string.hint11),new String[]{"Avellana","Pardos", "Verde", "Azul", "Azul"},getResources().getString(R.string.botxet19),new String[]{"Blanco","Violeta", "Azul", "Pardos", "Rosa", "Rubio", "Avellana", "Café", "Castaño", "Negro", "Verde", "Rojo"});
        layouts[20] = new Layout(new int[]{0},new String[]{getResources().getString(R.string.msn101),getResources().getString(R.string.msn102),getResources().getString(R.string.msn103),getResources().getString(R.string.msn104),getResources().getString(R.string.msn105),getResources().getString(R.string.msn106)},getResources().getString(R.string.hint12),new String[]{"A","O","D","C","F","M"},getResources().getString(R.string.botxet19),new String[]{"I","U","O","C","A","M","B","J","Z","S","P","D","N","F","L","E"});
        layouts[21] = new Layout(new int[]{R.drawable.img91},null,getResources().getString(R.string.hint13),null,getResources().getString(R.string.botxet20),null);
        layouts[22] = new Layout(new int[]{0},new String[]{getResources().getString(R.string.msn111)},null,null,getResources().getString(R.string.botxet21),null);
        layouts[23] = new Layout(new int[]{0},new String[]{getResources().getString(R.string.msn121)},null,null,getResources().getString(R.string.botxet22),null);
        layouts[24] = new Layout(new int[]{R.drawable.img101},null,getResources().getString(R.string.hint14),new String[]{"Persona de pelo " + layouts[18].getRespuesta()+", ojos " + layouts[19].getRespuesta() +", y de nombre " + checkName(layouts[20].getRespuesta())+"."},getResources().getString(R.string.botxet23),new String[]{"Persona de pelo negro, ojos Avellana,\ny de nombre Ana.","Persona de pelo negro, ojos Avellana,\ny de nombre Ofelia.","Persona de pelo negro, ojos Avellana,\ny de nombre Diana.","Persona de pelo negro, ojos Avellana,\ny de nombre Carla.","Persona de pelo negro, ojos Avellana,\ny de nombre Fabiola.","Persona de pelo negro, ojos Avellana,\ny de nombre Marta.","Persona de pelo negro, ojos Pardos,\ny de nombre Ana.","Persona de pelo negro, ojos Pardos,\ny de nombre Ofelia.","Persona de pelo negro, ojos Pardos,\ny de nombre Diana.","Persona de pelo negro, ojos Pardos,\ny de nombre Carla.","Persona de pelo negro, ojos Pardos,\ny de nombre Fabiola.","Persona de pelo negro, ojos Pardos,\ny de nombre Marta.","Persona de pelo negro, ojos Verde,\ny de nombre Ana.","Persona de pelo negro, ojos Verde,\ny de nombre Ofelia.","Persona de pelo negro, ojos Verde,\ny de nombre Diana.","Persona de pelo negro, ojos Verde,\ny de nombre Carla.","Persona de pelo negro, ojos Verde,\ny de nombre Fabiola.","Persona de pelo negro, ojos Verde,\ny de nombre Marta.","Persona de pelo negro, ojos Azul,\ny de nombre Ana.","Persona de pelo negro, ojos Azul,\ny de nombre Ofelia.","Persona de pelo negro, ojos Azul,\ny de nombre Diana.","Persona de pelo negro, ojos Azul,\ny de nombre Carla.","Persona de pelo negro, ojos Azul,\ny de nombre Fabiola.","Persona de pelo negro, ojos Azul,\ny de nombre Marta.","Persona de pelo rojo, ojos Avellana,\ny de nombre Ana.","Persona de pelo rojo, ojos Avellana,\ny de nombre Ofelia.","Persona de pelo rojo, ojos Avellana,\ny de nombre Diana.","Persona de pelo rojo, ojos Avellana,\ny de nombre Carla.","Persona de pelo rojo, ojos Avellana,\ny de nombre Fabiola.","Persona de pelo rojo, ojos Avellana,\ny de nombre Marta.","Persona de pelo rojo, ojos Pardos,\ny de nombre Ana.","Persona de pelo rojo, ojos Pardos,\ny de nombre Ofelia.","Persona de pelo rojo, ojos Pardos,\ny de nombre Diana.","Persona de pelo rojo, ojos Pardos,\ny de nombre Carla.","Persona de pelo rojo, ojos Pardos,\ny de nombre Fabiola.","Persona de pelo rojo, ojos Pardos,\ny de nombre Marta.","Persona de pelo rojo, ojos Verde,\ny de nombre Ana.","Persona de pelo rojo, ojos Verde,\ny de nombre Ofelia.","Persona de pelo rojo, ojos Verde,\ny de nombre Diana.","Persona de pelo rojo, ojos Verde,\ny de nombre Carla.","Persona de pelo rojo, ojos Verde,\ny de nombre Fabiola.","Persona de pelo rojo, ojos Verde,\ny de nombre Marta.","Persona de pelo rojo, ojos Azul,\ny de nombre Ana.","Persona de pelo rojo, ojos Azul,\ny de nombre Ofelia.","Persona de pelo rojo, ojos Azul,\ny de nombre Diana.","Persona de pelo rojo, ojos Azul,\ny de nombre Carla.","Persona de pelo rojo, ojos Azul,\ny de nombre Fabiola.","Persona de pelo rojo, ojos Azul,\ny de nombre Marta."});
        layouts[26] = new Layout(new int[]{R.drawable.img111},null,getResources().getString(R.string.hint15),null,getResources().getString(R.string.botxet24),null);
        layouts[27] = new Layout(new int[]{0},new String[]{getResources().getString(R.string.msn131)},null,null,getResources().getString(R.string.botxet25),null);

        event = new Event(layouts);




        b = findViewById(R.id.button);
        b.setOnClickListener(this);

        scrollView = (ScrollView) findViewById(R.id.mar);

        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if(isPermissionGranted()){
            //do your specific task after read phone state
            imei = telephonyManager.getImei();
        }
        //Peticion del currentLayout
        //Creamos el json
        gson = new Gson();
        CurrentLayoutRequest currentLayoutRequest = new CurrentLayoutRequest(imei);
        String requestJson = gson.toJson(currentLayoutRequest);

        //Mandamos la peticion
        AsyncHttpClient client = new AsyncHttpClient();
        try {
            client.post(
                    this,
                    "http://92.222.89.84/xplore/getLevel.php",
                    new StringEntity(requestJson),
                    "application/json",
                    new CurrentLayoutResponseHandler(gson, this, this)
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    public boolean checkDate(String date) {
        String[] datevalue = date.split("/");
        Date currentTime = Calendar.getInstance().getTime();
        boolean b = currentTime.before(new Date(Integer.parseInt(datevalue[2]),Integer.parseInt(datevalue[1]), Integer.parseInt(datevalue[0])));

        return b;
    }

    public String checkName(String letra) {
        switch (letra){
            case "A":
                return "Ana";
                case "O":
                    return "Ofelia";
                    case "D":
                        return "Diana";
                    case "C":
                        return "Carla";
                    case "F":
                        return "Fabiola";
                    case "M":
                        return "Marta";
                        default:
                            return null;
        }
    }


    public void makeLayout(int i) {
        Layout layout = event.layouts[i];
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

        if(currentLayout==3 || currentLayout==6 ||currentLayout==7 ||currentLayout==9 ||currentLayout==10 ||currentLayout==13 || currentLayout ==18|| currentLayout == 19 || currentLayout == 20 || currentLayout ==24 ){
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
        if(currentLayout < event.layouts.length -1 ) {
            if(currentLayout < 14 || checkDate("20/10/2019")) {
                currentLayout++;
                makeLayout(currentLayout);

                //Le decimos al servidor que hemos avanzado un Layout
                gson = new Gson();
                IncrementLayoutRequest incrementLayoutRequest = new IncrementLayoutRequest(imei);
                String requestJson = gson.toJson(incrementLayoutRequest);

                //Mandamos la peticion
                AsyncHttpClient client = new AsyncHttpClient();
                try {
                    client.post(
                            this,
                            "http://92.222.89.84/xplore/incrementLevel.php",
                            new StringEntity(requestJson),
                            "application/json",
                            new IncrementLayoutResponseHandler(gson, this, this)
                    );
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            this.finish();
        }
    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 2: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    //do your specific task after read phone state granted
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void setCurrentLayout(int currentLayout){
        this.currentLayout = currentLayout;
    }
}
