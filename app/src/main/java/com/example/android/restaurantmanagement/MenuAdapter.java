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

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    public JSONArray elements;

    public MenuAdapter(JSONArray elements){
        this.elements = elements;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView plate, price, ingredients;
        RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            plate = itemView.findViewById(R.id.plate);
            price = itemView.findViewById(R.id.price);
            ingredients = itemView.findViewById(R.id.ingredients);
            container = itemView.findViewById(R.id.element_view_container);
        }
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_view,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
        try {
            JSONObject element = elements.getJSONObject(position);

            String name = element.getString("name");
            String price = element.getString("price");
            String ingredients = element.getString("ingredients");

            holder.plate.setText(name);
            holder.price.setText("S/."+price);
            holder.ingredients.setText(ingredients);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return elements.length();
    }
}

