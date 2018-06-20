package com.example.ziyangruan.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ziyangruan.popularmovies.model.Movie;
import com.example.ziyangruan.popularmovies.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ItemClickListener{
    private MovieAdapter mAdapter;
    @BindView(R.id.rvNumbers)
    RecyclerView mMovieList;
    private static final String API_KEY = "0e742a14316d5900fb0a3682058e27db"; //You should insert an API key here.
    private static final String TAG = "MovieFetchr";
    private ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mMovieList.setLayoutManager(layoutManager);
        if (savedInstanceState != null && savedInstanceState.containsKey("movies")) {
            movies = savedInstanceState.getParcelableArrayList("movies");
            setupAdapter();
        } else {
            new FetchPopularTask().execute();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.top_rate:
                sortBytoprated();
                return true;
            case R.id.most_popular:
                sortBymostpopular();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sortBytoprated() {
        new FetchTopRatedTask().execute();
    }

    private void sortBymostpopular() {
        new FetchPopularTask().execute();
    }

    @Override
    public void onItemClick(int clickedItemIndex) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.MOVIE, movies.get(clickedItemIndex));
        startActivity(intent);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies", movies);
        super.onSaveInstanceState(outState);
    }

    private void setupAdapter() {
        mAdapter = new MovieAdapter(this, movies.size(), this, movies);

        mMovieList.setAdapter(mAdapter);
    }

    private class FetchPopularTask extends AsyncTask<Void, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            try {
                String url = Uri.parse("https://api.themoviedb.org/3/movie/popular")
                                .buildUpon()
                                .appendQueryParameter("api_key", API_KEY)
                                .build().toString();
                String jsonString = new JsonUtils().getUrlString(url);
                Log.i(TAG, "Received JSON: " + jsonString);
                return JsonUtils.parseMovieJson(jsonString);
            } catch (IOException ioe) {
                Log.e(TAG, "Failed to fetch items", ioe);
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> items) {
            movies = items;
            setupAdapter();
        }
    }

    private class FetchTopRatedTask extends AsyncTask<Void, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            try {
                String url = Uri.parse("https://api.themoviedb.org/3/movie/top_rated")
                        .buildUpon()
                        .appendQueryParameter("api_key", API_KEY)
                        .build().toString();
                String jsonString = new JsonUtils().getUrlString(url);
                Log.i(TAG, "Received JSON: " + jsonString);
                return JsonUtils.parseMovieJson(jsonString);
            } catch (IOException ioe) {
                Log.e(TAG, "Failed to fetch items", ioe);
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> items) {
            movies = items;
            setupAdapter();
        }
    }
}
