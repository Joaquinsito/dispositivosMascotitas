package com.example.projectmascotitas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final TextView txtResult = findViewById(R.id.nameproduct);
        final TextView txtResult2 = findViewById(R.id.priceproduct);
        EditText quantity = (EditText) findViewById(R.id.quantityproduct);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent getData = getIntent();
        String textoRecibido = getData.getExtras().getString("name");
        String textoRecibido2 = getData.getExtras().getString("price");
        String quantitytxt = "1";

        txtResult.setText(textoRecibido);
        txtResult2.setText(textoRecibido2);

        Button button = (Button) findViewById(R.id.cart);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toCartPay = new Intent(details.this, Cart.class);
                toCartPay.putExtra("quantity", quantitytxt);
                toCartPay.putExtra("name", textoRecibido);
                toCartPay.putExtra("price", textoRecibido2);
                startActivity(toCartPay);
            }
        });
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