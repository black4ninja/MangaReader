package com.black4world;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.parse.*;

import java.io.*;
import java.util.List;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: black4ninja
 * Date: 22/07/13
 * Time: 11:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class MangaList extends Activity {
    private String temp;
    private Context context = this;
    static final String[] urls = new String[] {
            "http://cdn.mangaeden.com/mangasimg/a2/a2e5af8073b16751553de2869d66316638e33400f774354213c014ac.jpg",
            "http://cdn.mangaeden.com/mangasimg/92/921a1df16c23e0e13fa05471a096fadd1848284a245d3a03c06985e6.jpg",
            "http://cdn.mangaeden.com/mangasimg/c1/c1a2e639e5d3316e4aa464a092119a1967c86de4f437347c781deeba.jpg",
            "http://cdn.mangaeden.com/mangasimg/79/79a56c7fe0bb0dc9cbe9ccc71083102e9090a251acd8a2ba68febe9f.jpg",
            "http://cdn.mangaeden.com/mangasimg/bd/bda9f6af19ea9e7f19f5f10aca0b9efba42fb42098966f6bd70090c9.jpg",
            "http://cdn.mangaeden.com/mangasimg/81/814212af8ba1cbdfebccdd32495d127a766550e6bf9bf5a6a97f269f.png",
            "http://cdn.mangaeden.com/mangasimg/ad/ad7db40ebd8444355421da1f472b05ae71008078054d49a59d90548c.png"};


    @Override
    public void onCreate(Bundle icicle){
        super.onCreate(icicle);
        setContentView(R.layout.manga_eden_list);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            temp = extras.getString("LETTER");
            //32
            if(temp.charAt(0) != '#'){
                if((int)temp.charAt(0) >= 65 ||(int)temp.charAt(0) <= 90 ){
                    temp = "" + (char) ((int) temp.charAt(0) + 32);
                }
            }
            System.out.println("Salida->" + temp);
        }

        Vector<String> v = new Vector<String>();


        Parse.initialize(this, "ojmLqCOWe2dExjYGSaDGlgqGyyxwPHUMU3lMMl0n", "V1oQZV2U1cP2FwslgzCSkaP2ynNM7XSllfuAKx6p");
        ParseAnalytics.trackAppOpened(getIntent());

        ParseQuery<ParseObject> query = ParseQuery.getQuery("MangasInfo");
        query.whereEqualTo("fileIndex", temp + "-Manga");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    System.out.println("Salida->Recuperado el objeto");
                    ParseFile applicantResume = (ParseFile)scoreList.get(0).get("mangaFile");
                    applicantResume.getDataInBackground(new GetDataCallback() {
                        public void done(byte[] data, ParseException e) {

                            if (e == null) {
                                System.out.println("Salida->Recuperados los datos");
                                String filename = temp + "-Manga.txt";
                                FileOutputStream outputStream;

                                try {
                                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                                    outputStream.write(data);
                                    outputStream.close();
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }

                                System.out.println("Salida->Guardado el archivo");
                                FileInputStream in = null;
                                try {
                                    in = openFileInput(temp + "-Manga.txt");
                                    InputStreamReader inputStreamReader = new InputStreamReader(in);
                                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                    StringBuilder sb = new StringBuilder();
                                    String line;
                                    while ((line = bufferedReader.readLine()) != null) {
                                        sb.append(line);
                                        System.out.println("Salida->" + line);
                                    }

                                    Intent myIntent = new Intent(context, Home.class);
                                    context.startActivity(myIntent);

                                } catch (FileNotFoundException e1) {
                                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                } catch (IOException e2){
                                    e2.printStackTrace();
                                }

                            } else {
                                // something went wrong
                            }
                        }
                    });
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });



    }
}
