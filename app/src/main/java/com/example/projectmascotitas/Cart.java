package com.example.projectmascotitas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectmascotitas.modelo.Order;
import com.example.projectmascotitas.modelo.Usuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.UUID;

public class Cart extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    String textoRecibido;
    String textoRecibido2;
    String quantitytxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        final TextView txtResult = findViewById(R.id.nameproduct);
        final TextView txtResult2 = findViewById(R.id.priceproduct);
        final TextView quantity = findViewById(R.id.quantityproduct);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference();
        userID = user.getUid();
        Intent getData = getIntent();
        textoRecibido = getData.getExtras().getString("name");
        textoRecibido2 = getData.getExtras().getString("price");
        quantitytxt = getData.getExtras().getString("quantity");

        txtResult.setText(textoRecibido);
        txtResult2.setText(textoRecibido2);
        quantity.setText(quantitytxt);

        Button button = (Button) findViewById(R.id.btnpaycart);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Order p = new Order();
                p.setIdOrder(UUID.randomUUID().toString());
                p.setNameProduct(textoRecibido);
                p.setPrice(textoRecibido2);
                p.setQuantity(quantitytxt);
                p.setuId(userID);
                reference.child("Orders").child(p.getIdOrder()).setValue(p);
                Toast.makeText(getApplicationContext()," Successfull.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Cart.this, PayCart.class));
            }
        });




        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.cart);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.history:
                        startActivity(new Intent(Cart.this, History.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(Cart.this, index.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.offer:
                        startActivity(new Intent(Cart.this, Offers.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.cart:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(Cart.this, Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }

        });
    }


}