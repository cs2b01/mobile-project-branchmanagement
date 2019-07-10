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

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    public JSONArray elements;

    public EmployeeAdapter(JSONArray elements){
        this.elements = elements;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, position;
        RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.employee_name);
            position = itemView.findViewById(R.id.position);
            container = itemView.findViewById(R.id.element_view_container);
        }
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employees_view,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        try {
            JSONObject element = elements.getJSONObject(position);

            String sName = element.getString("name") + " " + element.getString("lastname");
            String sPosition = element.getString("position");

            holder.name.setText(sName);
            holder.position.setText(sPosition);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return elements.length();
    }
}

