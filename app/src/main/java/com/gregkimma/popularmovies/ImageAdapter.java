package com.gregkimma.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

//This class is the adapter used to input the views into the grid on the main screen
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<Movie> mMovieList = new ArrayList<>();

    public ImageAdapter(Context c, List<Movie> movies) {
        mContext = c;
        mMovieList = movies;
    }

    public int getCount() {
        return mMovieList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            imageView = (ImageView) convertView;
        }

        Movie movie = mMovieList.get(position);
        String url = movie.getImage();

        Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .fit()
                .into(imageView);

        return imageView;
    }
}