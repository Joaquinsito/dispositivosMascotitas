package com.example.projectmascotitas.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectmascotitas.R;
import com.example.projectmascotitas.modelo.Product;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListViewPersonasAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> productData;
    LayoutInflater layoutInflater;
    Product productModel;

    public ListViewPersonasAdapter(Context context, ArrayList<Product> productData) {
        this.context = context;
        this.productData = productData;
        layoutInflater = (LayoutInflater) context.getSystemService(
                context.LAYOUT_INFLATER_SERVICE
        );
    }

    @Override
    public int getCount() {
        return productData.size();
    }

    @Override
    public Object getItem(int i) {
        return productData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        if(rowView == null){
            rowView = layoutInflater.inflate(R.layout.listproducts, null, true);
        }

        TextView names = rowView.findViewById(R.id.names);
        TextView price = rowView.findViewById(R.id.prices);
        TextView description = rowView.findViewById(R.id.descriptions);

        productModel = productData.get(i);
        names.setText(productModel.getName());
        price.setText(productModel.getPrice());
        description.setText(productModel.getDescription());
        return rowView;
    }
}
