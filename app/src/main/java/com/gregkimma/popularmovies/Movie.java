package com.gregkimma.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

//This class is used to store all the data for a specific movie from the website
public class Movie implements Parcelable{

    private String mTitle;
    private String mSynopsis;
    private String mRating;
    private String mReleaseDate;
    private String mImage;

    public Movie(){
    }

    private Movie(Parcel in) {
        mTitle = in.readString();
        mSynopsis = in.readString();
        mRating = in.readString();
        mReleaseDate = in.readString();
        mImage = in.readString();
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSynopsis() {
        return mSynopsis;
    }

    public String getRating() {
        return mRating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getImage() {
        mImage = "http://image.tmdb.org/t/p/w185" + mImage;
        return mImage;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setSynopsis(String synopsis) {
        mSynopsis = synopsis;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public void setImage(String image) {
        mImage = image;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mSynopsis);
        parcel.writeString(mRating);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mImage);
    }

    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };

//    @Override
//    public String toString() {
//        return "Movie{" +
//                "mTitle='" + mTitle + '\'' +
//                ", mSynopsis='" + mSynopsis + '\'' +
//                ", mRating='" + mRating + '\'' +
//                ", mReleaseDate='" + mReleaseDate + '\'' +
//                ", mImage='" + mImage + '\'' +
//                '}';
//    }
}
