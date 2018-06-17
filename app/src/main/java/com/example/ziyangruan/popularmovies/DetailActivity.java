package com.example.ziyangruan.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ziyangruan.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    public static final String MOVIE = "movie";

    @BindView(R.id.title_tv)
    TextView titleTextview;

    @BindView(R.id.released_date_tv)
    TextView releasedateTextview;

    @BindView(R.id.user_rating_tv)
    TextView userratingTextview;

    @BindView(R.id.overview_tv)
    TextView overviewTextview;

    @BindView(R.id.image_iv)
    ImageView posterImageview;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }

        movie = intent.getParcelableExtra(MOVIE);

        titleTextview.setText(movie.getTitle());
        releasedateTextview.setText(movie.getReleaseddate());
        userratingTextview.setText(movie.getUserrating());
        overviewTextview.setText(movie.getOverview());
        Picasso.with(this).load(movie.getImage()).into(posterImageview);

    }
}
