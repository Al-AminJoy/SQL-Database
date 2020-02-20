package com.example.sqldatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    Context context;
    ArrayList<ModelClass> list;

    public ListAdapter(Context context, ArrayList<ModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(this.context);
        View view=layoutInflater.inflate(R.layout.list_layout,parent,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        ModelClass model=list.get(position);
        String Id=model.getId();
        String Name=model.getName();
        String Age=model.getAge();
        String Gender=model.getGender();

        holder.tvId.setText("ID : "+Id);
        holder.tvName.setText("Name : "+Name);
        holder.tvAge.setText("Age : "+Age);
        holder.tvGender.setText("Gender : "+Gender);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvId,tvName,tvAge,tvGender;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId=itemView.findViewById(R.id.tvListIdId);
            tvName=itemView.findViewById(R.id.tvListNameId);
            tvAge=itemView.findViewById(R.id.tvListAgeId);
            tvGender=itemView.findViewById(R.id.tvListGenderId);
        }
    }
}
