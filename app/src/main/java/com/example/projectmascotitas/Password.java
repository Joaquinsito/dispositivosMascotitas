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
import com.example.projectmascotitas.modelo.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Password extends AppCompatActivity {
    private List<Usuario> listdireccion =new ArrayList<Usuario>();
    ArrayAdapter<Usuario> arrayAdapterUsuario;
    EditText olncontra,newcontra;
    ListView listv_Usuario;
    private Button Botonactualizar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Usuario UsuarioSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        databaseReference =FirebaseDatabase.getInstance().getReference();
        olncontra=findViewById(R.id.editoldpasswor);
        newcontra=findViewById(R.id.editnewpassword);
        Botonactualizar=findViewById(R.id.salvarpassword);
        listv_Usuario=findViewById(R.id.datoscontraseña);
        inicializacionFireBase();
        listarDatos();
        listv_Usuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UsuarioSeleccionada=(Usuario) parent.getItemAtPosition(position);
                olncontra.setText(UsuarioSeleccionada.getPassword());
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Botonactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpassword = olncontra.getText().toString();
                String newpassword = newcontra.getText().toString();

                if(oldpassword.equals("")||newpassword.equals("")){
                    validacion();

                }else{
                    Usuario p= new Usuario();
                    p.setUid(UsuarioSeleccionada.getUid());
                    p.setName(UsuarioSeleccionada.getName());
                    p.setLastname(UsuarioSeleccionada.getLastname());
                    p.setAddres(UsuarioSeleccionada.getAddres());
                    p.setPhone(UsuarioSeleccionada.getPhone());
                    p.setMail(UsuarioSeleccionada.getMail());
                    p.setConpassword(UsuarioSeleccionada.getConpassword());
                    p.setPassword(newcontra.getText().toString().trim());
                    databaseReference.child("Usuario").child(p.getUid()).setValue(p);
                    Toast.makeText(Password.this, "Actualizado", Toast.LENGTH_LONG).show();


                }

            }
        });
    }

    private void listarDatos() {
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listdireccion.clear();
                for(DataSnapshot objSnaptshot: dataSnapshot.getChildren()){
                    Usuario p= objSnaptshot.getValue(Usuario.class);
                    listdireccion.add(p);

                    arrayAdapterUsuario =new ArrayAdapter<Usuario>(Password.this, android.R.layout.simple_list_item_1,listdireccion);
                    listv_Usuario.setAdapter(arrayAdapterUsuario);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void validacion() {
        String oldpassword = olncontra.getText().toString();
        String newpassword = newcontra.getText().toString();
        if(oldpassword.equals("")){
            olncontra.setError("Required");
        }else if(newpassword.equals("")){
            newcontra.setError("Required");
        }
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