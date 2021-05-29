package com.mystartup.favlistapp;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView itemTextView;

    public ItemViewHolder(View itemView) {

        super(itemView);

        itemTextView = itemView.findViewById(R.id.item_textView);


    }


}
