package com.example.adminpc.deliveryexecutiveapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {
    private final String ORDERS = Apis.BASE_URL + Apis.DELIVERY_ORDERS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        Bundle extras = getIntent().getExtras();
        final String username = extras.getString("username");
        final String latitude = extras.getString("latitude");
        final String longitude = extras.getString("longitude");

        ListView listView = (ListView)findViewById(R.id.list_item1);

        JsonLoadAndProcess jsonLoad = new JsonLoadAndProcess(this);
        String data = null;

        try {
            String params = "latitude="+Double.parseDouble(latitude)+"&longitude="+Double.parseDouble(longitude)+"&username="+username;
            Log.d("json Body: \n", params);
            data = jsonLoad.new JsonTaskGET().execute(ORDERS + params).get();
            Log.d("Response data: ", data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //String data = jsonLoad.returnData();
        if (data == null) {
            Toast.makeText(this, "Error while making the connection!", Toast.LENGTH_LONG).show();
        } else {
            //Toast.makeText(this, data, Toast.LENGTH_LONG).show();
            //System.out.println(data);
            try {
                // TODO: Testing with file but should be placed a link.
//                            InputStream inputStream = getResources().getAssets().open("json-validate");
//                            int size = inputStream.available();
//                            byte[] buffer = new byte[size];
//                            inputStream.read(buffer);
//                            inputStream.close();
//                            data = new String(buffer, "UTF-8");

                JSONObject jsonObject = new JSONObject(data); // orders
                JSONArray  jsonOrdersArray = jsonObject.getJSONArray("orders");
                if(jsonOrdersArray.length()==0){
                    // Alert Dialog to say orders not available.
                    final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(this).create();
                    alertDialog.setTitle("Message");
                    alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Intent intent = new Intent(getApplicationContext(), AvailableActivity.class);
                            startActivity(intent);

                        }
                    });
                    alertDialog.setMessage("Sorry! No orders available.");
                    alertDialog.show();

                }else{
                    List<OrdersPOJO> orders = new ArrayList<>();

                    for(int i=0; i<jsonOrdersArray.length(); i++){

                        JSONObject order = new JSONObject(jsonOrdersArray.getJSONObject(i).toString());

                        orders.add(new OrdersPOJO(order.getString("id"), order.getString("userId"),
                                order.getString("address"), order.getString("phoneNo"), order.getString("restaurantId"),
                                order.getJSONArray("items").length()));
                    }

                    ArrayAdapter arrayAdapter = new OrdersAdapter(this, 0, orders);
                    listView.setAdapter(arrayAdapter);
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error while Parsing!", Toast.LENGTH_LONG).show();
            }

        }

    }
}
