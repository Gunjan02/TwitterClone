package com.example.admin.twitterclone;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<PersonsList> {

    private Context mcontext;
    int mResource;

    public ListAdapter(@NonNull Context context, int resource, @NonNull List<PersonsList> arrayList) {
        super(context, resource,arrayList);
        mcontext = context;
        mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String userUserName = getItem(position).getUsername();
        int followImage = getItem(position).getImage();


        PersonsList personsList=new PersonsList(userUserName,followImage);

        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);

        convertView = layoutInflater.inflate(mResource,parent,false);


        TextView userTextView=(TextView)convertView.findViewById(R.id.usertext);
        ImageButton followImageView= (ImageButton) convertView.findViewById(R.id.tickBtn);


        userTextView.setText(userUserName);
        followImageView.setImageResource(followImage);

        return convertView;




    }


}