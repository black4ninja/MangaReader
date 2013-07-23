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
import com.parse.Parse;
import com.parse.ParseAnalytics;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manga_eden_list);

        Parse.initialize(this, "0RDOHsKDLzVwN0A6mSO1gms4WHU0riyEPN0INnMS", "1n6hv4yHud3EGZWsdkfSfi0KUivH4UZW11JXbSTI");
        ParseAnalytics.trackAppOpened(getIntent());
        if(isNetworkConnected()){
            pd = ProgressDialog.show(Home.this, getString(R.string.lblLoad) ,getString(R.string.lblLoad),true,false,null);
            try {
                URL url = new URL("http://www.mangaeden.com/api/list/0");
                HttpURLConnection con = (HttpURLConnection) url
                        .openConnection();
                readStream(con.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*
            //Parse
            for (int i = 0; i <bloque.size() ; i++) {
                ParseObject manga = new ParseObject("Mangas");
                manga.put("language", 1337);
                manga.put("image", "Sean Plott");
                manga.put("title", false);

            }
            */
            //MangaConnection task = new MangaConnection();
            //task.execute();


            for (int i = 0; i <2 ; i++) {
                try {
                    String titulo = bloque.get(i).get("t").toString();
                    if(titulo.length() > 20){
                        titulo = titulo.substring(0,16) + "...";
                    }
                    Manga temp = new Manga(0,titulo,"","");

                    mangas.add(temp);
                    System.out.println("Salida->");
                } catch (JSONException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            Collections.sort(mangas);
            MangaEdenAdapter customAdapter = new MangaEdenAdapter(mangas);


            pd.dismiss();
            listView = (ListView) findViewById(R.id.listME);
            listView.setAdapter(customAdapter);
        }
    }

    private void readStream(InputStream in) {
        BufferedReader reader = null;
        boolean bandera = false;

        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            String bloqueSimple="";
            while ((line = reader.readLine()) != null) {
                if(line.contains("{")){
                    bandera = true;
                    bloqueSimple = "{";
                }
                if(bandera == true && !line.contains("{") && !line.contains("}")){

                    bloqueSimple += line.replaceAll(" ","");
                }
                if(line.contains("}")){
                    bandera = false;
                    bloqueSimple+="}";

                    try {
                        JSONObject json = new JSONObject(bloqueSimple);
                        bloque.add(json);
                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
    private class MangaConnection extends AsyncTask<URL, Integer, Long> {


        @Override
        protected void onPreExecute(){
            pd = ProgressDialog.show(Home.this, getString(R.string.lblLoad) ,getString(R.string.lblLoad),true,false,null);
        }

        protected Long doInBackground(URL... urls) {
            long response = 0;

            //Llamada a internet para obtener mangas
            try {
                URL url = new URL("http://www.mangaeden.com/api/list/0");
                HttpURLConnection con = (HttpURLConnection) url
                        .openConnection();

                readStream(con.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }


            return response;
        }

        protected void onPostExecute(long response) {
            System.out.println("Esperanza");

            pd.dismiss();
        }


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