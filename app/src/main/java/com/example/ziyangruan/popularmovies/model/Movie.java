package com.example.ziyangruan.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable{
    private String title;
    private String releaseddate;
    private String userrating;
    private String overview;
    private String image;

    public Movie(String title, String releaseddate, String userrating, String overview, String image) {
        this.title = title;
        this.releaseddate = releaseddate;
        this.userrating = userrating;
        this.overview = overview;
        this.image = image;
    }

    private Movie(Parcel in) {
        title = in.readString();
        releaseddate = in.readString();
        userrating = in.readString();
        overview = in.readString();
        image = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseddate() {
        return releaseddate;
    }

    public void setReleaseddate(String releaseddate) {
        this.releaseddate = releaseddate;
    }

    public String getUserrating() {
        return userrating;
    }

    public void setUserrating(String userrating) {
        this.userrating = userrating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(releaseddate);
        dest.writeString(userrating);
        dest.writeString(overview);
        dest.writeString(image);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", releaseddate='" + releaseddate + '\'' +
                ", userrating='" + userrating + '\'' +
                ", overview='" + overview + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[0];
        }
    };
}
