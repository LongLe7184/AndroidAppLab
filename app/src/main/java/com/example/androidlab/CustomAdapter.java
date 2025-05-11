package com.example.androidlab;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private String[] labels;
    private String[] descriptions;
//    private int[] icons;
    private Bitmap[] icons;

    public CustomAdapter(Context context, String[] labels, String[] descriptions, Bitmap[] icons) {
        this.context = context;
        this.labels = labels;
        this.descriptions = descriptions;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return labels.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_list, parent, false);
        }

        ImageView icon = convertView.findViewById(R.id.item_icon);
        TextView label = convertView.findViewById(R.id.item_label);
        TextView description = convertView.findViewById(R.id.item_description);

//        icon.setImageResource(icons[position]);
        icon.setImageBitmap(icons[position]);
        label.setText(labels[position]);
        description.setText(descriptions[position]);

        return convertView;
    }
}