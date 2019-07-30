package com.nanodegrees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nanodegrees.adapter.HeaderMovieAdapter;
import com.nanodegrees.models.MovieRequest;
import com.nanodegrees.utils.ClientServices;
import com.nanodegrees.utils.Constants;
import com.nanodegrees.utils.IServices;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HeaderMovieAdapter adapter;
    private RecyclerView rvMovies;
    private ProgressBar progressBar;
    private IServices iServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies = findViewById(R.id.rv_movies);
        progressBar = findViewById(R.id.pb_loading);
        FloatingActionButton fab_popular = findViewById(R.id.fab_popular);
        FloatingActionButton fab_top_rated = findViewById(R.id.fab_top_rated);
        FloatingActionButton fab_up_coming = findViewById(R.id.fab_up_coming);

        fab_popular.setOnClickListener(this);
        fab_top_rated.setOnClickListener(this);
        fab_up_coming.setOnClickListener(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        adapter = new HeaderMovieAdapter(this, null);
        rvMovies.setLayoutManager(layoutManager);
        rvMovies.setHasFixedSize(false);
        rvMovies.setAdapter(adapter);

        iServices = ClientServices.getServices();

        getMoviePopular(iServices);

        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.most_popular));
    }

    private void getMoviePopular(IServices iServices) {
        iServices.getPopular(Constants.API_KEY_MOVIE, Constants.LANGUAGE, 1).enqueue(new Callback<MovieRequest>() {
            @Override
            public void onResponse(Call<MovieRequest> call, Response<MovieRequest> response) {
                adapter.setListMovie(response.body() != null ? response.body().getResults() : null);
                showList();
            }

            @Override
            public void onFailure(Call<MovieRequest> call, Throwable t) {
                t.getCause();
            }
        });
    }

    private void getMovieTopRated(IServices iServices) {
        iServices.getTopRated(Constants.API_KEY_MOVIE, Constants.LANGUAGE, 1).enqueue(new Callback<MovieRequest>() {
            @Override
            public void onResponse(Call<MovieRequest> call, Response<MovieRequest> response) {
                adapter.setListMovie(response.body() != null ? response.body().getResults() : null);
                showList();
            }

            @Override
            public void onFailure(Call<MovieRequest> call, Throwable t) {
                t.getCause();
            }
        });
    }

    private void getMovieUpComing(IServices iServices) {
        iServices.getUpComing(Constants.API_KEY_MOVIE, Constants.LANGUAGE, 1).enqueue(new Callback<MovieRequest>() {
            @Override
            public void onResponse(Call<MovieRequest> call, Response<MovieRequest> response) {
                adapter.setListMovie(response.body() != null ? response.body().getResults() : null);
                showList();
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
                showLoader();
                getMoviePopular(iServices);
                Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.most_popular));
                break;
            case R.id.fab_top_rated:
                showLoader();
                getMovieTopRated(iServices);
                Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.top_rated));
                break;
            case R.id.fab_up_coming:
                showLoader();
                getMovieUpComing(iServices);
                Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.upcoming));
                break;
        }
    }

    private void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
        rvMovies.setVisibility(View.GONE);
    }

    private void showList() {
        progressBar.setVisibility(View.GONE);
        rvMovies.setVisibility(View.VISIBLE);
    }
}
