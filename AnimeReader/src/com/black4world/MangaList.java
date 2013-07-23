package com.black4world;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: black4ninja
 * Date: 22/07/13
 * Time: 11:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class MangaList extends Activity {
    @Override
    public void onCreate(Bundle icicle){
        super.onCreate(icicle);
        setContentView(R.layout.manga_eden_list);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String temp = extras.getString("LETTER");
            System.out.println("Salida->"+temp);
        }
    }
}
