package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;




public class SecondActivity extends AppCompatActivity implements MovieAdapter.itemClickListener{

    Button logout;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true); //ottimizzazione
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        movieList = new ArrayList<>();
        fetchMovies();

        logout = findViewById(R.id.btn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false"); // impostiamo il valore della checkbox a false.
                editor.apply();

                finish();
            }
        });

    }//LOGOUTLISTENER

        private void fetchMovies() { // https://run.mocky.io/v3/0509b116-32a8-400c-8119-dc966b916f54
            //https://run.mocky.io/v3/90e598c7-fd5e-43cb-8f5a-a627d29e69ba  // https://run.mocky.io/v3/95115f15-b44d-4f83-9570-b2b30d13481d
            String url = "https://run.mocky.io/v3/db624dc4-0221-4ea2-b78d-ed95deb11a08";

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                            for (int i = 0 ; i < response.length() ; i ++){
                                try {
                                     JSONObject jsonObject = response.getJSONObject(i);
                                     String title = jsonObject.getString("title");
                                     String made = jsonObject.getString("made");
                                     String poster = jsonObject.getString("poster");
                                     Double rating = jsonObject.getDouble("rating");
                                     JSONArray cinemasArray = jsonObject.getJSONArray("cinema");
                                     List<String> cinemas = new ArrayList<>();
                                     for (int j = 0; j < cinemasArray.length(); j++) {
                                           // Log.d("result", "cinema: " + cinemasArray.getString(j)); //okokokok
                                            cinemas.add(cinemasArray.getString(j));
                                     }

                                     Movie movie = new Movie(title , poster , rating, made, cinemas ); // qua
                                     movieList.add(movie);
                                } catch (JSONException e) {
                                      e.printStackTrace();
                                }
                                 MovieAdapter adapter = new MovieAdapter(SecondActivity.this , movieList, SecondActivity.this);
                                 recyclerView.setAdapter(adapter);
                                }
                            }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SecondActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onItemClick(int position) {

        ArrayList<String> cinemasList = (ArrayList<String>) movieList.get(position).getCinemas();
        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
        intent.putStringArrayListExtra("cinema", cinemasList);
        startActivity(intent);
    }

}