package com.cosmic.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import com.cosmic.retrofitsample.interfaces.GerritAPI;
import com.cosmic.retrofitsample.model.Change;
import com.cosmic.retrofitsample.rvhelper.RecyclerAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements Callback<List<Change>> {

    private static final String TAG = "MainActivity";

    static final String API_ENDPOINT = "https://git.eclipse.org/r/";

    private Context context;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        startRecyclerView();
        initRetrofitClient();
    }

    private void startRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initRetrofitClient(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_ENDPOINT).addConverterFactory(GsonConverterFactory.create(gson)).build();
        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);
        Call<List<Change>> call = gerritAPI.loadChanges("status:open");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Change>> call, Response<List<Change>> response) {
        ArrayList<Change> changes = (ArrayList<Change>) response.body();
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(changes, context);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onFailure(Call<List<Change>> call, Throwable t) {

    }
}