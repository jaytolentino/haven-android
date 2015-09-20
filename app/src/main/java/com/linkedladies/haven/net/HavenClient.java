package com.linkedladies.haven.net;

import com.linkedladies.haven.models.Checkin;
import com.linkedladies.haven.models.Results;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.POST;

public class HavenClient {

    private static final String BASE_URL = "http://haven-api.herokuapp.com/api/";
    private static HavenService havenService;

    public static HavenService getHavenService() {
        if (havenService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
            havenService = restAdapter.create(HavenService.class);
        }
        return havenService;
    }

    public static void sendCheckin(int checkins, int injured, Callback<Results> callback) {
        getHavenService().sendCheckin(new Checkin(checkins, 1, injured), callback);
    }

    public interface HavenService {
        @POST("/disasters/checkin")
        void sendCheckin(@Body Checkin checkin, Callback<Results> callback);
    }

}
