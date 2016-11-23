package co.siddharth.haptikgroupchat;

import android.app.Application;
import android.support.v4.view.PagerAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by siddharth on 22/11/16.
 */

public class HaptikApplication extends Application {
    static Retrofit retrofit = null;

    @Override
    public void onCreate() {
        super.onCreate();
        getRetrofit();
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()

                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")

                    .create();
            return new Retrofit.Builder().baseUrl("http://haptik.co").
                    addConverterFactory(GsonConverterFactory.create(gson)).
                    build();
        } else {
            return retrofit;
        }
    }
}
