package com.black4world;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import com.black4world.Adapters.MangaEdenAdapter;
import com.parse.*;

import java.io.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: black4ninja
 * Date: 22/07/13
 * Time: 11:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class MangaList extends Activity {
    private String temp;
    private ListView listView;
    private Context context = this;
    private String info;
    private String response;
    private ProgressDialog pd;

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

        InternetConnection task = new InternetConnection();
        task.execute(new String[]{});




    }

    private class InternetConnection extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute(){
            pd = ProgressDialog.show(MangaList.this, getString(R.string.lblAuth) ,getString(R.string.lblSigning),true,false,null);
        }

        @Override
        protected String doInBackground(String... urls) {
            response = "";

            Parse.initialize(context, "ojmLqCOWe2dExjYGSaDGlgqGyyxwPHUMU3lMMl0n", "V1oQZV2U1cP2FwslgzCSkaP2ynNM7XSllfuAKx6p");
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
                                    String filename = temp + "File.txt";
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
                                        in = openFileInput(temp + "File.txt");
                                        InputStreamReader inputStreamReader = new InputStreamReader(in);
                                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                        StringBuilder sb = new StringBuilder();
                                        String line;
                                        while ((line = bufferedReader.readLine()) != null) {
                                            sb.append(line);
                                            System.out.println("Salida->" + line);
                                        }

                                        listView = (ListView) findViewById(R.id.listME);
                                        listView.setAdapter(new MangaEdenAdapter(sb.toString(),context));
                                        pd.dismiss();

                                    } catch (FileNotFoundException e1) {
                                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                    } catch (IOException e2){
                                        e2.printStackTrace();
                                    }

                                } else {
                                    response = "Error";
                                    pd.dismiss();
                                }
                            }
                        });
                    } else {
                        response = "Error";
                        pd.dismiss();
                    }
                }
            });

            return response;  //To change body of implemented methods use File | Settings | File Templates.

        }

        @Override
        protected void onPostExecute(String result){
            //pd.dismiss();
        }
    }
}
