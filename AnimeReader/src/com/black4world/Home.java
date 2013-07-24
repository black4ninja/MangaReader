package com.black4world;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ListView;
import com.black4world.Adapters.MangaEdenAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: black4ninja
 * Date: 7/07/13
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class Home extends Activity {

    private ListView listView;
    private Context context = this;
    private String info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manga_eden_list);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            info = extras.getString("INFO");
        }

        listView = (ListView) findViewById(R.id.listME);
        listView.setAdapter(new MangaEdenAdapter(info,context));

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
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