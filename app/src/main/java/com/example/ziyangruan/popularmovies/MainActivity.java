package com.example.ziyangruan.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ItemClickListener{
    private MovieAdapter mAdapter;
    private RecyclerView mMovieList;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMovieList = (RecyclerView) findViewById(R.id.rvNumbers);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mMovieList.setLayoutManager(layoutManager);

        mAdapter = new MovieAdapter(this, 12, this);
        mMovieList.setAdapter(mAdapter);
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
        System.out.println("hi top rated");
    }

    private void sortBymostpopular() {
        System.out.println("hi most popular");
    }

    @Override
    public void onItemClick(int clickedItemIndex) {

        if (mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();

    }
}
