package com.black4world.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.black4world.Basic.Manga;
import com.black4world.R;
import org.json.JSONObject;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: black4ninja
 * Date: 8/07/13
 * Time: 04:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class MangaEdenAdapter extends BaseAdapter {

    private static final String TAG = MangaEdenAdapter.class.getSimpleName();
    private  Vector<Manga> listArray = new Vector<Manga>();
    private  Vector<JSONObject> mangasData;

    public MangaEdenAdapter(Vector<Manga> list) {
        /*for (int i = 0; i <list.size() ; i++) {
            Manga temp = (Manga) list.get(i);
            if(temp == null){
                temp = new Manga(0,"","","");
            }
            listArray.add(temp);
        } */
        listArray = list;
    }

    @Override
    public int getCount() {
        return listArray.size();    // total number of elements in the list
    }

    @Override
    public Object getItem(int i) {
        return listArray.get(i);    // single item in the list
    }

    @Override
    public long getItemId(int i) {
        return i;                   // index number
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.manga_eden_row, parent, false);
        }





        final Manga dataModel = listArray.get(index);

        TextView titulo = (TextView) view.findViewById(R.id.title_me);
        ImageView cover = (ImageView) view.findViewById(R.id.cover_me);

        titulo.setText(dataModel.getTitle());
        //cover.setImageResource(dataModel.getImage());
        cover.setImageResource(R.drawable.lala);

        return view;
    }

}
