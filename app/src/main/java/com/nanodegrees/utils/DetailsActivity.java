package com.nanodegrees.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.nanodegrees.R;
import com.nanodegrees.utils.models.MovieDetailsRequest;
import com.nanodegrees.utils.utils.ClientServices;
import com.nanodegrees.utils.utils.Constants;
import com.nanodegrees.utils.utils.IServices;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    private static final String KEY = "KEY";
    private ImageView iv_movie;
    private TextView tv_overview_detail;
    private TextView tv_ranking;
    private TextView tv_date;
    private LinearLayout ll_loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ll_loader = findViewById(R.id.ll_loader);
        ll_loader.setVisibility(View.VISIBLE);
        iv_movie = findViewById(R.id.iv_movie);
        tv_overview_detail = findViewById(R.id.tv_overview_detail);
        tv_ranking = findViewById(R.id.tv_ranking);
        tv_date = findViewById(R.id.tv_date);
        Integer id = getIntent().getIntExtra(KEY, 0);
        IServices iServices = ClientServices.getServices();

        iServices.getDetails(id, Constants.API_KEY_MOVIE, Constants.LENGUAJE, 1).enqueue(new Callback<MovieDetailsRequest>() {
            @Override
            public void onResponse(Call<MovieDetailsRequest> call, Response<MovieDetailsRequest> response) {
                initDetailMovie(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetailsRequest> call, Throwable t) {
                t.getCause();
            }
        });

    }

    private void initDetailMovie(MovieDetailsRequest movie) {
        Glide.with(DetailsActivity.this).load(Constants.URI_BASE_IMAGE + movie.getBackdrop_path()).into(iv_movie);
        Objects.requireNonNull(getSupportActionBar()).setTitle(movie.getOriginal_title());
        tv_overview_detail.setText(movie.getOverview());
        tv_date.setText(movie.getRelease_date());
        tv_ranking.setText(movie.getVote_average().toString());
        ll_loader.setVisibility(View.GONE);
    }

    public static Intent newIntent(Context ctx, Integer id) {
        Intent intent = new Intent(ctx, DetailsActivity.class);
        intent.putExtra(KEY, id);
        return intent;
    }
}
