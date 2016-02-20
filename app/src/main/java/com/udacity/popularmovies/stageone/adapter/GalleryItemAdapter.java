package com.udacity.popularmovies.stageone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.stageone.R;
import com.udacity.popularmovies.stageone.network.Movie;

import java.util.List;

/**
 * Created by kunaljaggi on 2/19/16.
 */
public class GalleryItemAdapter extends ArrayAdapter<Movie> {
    private Context context;
    private List<Movie> movies;

    public GalleryItemAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
        this.context= context;
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.gallery_item, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.gallery_item_imageView);
        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w185/" + movies.get(position).getPosterPath())
                .into(imageView);
        return convertView;
    }
}