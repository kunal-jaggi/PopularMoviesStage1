package com.udacity.popularmovies.stageone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.stageone.R;
import com.udacity.popularmovies.stageone.network.model.Movie;
import com.udacity.popularmovies.stageone.util.Constants;

import java.util.List;

/**
 * Created by kunaljaggi on 2/19/16.
 */
public class GalleryItemAdapter extends ArrayAdapter<Movie> {

    private Context mContext;
    private List<Movie> mMovieList;

    public GalleryItemAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
        mContext= context;
        mMovieList = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.gallery_item, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.gallery_item_imageView);
        Picasso.with(mContext)
                .load(Constants.MOVIE_DB_POSTER_URL + Constants.POSTER_PHONE_SIZE + mMovieList.get(position).getmPosterPath())
                .into(imageView);
        return convertView;
    }
}