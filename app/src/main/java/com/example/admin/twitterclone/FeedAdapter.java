package com.example.admin.twitterclone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class FeedAdapter extends ArrayAdapter<PersonsList> {

    private Context mcontext;
    int mResource;

    public FeedAdapter(@NonNull Context context, int resource,ArrayList arrayList) {
        super(context, resource,arrayList);
        mcontext = context;
        mResource = resource;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String userUserName = getItem(position).getUsername();
        String userMessage = getItem(position).getMessage();


        PersonsList personsList=new PersonsList(userUserName,userMessage);

        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);

        convertView = layoutInflater.inflate(mResource,parent,false);


        TextView userTextView=(TextView)convertView.findViewById(R.id.usertv);
        TextView msgTextView= (TextView) convertView.findViewById(R.id.msgtv);


        userTextView.setText(userUserName);
        msgTextView.setText(userMessage);

        return convertView;




    }


}