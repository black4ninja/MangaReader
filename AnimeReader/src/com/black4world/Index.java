package com.black4world;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;
import com.black4world.Adapters.IndexAdapterExtra;
import com.black4world.Adapters.IndexAdapterNormal;

/**
 * Created with IntelliJ IDEA.
 * User: black4ninja
 * Date: 21/07/13
 * Time: 11:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Index extends Activity {
    GridView gridView;

    static final String[] numbers = new String[] {
            "#",
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};
    @Override
    public void onCreate(Bundle icicle){
        super.onCreate(icicle);
        setContentView(R.layout.index_list);




        //Determine screen size
        if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(this, "Large screen", Toast.LENGTH_LONG).show();

        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Toast.makeText(this, "Normal sized screen" , Toast.LENGTH_LONG).show();

            gridView = (GridView) findViewById(R.id.gridViewNormal);
            gridView.setColumnWidth(120);
            gridView.setAdapter(new IndexAdapterNormal(this));
        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            Toast.makeText(this, "Small sized screen" , Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Screen size is neither large, normal or small", Toast.LENGTH_LONG).show();
            gridView = (GridView) findViewById(R.id.gridViewNormal);
            gridView.setColumnWidth(350);
            gridView.setAdapter(new IndexAdapterExtra(this));
        }
    }
}
