package com.test.myapplication.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.myapplication.Models.ItemModel;
import com.test.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    private List<ItemModel> mItemModels;
    private List<ItemModel> tempData;
    onItemClickListener mListener;

    public MainAdapter(List<ItemModel> itemModels, onItemClickListener listener) {
        mItemModels = itemModels;
        tempData = itemModels;
        mListener = listener;
    }

    public void updateData(List<ItemModel> models) {
        mItemModels = models;
        notifyDataSetChanged();
    }

    public void clearFilter() {
        mItemModels = tempData;
        notifyDataSetChanged();
    }

    public void filter(String s, String[] data, String[] data2, String[] data3,String[] data4,String[] data5, String[] data6,
    String[] data7,String [] data8, String [] data9 , String [] data10) {
        tempData = new ArrayList<>(mItemModels);
        if (s.isEmpty()) {
            mItemModels = new ArrayList<>(tempData);
            notifyDataSetChanged();
        } else {
            mItemModels = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {
                if (data[i].toLowerCase().contains(s.toLowerCase())) {
                    mItemModels.add(new ItemModel(1, i));
                } else {
                    continue;
                }
            }
            for (int i = 0; i < data2.length; i++) {
                if (data2[i].toLowerCase().contains(s.toLowerCase())) {
                    mItemModels.add(new ItemModel(2, i));
                } else {
                    continue;
                }
            }
            for (int i = 0; i < data3.length; i++) {
                if (data3[i].toLowerCase().contains(s.toLowerCase())) {
                    mItemModels.add(new ItemModel(3, i));
                } else {
                    continue;
                }
            }
            for (int i = 0; i < data4.length; i++) {
                if (data4[i].toLowerCase().contains(s.toLowerCase())) {
                    mItemModels.add(new ItemModel(4, i));
                } else {
                    continue;
                }
            }
            for (int i = 0; i < data5.length; i++) {
                if (data5[i].toLowerCase().contains(s.toLowerCase())) {
                    mItemModels.add(new ItemModel(5, i));
                } else {
                    continue;
                }
            }
            for (int i = 0; i < data6.length; i++) {
                if (data6[i].toLowerCase().contains(s.toLowerCase())) {
                    mItemModels.add(new ItemModel(6, i));
                } else {
                    continue;
                }
            }
            for (int i = 0; i < data7.length; i++) {
                if (data7[i].toLowerCase().contains(s.toLowerCase())) {
                    mItemModels.add(new ItemModel(7, i));
                } else {
                    continue;
                }
            }
            for (int i = 0; i < data8.length; i++) {
                if (data8[i].toLowerCase().contains(s.toLowerCase())) {
                    mItemModels.add(new ItemModel(8, i));
                } else {
                    continue;
                }
            }
            for (int i = 0; i < data9.length; i++) {
                if (data9[i].toLowerCase().contains(s.toLowerCase())) {
                    mItemModels.add(new ItemModel(9, i));
                } else {
                    continue;
                }
            }
            for (int i = 0; i < data10.length; i++) {
                if (data10[i].toLowerCase().contains(s.toLowerCase())) {
                    mItemModels.add(new ItemModel(10, i));
                } else {
                    continue;
                }
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {

        holder.itemView.setOnClickListener(view -> {
            mListener.onItemClick(mItemModels.get(position));
        });

        if (mItemModels.get(position).section == 1) {
            String[] res = holder.itemView.getContext().getResources().getStringArray(R.array.item_section_perem);
            holder.titleTxt.setText(res[mItemModels.get(position).positionInList]);
        } else if (mItemModels.get(position).section == 2) {
            String[] res = holder.itemView.getContext().getResources().getStringArray(R.array.item_section_oper);
            holder.titleTxt.setText(res[mItemModels.get(position).positionInList]);
        }
        else if(mItemModels.get(position).section == 3){
            String[] res = holder.itemView.getContext().getResources().getStringArray(R.array.item_section_cycle);
            holder.titleTxt.setText(res[mItemModels.get(position).positionInList]);
        } else if (mItemModels.get(position).section == 4){
            String[] res = holder.itemView.getContext().getResources().getStringArray(R.array.item_section_OOP);
            holder.titleTxt.setText(res[mItemModels.get(position).positionInList]);
        } else if(mItemModels.get(position).section == 5) {
            String[] res = holder.itemView.getContext().getResources().getStringArray(R.array.item_section_core);
            holder.titleTxt.setText(res[mItemModels.get(position).positionInList]);
        } else if (mItemModels.get(position).section == 6) {
            String[] res = holder.itemView.getContext().getResources().getStringArray(R.array.item_section_collection);
            holder.titleTxt.setText(res[mItemModels.get(position).positionInList]);
        }else if   (mItemModels.get(position).section == 7){
            String[] res = holder.itemView.getContext().getResources().getStringArray(R.array.item_section_potok);
            holder.titleTxt.setText(res[mItemModels.get(position).positionInList]);
        } else if (mItemModels.get(position).section == 8) {
            String[] res = holder.itemView.getContext().getResources().getStringArray(R.array.item_section_serialization);
            holder.titleTxt.setText(res[mItemModels.get(position).positionInList]);
        }else if (mItemModels.get(position).section == 9){
            String[] res = holder.itemView.getContext().getResources().getStringArray(R.array.item_section_multithreading);
            holder.titleTxt.setText(res[mItemModels.get(position).positionInList]);
        } else{
            String[] res = holder.itemView.getContext().getResources().getStringArray(R.array.item_section_JDBC);
            holder.titleTxt.setText(res[mItemModels.get(position).positionInList]);
        }

    }

    @Override
    public int getItemCount() {
        return mItemModels.size();
    }

    static class MainHolder extends RecyclerView.ViewHolder {

        TextView titleTxt;
        ImageView favImg;

        MainHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            favImg = itemView.findViewById(R.id.favImg);
        }
    }

    public interface onItemClickListener {
        void onItemClick(ItemModel model);
    }
}
