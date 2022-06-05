package com.example.projectmascotitas.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectmascotitas.R;
import com.example.projectmascotitas.modelo.Order;
import com.example.projectmascotitas.modelo.Product;

import java.util.ArrayList;

public class ListViewOrdersAdapter extends BaseAdapter {
    Context context;
    ArrayList<Order> orderData;
    LayoutInflater layoutInflater;
    Order orderModel;

    public ListViewOrdersAdapter(Context context, ArrayList<Order> orderData) {
        this.context = context;
        this.orderData = orderData;
        layoutInflater = (LayoutInflater) context.getSystemService(
                context.LAYOUT_INFLATER_SERVICE
        );
    }


    public int getCount() {
        return orderData.size();
    }


    public Object getItem(int i) {
        return orderData.get(i);
    }


    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        if(rowView == null){
            rowView = layoutInflater.inflate(R.layout.listorders, null, true);
        }

        TextView idOrder = rowView.findViewById(R.id.idOrder);
        TextView name = rowView.findViewById(R.id.productName);
        TextView price = rowView.findViewById(R.id.productPrice);
        TextView quantity = rowView.findViewById(R.id.quantitypro);

        orderModel = orderData.get(i);
        idOrder.setText(orderModel.getIdOrder());
        name.setText(orderModel.getNameProduct());
        price.setText(orderModel.getPrice());
        quantity.setText(orderModel.getQuantity());
        return rowView;
    }

}
