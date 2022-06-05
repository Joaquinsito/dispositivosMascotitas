package com.example.projectmascotitas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectmascotitas.modelo.Direccion;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EditAddress extends AppCompatActivity {
    private List<Direccion> listdireccion =new ArrayList<Direccion>();
    ArrayAdapter<Direccion> arrayAdapterDireccion;
    EditText addres,zipcode,suburb,city,state;
    ListView listv_Direccion;
    private Button BotonRegistrar;
    private Button Botonactualizar;
    private Button Botoneliminar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Direccion direccionSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        databaseReference =FirebaseDatabase.getInstance().getReference();
        addres =findViewById(R.id.editaddress);
        zipcode=findViewById(R.id.editzipcode);
        suburb= findViewById(R.id.editsubrb);
        city=findViewById(R.id.editcity);
        state=findViewById(R.id.editstate);
        listv_Direccion= findViewById(R.id.datosaddres);
        BotonRegistrar = findViewById(R.id.buttonaddres);
        Botonactualizar= findViewById(R.id.actualizaraddres);
        Botoneliminar=findViewById(R.id.eliminaraddres);
        inicializacionFireBase();
        listarDatos();
        listv_Direccion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                direccionSeleccionada=(Direccion) parent.getItemAtPosition(position);
                addres.setText(direccionSeleccionada.getAddres());
                zipcode.setText(direccionSeleccionada.getZipcode());
                suburb.setText(direccionSeleccionada.getSuburb());
                city.setText(direccionSeleccionada.getCity());
                state.setText(direccionSeleccionada.getState());
            }
        });
        BotonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = addres.getText().toString();
                String zipcodes = zipcode.getText().toString();
                String suburbs =suburb.getText().toString();
                String citys= city.getText().toString();
                String states=state.getText().toString();
                if(address.equals("")||zipcodes.equals("")||suburbs.equals("")||citys.equals("")||states.equals("")){
                    validacion();

                }else{
                    Direccion p= new Direccion();
                    p.setUid(UUID.randomUUID().toString());
                    p.setAddres(address);
                    p.setZipcode(zipcodes);
                    p.setSuburb(suburbs);
                    p.setCity(citys);
                    p.setState(states);
                    databaseReference.child("Address").child(p.getUid()).setValue(p);
                    Toast.makeText(EditAddress.this, "Agregados", Toast.LENGTH_LONG).show();
                    limpiar();

                }
            }
        });
        Botonactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = addres.getText().toString();
                String zipcodes = zipcode.getText().toString();
                String suburbs =suburb.getText().toString();
                String citys= city.getText().toString();
                String states=state.getText().toString();
                if(address.equals("")||zipcodes.equals("")||suburbs.equals("")||citys.equals("")||states.equals("")){
                    validacion();

                }else{
                    Direccion p= new Direccion();
                    p.setUid(direccionSeleccionada.getUid());
                    p.setAddres(addres.getText().toString().trim());
                    p.setZipcode(zipcode.getText().toString().trim());
                    p.setSuburb(suburb.getText().toString().trim());
                    p.setCity(city.getText().toString().trim());
                    p.setState(state.getText().toString().trim());
                    databaseReference.child("Address").child(p.getUid()).setValue(p);
                    Toast.makeText(EditAddress.this, "Actualizado", Toast.LENGTH_LONG).show();


                }

            }
        });
        Botoneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = addres.getText().toString();
                String zipcodes = zipcode.getText().toString();
                String suburbs =suburb.getText().toString();
                String citys= city.getText().toString();
                String states=state.getText().toString();
                if(address.equals("")||zipcodes.equals("")||suburbs.equals("")||citys.equals("")||states.equals("")){
                    validacion();

                }else{
                    Direccion p= new Direccion();
                    p.setUid(direccionSeleccionada.getUid());
                    databaseReference.child("Address").child(p.getUid()).removeValue();
                    Toast.makeText(EditAddress.this, "Datos eliminados", Toast.LENGTH_LONG).show();
                    limpiar();


                }
            }
        });

    }

    private void listarDatos() {
        databaseReference.child("Address").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listdireccion.clear();
                for(DataSnapshot objSnaptshot: dataSnapshot.getChildren()){
                    Direccion p= objSnaptshot.getValue(Direccion.class);
                    listdireccion.add(p);

                    arrayAdapterDireccion =new ArrayAdapter<Direccion>(EditAddress.this, android.R.layout.simple_list_item_1,listdireccion);
                    listv_Direccion.setAdapter(arrayAdapterDireccion);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializacionFireBase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    private void limpiar() {
        addres.setText("");
        zipcode.setText("");
        suburb.setText("");
        city.setText("");
        state.setText("");
    }

    private void validacion() {
        String address = addres.getText().toString();
        String zipcodes = zipcode.getText().toString();
        String suburbs =suburb.getText().toString();
        String citys= city.getText().toString();
        String states=state.getText().toString();
        if (address.equals("")){
            addres.setError("Required");
        }else if(zipcodes.equals("")){
            zipcode.setError("Required");
        }else if(suburbs.equals("")){
            suburb.setError("Required");
        }else if(citys.equals("")){
            city.setError("Required");
        }else if(states.equals("")){
            state.setError("Required");
        }
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}