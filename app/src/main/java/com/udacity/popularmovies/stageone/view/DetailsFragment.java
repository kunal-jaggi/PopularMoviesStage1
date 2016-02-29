package com.udacity.popularmovies.stageone.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.stageone.R;
import com.udacity.popularmovies.stageone.network.model.Movie;
import com.udacity.popularmovies.stageone.util.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This Fragment class is added by DetailsActivity to show details screen.
 * Created by kunaljaggi on 2/20/16.
 */
public class DetailsFragment extends Fragment {

    private static final String LOG_TAG = DetailsFragment.class.getSimpleName();
    private static final String MOVIE_DETAILS_SHARE_HASHTAG = " #PopularMoviesApp";

    @Bind(R.id.movieTitle) TextView mMovieTileTxt;
    @Bind(R.id.moviePoster) ImageView mMoviePoster;
    @Bind(R.id.movieReleaseYear) TextView mMovieReleaseYear;
    @Bind(R.id.movieRating) TextView mMovieRating;
    @Bind(R.id.movieOverview) TextView mMovieOverview;

    private String mMovieTitle;

    public DetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);// fragment should handle menu events.
        setRetainInstance(true);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        //Parent activity is started by firing-off an explicit intent.
        //Inspect the intent for movie data.
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(DetailsActivity.EXTRA_MOVIE)) {
            Movie selectedMovie = intent.getParcelableExtra(DetailsActivity.EXTRA_MOVIE);
            if (selectedMovie != null) {
                mMovieTitle = selectedMovie.getmTitle();
                fillDetailScreen(selectedMovie);
            }
        }

        return view;
    }

    /**
     * Used to render original title, poster image, overview (plot), user rating and release date.
     *
     * @param selectedMovie
     */
    private void fillDetailScreen(final Movie selectedMovie) {
        mMovieTileTxt.setText(selectedMovie.getmTitle());
        Picasso.with(getContext())
                .load(Constants.MOVIE_DB_POSTER_URL + Constants.POSTER_PHONE_SIZE + selectedMovie.getmPosterPath())
                .placeholder(R.drawable.poster_placeholder) // support download placeholder
                .error(R.drawable.poster_placeholder_error) //support error placeholder, if back-end returns empty string or null
                .into(mMoviePoster);
        mMovieRating.setText("" + selectedMovie.getmVoteAverage() + "/10");
        mMovieOverview.setText(selectedMovie.getmOverview());

        // Movie DB API returns release date in yyyy--mm-dd format
        // Extract the year through regex
        Pattern datePattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        String year = selectedMovie.getmReleaseDate();
        Matcher dateMatcher = datePattern.matcher(year);
        if (dateMatcher.find()) {
            year = dateMatcher.group(1);

        }
        mMovieReleaseYear.setText(year);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.detailfragment, menu);

        // Retrieve the share menu item
        MenuItem menuItem = menu.findItem(R.id.action_share);

        // Get the provider and hold onto it to set/change the share intent.
        ShareActionProvider mShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        // Attach an intent to this ShareActionProvider.  You can update this at any time,
        // like when the user selects a new piece of data they might like to share.
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareMovieIntent());
        } else {
            Log.d(LOG_TAG, "Share Action Provider is null?");
        }
    }

    /**
     * Returns an implicit intent to launch another app. Movie title is added as intent extra.
     *
     * @return intent
     */
    private Intent createShareMovieIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND); //generic action
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET); //required to return to Popular Movies app
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                mMovieTitle + MOVIE_DETAILS_SHARE_HASHTAG);
        return shareIntent;
    }
}
