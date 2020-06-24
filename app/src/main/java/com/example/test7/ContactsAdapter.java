package com.example.test7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactsAdapter extends ArrayAdapter<Contacts> {
    private int resourceId;
    public ContactsAdapter(Context context, int resource, List<Contacts> objects){
        super(context, resource, objects);
        this.resourceId=resource;
    }
    class ViewHolder{
        TextView Name;
        TextView Number;
    }

    public View getView(int position,View convertView,ViewGroup parent) {
        Contacts contacts=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.Name=view.findViewById(R.id.contacts_name);
            viewHolder.Number=view.findViewById(R.id.contacts_number);
            view.setTag(viewHolder);
        }
        else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.Name.setText(contacts.getName());
        viewHolder.Number.setText(contacts.getNumber());
        return view;
    }
}
