package com.example.adminpc.deliveryexecutiveapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adminpc on 20-06-2019.
 */

public class OrdersAdapter extends ArrayAdapter<OrdersPOJO>{
    private Context context;
    private List<OrdersPOJO> ordersList = new ArrayList<>();

    public OrdersAdapter(@NonNull Context context, int resource, @NonNull List<OrdersPOJO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.ordersList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        OrdersPOJO order = ordersList.get(position);
        TextView orderId = (TextView) listItem.findViewById(R.id.orderId);
        orderId.setText("Order's Id: "+order.getOrderId());

        TextView userId = (TextView) listItem.findViewById(R.id.userId);
        userId.setText("User ID: "+order.getUserId());

        TextView address = (TextView) listItem.findViewById(R.id.address);
        address.setText("Address:  "+order.getAddress());

        TextView phoneno = (TextView) listItem.findViewById(R.id.phoneno);
        phoneno.setText("Phone No: " + order.getPhoneNo());

        TextView restaurantId = (TextView) listItem.findViewById(R.id.restaurantId);
        restaurantId.setText("Restaurant ID: "+order.getRestaurantId());

        TextView totalItems = (TextView) listItem.findViewById(R.id.totalItems);
        totalItems.setText("Total Items: "+order.getTotalItems());

        return listItem;
    }
}
