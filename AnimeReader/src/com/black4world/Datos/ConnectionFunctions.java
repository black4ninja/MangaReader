package com.black4world.Datos;

import com.black4world.Datos.JSONParser;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Brounie
 * Date: 11/06/13
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionFunctions {

    private JSONParser jsonParser;
    private List<NameValuePair> params;
    private JSONObject jsonRegreso;
    //--------------------------------------------------------------------------------------
    //URLS
    //--------------------------------------------------------------------------------------

    //private static String urlRastrea = "http://brounie.com/clientes/3guerras.php";
    //private static String urlPrecios = "http://brounie.com/clientes/3guerras_precios.php";


    // constructor
    public ConnectionFunctions(){
        jsonParser = new JSONParser();
    }

    public JSONObject connectGET(String url, String[] args, String[] vals){
        if(!url.equals("") || args.length == vals.length){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if(args != null || vals != null ){
            for (int i = 0; i <args.length ; i++) {
                params.add(new BasicNameValuePair(args[i], vals[i]));
            }
        }
        JSONObject json = jsonParser.makeHttpRequest(url, "GET", params);
        return json;
        }else{
            JSONObject json = null;
            return json;
        }
    }

    public JSONObject connectPOST(String url, String[] args, String[] vals){
        if(!url.equals("") || args.length == vals.length){
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            if(args != null || vals != null ){
                for (int i = 0; i <args.length ; i++) {
                    params.add(new BasicNameValuePair(args[i], vals[i]));
                }
            }
            JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);
            return json;
        }else{
            JSONObject json = null;
            return json;
        }
    }

    //--------------------------------------------------------------------------------------
    //Metodos para la api
    //--------------------------------------------------------------------------------------


    /*
    public JSONObject rastrea(String talon){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("talon", talon));
        JSONObject json = jsonParser.makeHttpRequest(urlRastrea, "GET", params);
        return json;
    }

    public JSONObject precios(String origen, String destino, String peso, String mt3, String valorDeclarado, String conRecoleccion, String conEntregaAD, String retenerIVA){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("origen", origen));
        params.add(new BasicNameValuePair("destino", destino));
        params.add(new BasicNameValuePair("peso", peso));
        params.add(new BasicNameValuePair("mt3", mt3));
        params.add(new BasicNameValuePair("valorDeclarado", valorDeclarado));
        params.add(new BasicNameValuePair("conRecoleccion", conRecoleccion));
        params.add(new BasicNameValuePair("conEntregaAD", conEntregaAD));
        params.add(new BasicNameValuePair("retenerIVA", retenerIVA));

        JSONObject json = jsonParser.makeHttpRequest(urlPrecios, "GET", params);
        return json;
    }
    */
}
