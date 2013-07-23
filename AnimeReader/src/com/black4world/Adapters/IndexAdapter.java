package com.black4world.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.black4world.MangaList;
import com.black4world.R;

/**
 * Created with IntelliJ IDEA.
 * User: black4ninja
 * Date: 22/07/13
 * Time: 10:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class IndexAdapter extends BaseAdapter {
    private Context context;
    private String[] indexes = new String[] {
            "#",
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};
    private int val;
    private TextView textView;

    public IndexAdapter(Context context) {
        this.context = context;
        val = 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.index_row_normal, null);

            // set value into textview
            textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            LinearLayout ll = (LinearLayout) gridView.findViewById(R.id.layout_normal);
            textView.setText(indexes[position]);
            val = position;
            if((position % 2) == 0){
                gridView.setBackgroundColor(Color.BLACK);
                textView.setTextColor(Color.WHITE);
                val = 1;
            }else{
                gridView.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);
                val = 2;
            }
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(context, MangaList.class);
                    //myIntent.putExtra("key", value); //Optional parameters
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myIntent.putExtra("LETTER", textView.getText().toString());
                    context.startActivity(myIntent);
                }
            });
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return indexes.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}