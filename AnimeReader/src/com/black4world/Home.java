package com.black4world;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import com.black4world.Adapters.MangaEdenAdapter;
import com.black4world.Basic.Manga;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: black4ninja
 * Date: 7/07/13
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class Home extends Activity {

    private Vector<JSONObject> bloque =new Vector<JSONObject>();
    private Vector<Manga> mangas = new Vector<Manga>();
    private ListView listView;
    private ProgressDialog pd;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manga_eden_list);

        listView = (ListView) findViewById(R.id.listME);
        listView.setAdapter(new MangaEdenAdapter(context));

    }



    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }

}

//Ejemplo JSoup

/*
        Document doc;
        try {

            // need http protocol
            doc = Jsoup.connect("http://google.com").get();

            // get page title
            String title = doc.title();
            System.out.println("Salida->title : " + title);

            // get all links
            Elements links = doc.select("a[href]");
            for (Element link : links) {

                // get the value from href attribute
                System.out.println("Salida->\nlink : " + link.attr("href"));
                System.out.println("Salida->text : " + link.text());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        */