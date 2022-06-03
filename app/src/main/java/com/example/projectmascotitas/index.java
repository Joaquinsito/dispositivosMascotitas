package com.example.projectmascotitas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectmascotitas.Adaptadores.ListViewPersonasAdapter;
import com.example.projectmascotitas.modelo.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class index extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private ArrayList<Product> listproducts = new ArrayList<Product>();
    ArrayAdapter<Product> arrayAdapterProduct;
    ListViewPersonasAdapter listViewPersonasAdapter;
    LinearLayout linearLayoutEditar;
    ListView listViewProducts;

    EditText inputName, inputPrice, inputDescription;
    Button btnCancel;

    Product productSelected;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        inputName = findViewById(R.id.inputName);
        inputPrice = findViewById(R.id.inputPrice);
        inputDescription = findViewById(R.id.inputDescription);


        listViewProducts = findViewById(R.id.listViewProducts);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), History.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
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

        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                productSelected = (Product) adapterView.getItemAtPosition(i);
                inputName.setText(productSelected.getName());
                inputName.setText(productSelected.getPrice());

            }
        });

        inicializarFirebase();
        listarProducts();

    }

//    public void details (View view){
//        LinearLayout app_layer = (LinearLayout) findViewById (R.id.product);
//        app_layer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), details.class));
//            }
//        });
//    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void listarProducts(){
        databaseReference.child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listproducts.clear();
                for(DataSnapshot objSnapshot : snapshot.getChildren()){
                    Product p = objSnapshot.getValue(Product.class);
                    listproducts.add(p);
                }

                arrayAdapterProduct = new ArrayAdapter<Product>(
                        index.this,
                        android.R.layout.simple_list_item_1,
                        listproducts
                );
                listViewProducts.setAdapter(arrayAdapterProduct);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        String name = inputName.getText().toString();
//        String price = inputName.getText().toString();

        switch (item.getItemId()){
            case R.id.menu_agregar:
                insert();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insert(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(
                index.this
        );
        View mView = getLayoutInflater().inflate(R.layout.insertproduct, null);

        Button btnInsertar = (Button) mView.findViewById(R.id.btnInsert);
        final EditText inputName = (EditText) mView.findViewById(R.id.inputName);
        final EditText inputPrice = (EditText) mView.findViewById(R.id.inputPrice);
        final EditText inputDescription = (EditText) mView.findViewById(R.id.inputDescription);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombres = inputName.getText().toString();
                String price = inputPrice.getText().toString();
                String description = inputDescription.getText().toString();

                if(nombres.isEmpty() || nombres.length()<3){
                    showError(inputName, "Invalid Name");
                }else{
                    Product p = new Product();
                    p.setIdproduct(UUID.randomUUID().toString());
                    p.setName(nombres);
                    p.setPrice(price);
                    p.setDescription(description);
                    databaseReference.child("Products").child(p.getIdproduct()).setValue(p);
                    Toast.makeText(
                            index.this,
                            "Registro correcto",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }

    public void showError(EditText input, String s){
        input.requestFocus();
        input.setError(s);

    }
}