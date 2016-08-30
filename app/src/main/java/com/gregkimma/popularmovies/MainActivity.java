package com.gregkimma.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private static final String LOG = "MainActivity";
    private List<Movie> mMovieList = new ArrayList<>();
    int restart = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String sort = prefs.getString(getString(R.string.pref_sort),
                getString(R.string.pref_sort_popular_value));

        GetMovieJSONData jsonData = new GetMovieJSONData(sort, getString(R.string.api_key));
        jsonData.execute();

        mMovieList = jsonData.getMovies();

        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this, mMovieList));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Details.class);
                intent.putExtra("MOVIE_TRANSFER", mMovieList.get(position));
                startActivity(intent);
            }
        });
    }

    //This will refresh the main screen when a new sort order is selected
    @Override
    protected void onStart() {
        super.onStart();
        if (restart == 1) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            restart = 0;
        }
        restart++;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.pref_general.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

