package com.example.projectmascotitas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.projectmascotitas.Adaptadores.ListViewOrdersAdapter;
import com.example.projectmascotitas.modelo.Order;
import com.example.projectmascotitas.modelo.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private ArrayList<Order> listorders = new ArrayList<Order>();
    ArrayAdapter<Order> arrayAdapterProduct;
    ListViewOrdersAdapter listViewOrdersAdapter;
    LinearLayout linearLayoutEditar;
    ListView listViewOrders;

    EditText inputName, inputPrice, inputDescription;
    Button btnCancel;

    Product productSelected;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listViewOrders = findViewById(R.id.listViewOrders);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.history);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.history:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), index.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.offer:
                        startActivity(new Intent(getApplicationContext(), Offers.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(), Cart.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }

        });

        inicializarFirebase();
        listarProducts();

    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void listarProducts(){
        databaseReference.child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listorders.clear();
                for(DataSnapshot objSnapshot : snapshot.getChildren()){
                    Order p = objSnapshot.getValue(Order.class);
                    listorders.add(p);
                }
                listViewOrdersAdapter = new ListViewOrdersAdapter(History.this,listorders);
//                arrayAdapterProduct = new ArrayAdapter<Product>(
//                        index.this,
//                        android.R.layout.simple_list_item_1,
//                        listproducts
//                );
                listViewOrders.setAdapter(listViewOrdersAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void details (View view){
        startActivity(new Intent(getApplicationContext(), details.class));
    }
}