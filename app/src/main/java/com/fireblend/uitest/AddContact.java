package com.fireblend.uitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fireblend.uitest.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

import static com.fireblend.uitest.R.id.boton_registro;

public class AddContact extends AppCompatActivity {

    @BindView(R.id.boton_registro)
    Button boton_registro;

    @BindView(R.id.TxtNombre)
    EditText TxtNombre;

    @BindView(R.id.TxtEdad)
    EditText TxtEdad;

    @BindView(R.id.TxtTelefono)
    EditText TxtTelefono;

    @BindView((R.id.TxtEmail))
    EditText TxtEmail;

    @BindView((R.id.provincias))
    Spinner provincias;

    @BindView(R.id.txt_provincia)
    TextView txt_provincia;

    String provincia = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ButterKnife.bind(this);

        final String[] prov = new String[]{"San José","Alajuela","Puntarenas","Guanacaste","Limón","Heredia","Cartago"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, prov);//Forma de mostrar el array
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//Saldrán las opciones hacía abajo
        provincias.setAdapter(adaptador);

        provincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

              provincia = (adapterView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



        @OnClick(R.id.boton_registro)
               public void boton(){
            String nombre = TxtNombre.getText().toString();
            String edad = TxtEdad.getText().toString();
            String telefono = TxtTelefono.getText().toString();
            String email = TxtEmail.getText().toString();
            //String provincia = provincia.getText().toString();

            //Validamos que no queden espacios sin llenar
            if(nombre.isEmpty()|edad.isEmpty()|telefono.isEmpty()|email.isEmpty()){
                Toast.makeText(AddContact.this,"Debe llenar todos los campos",Toast.LENGTH_LONG).show();
                return;
            }
            else
                {
                    //Creamos el Intent
                Integer edad1 = Integer.parseInt(edad);
                Intent myIntent = new Intent(AddContact.this, MainActivity.class);

                //Creamos el Bundle para capturar los datos que vamos a pasar al otro Activity
                Bundle extras = new Bundle();
                extras.putString("Nombre", nombre);
                extras.putInt("Edad", edad1);
                extras.putString("Telefono", telefono);
                extras.putString("Email", email);
                extras.putString("Provincia", provincia);

                //Pasamos los datos al Intent
                myIntent.putExtras(extras);
                //Toast.makeText(AddContact.this, provincia, Toast.LENGTH_LONG).show();
                startActivity(myIntent);
            }

        }



}
