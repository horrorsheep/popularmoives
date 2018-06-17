package com.example.ziyangruan.popularmovies.utils;

import com.example.ziyangruan.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class JsonUtils {
    private static final String TAG = "JsonUtils";

    public byte[] getUrlBytes(String urlspec) throws IOException {
        URL url = new URL(urlspec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + urlspec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }


    public static ArrayList<Movie> parseMovieJson(String json) {
        try {
            JSONObject jsonBody = new JSONObject(json);
            JSONArray results = jsonBody.getJSONArray("results");
            ArrayList<Movie> movies = new ArrayList<>();
            for (int i = 0; i < results.length(); i++) {
                JSONObject result = results.getJSONObject(i);
                movies.add(new Movie(result.getString("title"),
                            result.getString("release_date"),
                            result.getString("vote_average"),
                            result.getString("overview"),
                            "http://image.tmdb.org/t/p/w342" + result.getString("poster_path")));
            }
            return movies;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
