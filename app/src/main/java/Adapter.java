package com.example.thesparkbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private List<Model> customers;
    private  RecyclerViewClick recyclerViewClick;


    public Adapter(List<Model>customers, RecyclerViewClick recyclerViewClick) {

        this.customers = customers;
        this.recyclerViewClick = recyclerViewClick;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        int profile = customers.get(position).getImageView();
        String name = customers.get(position).getTx1();
        String email = customers.get(position).getTx2();
        Double bal = customers.get(position).getTx3();

        holder.setData(profile,name,email,bal);

    }

    @Override
    public int getItemCount() {

        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        private ImageView imageView;
        private TextView textView1,textView2,textView3;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView =  itemView.findViewById(R.id.profile);
            textView1 =  itemView.findViewById(R.id.name);
            textView2 =  itemView.findViewById(R.id.balance);
            textView3 =  itemView.findViewById(R.id.email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    recyclerViewClick.onItemClick(getAdapterPosition());
                }
            });


        }

        public void setData(int profile, String name, String email, Double bal) {

            imageView.setImageResource(profile);
            textView1.setText(name);
            textView2.setText(bal+" ₹");
            textView3.setText(email);

        }


    }

}
