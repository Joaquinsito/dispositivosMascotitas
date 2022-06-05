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
import com.example.projectmascotitas.modelo.Tarjetas;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EditCard extends AppCompatActivity {
    private List<Tarjetas> listtarjetas =new ArrayList<Tarjetas>();
    ArrayAdapter<Tarjetas> arrayAdapterTarjetas;
    EditText tarjetaNum,datecard,ccvs;
    ListView listv_Tarjetas;
    private Button BotonRegistrar;
    private Button Botonactualizar;
    private Button Botoneliminar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Tarjetas tarjetasSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);
        databaseReference =FirebaseDatabase.getInstance().getReference();
        tarjetaNum =findViewById(R.id.cardnumber);
        datecard=findViewById(R.id.duedate);
        ccvs=findViewById(R.id.ccv);
        listv_Tarjetas= findViewById(R.id.datospago);
        BotonRegistrar=findViewById(R.id.savepay);
        Botonactualizar=findViewById(R.id.updatepay);
        Botoneliminar=findViewById(R.id.deletepay);
        inicializacionFireBase();
        listarDatos();
        listv_Tarjetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tarjetasSeleccionada=(Tarjetas) parent.getItemAtPosition(position);
                tarjetaNum.setText(tarjetasSeleccionada.getNumbercar());
                datecard.setText(tarjetasSeleccionada.getDate());
                ccvs.setText(tarjetasSeleccionada.getCcv());

            }
        });

        BotonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardsnum = tarjetaNum.getText().toString();
                String fechacard = datecard.getText().toString();
                String ccvcard =ccvs.getText().toString();
                if(cardsnum.equals("")||fechacard.equals("")||ccvcard.equals("")){
                    validacion();

                }else{
                    Tarjetas p= new Tarjetas();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNumbercar(cardsnum);
                    p.setDate(fechacard);
                    p.setCcv(ccvcard);
                    databaseReference.child("Card").child(p.getUid()).setValue(p);
                    Toast.makeText(EditCard.this, "Agregados", Toast.LENGTH_LONG).show();
                    limpiar();

                }
            }
        });
        Botonactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardsnum = tarjetaNum.getText().toString();
                String fechacard = datecard.getText().toString();
                String ccvcard =ccvs.getText().toString();
                if(cardsnum.equals("")||fechacard.equals("")||ccvcard.equals("")){
                    validacion();

                }else{
                    Tarjetas p= new Tarjetas();
                    p.setUid(tarjetasSeleccionada.getUid());
                    p.setNumbercar(tarjetaNum.getText().toString().trim());
                    p.setDate(datecard.getText().toString().trim());
                    p.setCcv(ccvs.getText().toString().trim());
                    databaseReference.child("Card").child(p.getUid()).setValue(p);
                    Toast.makeText(EditCard.this, "Actualizado", Toast.LENGTH_LONG).show();


                }

            }
        });
        Botoneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardsnum = tarjetaNum.getText().toString();
                String fechacard = datecard.getText().toString();
                String ccvcard =ccvs.getText().toString();
                if(cardsnum.equals("")||fechacard.equals("")||ccvcard.equals("")){
                    validacion();


                }else{
                    Tarjetas p= new Tarjetas();
                    p.setUid(tarjetasSeleccionada.getUid());
                    databaseReference.child("Card").child(p.getUid()).removeValue();
                    Toast.makeText(EditCard.this, "Datos eliminados", Toast.LENGTH_LONG).show();
                    limpiar();


                }
            }
        });
    }

    private void limpiar() {
        tarjetaNum.setText("");
        datecard.setText("");
        ccvs.setText("");
    }

    private void validacion() {
        String cardsnum = tarjetaNum.getText().toString();
        String fechacard = datecard.getText().toString();
        String ccvcard =ccvs.getText().toString();
        if (cardsnum.equals("")){
            tarjetaNum.setError("Required");
        }else if(fechacard.equals("")){
            datecard.setError("Required");
        }else if(ccvcard.equals("")){
            ccvs.setError("Required");
        }

    }

    private void listarDatos() {
        databaseReference.child("Card").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listtarjetas.clear();
                for(DataSnapshot objSnaptshot: dataSnapshot.getChildren()){
                    Tarjetas p= objSnaptshot.getValue(Tarjetas.class);
                    listtarjetas.add(p);

                    arrayAdapterTarjetas =new ArrayAdapter<Tarjetas>(EditCard.this, android.R.layout.simple_list_item_1,listtarjetas);
                    listv_Tarjetas.setAdapter(arrayAdapterTarjetas);
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

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}