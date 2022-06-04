package com.ayushi.frontpeople.Adapters;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayushi.frontpeople.DataModels.Donor;
import com.ayushi.frontpeople.R;


import android.app.Activity;
import android.net.Uri;
import android.view.View.OnClickListener;

import androidx.core.content.PermissionChecker;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<Donor> dataSet;
    private Context context;

    public SearchAdapter(
            List<Donor> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.donor_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        String str = "Name: " + dataSet.get(position).getName();
        str += "\nCity: " + dataSet.get(position).getCity();
        holder.message.setText(str);
        holder.callButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // for later
                if (PermissionChecker.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                        == PermissionChecker.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + dataSet.get(position).getNumber()));
                    context.startActivity(intent);
                } else {
                    ((Activity) context).requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 401);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView message, numb;
        ImageView imageView, callButton;

        ViewHolder(final View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message);
            imageView = itemView.findViewById(R.id.image);
            numb = itemView.findViewById(R.id.number);
            callButton = itemView.findViewById(R.id.call_button);
        }

    }

}
