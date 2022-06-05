package com.example.projectmascotitas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectmascotitas.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Registro extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private EditText confirmpassword;
    private EditText name;
    private EditText lastname;
    private EditText address;
    private EditText phone;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mAuth = FirebaseAuth.getInstance();
        databaseReference =FirebaseDatabase.getInstance().getReference();

        email = (EditText) findViewById(R.id.mailRegister);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmPassword);
        name = (EditText) findViewById(R.id.nameR);
        lastname = (EditText) findViewById(R.id.lastnameR);
        address = (EditText) findViewById(R.id.addressR);
        phone = (EditText) findViewById(R.id.phoneR);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void registrarUsuario(View view){
        String emailtxt = email.getText().toString();
        String passwordtxt = password.getText().toString();
        String nametxt = name.getText().toString();
        String lastnametxt = lastname.getText().toString();
        String addresstxt = address.getText().toString();
        String phonetxt = phone.getText().toString();
        String conpasswordtxt = confirmpassword.getText().toString();

        if(nametxt.isEmpty()){
            name.setError("Name is required");
            name.requestFocus();
            return;
        }

        if(lastnametxt.isEmpty()){
            name.setError("Last Name is required");
            name.requestFocus();
            return;
        }

        if(addresstxt.isEmpty()){
            name.setError("Address is required");
            name.requestFocus();
            return;
        }
        if(phonetxt.isEmpty()){
            name.setError("Phone is required");
            name.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailtxt).matches()){
            email.setError("Please provide valide email");
            name.requestFocus();
            return;
        }

        if (password.getText().toString().equals(confirmpassword.getText().toString())){
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Usuario p = new Usuario();
                                p.setUid(UUID.randomUUID().toString());
                                p.setName(nametxt);
                                p.setLastname(lastnametxt);
                                p.setAddres(addresstxt);
                                p.setPhone(phonetxt);
                                p.setMail(emailtxt);
                                p.setPassword(passwordtxt);
                                p.setConpassword(conpasswordtxt);
                                databaseReference.child("Usuario").child(p.getUid()).setValue(p);
                                Toast.makeText(getApplicationContext()," Successfull.", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(Registro.this, index.class);
                                startActivity(i);

                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(),"Authentication failed.", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });


        }else{
            Toast.makeText(this, "Password dont coinciden", Toast.LENGTH_SHORT);
        }


    }
}