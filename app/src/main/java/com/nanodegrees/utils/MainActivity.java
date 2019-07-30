package com.nanodegrees.utils;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nanodegrees.R;
import com.nanodegrees.utils.adapter.HeaderMovieAdapter;
import com.nanodegrees.utils.models.MovieRequest;
import com.nanodegrees.utils.utils.ClientServices;
import com.nanodegrees.utils.utils.Constants;
import com.nanodegrees.utils.utils.IServices;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HeaderMovieAdapter adapter;
    private IServices iServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv_movies = findViewById(R.id.rv_movies);
        FloatingActionButton fab_popular = findViewById(R.id.fab_popular);
        FloatingActionButton fab_top_rated = findViewById(R.id.fab_top_rated);
        FloatingActionButton fab_up_coming = findViewById(R.id.fab_up_coming);

        fab_popular.setOnClickListener(this);
        fab_top_rated.setOnClickListener(this);
        fab_up_coming.setOnClickListener(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        adapter = new HeaderMovieAdapter(this, null);
        rv_movies.setLayoutManager(layoutManager);
        rv_movies.setAdapter(adapter);

        iServices = ClientServices.getServices();

        getMoviePopular(iServices);

        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.most_popular));
    }

    private void getMoviePopular(IServices iServices) {
        iServices.getPopular(Constants.API_KEY_MOVIE, Constants.LENGUAJE, 1).enqueue(new Callback<MovieRequest>() {
            @Override
            public void onResponse(Call<MovieRequest> call, Response<MovieRequest> response) {
                adapter.setListMovie(response.body() != null ? response.body().getResults() : null);
            }

            @Override
            public void onFailure(Call<MovieRequest> call, Throwable t) {
                t.getCause();
            }
        });
    }

    private void getMovieTopRated(IServices iServices) {
        iServices.getTopRated(Constants.API_KEY_MOVIE, Constants.LENGUAJE, 1).enqueue(new Callback<MovieRequest>() {
            @Override
            public void onResponse(Call<MovieRequest> call, Response<MovieRequest> response) {
                adapter.setListMovie(response.body() != null ? response.body().getResults() : null);
            }

            @Override
            public void onFailure(Call<MovieRequest> call, Throwable t) {
                t.getCause();
            }
        });
    }

    private void getMovieUpComing(IServices iServices) {
        iServices.getUpComing(Constants.API_KEY_MOVIE, Constants.LENGUAJE, 1).enqueue(new Callback<MovieRequest>() {
            @Override
            public void onResponse(Call<MovieRequest> call, Response<MovieRequest> response) {
                adapter.setListMovie(response.body() != null ? response.body().getResults() : null);
            }

            @Override
            public void onFailure(Call<MovieRequest> call, Throwable t) {
                t.getCause();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_popular:
                getMoviePopular(iServices);
                Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.most_popular));
                break;
            case R.id.fab_top_rated:
                getMovieTopRated(iServices);
                Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.top_rated));
                break;
            case R.id.fab_up_coming:
                getMovieUpComing(iServices);
                Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.upcoming));
                break;
        }
    }
}
