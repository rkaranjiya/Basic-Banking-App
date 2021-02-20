package com.example.thesparkbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<HistoryModel> transfer;
    public HistoryAdapter(List<HistoryModel> transfer){
        this.transfer = transfer;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_history_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {

        String name = transfer.get(position).getTx1();
        String name2 = transfer.get(position).getTx2();
        String date = transfer.get(position).getTx4();
        Double bal = transfer.get(position).getTx3();

        holder.setData(name,name2,date,bal);

    }

    @Override
    public int getItemCount() {
        return transfer.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        private TextView textView1,textView2,textView3,textView4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.name);
            textView2 = itemView.findViewById(R.id.name2);
            textView3 = itemView.findViewById(R.id.balance);
            textView4 = itemView.findViewById(R.id.date);

        }

        public void setData(String from, String to, String stamp,Double value) {

            textView1.setText(from);
            textView2.setText(to);
            textView3.setText(value +" ₹");
            textView4.setText(stamp);
        }
    }
}
