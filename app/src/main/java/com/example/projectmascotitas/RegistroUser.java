package com.example.projectmascotitas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectmascotitas.modelo.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RegistroUser extends AppCompatActivity {

    EditText nameR,lastR,addresR,phoneR, mailR,passworR,conpasswordR;
    private Button BotonRegistrar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_user);
        databaseReference =FirebaseDatabase.getInstance().getReference();
        nameR = findViewById(R.id.nameR);
        lastR = findViewById(R.id.lastnameR);
        addresR= findViewById(R.id.addressbtn);
        phoneR= findViewById(R.id.phoneRegister);
        mailR= findViewById(R.id.mailRegister);
        passworR= findViewById(R.id.password);
        conpasswordR = findViewById(R.id.confirmPassword);
        BotonRegistrar = findViewById(R.id.buttonRegisters);
        inicializacionFireBase();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BotonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameR.getText().toString();
                String lastname = lastR.getText().toString();
                String addres =addresR.getText().toString();
                String phone= phoneR.getText().toString();
                String mail=mailR.getText().toString();
                String password = passworR.getText().toString();
                String conpassword=conpasswordR.getText().toString();
                if(name.equals("")||lastname.equals("")||addres.equals("")||phone.equals("")||mail.equals("")||password.equals("")||conpassword.equals("")){
                    validacion();
                }
            else {
                Usuario p = new Usuario();
                p.setUid(UUID.randomUUID().toString());
                p.setName(name);
                p.setLastname(lastname);
                p.setAddres(addres);
                p.setPhone(phone);
                p.setMail(mail);
                p.setPassword(password);
                p.setConpassword(conpassword);
                databaseReference.child("Usuario").child(p.getUid()).setValue(p);
                    Toast.makeText(RegistroUser.this, "Agregado", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });
    }

    private void inicializacionFireBase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    private void limpiar() {
        nameR.setText("");
        lastR.setText("");
       addresR.setText("");
       phoneR.setText("");
       mailR.setText("");
       passworR.setText("");
       conpasswordR.setText("");

    }

    private void validacion() {
        String name =nameR.getText().toString();
        String lastname = lastR.getText().toString();
        String addres =addresR.getText().toString();
        String phone= phoneR.getText().toString();
        String mail=mailR.getText().toString();
        String password = passworR.getText().toString();
        String conpassword=conpasswordR.getText().toString();
        if (name.equals("")){
            nameR.setError("Required");
        }else if(lastname.equals("")){
            lastR.setError("Required");
        }else if(addres.equals("")){
            addresR.setError("Required");
        }else if(phone.equals("")){
            phoneR.setError("Required");
        }else if(mail.equals("")){
            mailR.setError("Required");
        }else if(password.equals("")){
            passworR.setError("Required");
        }else if (conpassword.equals("")){
            conpasswordR.setError("Required");
        }

    }


    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}