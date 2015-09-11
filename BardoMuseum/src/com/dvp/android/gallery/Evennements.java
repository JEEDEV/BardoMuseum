package com.dvp.android.gallery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Evennements extends Activity implements Runnable {
    ListView list;
    ProgressDialog pd;
    List<Events>results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        list = (ListView) findViewById(R.id.list);
        execute_search();
    }

    public void execute_search() {
        pd = ProgressDialog.show(Evennements.this, "", "Chargemnet...", true,false);

        Thread thread = new Thread(Evennements.this);
        thread.start();

    }

    public void run() {
        get_liste_item_service();

        handler.sendEmptyMessage(0);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
            get_list_item();

        }
    };
    private ArrayList<HashMap<String, String>> values_array ;

    private InputStream retrieveStream(String url) {

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet getRequest = new HttpGet(url);

        try {

            HttpResponse getResponse = client.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url);
                return null;
            }

            HttpEntity getResponseEntity = getResponse.getEntity();
            return getResponseEntity.getContent();

        }
        catch (IOException e) {
            getRequest.abort();
            Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }

        return null;

    }

    public void get_liste_item_service(){

        String url = "http://10.0.2.2/www/Bardo/events.php";

        InputStream source = retrieveStream(url);

        Gson gson = new Gson();

        Reader reader = new InputStreamReader(source);
        ListEvents response = null;
        try{
            response= gson.fromJson(reader, ListEvents.class);

        }
        catch (Exception e) {
            // TODO: handle exception
        }



        if(response !=null)
            results = response.Events;


    }
    public void get_list_item(){

        if(results!=null && results.size() > 0)
        {
            EventAdapter adapter = new EventAdapter(this, R.layout.affichageitem, results);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = null;
                    intent = new Intent(getApplicationContext(), Detail.class);
                    intent.putExtra("Nom", results.get(position).Nom);
                    intent.putExtra("LienImage", results.get(position).LienImage);
                    intent.putExtra("Detail", results.get(position).Detail);
                    startActivity(intent);

                }
            });
        }  }

}

