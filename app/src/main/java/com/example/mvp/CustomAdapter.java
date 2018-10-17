package com.example.mvp;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<GasStation> implements View.OnClickListener {
    private ArrayList<GasStation> gasStations=new ArrayList<GasStation>();
    private Context mContext;
    private String fuelType="Euro95";

    private static class ViewHolder {
        TextView txtAddress;
        TextView txtName;
        TextView txtFuelType;
        TextView txtFuelPrice;
        ImageView info;
    }

    public CustomAdapter(ArrayList<GasStation> gasStationsI, Context context) {
        super(context, R.layout.activity_station_list, gasStationsI);
        int count=0;
        this.gasStations=gasStationsI;
        this.mContext=context;
    }



    public void setFuelType(String fuelType) {
        this.fuelType=fuelType;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        GasStation station=(GasStation)object;

        /*switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Last updated on: " +station.getLastUpdated(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }*/
    }

    // private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        int count=0;
        GasStation station = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_station_list, parent, false);
            //viewHolder.txtAddress = (TextView) convertView.findViewById(R.id.address);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtFuelType = (TextView) convertView.findViewById(R.id.fuelType);
            viewHolder.txtFuelPrice = (TextView) convertView.findViewById(R.id.fuelPrice);
            //viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        //Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //result.startAnimation(animation);
        //lastPosition = position;

        //viewHolder.txtAddress.setText(station.getAddress());
        viewHolder.txtName.setText(station.getName());
        viewHolder.txtFuelType.setText(this.fuelType);
        if (this.fuelType.equals("Euro95")) {
            viewHolder.txtFuelPrice.setText(station.getEuro95());
        } else if (this.fuelType.equals("Diesel")) {
            viewHolder.txtFuelPrice.setText(station.getDiesel());
        }
        //viewHolder.info.setOnClickListener(this);
        //viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}