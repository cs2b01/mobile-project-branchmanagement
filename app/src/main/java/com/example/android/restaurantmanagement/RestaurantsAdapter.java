package com.example.android.restaurantmanagement;

import android.content.Intent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {

    public JSONArray elements;
    private Context context;
    public String admin;

    public RestaurantsAdapter(JSONArray elements, Context context, String admin){
        this.elements = elements;
        this.context = context;
        this.admin = admin;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView restaurant_name;
        Button menu_button, employee_button;
        RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            restaurant_name = itemView.findViewById(R.id.restaurant_name);
            menu_button = itemView.findViewById(R.id.button_menu);
            employee_button = itemView.findViewById(R.id.button_employee);
            container = itemView.findViewById(R.id.element_view_container);
        }
    }

    @NonNull
    @Override
    public RestaurantsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_view,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsAdapter.ViewHolder holder, int position) {
        try {
            JSONObject element = elements.getJSONObject(position);
            final String name = element.getString("name");
            final String id = element.getString("id");
            holder.restaurant_name.setText(name);

            if(admin.equals("N")){
                holder.employee_button.setX(10000);
            }

            holder.menu_button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                        Intent Menu_List = new Intent(context, MenuActivity.class);
                        Menu_List.putExtra("id",id);
                        Menu_List.putExtra("name",name);
                        context.startActivity(Menu_List);

                }
            });

            holder.employee_button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                        Intent Employee_List = new Intent(context, EmployeeActivity.class);
                        Employee_List.putExtra("id",id);
                        Employee_List.putExtra("name",name);
                        context.startActivity(Employee_List);

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return elements.length();
    }
}

