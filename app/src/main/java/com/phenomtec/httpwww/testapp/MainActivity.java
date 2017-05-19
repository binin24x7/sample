package com.phenomtec.httpwww.testapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.phenomtec.httpwww.testapp.adapter.ListAdapter;
import com.phenomtec.httpwww.testapp.model.ItemResponse;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class MainActivity extends AppCompatActivity {


    OkHttpClient mOkHttpClient;
    WebInterface webInterface;
    ListAdapter listAdapter;

    RecyclerView recyclerView;
    Callback<ItemResponse> registrationCallback = new Callback<ItemResponse>() {
        @Override
        public void success(ItemResponse itemResponse, Response response) {
            try {
                Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_LONG).show();
                listAdapter.loadItems(itemResponse.data);
                recyclerView.setAdapter(listAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failure(RetrofitError error) {
            Toast.makeText(MainActivity.this, "Network Error Occured... Try Again", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        listAdapter=new ListAdapter();
        recyclerView= (RecyclerView) findViewById(R.id.list_view);

        try {
            initializeRestAdapter();
            webInterface.getData(registrationCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initializeRestAdapter() {
        try {
            mOkHttpClient = new OkHttpClient();
            mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
            mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setConverter(new GsonConverter(new Gson()))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://zaskaapp.phenomtec.com/api/")
                    .setClient(new OkClient(mOkHttpClient))
                    .build();

            webInterface = restAdapter.create(WebInterface.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
